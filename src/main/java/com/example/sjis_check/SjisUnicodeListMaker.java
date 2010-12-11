package com.example.sjis_check;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class SjisUnicodeListMaker {
	private static final String SJIS = "SJIS";
	private static final String OUTPUT_FILENAME = "sjisUnicodeList.txt";
	private static final int UNDEFINED = 0xfffd;
	private static final int SINGLE_QUOTE = 0x27;
	private static final int BACK_SLASH = 0x5c;
	private static final boolean WRITE_CODEPOINT = true;

	private static void makeList() {
		try {
			PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(OUTPUT_FILENAME), SJIS));
			try {
				/*
				 * sjis one byte characters 95 + 63 = 158 code points
				 */
				for (int c = 0x20; c <= 0x7e; ++c) { // printable ASCII (95 code
														// points)
					processSingleByteCharacter(writer, (byte) c);
				}
				for (int c = 0xa1; c <= 0xdf; ++c) { // halfwidth katakana (63 code
														// points)
					processSingleByteCharacter(writer, (byte) c);
				}
	
				/*
				 * sjis two byte characters (31 + 29) * 188 = 11280 code points area.
				 * 6879 actual characters mapped in this area.
				 */
				for (int hi = 0x81; hi <= 0x9f; ++hi) {
					for (int lo = 0x40; lo <= 0xfc; ++lo) {
						if (lo == 0x7f) {
							continue;
						}
						int cp = processDoubleByteCharacter(writer, (byte) hi, (byte) lo);
						if (cp == UNDEFINED) {
							continue;
						}
					}
				}
				for (int hi = 0xe0; hi <= 0xfc; ++hi) {
					for (int lo = 0x40; lo <= 0xfc; ++lo) {
						if (lo == 0x7f) {
							continue;
						}
						int cp = processDoubleByteCharacter(writer, (byte) hi, (byte) lo);
						if (cp == UNDEFINED) {
							continue;
						}
					}
				}
			} finally {
				writer.close();
			}
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	private static int processSingleByteCharacter(PrintWriter writer, byte b) throws UnsupportedEncodingException {
		byte[] buf = new byte[1];
		buf[0] = b;
		String s = new String(buf, SJIS);
		int cp = toUnicodeCodePoint(buf);
		if (cp != UNDEFINED) {
			if (WRITE_CODEPOINT) {
				writeCodePoint(writer, cp, b, 2, s);
			} else {
				writeCharacter(writer, cp, b, 2, s);
			}
		}
		return cp;
	}

	private static int processDoubleByteCharacter(PrintWriter writer, byte hi, byte lo) throws UnsupportedEncodingException {
		byte[] buf = new byte[2];
		buf[0] = hi;
		buf[1] = lo;
		String s = new String(buf, SJIS);
		int cp = (int) s.charAt(0);
		if (cp != UNDEFINED) {
			int sjis = hi << 8 | lo;
			if (WRITE_CODEPOINT) {
				writeCodePoint(writer, cp, sjis, 4, s);
			} else {
				writeCharacter(writer, cp, sjis, 4, s);
			}
		}
		return cp;
	}

	private static int toUnicodeCodePoint(byte[] sjisBuf) throws UnsupportedEncodingException {
		String s = new String(sjisBuf, SJIS);
		return (int) s.charAt(0);
	}

	private static void writeCodePoint(PrintWriter writer, int codePoint, int sjis, int sjisLen, String text) {
		writer.println("0x" + toHex(codePoint, 4) + ", // " + toHex(sjis, sjisLen)
				+ " (" + text + ")");
	}

	private static void writeCharacter(PrintWriter writer, int codePoint, int sjis, int sjisLen, String text) {
		String charcterLiteral;
		switch (codePoint) {
		case SINGLE_QUOTE:
			charcterLiteral = "'\\''";
			break;
		case BACK_SLASH:
			charcterLiteral = "'\\\\'";
			break;
		default:
			charcterLiteral = "'\\u" + toHex(codePoint, 4) + "'";
			break;
		}
		writer.println(charcterLiteral + ", // " + toHex(sjis, sjisLen)
				+ " (" + text + ")");
	}

	private static String toHex(int cp, int length) {
		return leftPad(Integer.toHexString(cp), length, "0");
	}

	private static String leftPad(String text, int length, String padding) {
		int origLen = text.length();
		if (origLen >= length) {
			return text;
		}
		StringBuilder sb = new StringBuilder();
		int padLen = length - origLen;
		while (sb.length() < padLen) {
			sb.append(padding);
		}
		sb.append(text);
		return sb.toString();
	}

	public static void main(String[] args) {
		makeList();
	}
}
