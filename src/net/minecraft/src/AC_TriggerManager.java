package net.minecraft.src;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AC_TriggerManager {
	public static AC_TriggerManager instance = new AC_TriggerManager();
	private List triggerAreas = new ArrayList();

	public void addTriggerArea(AC_TriggerArea area) {
		this.triggerAreas.add(area);
	}

	public void removeTriggerArea(AC_TriggerArea area) {
		this.triggerAreas.remove(area);
	}

	public void clear() {
		this.triggerAreas.clear();
	}

	public void update(World world, EntityPlayer player) {
		if(player == null || world == null) {
			return;
		}

		double px = player.posX;
		double py = player.posY;
		double pz = player.posZ;

		Iterator iter = this.triggerAreas.iterator();
		while(iter.hasNext()) {
			AC_TriggerArea area = (AC_TriggerArea)iter.next();
			if(area.isInsideArea(px, py, pz)) {
				area.activate(world);
			} else {
				area.deactivate(world);
			}
		}
	}

	public List getTriggerAreas() {
		return this.triggerAreas;
	}

	public AC_TriggerArea getTriggerAreaByName(String name) {
		Iterator iter = this.triggerAreas.iterator();
		while(iter.hasNext()) {
			AC_TriggerArea area = (AC_TriggerArea)iter.next();
			if(area.name != null && area.name.equals(name)) {
				return area;
			}
		}
		return null;
	}
}
