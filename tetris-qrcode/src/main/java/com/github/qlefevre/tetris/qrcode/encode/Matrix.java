package com.github.qlefevre.tetris.qrcode.encode;

import com.google.zxing.qrcode.encoder.ByteMatrix;

public class Matrix {

	private final ByteMatrix byteMatrix;

	Matrix() {
		this.byteMatrix = null;
	}

	public Matrix(ByteMatrix byteMatrix) {
		this.byteMatrix = byteMatrix;
	}

	public int getHeight() {
		return byteMatrix.getHeight();
	}

	public int getWidth() {
		return byteMatrix.getWidth();
	}

	public byte get(int x, int y) {
		return byteMatrix.get(x, y);
	}

	public void set(int x, int y, int value) {
		byteMatrix.set(x, y, value);
	}

	@Override
	public String toString() {
		int width = getWidth();
		int height = getHeight();
		StringBuilder result = new StringBuilder(2 * width * height + 2);
		for (int y = 0; y < height; ++y) {
			for (int x = 0; x < width; ++x) {
				int val = get(x, y);
				if (val == 0) {
					result.append("  ");
				} else if (val < 10 && val > 0) {
					result.append('0').append(val);
				} else {
					result.append(val);
				}
			}
			result.append('\n');
		}
		return result.toString();
	}

	public String toStringMini() {
		int width = getWidth();
		int height = getHeight();
		StringBuilder result = new StringBuilder(2 * width * height + 2);
		for (int y = 0; y < height; ++y) {
			for (int x = 0; x < width; ++x) {
				int val = get(x, y);
				if (val == 0) {
					result.append(' ');
				} else if (val == -1) {
					result.append('P');
				} else {
					val = val / 10;
					result.append(val);
				}
			}
			result.append('\n');
		}
		return result.toString();
	}
}