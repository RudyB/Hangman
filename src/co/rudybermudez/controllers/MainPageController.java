package co.rudybermudez.controllers;
/*
 * @Author:  Rudy Bermudez
 * @Email:   hello@rudybermudez.co
 * @Date:    May 26, 2016
 * @Project: Hangman
 * @Package:    co.rudybermudez.controllers
 */

import co.rudybermudez.models.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class MainPageController {

    @FXML
    Text guessesRemainingTextField;

    @FXML
    Text guessedLettersTextField;

    @FXML
    Text currentProgressTextField;

    @FXML
    Button exitButton;

    @FXML
    GridPane gridPane;

    @FXML
    Button newGameButton;


    private Game mCurrentGame;


    public void setCurrentGame(Game currentGame) {
        mCurrentGame = currentGame;
    }

    public void initialize() {
        newGameButton.setVisible(false);
        updateDisplay();
        gridPane.setOnKeyReleased(event -> play(event.getText()));
    }

    @FXML
    private void exitApplication(ActionEvent actionEvent) {
        Stage primaryStage = (Stage) exitButton.getScene().getWindow();
        primaryStage.close();
    }

    private void updateDisplay() {
        currentProgressTextField.setText(mCurrentGame.getCurrentProgress());
        guessedLettersTextField.setText(mCurrentGame.getMisses());
        guessesRemainingTextField.setText(Integer.toString(mCurrentGame.getRemainingTries()));
    }

    public void play(String guessAsString) {
        if (mCurrentGame.getRemainingTries() > 0 && !mCurrentGame.isSolved()) {
            promptForGuess(guessAsString);
            updateDisplay();
        }
        if (mCurrentGame.isSolved()) {
            PopUpMessage popUpMessage = new PopUpMessage("Congratulations", String.format("The word was %s.\nCongratulations you won with %d tries remaining.", mCurrentGame.getAnswer(), mCurrentGame.getRemainingTries()));
            popUpMessage.launch();
            newGameButton.setVisible(true);
        }
        if (!mCurrentGame.isSolved() && mCurrentGame.getRemainingTries() <= 0) {
            PopUpMessage popUpMessage = new PopUpMessage("Sorry", String.format("Bummer the word was %s.", mCurrentGame.getAnswer()));
            popUpMessage.launch();
            newGameButton.setVisible(true);
        }
    }

    public boolean promptForGuess(String guessAsString) {
        boolean isHit = false;
        boolean isValidGuess = false;
        if (!isValidGuess) {
            try {
                isValidGuess = mCurrentGame.applyGuess(guessAsString);
            } catch (IllegalArgumentException iae) {
                PopUpMessage popUpMessage = new PopUpMessage("Error", String.format("%s.   Please try again.\n", iae.getMessage()));
                popUpMessage.launch();
            }
        }

        return isHit;
    }

    @FXML
    public void newGame(ActionEvent actionEvent) {
        try {
            Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            primaryStage.hide();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/StartPage.fxml"));
            Scene scene = new Scene(loader.load());
            primaryStage.setScene(scene);
            primaryStage.setTitle("Hangman");
            primaryStage.show();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }


}
