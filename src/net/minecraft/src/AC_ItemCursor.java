package net.minecraft.src;

public class AC_ItemCursor extends Item {
	public static int pos1X, pos1Y, pos1Z;
	public static int pos2X, pos2Y, pos2Z;
	public static boolean hasPos1 = false;
	public static boolean hasPos2 = false;
	private static boolean nextIsPos1 = true;

	public AC_ItemCursor(int itemId) {
		super(itemId);
		this.maxStackSize = 1;
		this.setItemName("acCursor");
	}

	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side) {
		if(!world.multiplayerWorld) {
			if(nextIsPos1) {
				pos1X = x;
				pos1Y = y;
				pos1Z = z;
				hasPos1 = true;
				player.addChatMessage("Pos1: " + x + ", " + y + ", " + z);
			} else {
				pos2X = x;
				pos2Y = y;
				pos2Z = z;
				hasPos2 = true;
				player.addChatMessage("Pos2: " + x + ", " + y + ", " + z);
				if(hasPos1) {
					int sx = Math.abs(pos2X - pos1X) + 1;
					int sy = Math.abs(pos2Y - pos1Y) + 1;
					int sz = Math.abs(pos2Z - pos1Z) + 1;
					player.addChatMessage("Selection: " + sx + "x" + sy + "x" + sz + " (" + (sx * sy * sz) + " blocks)");
				}
			}
			nextIsPos1 = !nextIsPos1;
		}
		return true;
	}

	public static int getMinX() { return Math.min(pos1X, pos2X); }
	public static int getMinY() { return Math.min(pos1Y, pos2Y); }
	public static int getMinZ() { return Math.min(pos1Z, pos2Z); }
	public static int getMaxX() { return Math.max(pos1X, pos2X); }
	public static int getMaxY() { return Math.max(pos1Y, pos2Y); }
	public static int getMaxZ() { return Math.max(pos1Z, pos2Z); }
}
