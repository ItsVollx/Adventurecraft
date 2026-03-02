package net.minecraft.src;

public class AC_Blocks {
	// Block IDs 150-183
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
	public static Block blockDarkness;
	public static Block blockLightbulb;
	public static Block blockHealDamage;
	// New blocks
	public static Block blockTriggerInverter;
	public static Block blockTriggerMemory;
	public static Block blockRedstonePower;
	public static Block blockRedstoneTrigger;
	public static Block blockClip;
	public static Block blockTriggerPushable;
	public static Block blockNPCPath;
	public static Block blockSpikes;
	public static Block blockPushable;
	public static Block blockStore;
	public static Block blockCrackedStone;
	public static Block blockCrackedCobblestone;
	public static Block blockBossDoor;
	public static Block blockLockedDoor;
	public static Block blockFan;
	public static Block blockMobSpawner;
	public static Block blockFanOn;
	public static Block blockUrl;

	// Decorative blocks (IDs 205-241) - render directly from terrain2/terrain3 atlases
	public static Block pillarStone;
	public static Block pillarMetal;
	public static Block plant1;
	public static Block tree;
	public static Block transparent1;
	public static Block transparent2;
	public static Block solid1;
	public static Block solid2;
	public static Block solid3;
	public static Block solid4;
	public static Block halfStep1;
	public static Block halfStep2;
	public static Block halfStep3;
	public static Block table;
	public static Block chair1;
	public static Block chair2;
	public static Block chair3;
	public static Block chair4;
	public static Block rope1;
	public static Block rope2;
	public static Block chain;
	public static Block ladder1;
	public static Block ladder2;
	public static Block ladder3;
	public static Block ladder4;
	public static Block lights1;
	public static Block plant2;
	public static Block plant3;
	public static Block overlay1;
	public static Block stairs1;
	public static Block stairs2;
	public static Block stairs3;
	public static Block stairs4;
	public static Block slopes1;
	public static Block slopes2;
	public static Block slopes3;
	public static Block slopes4;

	// Texture indices in terrain.png atlas - tiles injected from terrain2.png at runtime
	// Row 14 indices 237-238 are used by lava animated textures, so use row 13 instead
	public static final int TEX_BASE = 208; // Row 13 in terrain.png atlas (safe: 208-236)

