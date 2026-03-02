package net.minecraft.src;

public class AC_ItemPowerGlove extends Item {

	public AC_ItemPowerGlove(int itemId) {
		super(itemId);
		this.maxStackSize = 1;
		this.setMaxDamage(64);
		this.setItemName("powerGlove");
	}

	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if(!world.multiplayerWorld) {
			// Launch player forward in the direction they're looking
			float yaw = player.rotationYaw / 180.0F * 3.14159F;
			float pitch = player.rotationPitch / 180.0F * 3.14159F;
			player.motionX += -MathHelper.sin(yaw) * MathHelper.cos(pitch) * 2.0;
			player.motionY += -MathHelper.sin(pitch) * 0.8 + 0.4;
			player.motionZ += MathHelper.cos(yaw) * MathHelper.cos(pitch) * 2.0;
			player.fallDistance = 0.0F;
			world.playSoundAtEntity(player, "random.explode", 0.5F, 1.5F);
			stack.damageItem(1, player);
		}
		return stack;
	}
}
