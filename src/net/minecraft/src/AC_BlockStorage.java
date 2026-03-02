package net.minecraft.src;

public class AC_BlockStorage extends AC_BlockTrigger {

	public AC_BlockStorage(int blockID, int textureIndex) {
		super(blockID, textureIndex, Material.iron);
		this.setBlockName("storage");
	}

	public void onTriggerActivated(World world, int x, int y, int z) {
		super.onTriggerActivated(world, x, y, z);
		// When triggered, unlock this storage block (meta 1 = unlocked)
		world.setBlockMetadataWithNotify(x, y, z, 1);
	}

	public void onTriggerDeactivated(World world, int x, int y, int z) {
		super.onTriggerDeactivated(world, x, y, z);
		// When deactivated, lock this storage block (meta 0 = locked)
		world.setBlockMetadataWithNotify(x, y, z, 0);
	}

	public boolean blockActivated(World world, int x, int y, int z, EntityPlayer player) {
		int meta = world.getBlockMetadata(x, y, z);
		if(meta == 1) {
			// Unlocked - open chest GUI at this position if there's a chest nearby
			player.addChatMessage("Storage unlocked!");
			return true;
		} else if(AC_DebugMode.isEditMode()) {
			player.addChatMessage("Storage block (trigger to unlock)");
			return true;
		} else {
			player.addChatMessage("This is locked.");
			return true;
		}
	}
}
