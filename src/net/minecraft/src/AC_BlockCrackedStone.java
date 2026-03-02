package net.minecraft.src;

/**
 * Cracked Stone Block - a bomb-able stone block.
 * Looks like regular stone but with crack lines.
 * Any explosion (creeper, TNT, or thrown Bomb) within 2-block radius destroys it.
 */
public class AC_BlockCrackedStone extends Block {

	protected AC_BlockCrackedStone(int blockID, int textureIndex) {
		super(blockID, textureIndex, Material.rock);
		this.setBlockName("crackedStone");
		this.setHardness(1.5F);
		this.setResistance(0.5F); // Low blast resistance so explosions destroy it
	}

	/**
	 * Very low blast resistance so bombs/explosions easily destroy it.
	 */
	public float getExplosionResistance(Entity entity) {
		return 0.5F;
	}

	/**
	 * Drops nothing when destroyed by explosion - just vanishes.
	 */
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
