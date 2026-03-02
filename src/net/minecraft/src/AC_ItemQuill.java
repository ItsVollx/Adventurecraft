package net.minecraft.src;

public class AC_ItemQuill extends Item {

	public AC_ItemQuill(int itemId) {
		super(itemId);
		this.maxStackSize = 1;
		this.setItemName("quill");
	}

	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side) {
		if(!world.multiplayerWorld) {
			// Find the top of the column
			int topY = world.getHeightValue(x, z);
			player.setPositionAndRotation((double)x + 0.5, (double)topY + 1.0, (double)z + 0.5, player.rotationYaw, player.rotationPitch);
			player.fallDistance = 0.0F;
			player.addChatMessage("Teleported to top: " + x + ", " + topY + ", " + z);
		}
		return true;
	}
}
