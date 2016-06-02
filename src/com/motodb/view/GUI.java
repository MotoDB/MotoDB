package com.motodb.view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.URL;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class GUI extends Application implements GuiInterface {

	private static BorderPane root = new BorderPane();
	
	public void start(Stage splashStage) throws IOException {
		
		// Getting screen size
		final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		// Loading splash image
		ImageView splash = new ImageView(new Image(getClass().getResource("/splash.png").toString()));     
		
		// Initializing the splash screen, first making a layout and adding the image to it
		StackPane splashLayout = new StackPane();
        splashLayout.setStyle("-fx-background-color: transparent;");
        splashLayout.getChildren().add(splash);
        // Then making a new transparent scene with the image in it, big as the image 
        Scene splashScene = new Scene(splashLayout, splash.getImage().getWidth(), splash.getImage().getHeight());
        splashScene.setFill(Color.TRANSPARENT);
        splashStage.initStyle(StageStyle.TRANSPARENT); 
        splashStage.setScene(splashScene);
        // Finally showing the splash stage
        splashStage.show();
        
        // Initializing the actual main stage, first creating the full-screen scene
        Scene scene = new Scene(root, screenSize.getWidth(), screenSize.getHeight());
        scene.getStylesheets().add(getClass().getResource("/materialDesign.css").toExternalForm());
        Stage mainStage = new Stage(StageStyle.DECORATED);    
        mainStage.setTitle("book-depot");
        mainStage.getIcons().add(new Image(getClass().getResourceAsStream("/icon.png")));
        mainStage.setIconified(false);
        mainStage.setScene(scene);
        //mainStage.setFullScreen(true);;
        
        // Showing the main stage after and hiding splashStage with an animation
    	FadeTransition fadeSplash = new FadeTransition(Duration.millis(1.0), splashLayout);
    	fadeSplash.setDelay(Duration.seconds(2.5));
    	fadeSplash.setFromValue(1.0);
    	fadeSplash.setToValue(0.0);
    	fadeSplash.setOnFinished(e -> {mainStage.show(); splashStage.hide();});
    	fadeSplash.play();
		
		// Loading the custom font before loading the files which use it
    	final int fontSize = 10; // This value is not needed, as it will be overrided by CSS
		Font.loadFont(getClass().getResource("/Roboto-Regular.ttf").toExternalForm(), fontSize);
		Font.loadFont(getClass().getResource("/Roboto-Bold.ttf").toExternalForm(), fontSize);
		Font.loadFont(getClass().getResource("/Roboto-Medium.ttf").toExternalForm(), fontSize);
		Font.loadFont(getClass().getResource("/Roboto-Black.ttf").toExternalForm(), fontSize);
		Font.loadFont(getClass().getResource("/Roboto-Light.ttf").toExternalForm(), fontSize);
		Font.loadFont(getClass().getResource("/Material-Design-Iconic-Font.ttf").toExternalForm(), fontSize);
		
		// Loading the top menu
		URL menuUrl = getClass().getResource("menu.fxml");
		ToolBar menu = FXMLLoader.load( menuUrl );

		// Loading the movements pane, which will be the welcome page, as it's the most used one
	    URL movementsUrl = getClass().getResource("worldStanding.fxml");
	    ScrollPane movements = FXMLLoader.load( movementsUrl );
	
	    // Constructing our scene using the static root
	    root.setTop(menu);
	    root.setCenter(movements);

	}
	
	public BorderPane getMainPane(){
		return root;
	}
	
	public void launcher(String[] args) {
		launch(args);
	}

}