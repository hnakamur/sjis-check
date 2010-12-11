package com.example.sjis_check;

import java.io.UnsupportedEncodingException;

public class SjisUtil {
	private static final String SJIS = "SJIS";

	public static boolean isSjis(String text) {
		try {
			return text.equals(new String(text.getBytes(SJIS), SJIS));
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
}
