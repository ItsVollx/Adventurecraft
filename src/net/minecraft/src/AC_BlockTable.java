package net.minecraft.src;

/**
 * Table decorative block.
 * Top/bottom use blockIndexInTexture + metadata.
 * Sides use blockIndexInTexture + 16 + metadata (row below in atlas).
 */
public class AC_BlockTable extends AC_BlockSolid {
	public AC_BlockTable(int id, int tex) {
		super(id, tex);
	}

	public int getBlockTextureFromSideAndMetadata(int side, int metadata) {
		if (side == 0 || side == 1) {
			return this.blockIndexInTexture + metadata;
		}
		return this.blockIndexInTexture + 16 + metadata;
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	public int getRenderType() {
		return 0; // Cube for now (type 33 deferred)
	}
}
