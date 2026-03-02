package net.minecraft.src;

public class AC_ItemShotgun extends Item {
	private static final int MAX_AMMO = 7;

	public AC_ItemShotgun(int itemId) {
		super(itemId);
		this.maxStackSize = 1;
		this.setMaxDamage(MAX_AMMO);
		this.setItemName("shotgun");
	}

	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if(!world.multiplayerWorld) {
			if(stack.getItemDamage() < MAX_AMMO) {
				// Fire 6 pellets with high spread
				for(int i = 0; i < 6; i++) {
					EntityArrow arrow = new EntityArrow(world, player);
					arrow.setArrowHeading(
						arrow.motionX + (world.rand.nextDouble() - 0.5) * 0.5,
						arrow.motionY + (world.rand.nextDouble() - 0.5) * 0.3,
						arrow.motionZ + (world.rand.nextDouble() - 0.5) * 0.5,
						2.0F, 8.0F
					);
					world.entityJoinedWorld(arrow);
				}
				world.playSoundAtEntity(player, "random.explode", 0.6F, 1.5F);
				stack.damageItem(1, player);
			} else {
				stack.setItemDamage(0);
				world.playSoundAtEntity(player, "random.click", 0.5F, 1.0F);
				player.addChatMessage("Reloaded!");
			}
		}
		return stack;
	}
}
