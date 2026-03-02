package net.minecraft.src;

/**
 * Mob Spawner Block - customizable mob spawner.
 * Map creators can configure what type of mob spawns and its properties.
 * Uses the vanilla mob spawner texture.
 */
public class AC_BlockMobSpawner extends BlockContainer {

	protected AC_BlockMobSpawner(int blockID, int textureIndex) {
		super(blockID, textureIndex, Material.rock);
		this.setBlockName("mobSpawner2");
		this.setHardness(5.0F);
		this.setResistance(10.0F);
	}

	protected TileEntity getBlockEntity() {
		return new AC_TileEntityData();
	}

	public int idDropped(int metadata, java.util.Random random) {
		return 0; // No drops
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
}
