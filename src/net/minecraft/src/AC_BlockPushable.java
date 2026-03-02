package net.minecraft.src;

/**
 * Pushable Block - can be pushed/pulled using the Power Glove.
 * Affected by gravity like sand and gravel.
 * Acts as a Trigger Block - triggers Trigger Pushable blocks when placed adjacent.
 * Color can be changed with the Paintbrush tool (7 colors available).
 * Texture resembles the Weighted Storage Cube from Portal.
 * Can be pushed onto/replace water, lava, fire, and snow.
 * Blocks Fan air currents.
 */
public class AC_BlockPushable extends Block {

	protected AC_BlockPushable(int blockID, int textureIndex) {
		super(blockID, textureIndex, Material.iron);
		this.setBlockName("pushable");
		this.setHardness(1.5F);
		this.setResistance(10.0F);
	}

	/**
	 * When placed or moved, notify neighbors (for Trigger Pushable detection).
	 */
	public void onBlockAdded(World world, int x, int y, int z) {
		world.notifyBlocksOfNeighborChange(x, y, z, this.blockID);
		// Check if should fall (gravity)
		world.scheduleBlockUpdate(x, y, z, this.blockID, this.tickRate());
	}

	/**
	 * Gravity tick - like sand, falls if nothing below.
	 */
	public void updateTick(World world, int x, int y, int z, java.util.Random rand) {
		tryToFall(world, x, y, z);
	}

	private void tryToFall(World world, int x, int y, int z) {
		if(y > 0) {
			int below = world.getBlockId(x, y - 1, z);
			if(below == 0 || below == Block.waterMoving.blockID || below == Block.waterStill.blockID ||
			   below == Block.lavaMoving.blockID || below == Block.lavaStill.blockID ||
			   below == Block.fire.blockID || below == Block.snow.blockID) {
				int meta = world.getBlockMetadata(x, y, z);
				world.setBlockWithNotify(x, y, z, 0);
				world.setBlockAndMetadataWithNotify(x, y - 1, z, this.blockID, meta);
				world.scheduleBlockUpdate(x, y - 1, z, this.blockID, this.tickRate());
			}
		}
	}

	public void onBlockRemoval(World world, int x, int y, int z) {
		world.notifyBlocksOfNeighborChange(x, y, z, this.blockID);
	}

	public int tickRate() {
		return 3;
	}

	public boolean isOpaqueCube() {
		return true;
	}

	public boolean renderAsNormalBlock() {
		return true;
	}
}
