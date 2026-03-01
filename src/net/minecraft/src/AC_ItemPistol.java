package net.minecraft.src;

public class AC_ItemPistol extends Item {
	private static final int MAX_AMMO = 15;

	public AC_ItemPistol(int itemId) {
		super(itemId);
		this.maxStackSize = 1;
		this.setMaxDamage(MAX_AMMO);
		this.setItemName("acPistol");
	}

	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if(!world.multiplayerWorld) {
			if(stack.getItemDamage() < MAX_AMMO) {
				EntityArrow arrow = new EntityArrow(world, player);
				arrow.setArrowHeading(arrow.motionX, arrow.motionY, arrow.motionZ, 2.5F, 1.5F);
				world.entityJoinedWorld(arrow);
				world.playSoundAtEntity(player, "random.explode", 0.3F, 2.0F);
				stack.damageItem(1, player);
			} else {
				// Reload
				stack.setItemDamage(0);
				world.playSoundAtEntity(player, "random.click", 0.5F, 1.0F);
				player.addChatMessage("Reloaded!");
			}
		}
		return stack;
	}
}
