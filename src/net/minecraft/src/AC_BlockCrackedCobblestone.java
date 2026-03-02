package net.minecraft.src;

/**
 * Cracked Cobblestone Block - a bomb-able cobblestone block.
 * Similar to Cracked Stone but with cobblestone appearance.
 * Any explosion (creeper, TNT, or thrown Bomb) within 2-block radius destroys it.
 */
public class AC_BlockCrackedCobblestone extends Block {

	protected AC_BlockCrackedCobblestone(int blockID, int textureIndex) {
		super(blockID, textureIndex, Material.rock);
		this.setBlockName("crackedStonebrick");
		this.setHardness(1.5F);
		this.setResistance(0.5F);
	}

	public float getExplosionResistance(Entity entity) {
		return 0.5F;
	}

	public int idDropped(int metadata, java.util.Random random) {
		return 0;
	}

	public boolean isOpaqueCube() {
		return true;
	}

	public boolean renderAsNormalBlock() {
		return true;
	}
}
