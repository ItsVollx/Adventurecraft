package net.minecraft.src;

/**
 * Redstone Power Block - a triggerable block that acts as wireless redstone output.
 * When triggered via the trigger system, it emits a redstone signal.
 * Paired with Redstone Trigger for wireless redstone circuits.
 * Visible only in Debug Mode.
 */
public class AC_BlockRedstonePower extends Block implements AC_IBlockTriggerable {

	protected AC_BlockRedstonePower(int blockID, int textureIndex) {
		super(blockID, textureIndex, Material.glass);
		this.setBlockName("redstonePower");
	}

	public void onTriggerActivated(World world, int x, int y, int z) {
		world.setBlockMetadataWithNotify(x, y, z, 1);
		world.notifyBlocksOfNeighborChange(x, y, z, this.blockID);
	}

	public void onTriggerDeactivated(World world, int x, int y, int z) {
		world.setBlockMetadataWithNotify(x, y, z, 0);
		world.notifyBlocksOfNeighborChange(x, y, z, this.blockID);
	}

	public void reset(World world, int x, int y, int z) {
		world.setBlockMetadataWithNotify(x, y, z, 0);
		world.notifyBlocksOfNeighborChange(x, y, z, this.blockID);
	}

	public boolean isPoweringTo(IBlockAccess world, int x, int y, int z, int side) {
		return world.getBlockMetadata(x, y, z) > 0;
	}

	public boolean isIndirectlyPoweringTo(World world, int x, int y, int z, int side) {
		return world.getBlockMetadata(x, y, z) > 0;
	}

	public boolean canProvidePower() {
		return true;
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return true;
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		return null;
	}

	public boolean isCollidable() {
		return false;
	}
}
