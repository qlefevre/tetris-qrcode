/*
 * 2016
 */
package com.github.qlefevre.tetris.qrcode.pattern;

import java.util.Collections;
import java.util.List;

import com.github.qlefevre.tetris.qrcode.encode.Matrix;

/**
 *
 * @author dsdsystem
 */
public abstract class AbstractPatternStrategy implements PatternStrategy {

	protected final Matrix matrix;

	public AbstractPatternStrategy(Matrix matrix) {
		this.matrix = matrix;
	}

	@Override
	public void applyStrategy() {

		// For all matrixes
		for (Matrix matrixLoc : getMatrixes()) {
			// Detect and change patterns
			for (Pattern pattern : getPatterns()) {
				for (int col = 0; col < matrixLoc.getWidth(); col++) {
					for (int row = 0; row < matrixLoc.getHeight(); row++) {
						if (detectPattern(matrixLoc, pattern, col, row)) {
							fillPattern(matrixLoc, pattern, col, row);
						}
					}
				}
			}
		}
	}

	protected boolean detectPattern(final Matrix matrix, final Pattern pattern, final int x, final int y) {
		int x1, y1;
		boolean result = true;
		for (int[] coordinate : pattern.getPositions()) {
			x1 = x + coordinate[0];
			y1 = y + coordinate[1];
			if (!(x1 < matrix.getWidth() && y1 < matrix.getHeight() && matrix.get(x1, y1) == Pattern.PATTERN_DEFAULT)) {
				result = false;
				break;
			}
		}
		return result;
	}

	protected void fillPattern(final Matrix matrix, final Pattern pattern, final int x, final int y) {
		int x1, y1;
		for (int[] coordinate : pattern.getPositions()) {
			x1 = x + coordinate[0];
			y1 = y + coordinate[1];
			if (x1 < matrix.getWidth() && y1 < matrix.getHeight() && matrix.get(x1, y1) <= Pattern.PATTERN_DEFAULT) {
				matrix.set(x1, y1, pattern.getValue());
			}
		}
	}

	protected List<Matrix> getMatrixes() {
		return Collections.singletonList(matrix);
	}

}
