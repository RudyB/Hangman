package co.rudybermudez;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;

public class Main extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        com.apple.eawt.Application application = com.apple.eawt.Application.getApplication();
        Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/appIcon.png"));
        application.setDockIconImage(image);
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/StartPage.fxml"));
        primaryStage.setTitle("Hangman");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
