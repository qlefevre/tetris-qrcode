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
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.encoder.QRCode;

/**
 *
 * @author Quentin Lefèvre
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
