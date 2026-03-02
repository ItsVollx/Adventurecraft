package net.minecraft.src;

public class AC_ItemBoomerang extends Item {

	public AC_ItemBoomerang(int itemId) {
		super(itemId);
		this.maxStackSize = 1;
		this.setMaxDamage(128);
		this.setItemName("boomerang");
	}

	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if(!world.multiplayerWorld) {
			AC_EntityBoomerang boomerang = new AC_EntityBoomerang(world, player);
			world.entityJoinedWorld(boomerang);
			world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F);
			stack.damageItem(1, player);
		}
		return stack;
	}
}
