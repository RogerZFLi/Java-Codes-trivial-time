/**
 * File Name: Results.java
 * Author: Roger Li
 * Course: CST8284_300_Object-Oriented Programming (Java)
 * Assignment: #2
 * Date: 2018-04-18
 * Professor: David B Houtman
 * Purpose: This File has a class which stores the result of the trivia game.
 * Class list:
 */
package cst8284.triviatime;

/**
 * This class is to store the result of the trivia game.
 * @author Roger Li
 * @version 1.0
 * @since jdk1.8.0_161
 */

public class Results {
	/** Stores the accumulated points by answering questions correctly*/
	private static int score = 0; 
	/**
	 * Sets score as given value
	 * @param scr - given current score
	 */
	public static void setCurrentScr(int scr) {
		score = scr;
	}
	/**
	 * Returns current score
	 * @return an integer as current score
	 */
	public static int getCurrentScr() {
		return score;
	}
	
	/**
	 * Resets the current score to 0
	 */
	public static void resetScr() {
		setCurrentScr(0);
	}
	

}
