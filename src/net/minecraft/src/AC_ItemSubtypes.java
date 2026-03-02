package net.minecraft.src;

/**
 * ItemBlock for blocks with subtypes (decorative blocks).
 * Each damage value maps to a different block texture subtype.
 * The item icon gets its texture from the block's getBlockTextureFromSideAndMetadata.
 */
public class AC_ItemSubtypes extends ItemBlock {
	public AC_ItemSubtypes(int var1) {
		super(var1);
		this.setIconIndex(0);
		this.setHasSubtypes(true);
	}

	public int getIconFromDamage(int var1) {
		return Block.blocksList[this.shiftedIndex].getBlockTextureFromSideAndMetadata(0, var1);
	}

	public int getPlacedBlockMetadata(int var1) {
		return var1;
	}
}
