package net.minecraft.src;

public class AC_ItemNpcStick extends Item {

	public AC_ItemNpcStick(int itemId) {
		super(itemId);
		this.maxStackSize = 1;
		this.setItemName("npcStick");
	}

	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side) {
		if(!world.multiplayerWorld) {
			if(side == 0) y--;
			if(side == 1) y++;
			if(side == 2) z--;
			if(side == 3) z++;
			if(side == 4) x--;
			if(side == 5) x++;

			AC_EntityNPC npc = new AC_EntityNPC(world);
			npc.setPosition((double)x + 0.5, (double)y, (double)z + 0.5);
			world.entityJoinedWorld(npc);
			player.addChatMessage("NPC spawned.");
		}
		return true;
	}
}
