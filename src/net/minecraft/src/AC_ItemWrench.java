package net.minecraft.src;

public class AC_ItemWrench extends Item {

	public AC_ItemWrench(int itemId) {
		super(itemId);
		this.maxStackSize = 1;
		this.setItemName("wrench");
	}

	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side) {
		if(!world.multiplayerWorld) {
			int blockId = world.getBlockId(x, y, z);
			int meta = world.getBlockMetadata(x, y, z);

			// Rotate block (cycle metadata)
			meta = (meta + 1) % 16;
			world.setBlockMetadataWithNotify(x, y, z, meta);

			Block block = Block.blocksList[blockId];
			String name = block != null ? block.getBlockName() : "unknown";
			player.addChatMessage(name + " meta: " + meta);
		}
		return true;
	}
}
