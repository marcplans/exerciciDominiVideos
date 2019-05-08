package com.videos.tools;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Tools {

	// Returns current date string formatted.
	public static String getDate() {
		LocalDateTime date = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter
				.ofPattern("yyyy/MM/dd");
		return date.format(format);
	}

	// Returns formatted exception message.
	public static String exceptionToString(Exception e) {
		return e.getClass().toString().substring(6) + "\n"
				+ e.getLocalizedMessage() + "\n";
	}

	// Returns given string with first char upper case and the rest lower case.
	public static String firstUppercase(String inputStr) {
		return inputStr.substring(0, 1).toUpperCase()
				+ inputStr.substring(1).toLowerCase();
	}

}
