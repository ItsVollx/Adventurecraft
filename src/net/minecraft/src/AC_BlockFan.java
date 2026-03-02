package net.minecraft.src;

/**
 * Fan Block - a triggerable block that pushes entities with air current.
 * Comes in two formats: off-until-triggered and on-until-triggered.
 * Uses same texture as Note Blocks and Jukeboxes.
 * Pushes entities ~2.5 blocks up (7 with umbrella), 6 blocks horizontally.
 * As of r341, can push mobs through liquid blocks.
 */
public class AC_BlockFan extends Block implements AC_IBlockTriggerable {

	private boolean defaultOn;

	protected AC_BlockFan(int blockID, int textureIndex, boolean defaultOn) {
		super(blockID, textureIndex, Material.wood);
		this.defaultOn = defaultOn;
		this.setBlockName("fan");
		this.setHardness(1.0F);
		this.setResistance(10.0F);
	}

	public void onTriggerActivated(World world, int x, int y, int z) {
		world.setBlockMetadataWithNotify(x, y, z, defaultOn ? 0 : 1);
	}

	public void onTriggerDeactivated(World world, int x, int y, int z) {
		world.setBlockMetadataWithNotify(x, y, z, defaultOn ? 1 : 0);
	}

	public void reset(World world, int x, int y, int z) {
		world.setBlockMetadataWithNotify(x, y, z, defaultOn ? 1 : 0);
	}

	public void onBlockAdded(World world, int x, int y, int z) {
		if(defaultOn) {
			world.setBlockMetadataWithNotify(x, y, z, 1);
		}
	}

	public boolean isOpaqueCube() {
		return true;
	}

	public boolean renderAsNormalBlock() {
		return true;
	}
}
