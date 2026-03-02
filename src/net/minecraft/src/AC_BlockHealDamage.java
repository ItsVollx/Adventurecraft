package net.minecraft.src;

/**
 * Heal/Damage Block - a triggerable block that heals or damages the player.
 * When triggered, applies health change based on configured amount (-40 to +40).
 * Visible in Debug Mode, invisible in Adventure Mode.
 * Categorized as a Triggerable Block / Mechanical block.
 */
public class AC_BlockHealDamage extends Block {

	protected AC_BlockHealDamage(int blockID, int textureIndex) {
		super(blockID, textureIndex, Material.glass);
		this.setBlockName("healDamage");
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
