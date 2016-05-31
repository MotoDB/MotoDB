package com.motodb.view.alert;

import java.io.IOException;
import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class AlertTypesImpl implements AlertTypes{

	private final String cssPath = getClass().getResource("/materialDesign.css").toExternalForm();
	private final Alert warning = new Alert(AlertType.WARNING);
	private final Alert error = new Alert(AlertType.ERROR);
	private final Alert confirmation = new Alert(AlertType.CONFIRMATION);
	private final Alert information = new Alert(AlertType.INFORMATION);
	
	public AlertTypesImpl(){
		//Added CSS Style to the alert panels
		confirmation.getDialogPane().getStylesheets().add(cssPath);
		error.getDialogPane().getStylesheets().add(cssPath);
		warning.getDialogPane().getStylesheets().add(cssPath);
		information.getDialogPane().getStylesheets().add(cssPath);
	}

	
	@Override
	public void showConverted() {
		information.setTitle("Thanks");
		information.setHeaderText("Data has been converted succesfully!");
		information.setContentText("");
		information.showAndWait();
	}
	
	@Override
	public Optional<ButtonType> showConfirmation(String element) {
		confirmation.setTitle("Confirmation Dialog");
		confirmation.setHeaderText("Do you really want to delete the following element?");
		confirmation.setContentText(element);
		
		return confirmation.showAndWait();
	}

	@Override
	public void showError(Exception e) {
		error.setTitle("Pay Attention");
    	error.setHeaderText("Error!");
    	error.setContentText(e.getMessage());
    	error.showAndWait();
	}

	@Override
	public void showWarning(Exception e) {
		warning.setTitle("Pay Attention");
    	warning.setHeaderText("Error!");
    	warning.setContentText(e.getMessage());
    	warning.showAndWait();
	}

	@Override
	public void showConvertError(IOException e) {
        error.setHeaderText("Could not load a conversion template for this table");
        error.setContentText("FO Template not found, you may have to make it yourself");
        error.showAndWait();	
	}
	
	@Override
	public void showPriceError() {
		error.setTitle("Pay Attention");
        error.setHeaderText("Error!");
        error.setContentText("Price format not valid! (IE 12.50)");
        error.showAndWait();
	}

	@Override
	public void showEmailNotSentError(Exception e) {
		error.setTitle("Something went wrong!");
        error.setHeaderText("The email has not been sent");
        error.setContentText(e.getMessage());
        error.showAndWait();
	}
	
	@Override
	public void showEmailSentSuccessfully() {
		information.setTitle("Congratulations");
		information.setHeaderText("Email sent successfully!");
		information.setContentText("");
		information.showAndWait();
	}
}
