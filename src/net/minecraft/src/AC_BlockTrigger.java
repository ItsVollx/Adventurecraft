package net.minecraft.src;

public class AC_BlockTrigger extends BlockContainer implements AC_IBlockTriggerable {

	protected AC_BlockTrigger(int blockID, int textureIndex, Material material) {
		super(blockID, textureIndex, material);
	}

	protected TileEntity getBlockEntity() {
		return new AC_TileEntityData();
	}

	public AC_TileEntityData getACData(World world, int x, int y, int z) {
		TileEntity te = world.getBlockTileEntity(x, y, z);
		if(te instanceof AC_TileEntityData) {
			return (AC_TileEntityData)te;
		}
		return null;
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

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	public int getRenderType() {
		return 0;
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

	public boolean blockActivated(World world, int x, int y, int z, EntityPlayer player) {
		if(AC_DebugMode.isEditMode()) {
			int meta = world.getBlockMetadata(x, y, z);
			if(meta == 0) {
				this.onTriggerActivated(world, x, y, z);
			} else {
				this.onTriggerDeactivated(world, x, y, z);
			}
			return true;
		}
		return false;
	}
}
