package net.minecraft.src;

/**
 * Darkness block - lowers the light level in a block space.
 * Visible in Debug Mode, transparent in Adventure Mode but absorbs light.
 * Stacking multiple Darkness blocks creates even darker areas.
 */
public class AC_BlockDarkness extends Block {

	protected AC_BlockDarkness(int blockID, int textureIndex) {
		super(blockID, textureIndex, Material.glass);
		this.setBlockName("darkness");
		// Light opacity of 2 means each Darkness block reduces light by 2 levels
		Block.lightOpacity[blockID] = 2;
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return true;
	}

	public int getRenderBlockPass() {
		return 0;
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		return null; // No collision - player can walk through
	}

	public boolean isCollidable() {
		return false;
	}
}
