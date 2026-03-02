package net.minecraft.src;

/**
 * Half-step (slab) decorative block.
 * Even metadata = bottom slab, odd metadata = top slab.
 * Texture subtype = metadata / 2.
 */
public class AC_BlockHalfStep extends AC_BlockSolid {
	public AC_BlockHalfStep(int id, int tex) {
		super(id, tex);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
		this.setLightOpacity(255);
	}

	public int getBlockTextureFromSideAndMetadata(int side, int metadata) {
		int subtype = (metadata / 2) * 2;
		if (side == 0 || side == 1) {
			return this.blockIndexInTexture + subtype + 1;
		}
		return this.blockIndexInTexture + subtype;
	}

	public void setBlockBoundsBasedOnState(IBlockAccess blockAccess, int x, int y, int z) {
		int meta = blockAccess.getBlockMetadata(x, y, z);
		if (meta % 2 == 0) {
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
		} else {
			this.setBlockBounds(0.0F, 0.5F, 0.0F, 1.0F, 1.0F, 1.0F);
		}
	}

	public void setBlockBoundsForItemRender() {
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public void incrementColor(World world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		int placement = meta % 2;
		int subtype = meta / 2;
		subtype = (subtype + 1) % Block.subTypes[this.blockID];
		world.setBlockMetadataWithNotify(x, y, z, placement + subtype * 2);
	}
}
