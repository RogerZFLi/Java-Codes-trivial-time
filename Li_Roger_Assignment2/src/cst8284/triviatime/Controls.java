/**
 * File Name: Controls.java
 * Author: Roger Li
 * Course: CST8284_300_Object-Oriented Programming (Java)
 * Assignment: #2
 * Date: 2018-04-18
 * Professor: David B Houtman
 * Purpose: This File has a class that contains static methods related mostly to the menus, as well as the "Next Question" button 
 * that appears in the bottom right corner of the display when the questions are loaded.
 * Class list:
 */
package cst8284.triviatime;

import java.util.Collections;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * contains static methods related mostly to the menus, as well as the "Next Question" button 
 * that appears in the bottom right corner of the display when the questions are loaded.
 * @author Roger Li
 * @version 1.0
 * @see java.util.Collections
 * @see java.util.Comparator
 * @see javafx.application.Platform
 * @see javafx.event.ActionEvent
 * @see javafx.event.EventHandler
 * @see javafx.geometry.Insets
 * @see javafx.geometry.Pos
 * @see javafx.scene.control.Alert
 * @see javafx.scene.control.Alert.AlertType
 * @see javafx.scene.control.Button
 * @see javafx.scene.control.Label
 * @see javafx.scene.control.Menu
 * @see javafx.scene.control.MenuBar
 * @see javafx.scene.control.MenuItem
 * @see javafx.scene.input.KeyCode
 * @see javafx.scene.input.KeyCodeCombination
 * @see javafx.scene.input.KeyCombination
 * @see javafx.scene.layout.BorderPane
 * @see javafx.scene.layout.HBox
 * @see javafx.scene.layout.VBox
 * @see javafx.scene.paint.Color
 * @see javafx.stage.Stage
 * @since jdk1.8.0_161
 */

public class Controls {

	/**** Generic Menu/Menu Item Properties ****/
	/**A MenuItem stores actions by clicking it.*/
	private static MenuItem mnuItm; 
	/** A Menu contains multiple MenuItem objects.*/
	private static Menu mnu; 
	/** primary stage showing onto the screen.*/
	private static Stage stage; 
	/** The sequence number of questions, start from 0.*/
	private static int currentQuestion = 0; /** The sequence number of questions, start from 0.*/
	
