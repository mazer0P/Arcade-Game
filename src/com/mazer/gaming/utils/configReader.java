package com.mazer.gaming.utils;

import java.util.ResourceBundle;

public class configReader {

	private static ResourceBundle rb = ResourceBundle.getBundle("config");
	public static String getValue(String key)
	{
		return rb.getString(key);
	}
	
}
