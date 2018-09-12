/**
 * File Name: QA.java
 * Author: Roger Li
 * Course: CST8284_300_Object-Oriented Programming (Java)
 * Assignment: #2
 * Date: 2018-04-18
 * Professor: David B Houtman
 * Purpose: This File has a class that defines the format of the question/answer in the trivia file.
 * Class list:
 */
package cst8284.triviatime;

/**
 * This class defines the format of the question/answer in the trivia file.
 * @author Roger Li
 * @version 1.0
 * @since jdk1.8.0_161
 */

public class QA extends QARequirements{
	/** 
	 * Fields of QA elements of the trivia file
	 */
	private String question, category, explanation; 
	/** 
	 * Fields of QA elements of the trivia file
	 */
	private int difficulty, points, correctAnswer;
	/** 
	 * Fields of QA elements of the trivia file
	 */
	private String[] answers;
	/** 
	 * Fields of QA elements of the trivia file
	 */
	private boolean result;
	/**
	 * Creates a new QA instance with given specifications
	 * @param question - question extracted from the trivia file
	 * @param answers - answer list extracted from the trivia file
	 * @param category - category info of the extracted question
	 * @param explanation - explanation of extracted question answer
	 * @param difficulty - difficulty info of the extracted question
	 * @param points - points info of the extracted question
	 * @param correctAnswer - the number of potential correct answer
	 */
	public QA(String question, String[] answers, String category, String explanation, int difficulty, int points, int correctAnswer) {
		setQuestion(question);
		setAnswers(answers);
		setCategory(category);
		setExplanation(explanation);
		setDifficulty(difficulty);
		setPoints(points);
		setCorrectAnswerNumber(correctAnswer);
		
	}
	
	@Override
	
	public String getQuestion() {
		return question;
	}

	@Override
	
	public void setQuestion(String question) {
		this.question = question;
	}

	@Override
	public String[] getAnswers() {
		return answers;
	}

	@Override
	public void setAnswers(String[] answers) {
		for (int i = 0; i <answers.length; i++)
			this.answers[i] = answers[i];
	}

	@Override
	public String getExplanation() {
		return explanation;
	}

	@Override
	public void setExplanation(String question) {
		this.explanation = question;
	}

	@Override
	public String getCategory() {
		return category;
	}

	@Override
	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public int getDifficulty() {
		return difficulty;
	}

	@Override
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

	@Override
	public int getPoints() {
		return points;
	}

	@Override
	public void setPoints(int points) {
		this.points = points;
	}

	@Override
	public int getCorrectAnswerNumber() {
		return correctAnswer;
	}

	@Override
	public void setCorrectAnswerNumber(int correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	@Override
	public boolean isCorrect() {
		return result;
	}

	@Override
	public void setResult(boolean b) {
		this.result = b;
	}

}
