package net.minecraft.src;

public class AC_ItemTriggerStick extends Item {

	public AC_ItemTriggerStick(int itemId) {
		super(itemId);
		this.maxStackSize = 1;
		this.setItemName("triggerStick");
	}

	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side) {
		if(!world.multiplayerWorld) {
			int blockId = world.getBlockId(x, y, z);
			Block block = Block.blocksList[blockId];
			if(block instanceof AC_IBlockTriggerable) {
				AC_IBlockTriggerable triggerable = (AC_IBlockTriggerable)block;
				if(player.isSneaking()) {
					triggerable.onTriggerDeactivated(world, x, y, z);
					player.addChatMessage("Trigger deactivated.");
				} else {
					triggerable.onTriggerActivated(world, x, y, z);
					player.addChatMessage("Trigger activated!");
				}
				return true;
			} else {
				player.addChatMessage("Not a triggerable block.");
			}
		}
		return false;
	}
}
