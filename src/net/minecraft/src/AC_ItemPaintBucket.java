package net.minecraft.src;

public class AC_ItemPaintBucket extends Item {

	public AC_ItemPaintBucket(int itemId) {
		super(itemId);
		this.maxStackSize = 1;
		this.setItemName("paintBucket");
	}

	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side) {
		if(!world.multiplayerWorld && AC_ItemCursor.hasPos1 && AC_ItemCursor.hasPos2) {
			int sourceMeta = world.getBlockMetadata(x, y, z);

			int minX = AC_ItemCursor.getMinX();
			int minY = AC_ItemCursor.getMinY();
			int minZ = AC_ItemCursor.getMinZ();
			int maxX = AC_ItemCursor.getMaxX();
			int maxY = AC_ItemCursor.getMaxY();
			int maxZ = AC_ItemCursor.getMaxZ();

			int count = 0;
			for(int bx = minX; bx <= maxX; bx++) {
				for(int by = minY; by <= maxY; by++) {
					for(int bz = minZ; bz <= maxZ; bz++) {
						if(world.getBlockId(bx, by, bz) != 0) {
							world.setBlockMetadataWithNotify(bx, by, bz, sourceMeta);
							count++;
						}
					}
				}
			}
			player.addChatMessage("Painted " + count + " blocks with meta " + sourceMeta);
			return true;
		}
		return false;
	}
}
