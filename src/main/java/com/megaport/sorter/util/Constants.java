package com.megaport.sorter.util;

public final class Constants {

	private Constants () {}
	
	public static final String REGEX_FILE_LINE_STRUCTURE = "([A-Za-z])+,([\\s+]?[A-Za-z]+)";
	public static final String REGEX_FILE_NAME = "[^\\\\/:*?\"<>|\\r\\n]+$";
	public static final String REGEX_VALIDATE_FILENAME_EXTENSION = "^[\\w,\\s-]+\\.[A-Za-z]{3}$";
}
