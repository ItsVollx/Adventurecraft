package net.minecraft.src;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class AC_TileEntityData extends TileEntity {
	private HashMap data = new HashMap();

	public void setData(String key, String value) {
		this.data.put(key, value);
	}

	public String getData(String key) {
		return (String)this.data.get(key);
	}

	public String getData(String key, String defaultValue) {
		String val = (String)this.data.get(key);
		return val != null ? val : defaultValue;
	}

	public int getIntData(String key, int defaultValue) {
		String val = (String)this.data.get(key);
		if(val != null) {
			try {
				return Integer.parseInt(val);
			} catch(NumberFormatException e) {
			}
		}
		return defaultValue;
	}

	public double getDoubleData(String key, double defaultValue) {
		String val = (String)this.data.get(key);
		if(val != null) {
			try {
				return Double.parseDouble(val);
			} catch(NumberFormatException e) {
			}
		}
		return defaultValue;
	}

	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		NBTTagCompound acData = nbt.getCompoundTag("ACData");
		if(acData != null) {
			Iterator iter = acData.func_28110_c().iterator();
			while(iter.hasNext()) {
				NBTBase tag = (NBTBase)iter.next();
				if(tag instanceof NBTTagString) {
					this.data.put(tag.getKey(), ((NBTTagString)tag).stringValue);
				}
			}
		}
	}

	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		NBTTagCompound acData = new NBTTagCompound();
		Iterator iter = this.data.entrySet().iterator();
		while(iter.hasNext()) {
			Map.Entry entry = (Map.Entry)iter.next();
			acData.setString((String)entry.getKey(), (String)entry.getValue());
		}
		nbt.setCompoundTag("ACData", acData);
	}
}
