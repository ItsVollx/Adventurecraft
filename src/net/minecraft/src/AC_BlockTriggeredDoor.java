package net.minecraft.src;

public class AC_BlockTriggeredDoor extends AC_BlockTrigger {
	private boolean isOpen = false;

	public AC_BlockTriggeredDoor(int blockID, int textureIndex) {
		super(blockID, textureIndex, Material.iron);
		this.setBlockName("triggeredDoor");
	}

	public void onTriggerActivated(World world, int x, int y, int z) {
		super.onTriggerActivated(world, x, y, z);
		world.playSoundEffect((double)x + 0.5, (double)y + 0.5, (double)z + 0.5, "random.door_open", 1.0F, 1.0F);
	}

	public void onTriggerDeactivated(World world, int x, int y, int z) {
		super.onTriggerDeactivated(world, x, y, z);
		world.playSoundEffect((double)x + 0.5, (double)y + 0.5, (double)z + 0.5, "random.door_close", 1.0F, 1.0F);
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		if(meta > 0) {
			// Open - no collision
			return null;
		}
		return super.getCollisionBoundingBoxFromPool(world, x, y, z);
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return true;
	}

	public int getBlockTextureFromSideAndMetadata(int side, int meta) {
		if(meta > 0) {
			// Open state - use a transparent-ish texture (glass)
			return 49;
		}
		return this.blockIndexInTexture;
	}
}
