package net.minecraft.src;

public class AC_BlockScript extends AC_BlockTrigger {

	public AC_BlockScript(int blockID, int textureIndex) {
		super(blockID, textureIndex, Material.iron);
		this.setBlockName("acScript");
	}

	public void onTriggerActivated(World world, int x, int y, int z) {
		super.onTriggerActivated(world, x, y, z);
		AC_TileEntityData data = this.getACData(world, x, y, z);
		String script = "";
		if(data != null) {
			script = data.getData("script", "");
		}

		// Execute simple built-in commands
		if(script.length() > 0) {
			this.executeScript(world, x, y, z, script);
		}
	}

	private void executeScript(World world, int x, int y, int z, String script) {
		String[] commands = script.split(";");
		for(int i = 0; i < commands.length; i++) {
			String cmd = commands[i].trim();
			if(cmd.startsWith("msg ")) {
				String message = cmd.substring(4);
				EntityPlayer player = world.getClosestPlayer((double)x, (double)y, (double)z, 64.0);
				if(player != null) {
					player.addChatMessage(message);
				}
			} else if(cmd.startsWith("time ")) {
				try {
					long time = Long.parseLong(cmd.substring(5).trim());
					world.setWorldTime(time);
				} catch(NumberFormatException e) {
				}
			} else if(cmd.startsWith("sound ")) {
				String sound = cmd.substring(6).trim();
				world.playSoundEffect((double)x + 0.5, (double)y + 0.5, (double)z + 0.5, sound, 1.0F, 1.0F);
			} else if(cmd.startsWith("spawn ")) {
				String entityName = cmd.substring(6).trim();
				Entity entity = EntityList.createEntityInWorld(entityName, world);
				if(entity != null) {
					entity.setPosition((double)x + 0.5, (double)y + 1.0, (double)z + 0.5);
					world.entityJoinedWorld(entity);
				}
			} else if(cmd.startsWith("effect ")) {
				String effect = cmd.substring(7).trim();
				for(int j = 0; j < 10; j++) {
					double px = (double)x + world.rand.nextDouble();
					double py = (double)y + world.rand.nextDouble();
					double pz = (double)z + world.rand.nextDouble();
					world.spawnParticle(effect, px, py, pz, 0.0, 0.0, 0.0);
				}
			} else if(cmd.startsWith("setblock ")) {
				try {
					String[] parts = cmd.substring(9).trim().split(" ");
					if(parts.length >= 4) {
						int bx = Integer.parseInt(parts[0]);
						int by = Integer.parseInt(parts[1]);
						int bz = Integer.parseInt(parts[2]);
						int id = Integer.parseInt(parts[3]);
						world.setBlockWithNotify(bx, by, bz, id);
					}
				} catch(Exception e) {
				}
			} else if(cmd.startsWith("explode")) {
				world.createExplosion((Entity)null, (double)x + 0.5, (double)y + 0.5, (double)z + 0.5, 4.0F);
			}
		}
	}

	public boolean blockActivated(World world, int x, int y, int z, EntityPlayer player) {
		if(AC_DebugMode.isEditMode()) {
			AC_TileEntityData data = this.getACData(world, x, y, z);
			if(data != null) {
				String current = data.getData("script", "(empty)");
				player.addChatMessage("Script: " + current);
				player.addChatMessage("Commands: msg, time, sound, spawn, effect, setblock, explode");
				player.addChatMessage("Separate with ; for multiple commands");
			}
			return true;
		}
		return false;
	}
}
