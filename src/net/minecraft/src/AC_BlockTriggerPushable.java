package net.minecraft.src;

/**
 * Trigger Pushable Block - a trigger block activated by Pushable Blocks.
 * Also technically a triggerable block. Triggered when a same-colored
 * Pushable Block is placed adjacent to it (any direction).
 * Color can be changed using the Paintbrush tool.
 */
public class AC_BlockTriggerPushable extends AC_BlockTrigger {

	protected AC_BlockTriggerPushable(int blockID, int textureIndex) {
		super(blockID, textureIndex, Material.iron);
		this.setBlockName("triggerPushable");
	}

	/**
	 * Check if a neighboring Pushable Block matches this block's color.
	 */
	public void onNeighborBlockChange(World world, int x, int y, int z, int neighborID) {
		if(neighborID == AC_Blocks.blockPushable.blockID) {
			// A pushable block was placed/moved next to us
			// Check all 6 adjacent positions for matching pushable blocks
			if(hasMatchingPushable(world, x, y, z)) {
				onTriggerActivated(world, x, y, z);
			} else {
				onTriggerDeactivated(world, x, y, z);
			}
		}
	}

	private boolean hasMatchingPushable(World world, int x, int y, int z) {
		int myColor = world.getBlockMetadata(x, y, z);
		int[][] offsets = {{1,0,0},{-1,0,0},{0,1,0},{0,-1,0},{0,0,1},{0,0,-1}};
		for(int[] off : offsets) {
			int nx = x + off[0], ny = y + off[1], nz = z + off[2];
			if(world.getBlockId(nx, ny, nz) == AC_Blocks.blockPushable.blockID) {
				if(world.getBlockMetadata(nx, ny, nz) == myColor) {
					return true;
				}
			}
		}
		return false;
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		return null;
	}

	public boolean isCollidable() {
		return false;
	}
}
