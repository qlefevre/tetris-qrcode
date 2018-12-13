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
 *
 */
package com.github.qlefevre.tetris.qrcode.encode;

/**
 * MirrorMatrix is ​​a wrapper to apply horizontal and/or vertical transforms
 *
 * @author Quentin Lefèvre
 */
public class MirrorMatrix extends Matrix {

	private Matrix matrix;
	boolean verticalTransform;
	boolean horizontalTransform;

	/**
	 * Constructor
	 * 
	 * @param matrix the matrix to transform
	 * @param horizontalTransform applies an horizontal transform
	 * @param verticalTransform applies an vertical transform
	 */
	public MirrorMatrix(Matrix matrix, boolean horizontalTransform, boolean verticalTransform) {
		this.matrix = matrix;
		this.horizontalTransform = horizontalTransform;
		this.verticalTransform = verticalTransform;
	}

	/**
	 * Return height
	 * 
	 * @return height
	 */
	@Override
	public int getHeight() {
		return matrix.getHeight();
	}

	/**
	 * Return width
	 * 
	 * @return width
	 */
	@Override
	public int getWidth() {
		return matrix.getWidth();
	}

	/**
	 * Get the given value at x,y position
	 * 
	 * @return the given value at x,y position
	 */
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

	/**
	 * Set the given value at x,y position
	 */
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