	public static void init() {
		// Texture sources verified against original ACBin bytecode decompilation
		// terrain2 = AC custom atlas #2, terrain3 = AC custom atlas #3
		// Some blocks use vanilla terrain.png tiles directly (baked into AC's terrain.png)
		blockMessage  = new AC_BlockMessage(150, TEX_BASE + 0).setHardness(1.0F).setResistance(10.0F);   // terrain2 tile 7  (peach)
		blockScript   = new AC_BlockScript(151, TEX_BASE + 1).setHardness(1.0F).setResistance(10.0F);    // terrain2 tile 15 (red)
		blockCamera   = new AC_BlockCamera(152, TEX_BASE + 2).setHardness(1.0F).setResistance(10.0F);    // terrain2 tile 6  (gray)
		blockDoor     = new AC_BlockTriggeredDoor(153, TEX_BASE + 3).setHardness(1.0F).setResistance(10.0F); // terrain3 tile 208 (green border)
		blockSpawn    = new AC_BlockSpawn(154, TEX_BASE + 4).setHardness(1.0F).setResistance(10.0F);     // terrain2 tile 0  (cyan)
		blockTeleport = new AC_BlockTeleport(155, TEX_BASE + 5).setHardness(1.0F).setResistance(10.0F);  // terrain2 tile 13 (brown)
		blockStorage  = new AC_BlockStorage(156, TEX_BASE + 6).setHardness(1.0F).setResistance(10.0F);   // terrain2 tile 11 (green)
		blockTimer    = new AC_BlockTimer(157, TEX_BASE + 7).setHardness(1.0F).setResistance(10.0F);     // terrain2 tile 8  (purple)
		blockMusic    = new AC_BlockMusic(158, TEX_BASE + 8).setHardness(1.0F).setResistance(10.0F);     // terrain2 tile 9  (mint green)
		blockWeather  = new AC_BlockWeather(159, TEX_BASE + 9).setHardness(1.0F).setResistance(10.0F);   // terrain2 tile 5  (cyan-blue)
		blockEffect   = new AC_BlockEffect(160, TEX_BASE + 10).setHardness(1.0F).setResistance(10.0F);   // terrain3 tile 244 (maroon)
		blockTrigger  = new AC_BlockTrigger(161, TEX_BASE + 11, Material.iron).setHardness(1.0F).setResistance(10.0F); // terrain2 tile 1 (orange)
		blockDarkness  = new AC_BlockDarkness(162, TEX_BASE + 12).setHardness(0.5F).setResistance(5.0F);  // terrain2 tile 10 (black/white)
		blockLightbulb  = new AC_BlockLightbulb(163, TEX_BASE + 13).setHardness(0.5F).setResistance(5.0F); // terrain2 tile 14 (blue/teal)
		blockHealDamage = new AC_BlockHealDamage(164, TEX_BASE + 14).setHardness(1.0F).setResistance(10.0F); // terrain2 tile 12 (green/red)

		// New blocks (IDs 165-180)
		blockTriggerInverter    = new AC_BlockTriggerInverter(165, TEX_BASE + 15).setHardness(1.0F).setResistance(10.0F);        // terrain2 tile 2 (yellow)
		blockTriggerMemory      = new AC_BlockTriggerMemory(166, TEX_BASE + 16).setHardness(1.0F).setResistance(10.0F);          // terrain2 tile 3 (magenta)
		blockRedstonePower      = new AC_BlockRedstonePower(167, TEX_BASE + 17).setHardness(5.0F).setResistance(10.0F);     // ACBin terrain tile 185
		blockRedstoneTrigger    = new AC_BlockRedstoneTrigger(168, TEX_BASE + 18).setHardness(1.0F).setResistance(10.0F);        // terrain3 tile 228
		blockClip               = new AC_BlockClip(169, TEX_BASE + 19).setHardness(1.0F).setResistance(10.0F);                  // terrain2 tile 4 (blue-gray)
		blockTriggerPushable    = new AC_BlockTriggerPushable(170, TEX_BASE + 20).setHardness(1.0F).setResistance(10.0F);        // terrain3 tile 213
		blockNPCPath            = new AC_BlockNPCPath(171, TEX_BASE + 21).setHardness(1.0F).setResistance(10.0F);                // terrain3 tile 245 (yellow)
		blockSpikes             = new AC_BlockSpikes(172, TEX_BASE + 22);                                                        // terrain3 tile 246
		blockPushable           = new AC_BlockPushable(173, TEX_BASE + 23);                                                      // terrain3 tile 212
		blockStore              = new AC_BlockStore(174, TEX_BASE + 24);                                                         // terrain2 tile 49
		blockCrackedStone       = new AC_BlockCrackedStone(175, TEX_BASE + 25);                                            // ACBin terrain tile 167
		blockCrackedCobblestone = new AC_BlockCrackedCobblestone(176, TEX_BASE + 26);                                       // ACBin terrain tile 166
		blockBossDoor           = new AC_BlockBossDoor(177, TEX_BASE + 27);                                                      // terrain3 tile 210
		blockLockedDoor         = new AC_BlockLockedDoor(178, TEX_BASE + 28);                                                    // terrain3 tile 208
		blockFan                = new AC_BlockFan(179, TEX_BASE + 30, true).setHardness(5.0F).setResistance(10.0F);        // ACBin terrain tile 184 (fan ON)
		blockMobSpawner         = new AC_BlockMobSpawner(180, 65);                                                               // vanilla spawner texture
		blockFanOn              = new AC_BlockFan(181, TEX_BASE + 31, false).setHardness(5.0F).setResistance(10.0F);       // ACBin terrain tile 200 (fan OFF)
		blockUrl                = new AC_BlockUrl(182, TEX_BASE + 29).setHardness(5.0F).setResistance(10.0F);                     // terrain3 tile 245

		// Register ItemBlocks for functional blocks
		for(int i = 150; i <= 182; i++) {
			if(Block.blocksList[i] != null && Item.itemsList[i] == null) {
				Item.itemsList[i] = new ItemBlock(i - 256);
			}
		}

		// ===== DECORATIVE BLOCKS (IDs 205-241) =====
		// These render directly from terrain2.png (textureNum=2) and terrain3.png (textureNum=3)
		// Tile indices are positions within those atlases (16x16 grid, row*16+col)

		// terrain2 blocks (textureNum=2)
		pillarStone   = new AC_BlockPillar(205, 32).setTextureNum(2).setHardness(5.0F).setStepSound(Block.soundStoneFootstep).setBlockName("pillarStone").setSubTypes(16);
		pillarMetal   = new AC_BlockPillar(206, 80).setTextureNum(2).setHardness(5.0F).setStepSound(Block.soundMetalFootstep).setBlockName("pillarMetal").setSubTypes(16);
		plant1        = new AC_BlockPlant(207, 112).setTextureNum(2).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setBlockName("flower").setSubTypes(16);
		tree          = new AC_BlockTree(208, 128).setTextureNum(2).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setBlockName("sapling").setSubTypes(16);
		transparent1  = new AC_BlockTransparent(209, 144).setTextureNum(2).setHardness(5.0F).setStepSound(Block.soundGlassFootstep).setBlockName("glass").setSubTypes(16);
		transparent2  = new AC_BlockTransparent(210, 160).setTextureNum(2).setHardness(5.0F).setStepSound(Block.soundMetalFootstep).setBlockName("cage").setSubTypes(16);
		solid1        = new AC_BlockSolid(211, 176).setTextureNum(2).setHardness(5.0F).setStepSound(Block.soundStoneFootstep).setBlockName("stone").setSubTypes(16);
		solid2        = new AC_BlockSolid(212, 192).setTextureNum(2).setHardness(5.0F).setStepSound(Block.soundStoneFootstep).setBlockName("stone").setSubTypes(16);
		solid3        = new AC_BlockSolid(213, 208).setTextureNum(2).setHardness(5.0F).setStepSound(Block.soundStoneFootstep).setBlockName("stone").setSubTypes(16);
		solid4        = new AC_BlockSolid(214, 224).setTextureNum(2).setHardness(5.0F).setStepSound(Block.soundWoodFootstep).setBlockName("planks").setSubTypes(16);
		halfStep1     = new AC_BlockHalfStep(215, 240).setTextureNum(2).setHardness(5.0F).setStepSound(Block.soundStoneFootstep).setBlockName("halfStep").setSubTypes(16);

		// terrain3 blocks (textureNum=3)
		halfStep2     = new AC_BlockHalfStep(216, 0).setTextureNum(3).setHardness(5.0F).setStepSound(Block.soundStoneFootstep).setBlockName("halfStep").setSubTypes(16);
		halfStep3     = new AC_BlockHalfStep(217, 16).setTextureNum(3).setHardness(5.0F).setStepSound(Block.soundWoodFootstep).setBlockName("halfStepWood").setSubTypes(16);
		table         = new AC_BlockTable(218, 32).setTextureNum(3).setHardness(5.0F).setStepSound(Block.soundWoodFootstep).setBlockName("table").setSubTypes(16);
		chair1        = new AC_BlockChair(219, 64).setTextureNum(3).setHardness(5.0F).setStepSound(Block.soundWoodFootstep).setBlockName("chair").setSubTypes(4);
		chair2        = new AC_BlockChair(220, 68).setTextureNum(3).setHardness(5.0F).setStepSound(Block.soundWoodFootstep).setBlockName("chair").setSubTypes(4);
		chair3        = new AC_BlockChair(221, 72).setTextureNum(3).setHardness(5.0F).setStepSound(Block.soundMetalFootstep).setBlockName("chair").setSubTypes(4);
		chair4        = new AC_BlockChair(222, 76).setTextureNum(3).setHardness(5.0F).setStepSound(Block.soundMetalFootstep).setBlockName("chair").setSubTypes(4);
		rope1         = new AC_BlockRope(223, 96).setTextureNum(3).setHardness(0.5F).setStepSound(Block.soundClothFootstep).setBlockName("rope").setSubTypes(15);
		rope2         = new AC_BlockRope(224, 101).setTextureNum(3).setHardness(0.5F).setStepSound(Block.soundClothFootstep).setBlockName("rope").setSubTypes(15);
		chain         = new AC_BlockChain(225, 106).setTextureNum(3).setHardness(5.0F).setStepSound(Block.soundMetalFootstep).setBlockName("chain").setSubTypes(9);
		ladder1       = new AC_BlockLadderSubtypes(226, 112).setTextureNum(3).setHardness(2.0F).setStepSound(Block.soundWoodFootstep).setBlockName("ladder").setSubTypes(4);
		ladder2       = new AC_BlockLadderSubtypes(227, 116).setTextureNum(3).setHardness(2.0F).setStepSound(Block.soundWoodFootstep).setBlockName("ladder").setSubTypes(4);
		ladder3       = new AC_BlockLadderSubtypes(228, 120).setTextureNum(3).setHardness(2.0F).setStepSound(Block.soundMetalFootstep).setBlockName("ladder").setSubTypes(4);
		ladder4       = new AC_BlockLadderSubtypes(229, 124).setTextureNum(3).setHardness(2.0F).setStepSound(Block.soundMetalFootstep).setBlockName("ladder").setSubTypes(4);
		lights1       = new AC_BlockPlant(230, 128).setTextureNum(3).setHardness(0.5F).setStepSound(Block.soundGlassFootstep).setBlockName("torch").setSubTypes(14);
		plant2        = new AC_BlockPlant(231, 144).setTextureNum(3).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setBlockName("flower").setSubTypes(16);
		plant3        = new AC_BlockPlant(232, 160).setTextureNum(3).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setBlockName("flower").setSubTypes(16);
		overlay1      = new AC_BlockOverlay(233, 176).setTextureNum(3).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setBlockName("overlay").setSubTypes(7);
		stairs1       = new AC_BlockStairMulti(234, 192, Block.cobblestone).setTextureNum(3).setBlockName("stairs").setSubTypes(4);
		stairs2       = new AC_BlockStairMulti(235, 196, Block.cobblestone).setTextureNum(3).setBlockName("stairs").setSubTypes(4);
		stairs3       = new AC_BlockStairMulti(236, 200, Block.planks).setTextureNum(3).setBlockName("stairs").setSubTypes(4);
		stairs4       = new AC_BlockStairMulti(237, 204, Block.planks).setTextureNum(3).setBlockName("stairs").setSubTypes(4);
		slopes1       = new AC_BlockSlope(238, 192, Block.cobblestone).setTextureNum(3).setBlockName("slopes").setSubTypes(4);
		slopes2       = new AC_BlockSlope(239, 196, Block.cobblestone).setTextureNum(3).setBlockName("slopes").setSubTypes(4);
		slopes3       = new AC_BlockSlope(240, 200, Block.planks).setTextureNum(3).setBlockName("slopes").setSubTypes(4);
		slopes4       = new AC_BlockSlope(241, 204, Block.planks).setTextureNum(3).setBlockName("slopes").setSubTypes(4);

		// Register AC_ItemSubtypes for decorative blocks (for creative inventory subtypes)
		for(int i = 205; i <= 241; i++) {
			if(Block.blocksList[i] != null) {
				Item.itemsList[i] = new AC_ItemSubtypes(i - 256);
			}
		}

		System.out.println("[AdventureCraft] Registered 33 functional blocks (IDs 150-182) + 37 decorative blocks (IDs 205-241)");
	}
}
