package net.minecraft.src;

public class AC_BlockMusic extends AC_BlockTrigger {

	public AC_BlockMusic(int blockID, int textureIndex) {
		super(blockID, textureIndex, Material.iron);
		this.setBlockName("acMusic");
	}

	public void onTriggerActivated(World world, int x, int y, int z) {
		super.onTriggerActivated(world, x, y, z);
		AC_TileEntityData data = this.getACData(world, x, y, z);
		String sound = "note.harp";
		float pitch = 1.0F;
		if(data != null) {
			sound = data.getData("sound", "note.harp");
			pitch = (float)data.getDoubleData("pitch", 1.0);
		}

		world.playSoundEffect((double)x + 0.5, (double)y + 0.5, (double)z + 0.5, sound, 3.0F, pitch);
		world.spawnParticle("note", (double)x + 0.5, (double)y + 1.2, (double)z + 0.5, 0.0, 0.0, 0.0);
	}

	public void onTriggerDeactivated(World world, int x, int y, int z) {
		super.onTriggerDeactivated(world, x, y, z);
	}

	public boolean blockActivated(World world, int x, int y, int z, EntityPlayer player) {
		if(AC_DebugMode.isEditMode()) {
			AC_TileEntityData data = this.getACData(world, x, y, z);
			if(data != null) {
				String current = data.getData("sound", "note.harp");
				String[] sounds = {"note.harp", "note.bass", "note.bd", "note.hat", "note.snare", "note.pling", "mob.zombie", "mob.skeleton", "random.explode", "random.click"};
				int idx = 0;
				for(int i = 0; i < sounds.length; i++) {
					if(sounds[i].equals(current)) {
						idx = (i + 1) % sounds.length;
						break;
					}
				}
				data.setData("sound", sounds[idx]);
				player.addChatMessage("Sound: " + sounds[idx]);
			}
			return true;
		}
		return false;
	}
}
