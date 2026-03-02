package net.minecraft.src;

/**
 * Bombable color block - can be destroyed by explosions.
 * Extends AC_BlockColor for color tinting behavior.
 */
public class AC_BlockBombable extends AC_BlockColor {
	public AC_BlockBombable(int id, int tex) {
		super(id, tex);
		this.setResistance(0.0F);
	}
}
