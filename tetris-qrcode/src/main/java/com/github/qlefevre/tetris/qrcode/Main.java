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
 * @author dsdsystem
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
