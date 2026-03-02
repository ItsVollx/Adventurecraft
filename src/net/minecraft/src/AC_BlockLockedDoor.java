package net.minecraft.src;

/**
 * Locked Door Block - a golden door block opened with a Gold Key.
 * Can be placed as any size (1 block or larger).
 * The keyhole will attempt to center itself on the door grouping.
 * In Debug Mode, player can walk through. In Adventure Mode, blocks passage.
 * Clicking with Gold Key removes all connected Locked Door blocks.
 * Also a triggerable block.
 */
public class AC_BlockLockedDoor extends Block implements AC_IBlockTriggerable {

	protected AC_BlockLockedDoor(int blockID, int textureIndex) {
		super(blockID, textureIndex, Material.iron);
		this.setBlockName("lockedDoor");
		this.setHardness(50.0F);
		this.setResistance(2000.0F);
	}

	/**
	 * Right-click with gold ingot (Gold Key placeholder) to open (remove) the door.
	 */
	public boolean blockActivated(World world, int x, int y, int z, EntityPlayer player) {
		ItemStack held = player.inventory.getCurrentItem();
		if(held != null && held.itemID == Item.ingotGold.shiftedIndex) {
			held.stackSize--;
			if(held.stackSize <= 0) {
				player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
			}
			removeConnected(world, x, y, z);
			world.playSoundEffect((double)x + 0.5, (double)y + 0.5, (double)z + 0.5, "random.door_open", 1.0F, 1.0F);
			return true;
		}
		return false;
	}

	private void removeConnected(World world, int x, int y, int z) {
		if(world.getBlockId(x, y, z) != this.blockID) return;
		world.setBlockWithNotify(x, y, z, 0);
		removeConnected(world, x + 1, y, z);
		removeConnected(world, x - 1, y, z);
		removeConnected(world, x, y + 1, z);
		removeConnected(world, x, y - 1, z);
		removeConnected(world, x, y, z + 1);
		removeConnected(world, x, y, z - 1);
	}

	public void onTriggerActivated(World world, int x, int y, int z) {
		removeConnected(world, x, y, z);
	}

	public void onTriggerDeactivated(World world, int x, int y, int z) {
		// Locked doors don't reappear
	}

	public void reset(World world, int x, int y, int z) {
		// Locked doors can't be reset
	}

	public int idDropped(int metadata, java.util.Random random) {
		return 0;
	}

	public boolean isOpaqueCube() {
		return true;
	}

	public boolean renderAsNormalBlock() {
		return true;
	}
}
