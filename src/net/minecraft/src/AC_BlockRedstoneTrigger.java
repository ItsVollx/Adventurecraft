package net.minecraft.src;

/**
 * Redstone Trigger Block - a trigger block activated by redstone/lever input.
 * When powered by redstone in Adventure Mode, it triggers its target area.
 * Paired with Redstone Power for wireless redstone circuits.
 * Visible only in Debug Mode.
 */
public class AC_BlockRedstoneTrigger extends AC_BlockTrigger {

	protected AC_BlockRedstoneTrigger(int blockID, int textureIndex) {
		super(blockID, textureIndex, Material.iron);
		this.setBlockName("redstoneTrigger");
	}

	/**
	 * Called when a neighboring block changes - checks for redstone power.
	 */
	public void onNeighborBlockChange(World world, int x, int y, int z, int neighborID) {
		boolean powered = world.isBlockIndirectlyGettingPowered(x, y, z);
		int meta = world.getBlockMetadata(x, y, z);

		if(powered && meta == 0) {
			// Just became powered - activate trigger
			onTriggerActivated(world, x, y, z);
		} else if(!powered && meta > 0) {
			// Lost power - deactivate trigger
			onTriggerDeactivated(world, x, y, z);
		}
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		return null;
	}

	public boolean isCollidable() {
		return false;
	}
}
