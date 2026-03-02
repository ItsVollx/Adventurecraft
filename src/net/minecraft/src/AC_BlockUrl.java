package net.minecraft.src;

/**
 * URL Block - a block that opens a URL when clicked in adventure mode.
 * Used by map creators to link to websites (e.g., forums, guides).
 * Visible only in Debug Mode.
 */
public class AC_BlockUrl extends Block {

	protected AC_BlockUrl(int blockID, int textureIndex) {
		super(blockID, textureIndex, Material.rock);
		this.setBlockName("url");
		this.setHardness(5.0F);
		this.setResistance(10.0F);
	}

	public boolean blockActivated(World world, int x, int y, int z, EntityPlayer player) {
		// URL functionality handled by tile entity data in original
		return true;
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return true;
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		return null;
	}

	public boolean isCollidable() {
		return false;
	}
}
