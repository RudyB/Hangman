package co.rudybermudez.controllers;
/*
 * @Author:  Rudy Bermudez
 * @Email:   hello@rudybermudez.co
 * @Date:    May 26, 2016
 * @Project: Hangman
 * @Package: co.rudybermudez.controllers
 */

import co.rudybermudez.models.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * The Main game controller.
 */
public class MainGameController {

    /**
     * The Hangman's right leg.
     */
    @FXML
    private
    Line hangmanRightLeg;

    /**
     * The Hangman's left leg.
     */
    @FXML
    private
    Line hangmanLeftLeg;

    /**
     * The Hangman's body.
     */
    @FXML
    private
    Line hangmanBody;

    /**
     * The Hangman's left arm.
     */
    @FXML
    private
    Line hangmanLeftArm;

    /**
     * The Hangman's right arm.
     */
    @FXML
    private
    Line hangmanRightArm;

    /**
     * The Hangman's head.
     */
    @FXML
    private
    Circle hangmanHead;

    /**
     * The number of Guesses remaining.
     */
    @FXML
    private
    Text numOfGuessesRemainingText;

    /**
     * The Incorrectly Guessed letters.
     */
    @FXML
    private
    Text incorrectlyGuessedLettersText;

    /**
     * The Current progress text.
     */
    @FXML
    private
    Text currentProgressTextField;

    /**
     * The Exit button.
     */
    @FXML
    private
    Button exitButton;

    /**
     * The Grid pane.
     */
    @FXML
    private
    GridPane gridPane;

    /**
     * The New game button.
     */
    @FXML
    private
    Button newGameButton;


    /**
     * Am instance of the Model class Game.
     */
    private Game mCurrentGame;

    /**
     * A list of the hangman's body parts.
     */
    private List<Shape> mHangmanBodyParts;

    /**
     * Sets current game.
     *
     * @param currentGame the current game
     */
    void setCurrentGame(Game currentGame) {
        mCurrentGame = currentGame;
    }

    /**
     * Initialize the FXML Fields.
     */
    public void initialize() {
        // Note the first item is null because we want the head to appear when there has been 1 incorrect letter aka index 1 not 0
        mHangmanBodyParts = Arrays.asList(hangmanHead, hangmanBody, hangmanLeftArm, hangmanRightArm, hangmanLeftLeg, hangmanRightLeg);
        newGameButton.setVisible(false);
        updateDisplay();
        gridPane.setOnKeyReleased(event -> play(event.getText()));
    }

    /**
     * Exit application.
     *
     * @param actionEvent the action event
     */
    @FXML
    private void exitApplication(MouseEvent actionEvent) {
        Stage primaryStage = (Stage) exitButton.getScene().getWindow();
        primaryStage.close();
    }

    /**
     * Updates display.
     */
    private void updateDisplay() {
        currentProgressTextField.setText(mCurrentGame.getProperlyCapitalizedProgress());
        incorrectlyGuessedLettersText.setText(mCurrentGame.getMisses().toUpperCase());
        numOfGuessesRemainingText.setText(Integer.toString(mCurrentGame.getRemainingTries()));
        updateHangmanVisual();
    }

    /**
     * Method to Play the game.
     *
     * @param guessAsString the user entered guess as type String
     */
    private void play(String guessAsString) {
        if (mCurrentGame.getRemainingTries() > 0 && !mCurrentGame.isSolved()) {
            promptForGuess(guessAsString);
            updateDisplay();
        }
        if (mCurrentGame.isSolved()) {
            PopUpMessage popUpMessage = new PopUpMessage("Congratulations", String.format("The word was %s.\n\nCongratulations you won with %d tries remaining.", mCurrentGame.getProperlyCapitalizedProgress(), mCurrentGame.getRemainingTries()));
            popUpMessage.launch();
            newGameButton.setVisible(true);
        }
        if (!mCurrentGame.isSolved() && mCurrentGame.getRemainingTries() <= 0) {
            PopUpMessage popUpMessage = new PopUpMessage("Sorry", String.format("Bummer the word was %s.", mCurrentGame.getAnswer()));
            popUpMessage.launch();
            newGameButton.setVisible(true);
        }
    }

    /**
     * Validates the user entered guess.
     *
     * @param guessAsString the guess as string
     * @return the boolean
     */
    private boolean promptForGuess(String guessAsString) {
        boolean isHit = false;
        boolean isValidGuess = false;
        if (!isValidGuess) {
            try {
                isValidGuess = mCurrentGame.applyGuess(guessAsString);
            } catch (IllegalArgumentException iae) {
                PopUpMessage popUpMessage = new PopUpMessage("Error", String.format("%s.\n\nPlease try again.", iae.getMessage()));
                popUpMessage.launch();
            }
        }

        return isHit;
    }

    /**
     * Method that is called if user wants to load new game.
     *
     * @param actionEvent the action event
     */
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

    /**
     * Update hangman visual.
     */
    private void updateHangmanVisual() {
        if (!mCurrentGame.getMisses().isEmpty())
            mHangmanBodyParts.get(mCurrentGame.getHangmanBodyPartNumber()).setVisible(true);
    }


}
