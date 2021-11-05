package ru.vsu.csf;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.vsu.csf.ui.StartController;

import java.util.ResourceBundle;

public class FxApp extends Application {

    private final ResourceBundle bundle = ResourceBundle.getBundle("message");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader startLoader = new FXMLLoader(getClass().getResource("ui/StartController.fxml"));
        Parent startUi = startLoader.load();

        Scene startScene = new Scene(startUi, 300, 100);

        FXMLLoader gameLoader = new FXMLLoader(getClass().getResource("ui/GameController.fxml"));

        StartController startController = startLoader.getController();
        startController.setNextSceneLoader(gameLoader);
        startController.setStage(primaryStage);

        primaryStage.setScene(startScene);
        primaryStage.show();
    }

}