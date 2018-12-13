/*
 *
 */
package com.github.qlefevre.tetris.qrcode.encode;

/**
 *
 * @author
 */
public class MirrorMatrix extends Matrix {

	private Matrix matrix;
	boolean verticalTransform;
	boolean horizontalTransform;

	public MirrorMatrix(Matrix matrix, boolean horizontalTransform, boolean verticalTransform) {
		this.matrix = matrix;
		this.horizontalTransform = horizontalTransform;
		this.verticalTransform = verticalTransform;
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
		int transX = x;
		int transY = y;
		if (horizontalTransform) {
			transX = getWidth() - 1 - transX;
		}
		if (verticalTransform) {
			transY = getHeight() - 1 - transY;
		}

		return matrix.get(transX, transY);
	}

	@Override
	public void set(int x, int y, int value) {
		int transX = x;
		int transY = y;
		if (horizontalTransform) {
			transX = getWidth() - 1 - transX;
		}
		if (verticalTransform) {
			transY = getHeight() - 1 - transY;
		}
		matrix.set(transX, transY, value);
	}

}
