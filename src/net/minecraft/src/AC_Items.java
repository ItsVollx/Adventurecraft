package net.minecraft.src;

public class AC_Items {
	// Raw item IDs (shiftedIndex = id + 256)
	public static Item itemBomb;
	public static Item itemBoomerang;
	public static Item itemBrush;
	public static Item itemCursor;
	public static Item itemEraser;
	public static Item itemHammer;
	public static Item itemHookshot;
	public static Item itemLantern;
	public static Item itemNpcStick;
	public static Item itemPaintBucket;
	public static Item itemPaste;
	public static Item itemPistol;
	public static Item itemPowerGlove;
	public static Item itemQuill;
	public static Item itemRifle;
	public static Item itemShotgun;
	public static Item itemTriggerStick;
	public static Item itemUmbrella;
	public static Item itemWrench;

	public static void init() {
		// Raw IDs 400+ (shiftedIndex = 656+)
		// Icon indices: using various vanilla item textures as placeholders
		// iconIndex = col + row * 16 in items.png
		itemBomb        = new AC_ItemBomb(400).setIconIndex(8 + 0 * 16);       // gunpowder
		itemBoomerang   = new AC_ItemBoomerang(401).setIconIndex(5 + 3 * 16);  // stick
		itemBrush       = new AC_ItemBrush(402).setIconIndex(5 + 1 * 16);      // gold ingot
		itemCursor      = new AC_ItemCursor(403).setIconIndex(7 + 0 * 16);     // iron ingot
		itemEraser      = new AC_ItemEraser(404).setIconIndex(13 + 1 * 16);    // flint
		itemHammer      = new AC_ItemHammer(405).setIconIndex(2 + 3 * 16);     // iron axe
		itemHookshot    = new AC_ItemHookshot(406).setIconIndex(4 + 2 * 16);   // fishing rod
		itemLantern     = new AC_ItemLantern(407).setIconIndex(5 + 0 * 16);    // torch-like
		itemNpcStick    = new AC_ItemNpcStick(408).setIconIndex(5 + 3 * 16);   // stick
		itemPaintBucket = new AC_ItemPaintBucket(409).setIconIndex(10 + 4 * 16); // bucket
		itemPaste       = new AC_ItemPaste(410).setIconIndex(11 + 4 * 16);     // water bucket
		itemPistol      = new AC_ItemPistol(411).setIconIndex(5 + 2 * 16);     // bow
		itemPowerGlove  = new AC_ItemPowerGlove(412).setIconIndex(0 + 0 * 16); // diamond
		itemQuill       = new AC_ItemQuill(413).setIconIndex(6 + 3 * 16);      // feather
		itemRifle       = new AC_ItemRifle(414).setIconIndex(5 + 2 * 16);      // bow
		itemShotgun     = new AC_ItemShotgun(415).setIconIndex(5 + 2 * 16);    // bow
		itemTriggerStick= new AC_ItemTriggerStick(416).setIconIndex(4 + 5 * 16); // blaze rod
		itemUmbrella    = new AC_ItemUmbrella(417).setIconIndex(5 + 3 * 16);   // stick
		itemWrench      = new AC_ItemWrench(418).setIconIndex(2 + 3 * 16);     // iron axe

		System.out.println("[AdventureCraft] Registered " + 19 + " items (IDs 400-418)");
	}
}
