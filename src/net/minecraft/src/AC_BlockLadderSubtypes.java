package net.minecraft.src;

/**
 * Ladder with subtypes (decorative ladders).
 * Extends BlockLadder and adds AC_IBlockColor for texture cycling.
 */
public class AC_BlockLadderSubtypes extends BlockLadder implements AC_IBlockColor {
	protected AC_BlockLadderSubtypes(int id, int tex) {
		super(id, tex);
	}

	public int getBlockTextureFromSideAndMetadata(int side, int metadata) {
		return this.blockIndexInTexture + metadata;
	}

	public void incrementColor(World world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		world.setBlockMetadataWithNotify(x, y, z, (meta + 1) % Block.subTypes[this.blockID]);
	}
}
