package net.minecraft.src;

/**
 * Spikes Block - damage-dealing block that harms players on contact.
 * Deals half a heart (1 damage) of damage continuously while in contact.
 * Can be placed on floor, ceiling, or walls.
 * One of the few things that can damage the Skeleton Boss.
 */
public class AC_BlockSpikes extends Block {

	protected AC_BlockSpikes(int blockID, int textureIndex) {
		super(blockID, textureIndex, Material.iron);
		this.setBlockName("spike");
		this.setHardness(2.0F);
		this.setResistance(10.0F);
	}

	/**
	 * Called when an entity walks on / collides with this block.
	 * Deals damage to the entity.
	 */
	public void onEntityWalking(World world, int x, int y, int z, Entity entity) {
		if(entity instanceof EntityLiving) {
			entity.attackEntityFrom((Entity)null, 1); // Half heart damage
		}
	}

	/**
	 * Also check for entities inside the block's collision area.
	 */
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
		if(entity instanceof EntityLiving) {
			entity.attackEntityFrom((Entity)null, 1);
		}
	}

	public boolean isOpaqueCube() {
		return true;
	}

	public boolean renderAsNormalBlock() {
		return true;
	}
}
