package net.minecraft.src;

public class AC_BlockCamera extends AC_BlockTrigger {

	public AC_BlockCamera(int blockID, int textureIndex) {
		super(blockID, textureIndex, Material.iron);
		this.setBlockName("camera");
	}

	public void onTriggerActivated(World world, int x, int y, int z) {
		super.onTriggerActivated(world, x, y, z);
		AC_TileEntityData data = this.getACData(world, x, y, z);
		if(data != null) {
			double destX = data.getDoubleData("camX", (double)x);
			double destY = data.getDoubleData("camY", (double)y + 1);
			double destZ = data.getDoubleData("camZ", (double)z);
			float yaw = (float)data.getDoubleData("camYaw", 0.0);
			float pitch = (float)data.getDoubleData("camPitch", 0.0);

			EntityPlayer player = world.getClosestPlayer((double)x, (double)y, (double)z, 64.0);
			if(player != null) {
				player.setPositionAndRotation(destX, destY, destZ, yaw, pitch);
			}
		}
	}

	public boolean blockActivated(World world, int x, int y, int z, EntityPlayer player) {
		if(AC_DebugMode.isEditMode()) {
			AC_TileEntityData data = this.getACData(world, x, y, z);
			if(data != null) {
				data.setData("camX", String.valueOf(player.posX));
				data.setData("camY", String.valueOf(player.posY));
				data.setData("camZ", String.valueOf(player.posZ));
				data.setData("camYaw", String.valueOf(player.rotationYaw));
				data.setData("camPitch", String.valueOf(player.rotationPitch));
				player.addChatMessage("Camera target set to your position.");
			}
			return true;
		}
		return false;
	}
}
