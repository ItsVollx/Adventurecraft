package net.minecraft.src;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class AC_Mod {
	private static boolean initialized = false;

	public static void init(RenderEngine renderEngine) {
		if(initialized) {
			return;
		}
		initialized = true;

		System.out.println("=== " + AC_Version.getFullVersion() + " ===");
		System.out.println("[AdventureCraft] Initializing...");

		// Register blocks
		AC_Blocks.init();

		// Register items
		AC_Items.init();

		// Register TileEntity
		TileEntity.addMapping(AC_TileEntityData.class, "ACData");

		// Register entities
		EntityList.addMapping(AC_EntityBoomerang.class, "Boomerang", 200);
		EntityList.addMapping(AC_EntityNPC.class, "NPC", 201);
		EntityList.addMapping(AC_EntityHook.class, "Hook", 202);

		// Register entity renderers (must call setRenderManager since we register after RenderManager's constructor loop)
		Render boomerangRenderer = new AC_RenderBoomerang();
		boomerangRenderer.setRenderManager(RenderManager.instance);
		RenderManager.instance.entityRenderMap.put(AC_EntityBoomerang.class, boomerangRenderer);

		Render npcRenderer = new RenderBiped(new ModelBiped(), 0.5F);
		npcRenderer.setRenderManager(RenderManager.instance);
		RenderManager.instance.entityRenderMap.put(AC_EntityNPC.class, npcRenderer);

		Render hookRenderer = new AC_RenderHook();
		hookRenderer.setRenderManager(RenderManager.instance);
		RenderManager.instance.entityRenderMap.put(AC_EntityHook.class, hookRenderer);

		// Register custom textures from terrain2.png and ac_items.png
		registerTextures(renderEngine);

		System.out.println("[AdventureCraft] Initialization complete!");
	}

	private static void registerTextures(RenderEngine renderEngine) {
		try {
			// Load terrain2.png for block textures
			BufferedImage terrain2 = ImageIO.read(AC_Mod.class.getResourceAsStream("/terrain2.png"));
			// Load terrain3.png for Effect block texture
			BufferedImage terrain3 = ImageIO.read(AC_Mod.class.getResourceAsStream("/terrain3.png"));

			// Load ACBin's terrain.png which has custom tiles baked in at positions
			// 166, 167, 184, 185, 200 that aren't in vanilla terrain.png
			BufferedImage acbinTerrain = null;
			try {
				java.io.File acbinJar = new java.io.File("lib/ACBin.jar");
				if(acbinJar.exists()) {
					java.util.jar.JarFile jar = new java.util.jar.JarFile(acbinJar);
					java.util.jar.JarEntry entry = jar.getJarEntry("terrain.png");
					if(entry != null) {
						acbinTerrain = ImageIO.read(jar.getInputStream(entry));
						System.out.println("[AdventureCraft] Loaded ACBin terrain.png for baked tile extraction");
					}
					jar.close();
				}
			} catch(Exception e) {
				System.out.println("[AdventureCraft] WARNING: Could not load ACBin terrain.png: " + e.getMessage());
			}

			if(terrain2 != null) {
				// Map AC blocks to their correct source tiles in terrain2.png
				// Verified against original ACBin bytecode decompilation
				int[][] terrain2Mappings = {
					// { atlasOffset, srcTileIndex }
					{ 0,  7 },  // Message         -> terrain2 tile 7
					{ 1, 15 },  // Script          -> terrain2 tile 15
					{ 2,  6 },  // Camera          -> terrain2 tile 6
					// Door (offset 3) -> terrain3 tile 208
					{ 4,  0 },  // Spawn           -> terrain2 tile 0
					{ 5, 13 },  // Teleport        -> terrain2 tile 13
					{ 6, 11 },  // Storage         -> terrain2 tile 11
					{ 7,  8 },  // Timer           -> terrain2 tile 8
					{ 8,  9 },  // Music           -> terrain2 tile 9
					{ 9,  5 },  // Weather         -> terrain2 tile 5
					// Effect (offset 10) -> terrain3 tile 244
					{11,  1 },  // Trigger         -> terrain2 tile 1
					{12, 10 },  // Darkness        -> terrain2 tile 10
					{13, 14 },  // Lightbulb       -> terrain2 tile 14
					{14, 12 },  // HealDamage      -> terrain2 tile 12
					{15,  2 },  // Trigger Inverter-> terrain2 tile 2
					{16,  3 },  // Trigger Memory  -> terrain2 tile 3
					// RedstonePower (offset 17) -> uses vanilla terrain.png tile 185 directly
					// RedstoneTrigger (offset 18) -> terrain3 tile 228
					{19,  4 },  // Clip            -> terrain2 tile 4
					// TriggerPushable (offset 20) -> terrain3 tile 213
					// NPC Path (offset 21) -> terrain3 tile 247
					// Spikes (offset 22) -> terrain3 tile 246
					// Pushable (offset 23) -> terrain3 tile 212
					{24, 49 },  // Store           -> terrain2 tile 49
					// CrackedStone (no offset) -> uses vanilla terrain.png tile 167 directly
					// CrackedCobble (no offset) -> uses vanilla terrain.png tile 166 directly
					// BossDoor (offset 27) -> terrain3 tile 210
					// LockedDoor (offset 28) -> terrain3 tile 208
				};
				for(int[] mapping : terrain2Mappings) {
					renderEngine.registerTextureFX(new AC_StaticTextureFX(AC_Blocks.TEX_BASE + mapping[0], 0, terrain2, mapping[1]));
				}
				System.out.println("[AdventureCraft] Loaded " + terrain2Mappings.length + " block textures from terrain2.png");
			} else {
				System.out.println("[AdventureCraft] WARNING: terrain2.png not found!");
			}

			// terrain3.png block textures - verified against ACBin bytecode
			if(terrain3 != null) {
				int[][] terrain3Mappings = {
					// { atlasOffset, srcTileIndex }
					{ 3, 208 },  // Triggered Door   -> terrain3 tile 208
					{10, 244 },  // Effect           -> terrain3 tile 244
					{18, 228 },  // Redstone Trigger -> terrain3 tile 228
					{20, 213 },  // Trigger Pushable -> terrain3 tile 213
					{21, 247 },  // NPC Path         -> terrain3 tile 247
					{22, 246 },  // Spikes           -> terrain3 tile 246
					{23, 212 },  // Pushable         -> terrain3 tile 212
					{27, 210 },  // Boss Door        -> terrain3 tile 210
					{28, 208 },  // Locked Door      -> terrain3 tile 208
					{29, 245 },  // URL Block        -> terrain3 tile 245
				};
				for(int[] mapping : terrain3Mappings) {
					renderEngine.registerTextureFX(new AC_StaticTextureFX(AC_Blocks.TEX_BASE + mapping[0], 0, terrain3, mapping[1]));
				}
				System.out.println("[AdventureCraft] Loaded " + terrain3Mappings.length + " block textures from terrain3.png");
			} else {
				System.out.println("[AdventureCraft] WARNING: terrain3.png not found!");
			}

			// ACBin terrain.png baked tiles - these are custom tiles at vanilla positions
			if(acbinTerrain != null) {
				int[][] acbinTerrainMappings = {
					// { atlasOffset, srcTileInACBinTerrain }
					{17, 185 },  // Redstone Power     -> ACBin terrain tile 185
					{25, 167 },  // Cracked Stone      -> ACBin terrain tile 167
					{26, 166 },  // Cracked Cobblestone-> ACBin terrain tile 166
					{30, 184 },  // Fan (ON)           -> ACBin terrain tile 184
					{31, 200 },  // Fan (OFF)          -> ACBin terrain tile 200
				};
				for(int[] mapping : acbinTerrainMappings) {
					renderEngine.registerTextureFX(new AC_StaticTextureFX(AC_Blocks.TEX_BASE + mapping[0], 0, acbinTerrain, mapping[1]));
				}
				System.out.println("[AdventureCraft] Loaded " + acbinTerrainMappings.length + " block textures from ACBin terrain.png");
			}

			// Item textures: use original positions already baked into ACBin's gui/items.png
			// No runtime injection needed - icon indices match original atlas positions
			System.out.println("[AdventureCraft] Item textures use original ACBin atlas positions");
		} catch(Exception e) {
			System.out.println("[AdventureCraft] ERROR loading textures: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
