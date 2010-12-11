package com.example.sjis_check;

public class App {
	private static final int ITERATION_COUNT = 100;

	public static void runSjisUtil() {
		long startTime = System.nanoTime();

		for (int it = 0; it < ITERATION_COUNT; ++it) {
			boolean[] results = new boolean[0x10000];
			char[] cbuf = new char[1];
			for (int c = 0x0000; c <= 0xffff; ++c) {
				cbuf[0] = (char) c;
				String s = new String(cbuf);
				results[c] = SjisUtil.isSjis(s);
			}
		}
		long endTime = System.nanoTime();
		double elapsed = (endTime - startTime) / 1e6;
		System.out.println("runSjisUtil            elapsed:" + elapsed + "(ms)");
	}

	public static void runSjisByte() {
		long startTime = System.nanoTime();

		for (int it = 0; it < ITERATION_COUNT; ++it) {
			boolean[] results = new boolean[0x10000];
			char[] cbuf = new char[1];
			for (int c = 0x0000; c <= 0xffff; ++c) {
				cbuf[0] = (char) c;
				String s = new String(cbuf);
				results[c] = SjisByte.isSjis(s);
			}
		}

		long endTime = System.nanoTime();
		double elapsed = (endTime - startTime) / 1e6;
		System.out.println("runSjisByte            elapsed:" + elapsed + "(ms)");
	}

	public static void runSjisCodePointSet() {
		//initialize
		long startTime = System.nanoTime();
		SjisCodePointSet.isSjis("");
		long endTime = System.nanoTime();
		double elapsed = (endTime - startTime) / 1e6;
		System.out.println("runSjisCodePointSet       init:" + elapsed + "(ms)");
		
		startTime = System.nanoTime();

		for (int it = 0; it < ITERATION_COUNT; ++it) {
			boolean[] results = new boolean[0x10000];
			char[] cbuf = new char[1];
			for (int c = 0x0000; c <= 0xffff; ++c) {
				cbuf[0] = (char) c;
				String s = new String(cbuf);
				results[c] = SjisCodePointSet.isSjis(s);
			}
		}

		endTime = System.nanoTime();
		elapsed = (endTime - startTime) / 1e6;
		System.out.println("runSjisCodePointSet    elapsed:" + elapsed + "(ms)");
	}

	public static void runSjisCharacterSet() {
		//initialize
		long startTime = System.nanoTime();
		SjisCharacterSet.isSjis("");
		long endTime = System.nanoTime();
		double elapsed = (endTime - startTime) / 1e6;
		System.out.println("runSjisCharacterSet       init:" + elapsed + "(ms)");

		startTime = System.nanoTime();

		for (int it = 0; it < ITERATION_COUNT; ++it) {
			boolean[] results = new boolean[0x10000];
			char[] cbuf = new char[1];
			for (int c = 0x0000; c <= 0xffff; ++c) {
				cbuf[0] = (char) c;
				String s = new String(cbuf);
				results[c] = SjisCharacterSet.isSjis(s);
			}
		}

		endTime = System.nanoTime();
		elapsed = (endTime - startTime) / 1e6;
		System.out.println("runSjisCharacterSet    elapsed:" + elapsed + "(ms)");
	}

	public static void runSjisCodePointBitSet() {
		//initialize
		long startTime = System.nanoTime();
		SjisCodePointBitSet.isSjis("");
		long endTime = System.nanoTime();
		double elapsed = (endTime - startTime) / 1e6;
		System.out.println("runSjisCodePointBitSet    init:" + elapsed + "(ms)");

		startTime = System.nanoTime();

		for (int it = 0; it < ITERATION_COUNT; ++it) {
			boolean[] results = new boolean[0x10000];
			char[] cbuf = new char[1];
			for (int c = 0x0000; c <= 0xffff; ++c) {
				cbuf[0] = (char) c;
				String s = new String(cbuf);
				results[c] = SjisCodePointBitSet.isSjis(s);
			}
		}

		endTime = System.nanoTime();
		elapsed = (endTime - startTime) / 1e6;
		System.out.println("runSjisCodePointBitSet elapsed:" + elapsed + "(ms)");
	}

	public static void runSjisCodePointTable() {
		//initialize
		long startTime = System.nanoTime();
		SjisCodePointTable.isSjis("");
		long endTime = System.nanoTime();
		double elapsed = (endTime - startTime) / 1e6;
		System.out.println("runSjisCodePointTable     init:" + elapsed + "(ms)");

		startTime = System.nanoTime();

		for (int it = 0; it < ITERATION_COUNT; ++it) {
			boolean[] results = new boolean[0x10000];
			char[] cbuf = new char[1];
			for (int c = 0x0000; c <= 0xffff; ++c) {
				cbuf[0] = (char) c;
				String s = new String(cbuf);
				results[c] = SjisCodePointTable.isSjis(s);
			}
		}

		endTime = System.nanoTime();
		elapsed = (endTime - startTime) / 1e6;
		System.out.println("runSjisCodePointTable  elapsed:" + elapsed + "(ms)");
	}

	public static void main(String[] args) {
		runSjisUtil();
		runSjisByte();
		runSjisCharacterSet();
		runSjisCodePointSet();
		runSjisCodePointBitSet();
		runSjisCodePointTable();
	}
}
