package net.minecraft.src;

/**
 * NPC Path Block - waypoint marker for NPC pathfinding.
 * NPCs follow paths defined by these blocks.
 * Visible only in Debug Mode, invisible in Adventure Mode.
 */
public class AC_BlockNPCPath extends Block {

	protected AC_BlockNPCPath(int blockID, int textureIndex) {
		super(blockID, textureIndex, Material.glass);
		this.setBlockName("npcPath");
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return true;
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		return null; // No collision
	}

	public boolean isCollidable() {
		return false;
	}
}
