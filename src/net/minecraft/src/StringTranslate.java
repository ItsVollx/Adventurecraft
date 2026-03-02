package net.minecraft.src;

import java.io.IOException;
import java.util.Properties;

public class StringTranslate {
	private static StringTranslate instance = new StringTranslate();
	private Properties translateTable = new Properties();

	private StringTranslate() {
		try {
			java.io.InputStream langStream = StringTranslate.class.getResourceAsStream("/lang/en_US.lang");
			if(langStream != null) {
				this.translateTable.load(langStream);
				langStream.close();
			} else {
				System.err.println("[StringTranslate] WARNING: /lang/en_US.lang not found!");
			}
			java.io.InputStream statsStream = StringTranslate.class.getResourceAsStream("/lang/stats_US.lang");
			if(statsStream != null) {
				this.translateTable.load(statsStream);
				statsStream.close();
			}
		} catch (IOException var2) {
			var2.printStackTrace();
		}
		System.out.println("[StringTranslate] Loaded " + this.translateTable.size() + " translation entries");
		// Debug: verify AC entries
		String testKey = "item.boomerang.name";
		String testVal = this.translateTable.getProperty(testKey, "NOT_FOUND");
		System.out.println("[StringTranslate] Test: " + testKey + " = " + testVal);
	}

	public static StringTranslate getInstance() {
		return instance;
	}

	public String translateKey(String var1) {
		return this.translateTable.getProperty(var1, var1);
	}

	public String translateKeyFormat(String var1, Object... var2) {
		String var3 = this.translateTable.getProperty(var1, var1);
		return String.format(var3, var2);
	}

	public String translateNamedKey(String var1) {
		return this.translateTable.getProperty(var1 + ".name", "");
	}
}
