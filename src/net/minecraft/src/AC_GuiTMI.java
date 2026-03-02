package net.minecraft.src;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class AC_GuiTMI extends GuiContainer {
	private static RenderItem tmiItemRenderer = new RenderItem();

	// TMI grid config
	private static final int COLS = 9;
	private static final int SLOT_SIZE = 18;

	// Computed rows based on screen height
	private int rows;

	// All valid item stacks
	private ItemStack[] allItems;
	private int currentPage = 0;
	private int totalPages;
	private int itemsPerPage;

	// Grid position
	private int gridX;
	private int gridY;

	// Player model look direction
	private float xSize_lo;
	private float ySize_lo;

	// Tooltip
	private String tooltipText = null;
	private int tooltipX, tooltipY;

	// Inventory save slots (7 slots)
	private static final int NUM_SAVE_SLOTS = 7;
	private static final String[] SLOT_NAMES = {
		"1", "2", "3", "4", "5", "6", "7"
	};
	// Static so they persist across GUI opens during the session
	private static ItemStack[][] savedMainInv = new ItemStack[NUM_SAVE_SLOTS][];
	private static ItemStack[][] savedArmorInv = new ItemStack[NUM_SAVE_SLOTS][];

	// Button layout
	private int btnX;
	private int btnStartY;
	private int[] btnWidths = new int[NUM_SAVE_SLOTS];

	// Header buttons (horizontal toolbar)
	private static final String[] HEADER_LABELS = {
		"Clear", "Sunset", "Midday", "Nightset", "Midnight"
	};
	private static final long[] TIME_VALUES = {
		-1, 12000L, 6000L, 18000L, 18000L
	};
	private int headerBarY;
	private int[] headerBtnX = new int[HEADER_LABELS.length];
	private int[] headerBtnW = new int[HEADER_LABELS.length];

	public AC_GuiTMI(EntityPlayer var1) {
		super(var1.inventorySlots);
		this.field_948_f = true;
		var1.addStat(AchievementList.openInventory, 1);
		buildItemList();
	}

	private void buildItemList() {
		int count = 0;
		for (int i = 0; i < Item.itemsList.length; i++) {
			if (Item.itemsList[i] != null) {
				count++;
			}
		}

		allItems = new ItemStack[count];
		int idx = 0;
		for (int i = 0; i < Item.itemsList.length; i++) {
			if (Item.itemsList[i] != null) {
				allItems[idx++] = new ItemStack(i, 1, 0);
			}
		}
	}

	// Actual columns (may be reduced to avoid overlap)
	private int cols;

	public void initGui() {
		super.initGui();
		this.controlList.clear();

		// Header bar is 14px tall at top
		headerBarY = 0;
		int headerH = 14;

		// Calculate the right edge of the inventory GUI
		int invRight = (this.width + this.xSize) / 2 + 4;

		// Available width for the item grid on the right side
		int availableWidth = this.width - invRight - 2;
		cols = availableWidth / SLOT_SIZE;
		if (cols > COLS) cols = COLS;
		if (cols < 1) cols = 1;

		// Grid starts right below header bar, anchored to right edge
		gridX = this.width - cols * SLOT_SIZE - 2;
		gridY = headerH + 1;

		// Calculate rows to fill remaining screen height
		rows = (this.height - gridY - 10) / SLOT_SIZE;
		if (rows < 1) rows = 1;

		itemsPerPage = cols * rows;
		totalPages = (allItems.length + itemsPerPage - 1) / itemsPerPage;
		if (currentPage >= totalPages) currentPage = totalPages - 1;
		if (currentPage < 0) currentPage = 0;

		// Save/Load buttons on left side, below header
		btnX = 4;
		btnStartY = headerH + 2;
	}

	public void drawScreen(int var1, int var2, float var3) {
		super.drawScreen(var1, var2, var3);
		this.xSize_lo = (float)var1;
		this.ySize_lo = (float)var2;

		drawTMIPanel(var1, var2, var3);
	}

	private String getSlotLabel(int slot) {
		boolean hasSave = savedMainInv[slot] != null;
		if (hasSave) {
			return "Load " + SLOT_NAMES[slot];
		} else {
			return "Save " + SLOT_NAMES[slot];
		}
	}

	private void drawTMIPanel(int mouseX, int mouseY, float partialTick) {
		GL11.glDisable(GL11.GL_LIGHTING);

		int bgW = cols * SLOT_SIZE + 4;

		// === Solid black header bar across full width ===
		drawRect(0, 0, this.width, 14, 0xFF000000);

		// Header buttons on the left
		int hx = 2;
		for (int i = 0; i < HEADER_LABELS.length; i++) {
			String label = HEADER_LABELS[i];

			// Time buttons get icon + text, Clear is text only
			int iconSize = 12;
			int labelW = this.fontRenderer.getStringWidth(label);
			boolean hasIcon = (i >= 1); // Sunset, Midday, Nightset, Midnight
			int btnW = hasIcon ? (iconSize + 2 + labelW + 4) : (labelW + 6);
			headerBtnX[i] = hx;
			headerBtnW[i] = btnW;

			if (mouseX >= hx && mouseX < hx + btnW && mouseY >= 1 && mouseY < 13) {
				drawRect(hx, 1, hx + btnW, 13, 0x40FFFFFF);
			}

			if (hasIcon) {
				// Pick sun or moon texture
				boolean isSun = (i == 1 || i == 2); // Sunset, Midday = sun
				String texPath = isSun ? "/terrain/sun.png" : "/terrain/moon.png";
				int texId = this.mc.renderEngine.getTexture(texPath);

				GL11.glEnable(GL11.GL_BLEND);
				GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
				GL11.glBindTexture(GL11.GL_TEXTURE_2D, texId);

				// Draw scaled icon using Tessellator
				Tessellator var9 = Tessellator.instance;
				var9.startDrawingQuads();
				var9.addVertexWithUV(hx + 2, 1 + iconSize, 0.0D, 0.0D, 1.0D);
				var9.addVertexWithUV(hx + 2 + iconSize, 1 + iconSize, 0.0D, 1.0D, 1.0D);
				var9.addVertexWithUV(hx + 2 + iconSize, 1, 0.0D, 1.0D, 0.0D);
				var9.addVertexWithUV(hx + 2, 1, 0.0D, 0.0D, 0.0D);
				var9.draw();

				GL11.glDisable(GL11.GL_BLEND);

				this.fontRenderer.drawStringWithShadow(label, hx + iconSize + 4, 3, 0xFFFFFF);
			} else {
				this.fontRenderer.drawStringWithShadow(label, hx + 3, 3, 0xFFFFFF);
			}

			hx += btnW + 2;
		}

		// Page counter on the right side of the header bar
		String pageStr = (currentPage + 1) + "/" + totalPages;
		int pageStrW = this.fontRenderer.getStringWidth(pageStr);
		int pageX = this.width - pageStrW - 16;
		this.fontRenderer.drawStringWithShadow(pageStr, pageX, 3, 0xFFFFFF);

		// Left arrow
		int arrowLeftX = pageX - 12;
		this.fontRenderer.drawStringWithShadow("<", arrowLeftX, 3, currentPage > 0 ? 0xFFFFFF : 0x606060);

		// Right arrow
		int arrowRightX = this.width - 10;
		this.fontRenderer.drawStringWithShadow(">", arrowRightX, 3, currentPage < totalPages - 1 ? 0xFFFFFF : 0x606060);

		// Save/Load buttons below header
		for (int i = 0; i < NUM_SAVE_SLOTS; i++) {
			int by = btnStartY + i * 14;
			String label = getSlotLabel(i);
			int labelW = this.fontRenderer.getStringWidth(label);
			btnWidths[i] = labelW + 6;

			int bx = btnX;

			// Button background
			drawRect(bx, by, bx + labelW + 6, by + 12, 0xA0000000);
			drawRect(bx, by, bx + labelW + 6, by + 1, 0xFF808080);
			drawRect(bx, by + 11, bx + labelW + 6, by + 12, 0xFF808080);
			drawRect(bx, by, bx + 1, by + 12, 0xFF808080);
			drawRect(bx + labelW + 5, by, bx + labelW + 6, by + 12, 0xFF808080);

			// Hover highlight for main button
			if (mouseX >= bx && mouseX < bx + labelW + 6 && mouseY >= by && mouseY < by + 12) {
				drawRect(bx + 1, by + 1, bx + labelW + 5, by + 11, 0x40FFFFFF);
			}

			this.fontRenderer.drawStringWithShadow(label, bx + 3, by + 2, 0xFFFFFF);

			// X button (delete/clear slot)
			int xx = bx + labelW + 10;
			drawRect(xx, by, xx + 12, by + 12, 0xA0000000);
			drawRect(xx, by, xx + 12, by + 1, 0xFF808080);
			drawRect(xx, by + 11, xx + 12, by + 12, 0xFF808080);
			drawRect(xx, by, xx + 1, by + 12, 0xFF808080);
			drawRect(xx + 11, by, xx + 12, by + 12, 0xFF808080);

			if (mouseX >= xx && mouseX < xx + 12 && mouseY >= by && mouseY < by + 12) {
				drawRect(xx + 1, by + 1, xx + 11, by + 11, 0x40FFFFFF);
			}

			this.fontRenderer.drawStringWithShadow("x", xx + 3, by + 2, 0xFFFFFF);
		}

		// Bottom status text
		String status = "TooManyItems 1.0.0 2011-11-19  ModLoader OFF";
		this.fontRenderer.drawStringWithShadow(status, 2, this.height - 10, 0x808080);

		// Draw item grid
		int scrollOffset = currentPage * itemsPerPage;
		tooltipText = null;

		GL11.glPushMatrix();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glEnable('\u803a');
		GL11.glRotatef(120.0F, 1.0F, 0.0F, 0.0F);
		RenderHelper.enableStandardItemLighting();
		GL11.glPopMatrix();

		GL11.glPushMatrix();
		GL11.glTranslatef(0.0F, 0.0F, 100.0F);

		for (int i = 0; i < itemsPerPage; i++) {
			int itemIndex = scrollOffset + i;
			if (itemIndex >= allItems.length) break;

			int col = i % cols;
			int row = i / cols;
			int slotX = gridX + col * SLOT_SIZE;
			int slotY = gridY + row * SLOT_SIZE;

			ItemStack stack = allItems[itemIndex];

			// Draw item
			tmiItemRenderer.renderItemIntoGUI(this.fontRenderer, this.mc.renderEngine, stack, slotX, slotY);

			// Hover check
			if (mouseX >= slotX && mouseX < slotX + SLOT_SIZE && mouseY >= slotY && mouseY < slotY + SLOT_SIZE) {
				GL11.glDisable(GL11.GL_LIGHTING);
				GL11.glDisable(GL11.GL_DEPTH_TEST);
				drawRect(slotX, slotY, slotX + 16, slotY + 16, 0x80FFFFFF);
				GL11.glEnable(GL11.GL_LIGHTING);
				GL11.glEnable(GL11.GL_DEPTH_TEST);

				String name = ("" + StringTranslate.getInstance().translateNamedKey(stack.getItemName())).trim();
				if (name.length() == 0) {
					// No translation found - use raw item name (strip "item." / "tile." prefix)
					String rawName = stack.getItemName();
					if (rawName != null && rawName.length() > 0) {
						if (rawName.startsWith("item.")) {
							name = rawName.substring(5);
						} else if (rawName.startsWith("tile.")) {
							name = rawName.substring(5);
						} else {
							name = rawName;
						}
						if (name.length() > 0) {
							name = Character.toUpperCase(name.charAt(0)) + name.substring(1);
						}
					}
				}
				if (name.length() > 0) {
					tooltipText = name + " (#" + stack.itemID + ")";
					tooltipX = mouseX + 12;
					tooltipY = mouseY - 12;
				}
			}
		}

		GL11.glPopMatrix();
		RenderHelper.disableStandardItemLighting();
		GL11.glDisable(GL11.GL_LIGHTING);

		// --- Black header line across full screen above the item grid ---
		// (already part of the header bar, no extra line needed)

		// Tooltip
		if (tooltipText != null) {
			GL11.glDisable(GL11.GL_DEPTH_TEST);
			int tw = this.fontRenderer.getStringWidth(tooltipText);
			int tx = tooltipX;
			int ty = tooltipY;
			if (tx + tw + 3 > this.width) tx = this.width - tw - 6;
			if (ty < 3) ty = 3;
			drawGradientRect(tx - 3, ty - 3, tx + tw + 3, ty + 8 + 3, 0xC0000000, 0xC0000000);
			drawRect(tx - 3, ty - 3, tx + tw + 3, ty - 2, 0xFF5000AA);
			drawRect(tx - 3, ty + 9, tx + tw + 3, ty + 10, 0xFF5000AA);
			this.fontRenderer.drawStringWithShadow(tooltipText, tx, ty, 0xFFFFFF);
			GL11.glEnable(GL11.GL_DEPTH_TEST);
		}

		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
	}

	// --- Inventory Save/Load ---

	private void saveInventory(int slot) {
		InventoryPlayer inv = this.mc.thePlayer.inventory;
		savedMainInv[slot] = new ItemStack[inv.mainInventory.length];
		for (int i = 0; i < inv.mainInventory.length; i++) {
			savedMainInv[slot][i] = inv.mainInventory[i] != null ? inv.mainInventory[i].copy() : null;
		}
		savedArmorInv[slot] = new ItemStack[inv.armorInventory.length];
		for (int i = 0; i < inv.armorInventory.length; i++) {
			savedArmorInv[slot][i] = inv.armorInventory[i] != null ? inv.armorInventory[i].copy() : null;
		}
	}

	private void loadInventory(int slot) {
		if (savedMainInv[slot] == null) return;
		InventoryPlayer inv = this.mc.thePlayer.inventory;
		for (int i = 0; i < inv.mainInventory.length && i < savedMainInv[slot].length; i++) {
			inv.mainInventory[i] = savedMainInv[slot][i] != null ? savedMainInv[slot][i].copy() : null;
		}
		if (savedArmorInv[slot] != null) {
			for (int i = 0; i < inv.armorInventory.length && i < savedArmorInv[slot].length; i++) {
				inv.armorInventory[i] = savedArmorInv[slot][i] != null ? savedArmorInv[slot][i].copy() : null;
			}
		}
	}

	private void clearSlot(int slot) {
		savedMainInv[slot] = null;
		savedArmorInv[slot] = null;
	}

	protected void drawGuiContainerForegroundLayer() {
		this.fontRenderer.drawString("Crafting", 86, 16, 4210752);
	}

	protected void drawGuiContainerBackgroundLayer(float var1) {
		int var2 = this.mc.renderEngine.getTexture("/gui/inventory.png");
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(var2);
		int var3 = (this.width - this.xSize) / 2;
		int var4 = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(var3, var4, 0, 0, this.xSize, this.ySize);

		// Draw player model
		GL11.glEnable('\u803a');
		GL11.glEnable(GL11.GL_COLOR_MATERIAL);
		GL11.glPushMatrix();
		GL11.glTranslatef((float)(var3 + 51), (float)(var4 + 75), 50.0F);
		float var5 = 30.0F;
		GL11.glScalef(-var5, var5, var5);
		GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
		float var6 = this.mc.thePlayer.renderYawOffset;
		float var7 = this.mc.thePlayer.rotationYaw;
		float var8 = this.mc.thePlayer.rotationPitch;
		float var9 = (float)(var3 + 51) - this.xSize_lo;
		float var10 = (float)(var4 + 75 - 50) - this.ySize_lo;
		GL11.glRotatef(135.0F, 0.0F, 1.0F, 0.0F);
		RenderHelper.enableStandardItemLighting();
		GL11.glRotatef(-135.0F, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(-((float)Math.atan((double)(var10 / 40.0F))) * 20.0F, 1.0F, 0.0F, 0.0F);
		this.mc.thePlayer.renderYawOffset = (float)Math.atan((double)(var9 / 40.0F)) * 20.0F;
		this.mc.thePlayer.rotationYaw = (float)Math.atan((double)(var9 / 40.0F)) * 40.0F;
		this.mc.thePlayer.rotationPitch = -((float)Math.atan((double)(var10 / 40.0F))) * 20.0F;
		this.mc.thePlayer.entityBrightness = 1.0F;
		GL11.glTranslatef(0.0F, this.mc.thePlayer.yOffset, 0.0F);
		RenderManager.instance.playerViewY = 180.0F;
		RenderManager.instance.livingPlayer = this.mc.thePlayer;
		RenderManager.instance.renderEntityWithPosYaw(this.mc.thePlayer, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
		this.mc.thePlayer.entityBrightness = 0.0F;
		this.mc.thePlayer.renderYawOffset = var6;
		this.mc.thePlayer.rotationYaw = var7;
		this.mc.thePlayer.rotationPitch = var8;
		GL11.glPopMatrix();
		RenderHelper.disableStandardItemLighting();
		GL11.glDisable('\u803a');
	}

	protected void mouseClicked(int var1, int var2, int var3) {
		// Check page arrows (in header bar)
		String pageStr = (currentPage + 1) + "/" + totalPages;
		int pageStrW = this.fontRenderer.getStringWidth(pageStr);
		int pageX = this.width - pageStrW - 16;
		int arrowLeftX = pageX - 12;
		int arrowRightX = this.width - 10;

		if (var2 >= 0 && var2 <= 14) {
			if (var1 >= arrowLeftX && var1 < arrowLeftX + 10 && currentPage > 0) {
				currentPage--;
				return;
			}
			if (var1 >= arrowRightX && var1 < arrowRightX + 10 && currentPage < totalPages - 1) {
				currentPage++;
				return;
			}
		}

		// Check header buttons (Clear, Sunset, Midday, Nightset, Midnight)
		for (int i = 0; i < HEADER_LABELS.length; i++) {
			if (var1 >= headerBtnX[i] && var1 < headerBtnX[i] + headerBtnW[i] && var2 >= 0 && var2 < 14) {
				if (i == 0) {
					// Clear inventory
					InventoryPlayer inv = this.mc.thePlayer.inventory;
					for (int j = 0; j < inv.mainInventory.length; j++) {
						inv.mainInventory[j] = null;
					}
					for (int j = 0; j < inv.armorInventory.length; j++) {
						inv.armorInventory[j] = null;
					}
				} else {
					// Set time
					this.mc.theWorld.setWorldTime(TIME_VALUES[i]);
				}
				return;
			}
		}

		// Check Save/Load buttons
		for (int i = 0; i < NUM_SAVE_SLOTS; i++) {
			int by = btnStartY + i * 14;
			String label = getSlotLabel(i);
			int labelW = this.fontRenderer.getStringWidth(label);

			// Main button click
			if (var1 >= btnX && var1 < btnX + labelW + 6 && var2 >= by && var2 < by + 12) {
				if (savedMainInv[i] != null) {
					// Load
					loadInventory(i);
				} else {
					// Save
					saveInventory(i);
				}
				return;
			}

			// X button click
			int xx = btnX + labelW + 10;
			if (var1 >= xx && var1 < xx + 12 && var2 >= by && var2 < by + 12) {
				clearSlot(i);
				return;
			}
		}

		// Check item grid clicks
		int scrollOffset = currentPage * itemsPerPage;
		if (var1 >= gridX && var1 < gridX + cols * SLOT_SIZE &&
			var2 >= gridY && var2 < gridY + rows * SLOT_SIZE) {

			int col = (var1 - gridX) / SLOT_SIZE;
			int row = (var2 - gridY) / SLOT_SIZE;
			int index = scrollOffset + row * cols + col;

			if (index >= 0 && index < allItems.length) {
				ItemStack stack = allItems[index].copy();

				if (var3 == 0) {
					stack.stackSize = stack.getItem().getItemStackLimit();
				} else if (var3 == 1) {
					stack.stackSize = 1;
				}

				this.mc.thePlayer.inventory.addItemStackToInventory(stack);
			}
			return;
		}

		super.mouseClicked(var1, var2, var3);
	}

	public void handleMouseInput() {
		super.handleMouseInput();

		int scroll = Mouse.getEventDWheel();
		if (scroll != 0) {
			if (scroll < 0) {
				if (currentPage < totalPages - 1) currentPage++;
			} else {
				if (currentPage > 0) currentPage--;
			}
		}
	}

	protected void keyTyped(char var1, int var2) {
		if (var2 == 1 || var2 == this.mc.gameSettings.keyBindInventory.keyCode) {
			this.mc.thePlayer.closeScreen();
		}
	}

	protected void actionPerformed(GuiButton var1) {
	}
}
