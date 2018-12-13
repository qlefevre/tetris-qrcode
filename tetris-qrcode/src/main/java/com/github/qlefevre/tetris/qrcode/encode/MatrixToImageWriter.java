
package com.github.qlefevre.tetris.qrcode.encode;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

public class MatrixToImageWriter {

	public static final int SPRITE_SIZE = 8;

	private static BufferedImage toBufferedImage(final Matrix byteMatrix) throws IOException {

		int size = (byteMatrix.getWidth() + 2) * SPRITE_SIZE;
		BufferedImage position = readImage("position.png");

		Map<Integer, BufferedImage> images = new HashMap<>();

		// Create image

		BufferedImage bi = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = bi.createGraphics();
		g.setPaint(new Color(0xff, 0xff, 0xff));
		g.fillRect(0, 0, bi.getWidth(), bi.getHeight());
		for (int col = 0; col < byteMatrix.getWidth(); col++) {
			for (int row = 0; row < byteMatrix.getWidth(); row++) {
				int data = byteMatrix.get(col, row);
				if (data > 0) {
					g.drawImage(getImage(images, data), col * SPRITE_SIZE + SPRITE_SIZE,
							row * SPRITE_SIZE + SPRITE_SIZE, null);
				}
			}
		}
		g.drawImage(position, 3 * SPRITE_SIZE, 3 * SPRITE_SIZE, null);
		g.drawImage(position, (byteMatrix.getWidth() - 4) * SPRITE_SIZE, 3 * SPRITE_SIZE, null);
		g.drawImage(position, 3 * SPRITE_SIZE, (byteMatrix.getHeight() - 4) * SPRITE_SIZE, null);
		g.dispose();

		return bi;
	}

	public static void writeToPath(final Matrix matrix, Path path) throws IOException {
		try (final FileOutputStream fileOutputStream = new FileOutputStream(path.toFile())) {
			BufferedImage bi = toBufferedImage(matrix);
			ImageIO.write(bi, "png", fileOutputStream);
		}
	}

	private static BufferedImage getImage(Map<Integer, BufferedImage> images, int index) throws IOException {
		BufferedImage image = images.get(index);
		if (image == null) {
			String file = "tetris" + index + ".png";
			image = readImage(file);
			images.put(index, image);
		}
		return image;
	}

	private static BufferedImage readImage(String imageFile) throws IOException {
		BufferedImage img;
		try (InputStream is = MatrixToImageWriter.class.getResourceAsStream("/" + imageFile)) {
			img = ImageIO.read(is);
		}
		return img;
	}

}
