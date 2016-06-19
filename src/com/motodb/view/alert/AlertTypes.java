package com.motodb.view.alert;

import java.io.IOException;
import java.util.Optional;

import javafx.scene.control.ButtonType;

public interface AlertTypes {

    /**
     * Confirmation Dialog before deleting an element from the TableView
     * 
     * @param the
     *            name of the element the user is trying to delete
     * @return the ButtonType selected by the user OK or CANCEL
     */
    public Optional<ButtonType> showConfirmation(String element);

    /**
     * Information Dialog after converting successfully the XML file to PDF
     */
    public void showConverted();

    /**
     * General Error Dialog
     * 
     * @param the
     *            exception has been throwed
     */
    public void showError(Exception e);

    /**
     * General Warning Dialog
     * 
     * @param the
     *            exception has been throwed
     */
    public void showWarning(Exception e);

    /**
     * Error Dialog after converting from XML to PDF
     * 
     * @param the
     *            exception has been throwed
     */
    public void showConvertError(IOException e);

    /**
     * Error Dialog after converting price
     */
    public void showPriceError();

    /**
     * Error Dialog after sending an email
     */
    public void showEmailNotSentError(Exception e);

    /**
     * Information Dialog after the email has been sent successfully
     * 
     * @param the
     *            exception has been throwed
     */
    public void showEmailSentSuccessfully();
}
