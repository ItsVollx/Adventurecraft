package net.minecraft.src;

public class AC_BlockSpawn extends AC_BlockTrigger {

	public AC_BlockSpawn(int blockID, int textureIndex) {
		super(blockID, textureIndex, Material.iron);
		this.setBlockName("spawn");
	}

	public void onTriggerActivated(World world, int x, int y, int z) {
		super.onTriggerActivated(world, x, y, z);
		AC_TileEntityData data = this.getACData(world, x, y, z);
		String entityType = "Pig";
		int count = 1;
		if(data != null) {
			entityType = data.getData("entity", "Pig");
			count = data.getIntData("count", 1);
		}

		for(int i = 0; i < count; i++) {
			Entity entity = EntityList.createEntityInWorld(entityType, world);
			if(entity != null) {
				double px = (double)x + 0.5 + (world.rand.nextDouble() - 0.5);
				double py = (double)y + 1.0;
				double pz = (double)z + 0.5 + (world.rand.nextDouble() - 0.5);
				entity.setPosition(px, py, pz);
				world.entityJoinedWorld(entity);
			}
		}
	}

	public boolean blockActivated(World world, int x, int y, int z, EntityPlayer player) {
		if(AC_DebugMode.isEditMode()) {
			AC_TileEntityData data = this.getACData(world, x, y, z);
			if(data != null) {
				String current = data.getData("entity", "Pig");
				String[] entities = {"Pig", "Cow", "Chicken", "Sheep", "Wolf", "Creeper", "Zombie", "Skeleton", "Spider", "Slime"};
				int idx = 0;
				for(int i = 0; i < entities.length; i++) {
					if(entities[i].equals(current)) {
						idx = (i + 1) % entities.length;
						break;
					}
				}
				data.setData("entity", entities[idx]);
				player.addChatMessage("Spawn: " + entities[idx]);
			}
			return true;
		}
		return false;
	}
}
