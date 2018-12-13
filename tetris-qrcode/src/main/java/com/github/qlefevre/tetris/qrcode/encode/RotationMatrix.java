/*
 * 2016
 */
package com.github.qlefevre.tetris.qrcode.encode;

/**
 *
 * @author dsdsystem
 */
public class RotationMatrix extends Matrix {

	private Matrix matrix;

	public RotationMatrix(Matrix matrix) {
		this.matrix = matrix;
	}

	@Override
	public int getHeight() {
		return matrix.getHeight();
	}

	@Override
	public int getWidth() {
		return matrix.getWidth();
	}

	@Override
	public byte get(int x, int y) {
		int transY = getHeight() - 1 - x;
		int transX = y;
		return matrix.get(transX, transY);
	}

	@Override
	public void set(int x, int y, int value) {
		int transY = getHeight() - 1 - x;
		int transX = y;
		matrix.set(transX, transY, value);
	}

}

