package ru.vsu.csf.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class StartController {

    @FXML
    private TextField player1Name;

    @FXML
    private TextField player2Name;

    private Stage stage;

    private FXMLLoader nextSceneLoader;

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        Parent gameUi = nextSceneLoader.load();
        GameController gameController = nextSceneLoader.getController();

        gameController.startGame(player1Name.getText(), player2Name.getText());
        Scene gameScene = new Scene(gameUi, 450, 250);

        stage.setScene(gameScene);
        stage.show();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setNextSceneLoader(FXMLLoader nextSceneLoader) {
        this.nextSceneLoader = nextSceneLoader;
    }
}