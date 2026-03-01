package net.minecraft.src;

public class AC_ItemEraser extends Item {

	public AC_ItemEraser(int itemId) {
		super(itemId);
		this.maxStackSize = 1;
		this.setItemName("acEraser");
	}

	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if(!world.multiplayerWorld && AC_ItemCursor.hasPos1 && AC_ItemCursor.hasPos2) {
			int minX = AC_ItemCursor.getMinX();
			int minY = AC_ItemCursor.getMinY();
			int minZ = AC_ItemCursor.getMinZ();
			int maxX = AC_ItemCursor.getMaxX();
			int maxY = AC_ItemCursor.getMaxY();
			int maxZ = AC_ItemCursor.getMaxZ();

			int count = 0;
			for(int x = minX; x <= maxX; x++) {
				for(int y = minY; y <= maxY; y++) {
					for(int z = minZ; z <= maxZ; z++) {
						if(world.getBlockId(x, y, z) != 0) {
							world.setBlockWithNotify(x, y, z, 0);
							count++;
						}
					}
				}
			}
			player.addChatMessage("Erased " + count + " blocks.");
		} else {
			player.addChatMessage("Select region with Cursor first!");
		}
		return stack;
	}
}
