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
package com.github.qlefevre.tetris.qrcode;

import com.github.qlefevre.tetris.qrcode.encode.Matrix;
import com.github.qlefevre.tetris.qrcode.encode.MatrixToImageWriter;
import com.github.qlefevre.tetris.qrcode.pattern.tetris.TetrisPatternStrategy;
import java.nio.file.Paths;

/**
 *
 * @author Quentin Lefèvre
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("SimpleQrcodeGenerator DEBUT");

		try {
			final String data = "google.fr";

			// encode
			final Matrix matrix = Encoder.encode(data);

			TetrisPatternStrategy patternStrategy = new TetrisPatternStrategy(matrix);
			patternStrategy.applyStrategy();

			// write in a file
			MatrixToImageWriter.writeToPath(matrix, Paths.get(args[0]));

			System.out.println("SimpleQrcodeGenerator FIN");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
