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
package com.github.qlefevre.tetris.qrcode.pattern.tetris;

import com.github.qlefevre.tetris.qrcode.encode.Matrix;
import com.github.qlefevre.tetris.qrcode.encode.MirrorMatrix;
import com.github.qlefevre.tetris.qrcode.encode.RotationMatrix;
import com.github.qlefevre.tetris.qrcode.pattern.AbstractPatternStrategy;
import com.github.qlefevre.tetris.qrcode.pattern.Pattern;
import com.github.qlefevre.tetris.qrcode.pattern.PatternStrategy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TetrisPatternStrategy extends AbstractPatternStrategy implements PatternStrategy {

	public static final List<Pattern> TETRIS_PATTERNS = Arrays.asList(new Tetris3Pattern(), new Tetris1Pattern(),
			new Tetris2Pattern(), new Tetris4Pattern(), new Tetris5Pattern(), new Tetris6Pattern(),
			new Tetris7Pattern());

	public static final List<Pattern> TETRIS_PATTERNS_REARRANGE = Arrays.asList(new Tetris4Pattern(),
			new Tetris5Pattern());

	public TetrisPatternStrategy(Matrix matrix) {
		super(matrix);
	}

	@Override
	public List<Pattern> getPatterns() {
		return TETRIS_PATTERNS;
	}

	@Override
	public void applyStrategy() {
		super.applyStrategy();

		// Add protection
		protectQRCode();

		// For all matrixes
		for (Matrix matrixLoc : getMatrixes()) { // Detect and change patterns (75%)
			for (Pattern pattern : TETRIS_PATTERNS_REARRANGE) {
				for (int col = 0; col < matrixLoc.getWidth(); col++) {
					for (int row = 0; row < matrixLoc.getHeight(); row++) {
						if (detectSimilarPattern(matrixLoc, pattern, col, row)) {
							fillPattern(matrixLoc, pattern, col, row);
						}
					}
				}
			}
		}

		fillRandom();

		// Dessine les positions
		drawPosition(0, 0);
		drawPosition(matrix.getWidth() - 7, 0);
		drawPosition(0, matrix.getHeight() - 7);

		// Dessine les barres
		drawPattern3();

		System.out.println(matrix.toStringMini());

	}

	// protect timing
	// format info
	private void protectQRCode() {
		for (int i = 6; i < matrix.getHeight() - 6; i++) {
			// vertical timing
			if (matrix.get(6, i) == 0) {
				matrix.set(6, i, -1);
			}
			// horizontal timing
			if (matrix.get(i, 6) == 0) {
				matrix.set(i, 6, -1);
			}
		}
		for (int i = 0; i < matrix.getHeight(); i++) {
			// vertical format
			if (matrix.get(8, i) == 0) {
				matrix.set(8, i, -1);
			}
			// horizontal format
			if (matrix.get(i, 8) == 0) {
				matrix.set(i, 8, -1);
			}
		}

//		// version info top right
//		for (int i = matrix.getWidth() - 11; i < (matrix.getWidth() - 8); i++) {
//			for (int y = 0; y < 7; y++) {
//				if (matrix.get(i, y) == 0) {
//					matrix.set(i, y, -1);
//				}
//			}
//		}
//		// version info bottom left
//		for (int i = matrix.getHeight() - 11; i < (matrix.getHeight() - 8); i++) {
//			for (int x = 0; x < 7; x++) {
//				if (matrix.get(x, i) == 0) {
//					matrix.set(x, i, -1);
//				}
//			}
//		}

        // version horizontal
		for (int x = 0; x < matrix.getWidth(); x++) {
			for (int y = 0; y < (matrix.getHeight()/2); y++) {
				if (matrix.get(x, y) == 0) {
					matrix.set(x, y, -1);
				}
			}
		}
		// version vertical
		for (int y = 0; y < matrix.getHeight(); y++) {
			for (int x = 0; x < (matrix.getWidth()/2); x++) {
				if (matrix.get(x, y) == 0) {
					matrix.set(x, y, -1);
				}
			}
		}
	}

	private void fillRandom() {
		int max = Pattern.PATTERNS.length;
		Random random = new Random();
		for (int col = 0; col < matrix.getWidth(); col++) {
			for (int row = 0; row < matrix.getHeight(); row++) {
				if (matrix.get(row, col) == Pattern.PATTERN_DEFAULT) {
					matrix.set(row, col, Pattern.PATTERNS[random.nextInt(max)]);
				}
			}
		}
	}

	private void drawPattern3() {

		// vertical
		for (int col = 0; col < matrix.getWidth(); col++) {
			for (int row = 0; row < matrix.getHeight() - 4; row++) {
				if (matrix.get(col, row) == Pattern.PATTERN_3 && matrix.get(col, row + 1) == Pattern.PATTERN_3
						&& matrix.get(col, row + 2) == Pattern.PATTERN_3
						&& matrix.get(col, row + 3) == Pattern.PATTERN_3) {
					matrix.set(col, row, 31);
					matrix.set(col, row + 1, 32);
					matrix.set(col, row + 2, 32);
					matrix.set(col, row + 3, 33);
				}
			}

		}
		// horizontal
		for (int row = 0; row < matrix.getHeight(); row++) {
			for (int col = 0; col < matrix.getWidth() - 4; col++) {
				if (matrix.get(col, row) == Pattern.PATTERN_3 && matrix.get(col + 1, row) == Pattern.PATTERN_3
						&& matrix.get(col + 2, row) == Pattern.PATTERN_3
						&& matrix.get(col + 3, row) == Pattern.PATTERN_3) {
					matrix.set(col, row, 34);
					matrix.set(col + 1, row, 35);
					matrix.set(col + 2, row, 35);
					matrix.set(col + 3, row, 36);
				}
			}
		}
	}

	private void drawPosition(int x, int y) {
		for (int i = 0; i < 7; i++) {
			matrix.set(i + x, y, Pattern.PATTERN_3);
			matrix.set(i + x, 6 + y, Pattern.PATTERN_3);
			matrix.set(x, i + y, Pattern.PATTERN_3);
			matrix.set(6 + x, i + y, Pattern.PATTERN_3);
		}
		matrix.set(x, y, Pattern.PATTERN_7);
		matrix.set(x + 1, y, Pattern.PATTERN_7);
		matrix.set(x, y + 1, Pattern.PATTERN_7);
		matrix.set(x, y + 2, Pattern.PATTERN_7);
		matrix.set(x + 6, y + 6, Pattern.PATTERN_7);
		matrix.set(x + 5, y + 6, Pattern.PATTERN_7);
		matrix.set(x + 6, y + 5, Pattern.PATTERN_7);
		matrix.set(x + 6, y + 4, Pattern.PATTERN_7);
	}

	@Override
	protected List<Matrix> getMatrixes() {
		List<Matrix> matrixes = new ArrayList<>();
		matrixes.add(matrix);
		matrixes.add(new MirrorMatrix(matrix, true, false));
		matrixes.add(new MirrorMatrix(matrix, false, true));
		matrixes.add(new MirrorMatrix(matrix, true, true));
		matrixes.add(new RotationMatrix(matrix));
		matrixes.add(new MirrorMatrix(new RotationMatrix(matrix), true, false));
		return matrixes;
	}

	protected boolean detectSimilarPattern(final Matrix matrix, final Pattern pattern, final int x, final int y) {
		int x1, y1;
		int found = 0;
		int value;
		for (int[] coordinate : pattern.getPositions()) {
			x1 = x + coordinate[0];
			y1 = y + coordinate[1];
			if (x1 < matrix.getWidth() && y1 < matrix.getHeight()) {
				value = matrix.get(x1, y1);
				if (value == Pattern.PATTERN_DEFAULT) {
					found++;
				}
				if (value > Pattern.PATTERN_DEFAULT || value == -1) {
					return false;
				}

			}
		}
		boolean result = found == 3;
		return result;
	}

}
