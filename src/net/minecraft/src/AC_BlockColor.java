package net.minecraft.src;

/**
 * Color-tinted decorative block.
 * Has 7 color variants via metadata.
 * Default color is 0xCCCCCC, tints applied per metadata value.
 */
public class AC_BlockColor extends Block implements AC_IBlockColor {
	private static final int[] COLORS = new int[] {
		0xCCCCCC,  // 0: light gray (default)
		0xCC4C4C,  // 1: red
		0xCCAA00,  // 2: orange/gold
		0xCCCC4C,  // 3: yellow
		0x4CCC4C,  // 4: green
		0x4CCCCC,  // 5: cyan
		0x4C4CCC,  // 6: blue
		0xCC4CCC   // 7: magenta (not used, only 7 subtypes 0-6)
	};

	public AC_BlockColor(int id, int tex) {
		super(id, tex, Material.rock);
	}

	public int getBlockTextureFromSideAndMetadata(int side, int metadata) {
		return this.blockIndexInTexture;
	}

	public int getRenderColor(int metadata) {
		if (metadata >= 0 && metadata < COLORS.length) {
			return COLORS[metadata];
		}
		return 0xCCCCCC;
	}

	public int colorMultiplier(IBlockAccess blockAccess, int x, int y, int z) {
		int meta = blockAccess.getBlockMetadata(x, y, z);
		return getRenderColor(meta);
	}

	public void incrementColor(World world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		world.setBlockMetadataWithNotify(x, y, z, (meta + 1) % Block.subTypes[this.blockID]);
	}
}
