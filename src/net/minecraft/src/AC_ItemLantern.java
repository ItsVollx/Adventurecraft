package net.minecraft.src;

public class AC_ItemLantern extends Item {

	public AC_ItemLantern(int itemId) {
		super(itemId);
		this.maxStackSize = 1;
		this.setMaxDamage(1200);
		this.setItemName("lantern");
	}

	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side) {
		if(!world.multiplayerWorld) {
			// Calculate placement position based on clicked face
			if(side == 0) y--;
			if(side == 1) y++;
			if(side == 2) z--;
			if(side == 3) z++;
			if(side == 4) x--;
			if(side == 5) x++;

			if(world.getBlockId(x, y, z) == 0) {
				world.setBlockWithNotify(x, y, z, Block.torchWood.blockID);
				world.playSoundEffect((double)x + 0.5, (double)y + 0.5, (double)z + 0.5, "random.pop", 0.5F, 1.0F);
				stack.damageItem(1, player);
				return true;
			}
		}
		return false;
	}
}
