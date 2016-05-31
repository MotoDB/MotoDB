package com.motodb.view;

import java.io.IOException;

import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Represent the initialization of the GUI
 */
public interface GuiInterface {
	
	/**
	 * Builds the application
	 */
	public void start(Stage primaryStage) throws IOException;
	
	/**
	 * Returns the mainPane, called by 'ScreenControl'
	 * @return mainPane
	 */
	public BorderPane getMainPane();
	
	/**
	 * Show the user interface, called by controller
	 */
	public void launcher(String[] args);
}
