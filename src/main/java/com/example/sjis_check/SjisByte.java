package com.example.sjis_check;

import java.io.UnsupportedEncodingException;

public class SjisByte {
	private static final String SJIS = "SJIS";

	public static final boolean isSjis(String s) {
		byte[] buf;
		try {
			buf = new String(s).getBytes(SJIS);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		int pos = 0;
		for (int i = 0; i < buf.length; ++i) {
			int b = buf[i] & 0xff;
			if (0x20 <= b && b <= 0x7e) { // ASCII printable
				if (b == 0x3f) { // '?'
					if (s.charAt(pos) != '?') {
						return false;
					}
				}
				++pos;
				continue;
			}
			if ((0x81 <= b && b <= 0x9f) || (0xe0 <= b && b <= 0xfc)) { // Lead byte
				b = buf[++i] & 0xff;
				if ((0x40 <= b && b <= 0x7e) || (0x80 <= b && b <= 0xfc)) { // Trail byte
					++pos;
					continue;
				}
			}
			return false;
		}
		return true; 
	}
}
