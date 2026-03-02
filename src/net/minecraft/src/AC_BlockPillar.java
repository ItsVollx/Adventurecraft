package net.minecraft.src;

/**
 * Pillar decorative block with subtypes.
 * Top face = blockIndexInTexture - 16 + metadata (one row above)
 * Bottom face = blockIndexInTexture + 16 + metadata (one row below)
 * Sides = blockIndexInTexture + metadata
 */
public class AC_BlockPillar extends Block implements AC_IBlockColor {
	public AC_BlockPillar(int id, int tex) {
		super(id, tex, Material.rock);
	}

	public int getBlockTextureFromSideAndMetadata(int side, int metadata) {
		if(side == 1) {
			return this.blockIndexInTexture - 16 + metadata;
		}
		if(side == 0) {
			return this.blockIndexInTexture + 16 + metadata;
		}
		return this.blockIndexInTexture + metadata;
	}

	public void incrementColor(World world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		world.setBlockMetadataWithNotify(x, y, z, (meta + 1) % Block.subTypes[this.blockID]);
	}
}
