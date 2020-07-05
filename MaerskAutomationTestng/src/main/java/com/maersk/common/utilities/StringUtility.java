
package com.maersk.common.utilities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

public class StringUtility {

	static Logger log = Logger.getLogger(StringUtility.class.getName());

	

	/**
	 * Extracts Numbers from String.
	 * 
	 * @param inputString
	 * @return string containing Numbers only
	 */

	public static String extractsDigits(String input) {
		final StringBuilder sb = new StringBuilder(input.length());
		for (int i = 0; i < input.length(); i++) {
			final char c = input.charAt(i);
			if (c > 47 && c < 58) {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	/**
	 * Checks whether the argument <tt>string</tt> is <code>null</code> or
	 * empty. Please note that the <tt>string</tt> is <strong>trimmed</strong>,
	 * so that a check on a string containing white spaces only will always
	 * return <code>true</code>.
	 * 
	 * @param string
	 *            the string to be checked
	 * @return <code>true</code> in case the argument <tt>string</tt> is
	 *         <code>null</code>, empty ({@link String#length()} returns 0) or
	 *         contains only white spaces (
	 *         <tt>{@link String#trim()}.length()</tt> returns 0)
	 */
	public static boolean isNullOrEmpty(String string) {
		return string == null || string.trim().length() == 0;
	}

	

	public static String generateCurrentDateAndTime() {
		SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy");// dd/MM/yyyy
		Date now = new Date();
		String strDate = sdfDate.format(now);
		return strDate;
	}

	public static List<String> getAllSubstrings(String data, String regex) {
		List<String> answer = new ArrayList<String>();
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(data);
		try {
			while (matcher.find()) {
				answer.add(matcher.group(0));
			}
		} catch (Exception e) {

		}
		return answer;
	}
}
