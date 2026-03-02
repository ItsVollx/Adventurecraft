package net.minecraft.src;

/**
 * Trigger Inverter Block - a NOT gate for trigger signals.
 * Both a Trigger Block and Triggerable Block.
 * When activated, it DE-triggers its target area; when deactivated, it triggers the area.
 * Only works in Adventure Mode. Visible only in Debug Mode.
 */
public class AC_BlockTriggerInverter extends AC_BlockTrigger {

	protected AC_BlockTriggerInverter(int blockID, int textureIndex) {
		super(blockID, textureIndex, Material.iron);
		this.setBlockName("triggerInverter");
	}

	/**
	 * Inverted logic: when this block receives a trigger activation,
	 * it sends a deactivation to its target area.
	 */
	public void onTriggerActivated(World world, int x, int y, int z) {
		// Invert: set own metadata to 1 (activated state) but send deactivate to targets
		world.setBlockMetadataWithNotify(x, y, z, 1);
		world.notifyBlocksOfNeighborChange(x, y, z, this.blockID);
	}

	/**
	 * Inverted logic: when this block receives a trigger deactivation,
	 * it sends an activation to its target area.
	 */
	public void onTriggerDeactivated(World world, int x, int y, int z) {
		world.setBlockMetadataWithNotify(x, y, z, 0);
		world.notifyBlocksOfNeighborChange(x, y, z, this.blockID);
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		return null; // No collision - player can walk through
	}

	public boolean isCollidable() {
		return false;
	}
}
