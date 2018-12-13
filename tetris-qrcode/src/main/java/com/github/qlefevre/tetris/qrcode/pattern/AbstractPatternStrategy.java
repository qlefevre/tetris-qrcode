/*******************************************************************************
 * Copyright 2018 Quentin Lefèvre
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
/*
 * 2016
 */
package com.github.qlefevre.tetris.qrcode.pattern;

import java.util.Collections;
import java.util.List;

import com.github.qlefevre.tetris.qrcode.encode.Matrix;

/**
 * AbstractPatternStrategy
 *
 * @author Quentin Lefèvre
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

	/**
	 * 
	 * @param matrix
	 * @param pattern
	 * @param x
	 * @param y
	 * @return
	 */
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

	/**
	 * Return a list of matrixes to transform
	 * 
	 * @return a list of matrixes to transform
	 */
	protected List<Matrix> getMatrixes() {
		return Collections.singletonList(matrix);
	}

}
