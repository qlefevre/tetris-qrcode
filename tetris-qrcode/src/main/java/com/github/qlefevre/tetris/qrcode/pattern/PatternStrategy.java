
package com.github.qlefevre.tetris.qrcode.pattern;

import java.util.List;

/**
 *
 * 
 */
public interface PatternStrategy {

	/**
	 * Retourne la la liste des patterns
	 * 
	 * @return
	 */
	public List<Pattern> getPatterns();

	/**
	 * Applique la stratégie
	 */
	public void applyStrategy();
}
