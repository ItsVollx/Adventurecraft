package net.minecraft.src;

public class AC_TriggerArea {
	public int x1, y1, z1;
	public int x2, y2, z2;
	public int targetX, targetY, targetZ;
	public boolean active = false;
	public String name;

	public AC_TriggerArea(String name, int x1, int y1, int z1, int x2, int y2, int z2) {
		this.name = name;
		this.x1 = Math.min(x1, x2);
		this.y1 = Math.min(y1, y2);
		this.z1 = Math.min(z1, z2);
		this.x2 = Math.max(x1, x2);
		this.y2 = Math.max(y1, y2);
		this.z2 = Math.max(z1, z2);
	}

	public void setTarget(int x, int y, int z) {
		this.targetX = x;
		this.targetY = y;
		this.targetZ = z;
	}

	public boolean isInsideArea(double x, double y, double z) {
		return x >= this.x1 && x <= this.x2 + 1 &&
			   y >= this.y1 && y <= this.y2 + 1 &&
			   z >= this.z1 && z <= this.z2 + 1;
	}

	public void activate(World world) {
		if(!this.active) {
			this.active = true;
			int blockId = world.getBlockId(this.targetX, this.targetY, this.targetZ);
			Block block = Block.blocksList[blockId];
			if(block instanceof AC_IBlockTriggerable) {
				((AC_IBlockTriggerable)block).onTriggerActivated(world, this.targetX, this.targetY, this.targetZ);
			}
		}
	}

	public void deactivate(World world) {
		if(this.active) {
			this.active = false;
			int blockId = world.getBlockId(this.targetX, this.targetY, this.targetZ);
			Block block = Block.blocksList[blockId];
			if(block instanceof AC_IBlockTriggerable) {
				((AC_IBlockTriggerable)block).onTriggerDeactivated(world, this.targetX, this.targetY, this.targetZ);
			}
		}
	}
}
