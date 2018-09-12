/**
 * File Name: QAPane.java
 * Author: Roger Li
 * Course: CST8284_300_Object-Oriented Programming (Java)
 * Assignment: #2
 * Date: 2018-04-18
 * Professor: David B Houtman
 * Purpose: This File has a class that defines the pane of question and answers to be shown onto the center of the root pane.
 * Class list:
 */
package cst8284.triviatime;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
/**
 * This class defines the pane of question and answers to be shown onto the center of the root pane.
 * @author Roger Li
 * @version 1.0
 * @see javafx.event.ActionEvent
 * @see javafx.geometry.Insets
 * @see javafx.geometry.Pos
 * @see javafx.scene.control.Button
 * @see javafx.scene.control.Label
 * @see javafx.scene.control.RadioButton
 * @see javafx.scene.control.ToggleGroup
 * @see javafx.scene.layout.HBox
 * @see javafx.scene.layout.VBox
 * @see javafx.scene.paint.Color
 * @since jdk1.8.0_161
 */
public class QAPane {
	/** An array contains a series of RadioButton objects*/
   private RadioButton[] rbAr; 
   /** A VBox that contains a QA object related information and the related button*/
   private VBox qaPane; 
   /**
    * Creates a instance of VBox that contains question, answer list, answer button, radio buttons of QA related information
    * @param qa - a QA instance
    */
   public QAPane(QA qa) {
	   //The VBox object that takes question label, radio buttons in answer VBox and answer confirmation button as its components.
	   VBox vb = new VBox();
	   vb.setMaxSize(600, 400);
	   vb.setAlignment(Pos.BOTTOM_CENTER);
	   // Label object containing specifications of a question
	   Label info = new Label("Category:  " +qa.getCategory() + "\t  Difficulty: " + qa.getDifficulty() +"\tPoints: "+qa.getPoints());
	   info.setStyle("-fx-font-size: 20;" + "-fx-font-weight:bold;");
	   info.setTextFill(Color.DEEPSKYBLUE);
	   info.setMinSize(600,50);
	   //Label object containing a question extracted from the trivia file
	   Label question = new Label(qa.getQuestion());
	   question.setStyle("-fx-font-weight:bold;"+"-fx-font-size:18;");
	   question.setTextFill(Color.ANTIQUEWHITE);
	   question.setWrapText(true);
	   question.setMinSize(600, 50);
	   //Button object used for confirming the answer and activating "Next Question" button
	   Button confirm = new Button ("_That's my answer!");
	   //HBox object that contains the answer confirmation button above
	   HBox bottom = new HBox();
	   bottom.getChildren().add(confirm);
	   confirm.setStyle("-fx-font-size:18;"+"-fx-border-radius:5;" + "-fx-background-radius:5;"+"-fx-background-color:yellow;"+ "-fx-font-weight:bold;");
	   bottom.setAlignment(Pos.CENTER_RIGHT);
	   bottom.setMinHeight(30);
	   //A Label object containing the result of chosen answer and the explanation.
	   Label result =new Label(); 
	   result.setWrapText(true);
	   result.setMinHeight(80);
	   result.setStyle("-fx-font-weight:bold;"+"-fx-font-size:18;");
	   //HBox object that takes the result label
	   HBox rsltBox = new HBox();
	   rsltBox.getChildren().add(result);
	   vb.getChildren().addAll(info, question,getAnswerPane(qa.getAnswers()),bottom,rsltBox);
	   confirm.setDisable(true);
	   //Set all the radio buttons able to choose
	   for (int i = 0; i <rbAr.length; i++) {
		   rbAr[i].setOnAction((ActionEvent e)->{
			   confirm.setDisable(false);
		   });
	   }
	   //Set Action Event for the answer confirmation button
	   confirm.setOnAction((ActionEvent e)->{
		   confirm.setDisable(true);
		   //disable all the radio buttons after clicking the answer confirmation button
		   for(RadioButton rb : rbAr)
			   rb.setDisable(true);
		   //check if the answer is correct or not and output relative result to the result label
		   if(qa.getCorrectAnswerNumber()==(getRadioButtonSelected()+1)) {
			   result.setText("Right!\n"+qa.getExplanation());
			   result.setTextFill(Color.GREEN);
			   qa.setResult(true);
		   }else {
			   result.setText("Wrong!\n"+qa.getExplanation());
			   result.setTextFill(Color.RED);
			   qa.setResult(false);
		   }

		   Controls.getRP().getBottom().setDisable(false);
	   });
	   setQAPane(vb);   
   }
   /**
    * Returns a VBox object that contains a group of RadioButton objects attached with answers
    * @param answerAr - a group of possible answers to given question
    * @return a VBox object with the answer group in it
    */
   public VBox getAnswerPane(String[] answerAr) {
	   //VBox object that contains an array of RadioButton objects
	   VBox ansVB = new VBox();
	   ansVB.setMinSize(600,150);
	   //ToggleGroup for the RadioButton array
	   final ToggleGroup rbs = new ToggleGroup();
	   rbAr = new RadioButton[answerAr.length];
	   //Loop to add all the RadioButton array elements into the ToggleGroup and the VBox
	   for (int i = 0; i <rbAr.length; i++) {
		   rbAr[i] = new RadioButton(answerAr[i]);
		   rbAr[i].setTextFill(Color.AZURE);
		   rbAr[i].setStyle("-fx-font-size:15;");
		   rbAr[i].setToggleGroup(rbs);
		   ansVB.getChildren().add(rbAr[i]);
		   VBox.setMargin(rbAr[i], new Insets(2,0,2,0));
	   }
	   return ansVB;
   }
   /**
    * Returns the index of selected RadioButton array element
    * @return an integer representing the index of selected radio button
    */
   public int getRadioButtonSelected() {
	   int i = 0;
	   //Loop to find out the selected radio button
	   for (; i < rbAr.length; i++) {
		  if (rbAr[i].isSelected()) {
			  break;
		  }
	   }
	   return i;
   }
   /**
    * Sets QAPane with given VBox object
    * @param vb - given VBox
    */
   private void setQAPane(VBox vb) {this.qaPane = vb;}
   /**
    * Returns the given VBox object
    * @return a VBox object
    */
   public VBox getQAPane() {return qaPane;}
}
