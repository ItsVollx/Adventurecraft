package net.minecraft.src;

public class AC_ItemBrush extends Item {

	public AC_ItemBrush(int itemId) {
		super(itemId);
		this.maxStackSize = 1;
		this.setItemName("brush");
	}

	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side) {
		if(!world.multiplayerWorld) {
			int meta = world.getBlockMetadata(x, y, z);
			meta = (meta + 1) % 16;
			world.setBlockMetadataWithNotify(x, y, z, meta);
			player.addChatMessage("Metadata: " + meta);
		}
		return true;
	}
}
