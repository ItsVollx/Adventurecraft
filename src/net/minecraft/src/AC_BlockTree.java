package net.minecraft.src;

/**
 * Tree/sapling decorative block.
 * Extends BlockFlower for plant-like behavior.
 */
public class AC_BlockTree extends BlockFlower implements AC_IBlockColor {
	public AC_BlockTree(int id, int tex) {
		super(id, tex);
		float f = 0.4F;
		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
	}

	public int getBlockTextureFromSideAndMetadata(int side, int metadata) {
		return this.blockIndexInTexture + metadata;
	}

	protected boolean canThisPlantGrowOnThisBlockID(int blockId) {
		return true; // Decorative, can be placed anywhere
	}

	public boolean canBlockStay(World world, int x, int y, int z) {
		return true; // Decorative, always stays
	}

	public int getRenderType() {
		return 1; // Cross shape (type 36 deferred)
	}

	public void incrementColor(World world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		world.setBlockMetadataWithNotify(x, y, z, (meta + 1) % Block.subTypes[this.blockID]);
	}
}
