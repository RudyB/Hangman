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
     * The Exit button.
     */
    @FXML
    Button exitButton;

    /**
     * The Start button.
     */
    @FXML
    Button startButton;

    /**
     * The TextField that contains the word the second player should guess.
     */
    @FXML
    TextField wordToGuessTextField;

    /**
     * The main Grid pane.
     */
    @FXML
    GridPane gridPane;

    /**
     * The M current game.
     */
    private Game mCurrentGame;


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
     * Create's an instance of Game.
     *
     * @param actionEvent the action event
     */
    @FXML
    private void createGame(Event actionEvent) {
        try {
            if (actionEvent instanceof KeyEvent) {
                KeyEvent keyEvent = (KeyEvent) actionEvent;
                if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                    mCurrentGame = new Game(wordToGuessTextField.getText());
                    Stage primaryStage = (Stage) gridPane.getScene().getWindow();
                    launchGame(primaryStage);
                }
            } else {
                mCurrentGame = new Game(wordToGuessTextField.getText());
                Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                launchGame(primaryStage);
            }
        } catch (IllegalArgumentException iae) {
            new PopUpMessage("Error", iae.getMessage()).launch();
        }

    }


    /**
     * Loads the MainGamePage and launches the game.
     *
     * @param primaryStage the primary stage
     */
    private void launchGame(Stage primaryStage) {
        try {
            primaryStage.hide();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainGamePage.fxml"));

            MainGameController controller = new MainGameController();
            controller.setCurrentGame(mCurrentGame);
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
