package net.minecraft.src;

/**
 * Clip Block - invisible collision block.
 * Prevents the player from passing through a space in Adventure Mode.
 * Only visible in Debug Mode. Disappears in water in Adventure Mode.
 */
public class AC_BlockClip extends Block {

	protected AC_BlockClip(int blockID, int textureIndex) {
		super(blockID, textureIndex, Material.glass);
		this.setBlockName("clip");
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

	/**
	 * Clip block HAS collision - that's its purpose.
	 * Unlike other debug blocks, players CANNOT walk through it.
	 */
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		return AxisAlignedBB.getBoundingBoxFromPool(x, y, z, x + 1, y + 1, z + 1);
	}

	public boolean isCollidable() {
		return true;
	}
}
