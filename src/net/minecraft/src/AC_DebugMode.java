package net.minecraft.src;

public class AC_DebugMode {
	public static boolean editMode = false;
	public static boolean showTriggerAreas = false;
	public static boolean showBlockInfo = true;

	public static void toggleEditMode() {
		editMode = !editMode;
		showTriggerAreas = editMode;
	}

	public static boolean isEditMode() {
		return editMode;
	}
}