	/***************** MenuBar *****************/
	/**
	 * Generates a menu bar with 3 menus showing at the top of the stage.
	 * Each menu includes several menu items each of which implements a action.
	 * @param stage - the stage that MenuBars being loaded and displayed onto.
	 * @return A MenuBar includes instantiated Menu objects.
	 */
	public static MenuBar getMenuBar(Stage stage) {
		//The instance of MenuBar to contain Menu objects instantiated afterward
		MenuBar mnuBar = new MenuBar(); 
		mnuBar.setPrefSize(1024, 20);
		//The Menu object that contains Menu/MenuItem objects which manipulate the game source file
		Menu file = getMenu("_File", "F"); 
		//The Menu object that contains 2 MenuItem objects that upload game file differently
		Menu newGame = getMenu("_New Game", "N"); 
		// The Menu object that contains 3 MenuItem objects that sort the sequence of questions in different way
		Menu settings = getMenu("_Settings", "S"); 
		// The Menu object that contains the MenuItem object showing the program information
		Menu help = getMenu("_Help", "H"); 
		
		newGame.getItems().addAll(
			getMnuItm("_Quick Start", "Q", (ActionEvent e)->{
			FileUtils.setQAArrayList("C:\\TriviaTime\\ComputerTrivia_Java100.trivia");
			reloadGame();
			}), 
			getMnuItm("_Choose a File", "C", (ActionEvent e)->{
				FileUtils.setQAArrayList();
				reloadGame();
			}));
		file.getItems().addAll(newGame, getMnuItm("_Exit", "X", (ActionEvent e)->Platform.exit()));
		settings.getItems().addAll(getMnuItm("_Random", "R", (ActionEvent e)->{
			if (FileUtils.getQAArrayList() !=null) {
				Collections.shuffle(FileUtils.getQAArrayList());
				reloadGame();
			}else {
				Alert loadGameAlert = new Alert(AlertType.INFORMATION);
				loadGameAlert.setContentText("You didn't start a new game");
				loadGameAlert.showAndWait();
			}
		}), getMnuItm("By _Difficulty", "D", (ActionEvent e)->{
			if (FileUtils.getQAArrayList() !=null) {
				Collections.sort(FileUtils.getQAArrayList(), (QA qa1, QA qa2)->qa1.getDifficulty() - qa2.getDifficulty());	
				reloadGame();
			}else {
				Alert loadGameAlert = new Alert(AlertType.INFORMATION);
				loadGameAlert.setContentText("You didn't start a new game");
				loadGameAlert.showAndWait();
			}
		}), getMnuItm("By _Topic", "T", (ActionEvent e)->{
			if (FileUtils.getQAArrayList() !=null) {
				Collections.sort(FileUtils.getQAArrayList(), (QA qa1, QA qa2) ->	qa1.getCategory().compareTo(qa2.getCategory()));
				reloadGame();	
			}else {
				Alert loadGameAlert = new Alert(AlertType.INFORMATION);
				loadGameAlert.setContentText("You didn't start a new game");
				loadGameAlert.showAndWait();
			}
		}));
		
		help.getItems().add(getMnuItm("_About", "A", (ActionEvent e) -> {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("About");
			alert.setHeaderText("About Trivia Time");
			alert.setContentText("Author: \n \t \t Roger Li \n \t \t Student Number: 040896855");
			alert.showAndWait();
		}));
 		mnuBar.getMenus().addAll(file, settings, help);
		setStage(stage);
		return mnuBar;
	}
	/******************* Menu ******************/
	/**
	 * Returns a Menu with the name and a accelerate key.
	 * @param mnuName - The name of the Menu object
	 * @param acKey - The accelerate key to activate the Menu object
	 * @return The Menu object defined with name and accelerate key.
	 */

	private static Menu getMenu(String mnuName, String acKey) {
		mnu = new Menu(mnuName);
		mnu.setStyle("-fx-font-size:15;");
		mnu.setAccelerator(new KeyCodeCombination(KeyCode.getKeyCode(acKey), KeyCombination.CONTROL_DOWN));
		return mnu;
	}
	
	/***************** MenuItems *****************/
	/**
	 * Returns a MenuItem with its name, accelerate key and the tasks it takes.
	 * @param mnuItmName -  The name of the MenuItem object
	 * @param acKey - The accelerate key to activate the MenuItem object
	 * @param e - The container where actions are included in
	 * @return The MenuItem object defined with name, accelerate key and actions.
	 */
	
	private static MenuItem getMnuItm(String mnuItmName, String acKey, EventHandler<ActionEvent> e) {
		mnuItm =new MenuItem(mnuItmName);
		mnuItm.setStyle("-fx-font-size:15;");
		mnuItm.setAccelerator(new KeyCodeCombination(KeyCode.getKeyCode(acKey), KeyCombination.CONTROL_DOWN));
		mnuItm.setOnAction(e);
		return mnuItm;
	}
	/**
	 * This method load a new QAPane to the primary stage, which includes a new array of QA
	 * and reset all the scores. It makes the game start over.
	 */
	private static void reloadGame() {
		currentQuestion = 0;
		Results.resetScr();
		getRP().setStyle("-fx-background-color:black;");
		if(!FileUtils.getQAArrayList().isEmpty()) {
			getRP().setCenter(new QAPane(FileUtils.getQAArrayList().get(currentQuestion)).getQAPane());
			getRP().setBottom(getNextQuestionPane());
			getRP().getBottom().setDisable(true);
		}
	}
	
	/**
	 * Change the primary stage shown on the screen
	 * @param s - the stage that set to show onto the screen
	 */
	private static void setStage(Stage s) {stage= s;}
	/**
	 * Returns the current stage showing to the screen
	 * @return the current stage
	 */
	public static Stage getStage() {return stage;}
	
