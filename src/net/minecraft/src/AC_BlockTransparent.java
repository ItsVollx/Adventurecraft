package net.minecraft.src;

/**
 * Transparent decorative block with subtypes (glass, cages).
 * Extends AC_BlockSolid but is NOT opaque.
 */
public class AC_BlockTransparent extends AC_BlockSolid {
	public AC_BlockTransparent(int id, int tex) {
		super(id, tex);
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean shouldSideBeRendered(IBlockAccess blockAccess, int x, int y, int z, int side) {
		int blockId = blockAccess.getBlockId(x, y, z);
		return blockId == this.blockID ? false : super.shouldSideBeRendered(blockAccess, x, y, z, side);
	}
}
