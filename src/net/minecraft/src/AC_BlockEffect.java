package net.minecraft.src;

public class AC_BlockEffect extends AC_BlockTrigger {

	public AC_BlockEffect(int blockID, int textureIndex) {
		super(blockID, textureIndex, Material.iron);
		this.setBlockName("effect");
	}

	public void onTriggerActivated(World world, int x, int y, int z) {
		super.onTriggerActivated(world, x, y, z);
		AC_TileEntityData data = this.getACData(world, x, y, z);
		String effectType = "explode";
		int count = 12;
		if(data != null) {
			effectType = data.getData("effect", "explode");
			count = data.getIntData("count", 12);
		}

		for(int i = 0; i < count; i++) {
			double px = (double)x + world.rand.nextDouble();
			double py = (double)y + world.rand.nextDouble();
			double pz = (double)z + world.rand.nextDouble();
			double vx = (world.rand.nextDouble() - 0.5) * 0.5;
			double vy = world.rand.nextDouble() * 0.5;
			double vz = (world.rand.nextDouble() - 0.5) * 0.5;
			world.spawnParticle(effectType, px, py, pz, vx, vy, vz);
		}
	}

	public boolean blockActivated(World world, int x, int y, int z, EntityPlayer player) {
		if(AC_DebugMode.isEditMode()) {
			AC_TileEntityData data = this.getACData(world, x, y, z);
			if(data != null) {
				String current = data.getData("effect", "explode");
				String[] effects = {"explode", "flame", "heart", "note", "portal", "smoke", "snowballpoof", "splash", "largesmoke", "reddust", "snowshovel", "slime", "bubble"};
				int idx = 0;
				for(int i = 0; i < effects.length; i++) {
					if(effects[i].equals(current)) {
						idx = (i + 1) % effects.length;
						break;
					}
				}
				data.setData("effect", effects[idx]);
				player.addChatMessage("Effect: " + effects[idx]);
			}
			return true;
		}
		return false;
	}
}
