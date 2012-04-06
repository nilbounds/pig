/**
 * 
 */
package com.diwayou.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

/**
 * @author diwayou
 *
 */
public class Config {

	private static final Properties PROP = new Properties();;
	private static final Config INSTANCE = new Config();
	private Config() {
		InputStream in = null;
		try {
			in = ClassLoader.getSystemResourceAsStream("conf/pig.properties");
			
			PROP.load(in);
		} catch (InvalidPropertiesFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public static Config getInstance() {
		return INSTANCE;
	}
}
