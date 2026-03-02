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

	// Icon indices match original ACBin.jar positions in gui/items.png
	// Textures are pre-baked in the original items.png atlas - no runtime injection needed

	public static void init() {
		itemBomb        = new AC_ItemBomb(400).setIconIndex(150);
		itemBoomerang   = new AC_ItemBoomerang(401).setIconIndex(144);
		itemBrush       = new AC_ItemBrush(402).setIconIndex(225);
		itemCursor      = new AC_ItemCursor(403).setIconIndex(224);
		itemEraser      = new AC_ItemEraser(404).setIconIndex(226);
		itemHammer      = new AC_ItemHammer(405).setIconIndex(228);
		itemHookshot    = new AC_ItemHookshot(406).setIconIndex(163);
		itemLantern     = new AC_ItemLantern(407).setIconIndex(180);
		itemNpcStick    = new AC_ItemNpcStick(408).setIconIndex(53);
		itemPaintBucket = new AC_ItemPaintBucket(409).setIconIndex(227);
		itemPaste       = new AC_ItemPaste(410).setIconIndex(231);
		itemPistol      = new AC_ItemPistol(411).setIconIndex(192);
		itemPowerGlove  = new AC_ItemPowerGlove(412).setIconIndex(177);
		itemQuill       = new AC_ItemQuill(413).setIconIndex(229);
		itemRifle       = new AC_ItemRifle(414).setIconIndex(193);
		itemShotgun     = new AC_ItemShotgun(415).setIconIndex(194);
		itemTriggerStick= new AC_ItemTriggerStick(416).setIconIndex(53);
		itemUmbrella    = new AC_ItemUmbrella(417).setIconIndex(179);
		itemWrench      = new AC_ItemWrench(418).setIconIndex(230);

		System.out.println("[AdventureCraft] Registered " + 19 + " items (IDs 400-418)");
	}
}
