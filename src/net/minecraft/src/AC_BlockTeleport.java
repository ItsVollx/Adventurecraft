package net.minecraft.src;

public class AC_BlockTeleport extends AC_BlockTrigger {

	public AC_BlockTeleport(int blockID, int textureIndex) {
		super(blockID, textureIndex, Material.iron);
		this.setBlockName("teleport");
	}

	public void onTriggerActivated(World world, int x, int y, int z) {
		super.onTriggerActivated(world, x, y, z);
		this.teleportPlayer(world, x, y, z);
	}

	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
		if(entity instanceof EntityPlayer) {
			int meta = world.getBlockMetadata(x, y, z);
			if(meta == 0) {
				// Auto-teleport on walk-through
				this.teleportPlayer(world, x, y, z);
			}
		}
	}

	private void teleportPlayer(World world, int x, int y, int z) {
		AC_TileEntityData data = this.getACData(world, x, y, z);
		if(data != null) {
			double destX = data.getDoubleData("destX", (double)x);
			double destY = data.getDoubleData("destY", (double)y);
			double destZ = data.getDoubleData("destZ", (double)z);

			EntityPlayer player = world.getClosestPlayer((double)x, (double)y, (double)z, 8.0);
			if(player != null) {
				player.setPositionAndRotation(destX + 0.5, destY + 1.0, destZ + 0.5, player.rotationYaw, player.rotationPitch);
				world.playSoundEffect(destX, destY, destZ, "mob.endermen.portal", 1.0F, 1.0F);
			}
		}
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		return null;
	}

	public boolean blockActivated(World world, int x, int y, int z, EntityPlayer player) {
		if(AC_DebugMode.isEditMode()) {
			AC_TileEntityData data = this.getACData(world, x, y, z);
			if(data != null) {
				if(player.isSneaking()) {
					// Set destination to player's position
					data.setData("destX", String.valueOf((int)player.posX));
					data.setData("destY", String.valueOf((int)player.posY));
					data.setData("destZ", String.valueOf((int)player.posZ));
					player.addChatMessage("Teleport destination set to: " + (int)player.posX + ", " + (int)player.posY + ", " + (int)player.posZ);
				} else {
					String dx = data.getData("destX", "not set");
					String dy = data.getData("destY", "not set");
					String dz = data.getData("destZ", "not set");
					player.addChatMessage("Teleport dest: " + dx + ", " + dy + ", " + dz);
					player.addChatMessage("Sneak+click to set destination");
				}
			}
			return true;
		}
		return false;
	}
}
