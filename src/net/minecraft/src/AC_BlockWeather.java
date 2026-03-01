package net.minecraft.src;

public class AC_BlockWeather extends AC_BlockTrigger {

	public AC_BlockWeather(int blockID, int textureIndex) {
		super(blockID, textureIndex, Material.iron);
		this.setBlockName("acWeather");
	}

	public void onTriggerActivated(World world, int x, int y, int z) {
		super.onTriggerActivated(world, x, y, z);
		AC_TileEntityData data = this.getACData(world, x, y, z);
		String mode = "clear";
		if(data != null) {
			mode = data.getData("weather", "clear");
		}

		if(mode.equals("rain")) {
			world.getWorldInfo().setRaining(true);
			world.getWorldInfo().setThundering(false);
		} else if(mode.equals("thunder")) {
			world.getWorldInfo().setRaining(true);
			world.getWorldInfo().setThundering(true);
		} else {
			world.getWorldInfo().setRaining(false);
			world.getWorldInfo().setThundering(false);
		}
	}

	public boolean blockActivated(World world, int x, int y, int z, EntityPlayer player) {
		if(AC_DebugMode.isEditMode()) {
			AC_TileEntityData data = this.getACData(world, x, y, z);
			if(data != null) {
				String current = data.getData("weather", "clear");
				String next;
				if(current.equals("clear")) {
					next = "rain";
				} else if(current.equals("rain")) {
					next = "thunder";
				} else {
					next = "clear";
				}
				data.setData("weather", next);
				player.addChatMessage("Weather: " + next);
			}
			return true;
		}
		return false;
	}
}
