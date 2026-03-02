package net.minecraft.src;

/**
 * Chair decorative block.
 * Lower 2 bits of metadata = rotation (0-3).
 * Upper bits (meta/4) = texture subtype.
 */
public class AC_BlockChair extends AC_BlockSolid {
	public AC_BlockChair(int id, int tex) {
		super(id, tex);
		this.setBlockBounds(0.125F, 0.0F, 0.125F, 0.875F, 0.625F, 0.875F);
	}

	public int getBlockTextureFromSideAndMetadata(int side, int metadata) {
		return this.blockIndexInTexture + (metadata / 4);
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	public int getRenderType() {
		return 34;
	}

	public void incrementColor(World world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		int rotation = meta & 3;
		int subtype = meta / 4;
		subtype = (subtype + 1) % Block.subTypes[this.blockID];
		world.setBlockMetadataWithNotify(x, y, z, rotation + subtype * 4);
	}
}
