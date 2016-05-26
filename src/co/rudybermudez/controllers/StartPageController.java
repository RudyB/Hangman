package co.rudybermudez.controllers;

import co.rudybermudez.models.Game;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The Start page controller.
 */
public class StartPageController {

    /**
     * The FXML Exit button.
     */
// FXML Annotations
    @FXML Button exitButton;

    /**
     * The FXML Start button.
     */
    @FXML Button startButton;

    /**
     * The FXML TextField that contains the word the second player should guess.
     */
    @FXML TextField wordToGuessTextField;

    /**
     * The FXML main Grid pane.
     */
    @FXML GridPane gridPane;

    /**
     * The String that stores the word the second player should guess.
     */
    private String mWordToGuess;


    /**
     * Exits the application.
     *
     * @param actionEvent the action event
     */
    @FXML
    private void exitApplication(ActionEvent actionEvent) {
        Stage primaryStage = (Stage) exitButton.getScene().getWindow();
        primaryStage.close();
    }


    /**
     * Loads the word from the wordToGuessTextField and stores it to mWordToGuess.
     *
     * @param actionEvent the action event
     */
    @FXML
    private void loadWord(Event actionEvent) {

        if (!wordToGuessTextField.getText().equals("")) {
            if (actionEvent instanceof KeyEvent) {
                KeyEvent keyEvent = (KeyEvent) actionEvent;
                if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                    mWordToGuess = wordToGuessTextField.getText();
                    Stage primaryStage = (Stage) gridPane.getScene().getWindow();
                    startGame(primaryStage);
                }
            } else {
                mWordToGuess = wordToGuessTextField.getText();
                Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                startGame(primaryStage);
            }

        } else {
            new PopUpMessage("Error", "You must enter a word").launch();
        }
    }


    /**
     * Loads the MainGamePage and Starts the game.
     *
     * @param primaryStage the primary stage
     */
    private void startGame(Stage primaryStage) {
        try {
            Game game = new Game(mWordToGuess);
            primaryStage.hide();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainGamePage.fxml"));

            MainGameController controller = new MainGameController();
            controller.setCurrentGame(game);
            loader.setController(controller);

            Scene scene = new Scene(loader.load());
            primaryStage.setScene(scene);
            primaryStage.setTitle("Hangman");
            primaryStage.show();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
