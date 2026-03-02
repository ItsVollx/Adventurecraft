package net.minecraft.src;

public class AC_BlockTimer extends AC_BlockTrigger {

	public AC_BlockTimer(int blockID, int textureIndex) {
		super(blockID, textureIndex, Material.iron);
		this.setTickOnLoad(true);
		this.setBlockName("timer");
	}

	public void onTriggerActivated(World world, int x, int y, int z) {
		super.onTriggerActivated(world, x, y, z);
		AC_TileEntityData data = this.getACData(world, x, y, z);
		int delay = 20;
		if(data != null) {
			delay = data.getIntData("delay", 20);
		}
		world.scheduleBlockUpdate(x, y, z, this.blockID, delay);
	}

	public void updateTick(World world, int x, int y, int z, java.util.Random rand) {
		int meta = world.getBlockMetadata(x, y, z);
		if(meta > 0) {
			// Timer fired - trigger connected blocks
			AC_TileEntityData data = this.getACData(world, x, y, z);
			if(data != null) {
				int targetX = data.getIntData("targetX", x);
				int targetY = data.getIntData("targetY", y + 1);
				int targetZ = data.getIntData("targetZ", z);

				int targetBlockId = world.getBlockId(targetX, targetY, targetZ);
				Block targetBlock = Block.blocksList[targetBlockId];
				if(targetBlock instanceof AC_IBlockTriggerable) {
					((AC_IBlockTriggerable)targetBlock).onTriggerActivated(world, targetX, targetY, targetZ);
				}
			}

			// Check if repeating
			if(data != null && "true".equals(data.getData("repeat", "false"))) {
				int delay = data.getIntData("delay", 20);
				world.scheduleBlockUpdate(x, y, z, this.blockID, delay);
			} else {
				world.setBlockMetadataWithNotify(x, y, z, 0);
			}
		}
	}

	public boolean blockActivated(World world, int x, int y, int z, EntityPlayer player) {
		if(AC_DebugMode.isEditMode()) {
			AC_TileEntityData data = this.getACData(world, x, y, z);
			if(data != null) {
				if(player.isSneaking()) {
					// Toggle repeat
					String repeat = data.getData("repeat", "false");
					repeat = "true".equals(repeat) ? "false" : "true";
					data.setData("repeat", repeat);
					player.addChatMessage("Timer repeat: " + repeat);
				} else {
					int delay = data.getIntData("delay", 20);
					delay += 20;
					if(delay > 200) delay = 20;
					data.setData("delay", String.valueOf(delay));
					player.addChatMessage("Timer delay: " + delay + " ticks (" + (delay / 20.0f) + "s)");
				}
			}
			return true;
		}
		return false;
	}
}
