/**
 * 
 */
package com.github.qlefevre.tetris.qrcode.pattern;

/**
 * @author quentin
 *
 */
public class Pattern {

	public static int PATTERN_DEFAULT = 1;
	public static int PATTERN_1 = 10;
	public static int PATTERN_2 = 20;
	public static int PATTERN_3 = 30;
	public static int PATTERN_4 = 40;
	public static int PATTERN_5 = 50;

	public static int PATTERN_6 = 60;
	public static int PATTERN_7 = 70;

	public static int[] PATTERNS = new int[] { PATTERN_1, PATTERN_2, PATTERN_4, PATTERN_5, PATTERN_6, PATTERN_7 };

	private final int[][] positions;

	private final int value;

	public Pattern(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4, int value) {
		this.positions = new int[][] { { x1, y1 }, { x2, y2 }, { x3, y3 }, { x4, y4 } };
		this.value = value;
	}

	public int[][] getPositions() {
		return positions;
	}

	public int getValue() {
		return value;
	}

}
