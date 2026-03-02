package net.minecraft.src;

/**
 * Trigger Memory Block - remembers trigger state and sends constant signal.
 * Both a Trigger Block and Triggerable Block.
 * Once triggered, stays active and sends a constant trigger signal to its target area
 * until explicitly reset via a Trigger block set to "Reset Target".
 * Has options: activate on trigger/detrigger, reset on death.
 */
public class AC_BlockTriggerMemory extends AC_BlockTrigger {

	protected AC_BlockTriggerMemory(int blockID, int textureIndex) {
		super(blockID, textureIndex, Material.iron);
		this.setBlockName("triggerMemory");
	}

	/**
	 * When triggered, latches ON and stays on until reset.
	 */
	public void onTriggerActivated(World world, int x, int y, int z) {
		// Latch: set metadata to 1 and keep it
		world.setBlockMetadataWithNotify(x, y, z, 1);
		world.notifyBlocksOfNeighborChange(x, y, z, this.blockID);
	}

	/**
	 * Memory block ignores deactivation - stays latched until reset.
	 */
	public void onTriggerDeactivated(World world, int x, int y, int z) {
		// Do nothing - memory holds state until reset
	}

	/**
	 * Reset clears the latched state.
	 */
	public void reset(World world, int x, int y, int z) {
		world.setBlockMetadataWithNotify(x, y, z, 0);
		world.notifyBlocksOfNeighborChange(x, y, z, this.blockID);
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		return null;
	}

	public boolean isCollidable() {
		return false;
	}
}
