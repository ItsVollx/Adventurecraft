package net.minecraft.src;

public class AC_ItemBomb extends Item {

	public AC_ItemBomb(int itemId) {
		super(itemId);
		this.maxStackSize = 16;
		this.setItemName("acBomb");
	}

	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if(!world.multiplayerWorld) {
			EntityTNTPrimed tnt = new EntityTNTPrimed(world);
			tnt.setPosition(player.posX, player.posY + 1.5, player.posZ);
			double lookX = -MathHelper.sin(player.rotationYaw / 180.0F * 3.14159F) * MathHelper.cos(player.rotationPitch / 180.0F * 3.14159F);
			double lookY = -MathHelper.sin(player.rotationPitch / 180.0F * 3.14159F);
			double lookZ = MathHelper.cos(player.rotationYaw / 180.0F * 3.14159F) * MathHelper.cos(player.rotationPitch / 180.0F * 3.14159F);
			tnt.motionX = lookX * 1.2;
			tnt.motionY = lookY * 1.2 + 0.3;
			tnt.motionZ = lookZ * 1.2;
			tnt.fuse = 60;
			world.entityJoinedWorld(tnt);
			world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F);
			stack.stackSize--;
		}
		return stack;
	}
}
