package net.minecraft.src;

public class AC_Mod {
	private static boolean initialized = false;

	public static void init() {
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

		// Register entity renderers
		RenderManager.instance.entityRenderMap.put(AC_EntityBoomerang.class, new AC_RenderBoomerang());
		RenderManager.instance.entityRenderMap.put(AC_EntityNPC.class, new RenderBiped(new ModelBiped(), 0.5F));
		RenderManager.instance.entityRenderMap.put(AC_EntityHook.class, new AC_RenderBoomerang());

		System.out.println("[AdventureCraft] Initialization complete!");
	}
}
