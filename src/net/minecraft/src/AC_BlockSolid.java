package net.minecraft.src;

/**
 * Solid decorative block with subtypes. All sides use the same texture.
 * Texture = blockIndexInTexture + metadata.
 */
public class AC_BlockSolid extends Block implements AC_IBlockColor {
	public AC_BlockSolid(int id, int tex) {
		super(id, tex, Material.rock);
	}

	public int getBlockTextureFromSideAndMetadata(int side, int metadata) {
		return this.blockIndexInTexture + metadata;
	}

	public void incrementColor(World world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		world.setBlockMetadataWithNotify(x, y, z, (meta + 1) % Block.subTypes[this.blockID]);
	}
}
