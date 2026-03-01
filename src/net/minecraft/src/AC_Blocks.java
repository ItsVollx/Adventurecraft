package net.minecraft.src;

public class AC_Blocks {
	// Block IDs 150-161
	public static Block blockMessage;
	public static Block blockScript;
	public static Block blockCamera;
	public static Block blockDoor;
	public static Block blockSpawn;
	public static Block blockTeleport;
	public static Block blockStorage;
	public static Block blockTimer;
	public static Block blockMusic;
	public static Block blockWeather;
	public static Block blockEffect;
	public static Block blockTrigger;

	public static void init() {
		// Texture indices: using distinctive vanilla textures as placeholders
		// 74 = jukebox side, 26 = sponge, 35 = bookshelf, 49 = glass
		// 66 = spawner, 31 = lapis, 51 = redstone ore, 64 = iron block
		blockMessage  = new AC_BlockMessage(150, 74).setHardness(1.0F).setResistance(10.0F);
		blockScript   = new AC_BlockScript(151, 35).setHardness(1.0F).setResistance(10.0F);
		blockCamera   = new AC_BlockCamera(152, 26).setHardness(1.0F).setResistance(10.0F);
		blockDoor     = new AC_BlockTriggeredDoor(153, 44).setHardness(1.0F).setResistance(10.0F);
		blockSpawn    = new AC_BlockSpawn(154, 65).setHardness(1.0F).setResistance(10.0F);
		blockTeleport = new AC_BlockTeleport(155, 31).setHardness(1.0F).setResistance(10.0F);
		blockStorage  = new AC_BlockStorage(156, 27).setHardness(1.0F).setResistance(10.0F);
		blockTimer    = new AC_BlockTimer(157, 51).setHardness(1.0F).setResistance(10.0F);
		blockMusic    = new AC_BlockMusic(158, 74).setHardness(1.0F).setResistance(10.0F);
		blockWeather  = new AC_BlockWeather(159, 66).setHardness(1.0F).setResistance(10.0F);
		blockEffect   = new AC_BlockEffect(160, 89).setHardness(1.0F).setResistance(10.0F);
		blockTrigger  = new AC_BlockTrigger(161, 55, Material.iron).setHardness(1.0F).setResistance(10.0F);

		// Register ItemBlocks for creative inventory
		for(int i = 150; i <= 161; i++) {
			if(Block.blocksList[i] != null && Item.itemsList[i] == null) {
				Item.itemsList[i] = new ItemBlock(i - 256);
			}
		}

		System.out.println("[AdventureCraft] Registered " + 12 + " blocks (IDs 150-161)");
	}
}
