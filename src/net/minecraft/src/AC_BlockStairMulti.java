package net.minecraft.src;

/**
 * Stair block with multiple subtypes.
 * Uses metadata >> 2 for texture subtype, lower 2 bits for orientation.
 * Renders as stairs (render type 10).
 */
public class AC_BlockStairMulti extends Block implements AC_IBlockColor {
	private Block modelBlock;

	public AC_BlockStairMulti(int id, int tex, Block model) {
		super(id, tex, model.blockMaterial);
		this.modelBlock = model;
		this.setHardness(model.blockHardness);
		this.setResistance(model.blockResistance / 3.0F);
		this.setStepSound(model.stepSound);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}

	public int getBlockTextureFromSideAndMetadata(int side, int metadata) {
		return this.blockIndexInTexture + (metadata >> 2);
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	public int getRenderType() {
		return 10; // Stairs
	}

	public void incrementColor(World world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		int orientation = meta & 3;
		int subtype = meta >> 2;
		subtype = (subtype + 1) % Block.subTypes[this.blockID];
		world.setBlockMetadataWithNotify(x, y, z, orientation | (subtype << 2));
	}
}
