package co.rudybermudez;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Main extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Image image = new Image(getClass().getResource("/images/appIcon.png").toExternalForm());
        primaryStage.getIcons().add(image);
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/StartPage.fxml"));
        primaryStage.setTitle("Hangman");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
