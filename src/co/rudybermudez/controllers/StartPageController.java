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

public class StartPageController {

    public Game mGame;

    @FXML
    Button exitButton;
    @FXML
    Button startButton;
    @FXML
    TextField wordToGuessTextField;
    @FXML
    GridPane gridPane;

    private String mWordToGuess;

    @FXML
    public void initialize() {

    }

    @FXML
    private void exitApplication(ActionEvent actionEvent) {
        Stage primaryStage = (Stage) exitButton.getScene().getWindow();
        primaryStage.close();
    }

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
            PopUpMessage popUpMessage = new PopUpMessage("Error", "You must enter a word");
            popUpMessage.launch();
        }
    }


    private void startGame(Stage primaryStage) {
        try {
            Game game = new Game(mWordToGuess);
            primaryStage.hide();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainPage.fxml"));

            MainPageController controller = new MainPageController();
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
