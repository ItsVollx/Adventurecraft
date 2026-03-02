package net.minecraft.src;

public class AC_ItemHookshot extends Item {

	public AC_ItemHookshot(int itemId) {
		super(itemId);
		this.maxStackSize = 1;
		this.setMaxDamage(256);
		this.setItemName("hookshot");
	}

	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if(!world.multiplayerWorld) {
			AC_EntityHook hook = new AC_EntityHook(world, player);
			world.entityJoinedWorld(hook);
			world.playSoundAtEntity(player, "random.bow", 1.0F, 0.5F);
			stack.damageItem(1, player);
		}
		return stack;
	}
}
