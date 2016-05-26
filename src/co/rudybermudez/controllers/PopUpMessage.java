package co.rudybermudez.controllers;
/*
 * @Author:  Rudy Bermudez
 * @Email:   hello@rudybermudez.co
 * @Date:    May 26, 2016
 * @Project: Hangman
 * @Package:    co.rudybermudez.controllers
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

public class PopUpMessage {

    @FXML
    Label messageLabel;
    @FXML
    Button okButton;
    private String mMessageLabel;
    private String mTitle;
    private String mButtonText;
    public PopUpMessage(String messageLabel, String title, String buttonText) {
        mMessageLabel = messageLabel;
        mTitle = title;
        mButtonText = buttonText;
    }
    public PopUpMessage(String title, String messageLabel) {
        mTitle = title;
        mMessageLabel = messageLabel;
        mButtonText = "OK";
    }

    public void launch() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PopUpMessage.fxml"));
            loader.setController(this);
            Scene editorPage = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setScene(editorPage);
            stage.setTitle(mTitle);
            stage.show();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void initialize() {
        messageLabel.setText(mMessageLabel);
        okButton.setText(mButtonText);
    }

    @FXML
    private void exitWindow(Event actionEvent) {
        if (actionEvent instanceof KeyEvent) {
            KeyEvent keyEvent = (KeyEvent) actionEvent;
            if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                Stage primaryStage = (Stage) okButton.getScene().getWindow();
                primaryStage.close();
            }
        } else {
            Stage primaryStage = (Stage) okButton.getScene().getWindow();
            primaryStage.close();
        }

    }
}
