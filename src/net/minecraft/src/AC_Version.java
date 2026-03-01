package net.minecraft.src;

public class AC_Version {
	public static final String VERSION = "1.0";
	public static final String MC_VERSION = "Beta 1.7.3";
	public static final String NAME = "AdventureCraft";

	public static String getFullVersion() {
		return NAME + " v" + VERSION + " for " + MC_VERSION;
	}
}
