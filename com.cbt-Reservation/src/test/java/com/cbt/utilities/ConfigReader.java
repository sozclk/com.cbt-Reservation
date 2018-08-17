package com.cbt.utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {
	
	private static Properties configfile;

	static {
		try {
			String path = "configuration.properties";
			FileInputStream input = new FileInputStream(path);
			configfile = new Properties();
			configfile.load(input);
			input.close();

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public static String getProperties(String keyName) {
		return configfile.getProperty(keyName);
	}


}
