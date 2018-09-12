/**
 * File Name: QARequirements.java
 * Author: Roger Li
 * Course: CST8284_300_Object-Oriented Programming (Java)
 * Assignment: #2
 * Date: 2018-04-18
 * Professor: David B Houtman
 * Purpose: This File contains a class which is the superclass of QA class, which defines the format of the question/answer in the trivia file.
 * Class list:
 */
package cst8284.triviatime;

import java.io.Serializable;

/**
 * This class is the superclass of QA class, which defines the format of the question/answer in the trivia file.
 * @author Roger Li
 * @version 1.0
 * @see java.io.Serializable
 * @since jdk1.8.0_161
 */

public abstract class QARequirements implements Serializable{
	/** UID of the trivia file*/
	public static final long serialVersionUID = 1L;
	/**
	 * Returns a string representing the content of extracted question
	 * @return the question content
	 */
	public abstract String getQuestion();
	/**
	 * 	Sets the content of the question
	 * @param question - content of the question
	 */
	public abstract void setQuestion(String question);
	/**
	 * Returns the string array of the answer list
	 * @return answer list
	 */
	public abstract String[] getAnswers();
	/**
	 * Sets the content of answer list
	 * @param answers - a string array represents answer list
	 */
	public abstract void setAnswers(String[] answers);
	/**
	 * Returns a string of explanation for the answer of the question
	 * @return the explanation
	 */
	public abstract String getExplanation();
	/**
	 * Sets explanation for the question answer
	 * @param question - explanation content
	 */
	public abstract void setExplanation(String question);
	/**
	 * Returns the category of the question
	 * @return content of the category of the question
	 */
	public abstract String getCategory();
	/**
	 * Sets the category of the question
	 * @param category - content of the category
	 */
	public abstract void setCategory(String category);
	/**
	 * Returns a integer represents the difficulty of given question
	 * @return a integer stands for difficulty level
	 */
	public abstract int getDifficulty();
	/**
	 * Sets difficulty for relative question
	 * @param difficulty - integer represents the difficulty
	 */
	public abstract void setDifficulty(int difficulty);
	/**
	 * Returns the point number of given question
	 * @return an integer as the points 
	 */
	public abstract int getPoints();
	/**
	 * Sets points number for given questions
	 * @param points - an integer stands for points
	 */
	public abstract void setPoints(int points);
	/**
	 * Returns the number of the correct answer
	 * @return an integer stands for the correct answer
	 */
	public abstract int getCorrectAnswerNumber();
	/**
	 * Sets a number for the correct answer
	 * @param correctAnswer - an integer set to stand for the correct answer
	 */
	public abstract void setCorrectAnswerNumber(int correctAnswer);
	/**
	 * return true if the result is correct
	 * @return a boolean stands for the result of the answer
	 */
	public abstract boolean isCorrect();
	/**
	 * Sets true if the answer is correct
	 * @param b - the result of the answer
	 */
	public abstract void setResult(boolean b);

}