	/**
	 * Returns the root pane on current stage.
	 * @return the border pane currently set on the stage
	 */
	public static BorderPane getRP() {
		// stores the pane object from current stage
		BorderPane root = (BorderPane)getStage().getScene().getRoot(); 
		return root;
	}
	/**
	 * Returns a HBox contains the button activating the next question and 
	 * recording the result info into a VBox to be shown after answering all the questions
	 * @return a HBox contains the button of activating next question.
	 */
    public static HBox getNextQuestionPane() {
    	// A button object to activate next question
    	Button nxtQBtn = new Button("_Next Question"); 
    	// A HBox containing the button object
    	HBox nxtQPane = new HBox(nxtQBtn); 
    	nxtQBtn.setStyle("-fx-font-size:18;"+"-fx-border-radius:5;" + "-fx-background-radius:5;"+"-fx-background-color:yellow;"+ "-fx-font-weight:bold;");
    	nxtQPane.setAlignment(Pos.TOP_RIGHT);
    	HBox.setMargin(nxtQBtn, new Insets(0,60,60,0));
    	// A Label containing the title of result board
    	Label rs = new Label("Result Summary:\n\n"); 
    	rs.setStyle("-fx-font-size:20;" + "-fx-font-weight : bold;");
    	rs.setTextFill(Color.BLUE);
    	// An array of Label objects that each of them contains one record of result of every question
		Label[] rsAr = new Label[FileUtils.getQAArrayList().size()]; 
		//Contains all the result information of the game
		VBox resultSum = new VBox(); 
		resultSum.getChildren().add(rs);
		resultSum.setPrefWidth(400);
		resultSum.setAlignment(Pos.CENTER);
    	nxtQBtn.setOnAction((ActionEvent e)->{
    		getRP().getBottom().setDisable(true);
    		//verify the result and display relative output
    		if(FileUtils.getQAArrayList().get(currentQuestion).isCorrect()) {
				rsAr[currentQuestion] = new Label((currentQuestion==0?"1":currentQuestion+1) + "\t\t\tCorrect");
				rsAr[currentQuestion].setTextFill(Color.GREEN);
				Results.setCurrentScr(Results.getCurrentScr() +FileUtils.getQAArrayList().get(currentQuestion).getPoints());
			}else {
				rsAr[currentQuestion] = new Label((currentQuestion==0?"1":currentQuestion+1) + "\t\t\tWrong");
				rsAr[currentQuestion].setTextFill(Color.RED);
			}
			rsAr[currentQuestion].setStyle("-fx-font-size:15;" + "-fx-font-weight : bold;");
			rsAr[currentQuestion].setPadding(new Insets(5,5,5,5));
			//verify the restart status to decide whether to clear up the result board and reload from the beginning
    		if (currentQuestion == 0) {
    			resultSum.getChildren().clear();
    			resultSum.getChildren().addAll(rs, rsAr[currentQuestion]);
    		}else
    			resultSum.getChildren().addAll(rsAr[currentQuestion]);
    		//verify whether to load the result board to the root pane
    		if(currentQuestion < FileUtils.getQAArrayList().size() -1) {
    			if(currentQuestion == FileUtils.getQAArrayList().size()-2)
    				nxtQBtn.setText("Done & View Result");
	    		getRP().setCenter(new QAPane(FileUtils.getQAArrayList().get(++currentQuestion)).getQAPane());
    		}else {    			
    			//Contains the final score information
				Label finalScore = new Label((Results.getCurrentScr()>=7
						?("\nFinal Score: \t" +Results.getCurrentScr() + "\tCongrats, You've passed!")
								:("\nFinal Score: \t" +Results.getCurrentScr() + "\tOops, You've failed!")));
				finalScore.setStyle("-fx-font-size:18;" + "-fx-font-weight : bold;");
				finalScore.setTextFill((Results.getCurrentScr()>=7?Color.GREEN:Color.RED));
				resultSum.getChildren().add(finalScore);
				getRP().setCenter(resultSum);
				getRP().setStyle("-fx-background-color:powderblue;");
				nxtQBtn.setVisible(false);
    		}
    	});
     	return nxtQPane;
    }
}
