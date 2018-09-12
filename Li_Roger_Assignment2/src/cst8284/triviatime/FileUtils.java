/**
 * File Name: FileUtils.java
 * Author: Roger Li
 * Course: CST8284_300_Object-Oriented Programming (Java)
 * Assignment: #2
 * Date: 2018-04-18
 * Professor: David B Houtman
 * Purpose: This File has a class which retrieves the file that contains the trivia question/answer information.
 * Class list:
 */
package cst8284.triviatime;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

/**
 * This class is to retrieve the file that contains the trivia question/answer information.
 * @author Roger Li
 * @version 1.0
 * @see javafx.stage.FileChooser
 * @see javafx.stage.FileChooser.ExtensionFilter
 * @see java.io.EOFException
 * @see java.io.File
 * @see java.io.FileInputStream
 * @see java.io.IOException
 * @see java.io.ObjectInputStream
 * @see java.util.ArrayList
 * @since jdk1.8.0_161
 */

public class FileUtils {
	/** a ArrayList that contains QA elements*/
	private static ArrayList<QA> qaAL; 
	
	/**
	 * Sets the QA list by opening the default game file
	 * @param absPath - the absolute path of the game file
	 */
	public static void setQAArrayList(String absPath) {
		qaAL = new ArrayList<QA>();
		if (fileExists(absPath)) {
			try {
				//FileinputStream to read the file from the given path
				FileInputStream fis = new FileInputStream(absPath);
				//ObjectInputStream to read the content of file.
				ObjectInputStream ois = new ObjectInputStream(fis);
				//to store QA elements read from the given file into the QA ArrayList
				while (true) 
					qaAL.add((QA) (ois.readObject()));
				//ois.close();
			} catch (EOFException e) {}
			catch (IOException | ClassNotFoundException e) {
				e.getStackTrace();
			} 
		}
		else 
			qaAL = null;
	}
	
	/**
	 * Sets the QA list by choosing a game file from any desired paths.
	 */
	
	public static void setQAArrayList() {
		qaAL = new ArrayList<QA>();
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Trivia File");
		fileChooser.getExtensionFilters().addAll(
		     new ExtensionFilter("TrivialTime Files", "*.trivia"),
		     new ExtensionFilter("All Files", "*.*"));
		//File object stores the game file chosen by user
		File f= fileChooser.showOpenDialog(Controls.getStage());
		if (fileExists(f))
			setQAArrayList(f.getAbsolutePath());
		
	}
	/**
	 * Returns a QA list read from the chosen file
	 * @return QA list
	 */
	public static ArrayList <QA> getQAArrayList() {
		return qaAL;
	}
	/**
	 * Returns true if given file exists, is a file, readable and not empty
	 * @param f - given file
	 * @return  a boolean value representing whether the given file fits the requirement or not
	 */
	public static boolean fileExists(File f) {
		return (f != null && f.exists() && f.isFile() && f.canRead() && (f.length() > 2));
	}
	/**
	 * Returns true if given file exists under given absolute path
	 * @param s - absolute path of given file
	 * @return a boolean value representing whether the given file under given path fits the requirement or not
	 */
	public static boolean fileExists(String s) {
		if (s !=null) 
			return (fileExists(new File(s)));
		else
			return false;
	}
	/**
	 * Returns the absolute path of given file
	 * @param f - given file
	 * @return  a string represents the absolute path of given file
	 */
	public static String getAbsPath(File f) {
		return f.getAbsolutePath();
	}
}
