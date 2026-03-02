package net.minecraft.src;

/**
 * Interface for blocks that can cycle through color/texture variants when clicked.
 * Used by decorative blocks (pillars, solids, transparents, etc.) to change subtypes.
 */
public interface AC_IBlockColor {
	void incrementColor(World world, int x, int y, int z);
}
