package co.rudybermudez.controllers;
/*
 * @Author:  Rudy Bermudez
 * @Email:   hello@rudybermudez.co
 * @Date:    May 26, 2016
 * @Project: Hangman
 * @Package: co.rudybermudez.controllers
 */

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Pop-up Message Class
 */
public class PopUpMessage {

    /**
     * The FXML Message label.
     */
    @FXML
    private Label messageLabel;

    /**
     * The button.
     */
    @FXML
    private Button button;

    /**
     * The Text in the Window.
     */
    private String mMessageLabel;

    /**
     * The Window Title.
     */
    private String mTitle;


    /**
     * The button text.
     */
    private String mButtonText;


    /**
     * Instantiates a new Pop-up window.
     *
     * @param messageLabel the message in the window
     * @param title        the title of the window
     * @param buttonText   the button text
     */
    PopUpMessage(String messageLabel, String title, String buttonText) {
        mMessageLabel = messageLabel;
        mTitle = title;
        mButtonText = buttonText;
    }

    /**
     * Instantiates a new Pop-up window assuming the Button Text is "Ok".
     *
     * @param messageLabel the message in the window
     * @param title        the title of the window
     */
    PopUpMessage(String title, String messageLabel) {
        mTitle = title;
        mMessageLabel = messageLabel;
        mButtonText = "Ok";
    }

    /**
     * Launch the pop-up window.
     */
    void launch() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PopUpMessage.fxml"));
            loader.setController(this);
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle(mTitle);
            stage.show();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Initialize.
     */
    public void initialize() {
        messageLabel.setText(mMessageLabel);
        button.setText(mButtonText);
    }

    /**
     * Exit window.
     *
     * @param actionEvent the action event
     */
    @FXML
    private void exitWindow(Event actionEvent) {
        if (actionEvent instanceof KeyEvent) {
            KeyEvent keyEvent = (KeyEvent) actionEvent;
            if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                Stage primaryStage = (Stage) button.getScene().getWindow();
                primaryStage.close();
            }
        } else {
            Stage primaryStage = (Stage) button.getScene().getWindow();
            primaryStage.close();
        }

    }
}
