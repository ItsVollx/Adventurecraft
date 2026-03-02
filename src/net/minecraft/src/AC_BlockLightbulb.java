package net.minecraft.src;

/**
 * Lightbulb block - acts as an invisible light source.
 * Visible in Debug Mode, invisible in Adventure Mode.
 * Emits light at level 15 (maximum brightness).
 */
public class AC_BlockLightbulb extends Block {

	protected AC_BlockLightbulb(int blockID, int textureIndex) {
		super(blockID, textureIndex, Material.glass);
		this.setBlockName("lightBulb");
		this.setLightValue(1.0F); // Light level 15 (max)
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
