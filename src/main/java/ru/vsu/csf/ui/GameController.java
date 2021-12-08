package ru.vsu.csf.ui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import ru.vsu.csf.GameUi;
import ru.vsu.csf.model.Game;
import ru.vsu.csf.stats.GameStats;

import java.util.List;
import java.util.Optional;

public class GameController implements GameUi {

    @FXML Label player1Name;
    @FXML Label player2Name;
    @FXML Button nextStep;
    @FXML HBox player1Balls;
    @FXML HBox player2Balls;

    private Game game;

    public void startGame(String player1, String player2) {
        player1Name.setText(player1);
        player2Name.setText(player2);
        game = new Game(player1, player2, this);
        updateStatistics();
    }

    @Override
    public int getBet(String player, int ballsCount) {

        TextInputDialog dialog = new TextInputDialog(Integer.toString(ballsCount));
        dialog.setTitle("Your bet?");
        dialog.setContentText(player + "- your bet: ");

        Optional<String> result = dialog.showAndWait();

        Integer i = result.map(Integer::parseInt).orElseThrow();

        return i;
    }

    public void doNextStep() {
        if (!game.gameOver()) {
            game.makeStep();
            updateStatistics();
        }

        if (game.gameOver()) {
            nextStep.setDisable(true);
            ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            Dialog<String> dialog = new Dialog<>();
            dialog.setContentText("Game Over");
            dialog.getDialogPane().getButtonTypes().add(ok);
            dialog.show();
        }
    }

    private void updateStatistics() {
        List<GameStats> statistics = game.getStatistics();
        player1Name.setText(statistics.get(0).playerName()+": "+statistics.get(0).ballsCount());
        player2Name.setText(statistics.get(1).playerName()+": "+statistics.get(1).ballsCount());

        player1Balls.getChildren().clear();
        player2Balls.getChildren().clear();

        for (int i = 0; i < statistics.get(0).ballsCount(); i++) {
            Circle c = new Circle(10);
            c.setFill(Color.RED);
            player1Balls.getChildren().add(c);
        }

        for (int i = 0; i < statistics.get(1).ballsCount(); i++) {
            Circle c = new Circle(10);
            c.setFill(Color.BLUE);
            player2Balls.getChildren().add(c);
        }
    }

}