package net.minecraft.src;

public interface AC_IBlockTriggerable {
	void onTriggerActivated(World world, int x, int y, int z);
	void onTriggerDeactivated(World world, int x, int y, int z);
	void reset(World world, int x, int y, int z);
}
