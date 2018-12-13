/*
 * 2016
 */
package com.github.qlefevre.tetris.qrcode;

import com.github.qlefevre.tetris.qrcode.encode.Matrix;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.encoder.QRCode;

/**
 *
 * @author dsdsystem
 */
public class Encoder {

	/**
	 * @param content
	 *            text to encode
	 * @param ecLevel
	 *            error correction level to use
	 * @return {@link QRCode} representing the encoded QR code
	 * @throws WriterException
	 *             if encoding can't succeed, because of for example invalid content
	 *             or configuration
	 */
	public static Matrix encode(String content) throws Exception {
		QRCode qr = com.google.zxing.qrcode.encoder.Encoder.encode(content, ErrorCorrectionLevel.H);
		Matrix matrix = new Matrix(qr.getMatrix());
		return matrix;
	}

}
