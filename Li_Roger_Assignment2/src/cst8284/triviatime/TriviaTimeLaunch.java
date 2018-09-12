/**
 * File Name: TriviaTimeLaunch.java
 * Author: Roger Li
 * Course: CST8284_300_Object-Oriented Programming (Java)
 * Assignment: #2
 * Date: 2018-04-18
 * Professor: David B Houtman
 * Purpose: This File has a class which is to display the trivia game onto a scene. 
 * It is a class that contains main method where the whole execution starts.
 * Class list:
 */
package cst8284.triviatime;

import java.security.SecureRandom;
import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.text.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Duration;
/**
 * This class is to display the trivia game onto a scene. 
 * It is a class that contains main method where the whole execution starts.
 * @author Roger Li
 * @version 1.0
 * @see javafx.animation.PathTransition.OrientationType
 * @see javafx.application.Application
 * @see javafx.animation.ScaleTransition
 * @see javafx.animation.PathTransition
 * @see javafx.animation.FillTransition
 * @see javafx.scene.Group
 * @see javafx.scene.Scene
 * @see javafx.scene.effect.DropShadow
 * @see javafx.scene.text.Text
 * @see javafx.scene.layout.BorderPane
 * @see javafx.scene.paint.Color
 * @see javafx.scene.shape.CubicCurveTo
 * @see javafx.scene.shape.LineTo
 * @see javafx.scene.shape.MoveTo
 * @see javafx.stage.Stage
 * @see javafx.util.Duration
 * @since jdk1.8.0_161
 */

public class TriviaTimeLaunch extends Application {
	/**  @Copyright Dave Houtman 2018.  For use in Winter 2018 - CST8284 classes only */
	
	/**  the root pane that is shown onto the screen*/
	private static BorderPane rootPane; 
	
	@Override
	/**
	 * Overridden start method from javafx.application.Application
	 */
	public void start(Stage primaryStage){	
	   // Display Splash Screen
	   setRootPane();
	   getRootPane().setTop(Controls.getMenuBar(primaryStage));
	   Scene scene =  new Scene(getRootPane(), 1024, 512);
	   getRootPane().setStyle("-fx-background-color: black;");
	   primaryStage.setTitle("Trivia Time");
	   primaryStage.setScene(scene);
	   primaryStage.show();	
	}
	/**
	 * Main method that starts running the program
	 * @param args - default parameter of main method
	 */
	public static void main(String[] args){
		Application.launch(args);
	}
	/**
	 * Sets the startup pane showing onto the center of root pane
	 * Learn this from www.java2s.com
	 */
	public static void setRootPane() {
		Text[] welcome = {new Text("W"),new Text("E"),new Text("L"),new Text("C"),new Text("O"),new Text("M"),new Text("E"),
				new Text(" "),new Text("T"),new Text("O"),new Text(" "),new Text("T"),new Text("R"),new Text("I"),new Text("V"),new Text("I"),
				new Text("A"),new Text("L"),new Text(" "),new Text("T"),new Text("I"),new Text("M"),new Text("E")};
		Color [] colors = {Color.RED, Color.GREEN, Color.YELLOW,Color.BLUE, Color.PURPLE, Color.ORANGE};
		SecureRandom randomNumber =new SecureRandom();
		Path [] paths = new Path[welcome.length];
	    Group [] groups = new Group [welcome.length];
	    Group group = new Group();
	    PathTransition [] pathTransitions= new PathTransition [welcome.length];
	    FillTransition fts[] = new FillTransition[welcome.length];
	    DropShadow ds[] = new DropShadow[welcome.length];
	    
	    for (int i = 0; i <welcome.length; i++) {
	    	ds[i] = new DropShadow();
	    	ds[i].setOffsetY(5.0);
		    ds[i].setColor(Color.YELLOW);
	    	welcome[i].setStyle("-fx-font-size: 32;"+"-fx-font-weight:bold;");
	    	welcome[i].setEffect(ds[i]);
	    	paths[i] = new Path();
	    	groups[i] = new Group();
	    	pathTransitions[i] =new PathTransition();
	    	paths[i].getElements().add(new MoveTo(50, 150));
	        paths[i].getElements().add(new CubicCurveTo(250, 50, 400, 50, 900, 150));
	        paths[i].getElements().add(new LineTo(124+i*25, 150));
	        paths[i].setOpacity(0.5);
	        paths[i].setVisible(false);
	        groups[i].getChildren().add(paths[i]);
	        groups[i].getChildren().add(welcome[i]);
	        groups[i].setRotate(180);
	        group.getChildren().add(groups[i]);
	        pathTransitions[i].setDuration(Duration.seconds(1.0+i*0.1));
	        pathTransitions[i].setDelay(Duration.seconds(0.5));
	        pathTransitions[i].setPath(paths[i]);
	        pathTransitions[i].setNode(welcome[i]);
	        pathTransitions[i]
	            .setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
	        pathTransitions[i].setAutoReverse(true);
	        pathTransitions[i].play();
	        
	        fts[i]=new FillTransition(Duration.millis(1000), welcome[i], colors[randomNumber.nextInt(6)], colors[randomNumber.nextInt(6)]);
	        fts[i].setCycleCount(Timeline.INDEFINITE);
	        fts[i].setAutoReverse(true);
	        fts[i].play();
	    }
        
        ScaleTransition st=new ScaleTransition(Duration.millis(2000),group);
        st.setByX(0.7f);
        st.setByY(0.8f);
        st.setCycleCount(Timeline.INDEFINITE);
        st.setAutoReverse(true);
        st.play();
		//instantiate the root pane
		rootPane = new BorderPane();
		rootPane.setCenter(group);
	}
	/**
	 * Returns the root pane
	 * @return BorderPane object as the root pane
	 */
	public static BorderPane getRootPane() {
		return rootPane;
	}
}