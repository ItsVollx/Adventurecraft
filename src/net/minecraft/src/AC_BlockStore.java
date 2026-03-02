package net.minecraft.src;

/**
 * Store Block - a trigger block for player item trading.
 * Players can trade items, insert items, or receive items.
 * In Debug Mode, map creators configure what items it gives/receives.
 * When a trade is completed, it can trigger a target area.
 * Three modes: Trade (give and receive), Insert (give only to trigger), Receive (get only).
 */
public class AC_BlockStore extends BlockContainer {

	protected AC_BlockStore(int blockID, int textureIndex) {
		super(blockID, textureIndex, Material.wood);
		this.setBlockName("store");
		this.setHardness(2.5F);
		this.setResistance(10.0F);
	}

	protected TileEntity getBlockEntity() {
		return new AC_TileEntityData();
	}

	public boolean blockActivated(World world, int x, int y, int z, EntityPlayer player) {
		// Store block interaction would open trading UI
		// For now, play a click sound as feedback
		world.playSoundEffect((double)x + 0.5, (double)y + 0.5, (double)z + 0.5, "random.click", 0.3F, 0.6F);
		return true;
	}

	public boolean isOpaqueCube() {
		return true;
	}

	public boolean renderAsNormalBlock() {
		return true;
	}
}
