package net.minecraft.src;

/**
 * Rope decorative block (cross-shaped like a plant).
 * metadata / 3 = texture subtype offset.
 */
public class AC_BlockRope extends AC_BlockPlant {
	public AC_BlockRope(int id, int tex) {
		super(id, tex);
	}

	public int getBlockTextureFromSideAndMetadata(int side, int metadata) {
		return this.blockIndexInTexture + (metadata / 3);
	}

	public int getRenderType() {
		return 1; // Cross shape (type 35 deferred)
	}

	public void incrementColor(World world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		world.setBlockMetadataWithNotify(x, y, z, (meta + 1) % Block.subTypes[this.blockID]);
	}
}
