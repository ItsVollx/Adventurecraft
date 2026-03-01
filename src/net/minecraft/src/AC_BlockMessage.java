package net.minecraft.src;

public class AC_BlockMessage extends AC_BlockTrigger {

	public AC_BlockMessage(int blockID, int textureIndex) {
		super(blockID, textureIndex, Material.iron);
		this.setBlockName("acMessage");
	}

	public void onTriggerActivated(World world, int x, int y, int z) {
		super.onTriggerActivated(world, x, y, z);
		AC_TileEntityData data = this.getACData(world, x, y, z);
		String message = "Hello!";
		if(data != null) {
			message = data.getData("message", "Hello!");
		}

		EntityPlayer player = world.getClosestPlayer((double)x, (double)y, (double)z, 64.0);
		if(player != null) {
			player.addChatMessage(message);
		}
	}

	public boolean blockActivated(World world, int x, int y, int z, EntityPlayer player) {
		if(AC_DebugMode.isEditMode()) {
			AC_TileEntityData data = this.getACData(world, x, y, z);
			if(data != null) {
				String current = data.getData("message", "Hello!");
				player.addChatMessage("Message block text: " + current);
				player.addChatMessage("(Set via TileEntity data editing)");
			}
			return true;
		}
		return false;
	}
}
