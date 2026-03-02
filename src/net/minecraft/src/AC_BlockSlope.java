package net.minecraft.src;

/**
 * Slope decorative block (diagonal/ramp shape).
 * Extends AC_BlockStairMulti but uses slope rendering.
 * Falls back to stairs render type for now (type 38 deferred).
 */
public class AC_BlockSlope extends AC_BlockStairMulti {
	public AC_BlockSlope(int id, int tex, Block model) {
		super(id, tex, model);
	}

	public int getRenderType() {
		return 10; // Stairs for now (type 38 deferred for proper slopes)
	}
}
