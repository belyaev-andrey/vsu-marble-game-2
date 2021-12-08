package ru.vsu.csf.model;

import ru.vsu.csf.GameUi;
import ru.vsu.csf.model.strategy.BetStrategy;
import ru.vsu.csf.model.strategy.ManualInputStrategy;
import ru.vsu.csf.model.strategy.RandomStrategy;
import ru.vsu.csf.stats.GameStats;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Game {

    private final Player[] players = new Player[2];

    int currentPlayer = 0;

    public Game(Player p1, Player p2) {
        players[0] = p1;
        players[1] = p2;
    }


    public Game(String p1, String p2, GameUi gameUi) {
        players[0] = new Player(p1, Color.BLACK, new RandomStrategy(p1));
        players[1] = new Player(p2, Color.BLUE, new ManualInputStrategy(p2, gameUi));
    }

    public void makeStep() {
        for (Player player : players) {
            player.makeBet();
        }
        int nextPlayer = (currentPlayer + 1) % 2;

        Guess guess = players[currentPlayer].makeGuess();

        if (players[nextPlayer].guess(guess)) {
            List<Ball> ballsToGive = players[nextPlayer].giveBalls();
            players[currentPlayer].takeBalls(ballsToGive);
        } else {
            List<Ball> ballsToGive = players[currentPlayer].giveBalls();
            players[nextPlayer].takeBalls(ballsToGive);
        }

        currentPlayer = nextPlayer;
    }

    public boolean gameOver() {
        boolean isOver = players[0].getBallsCount() == 0 || players[1].getBallsCount() == 0;
        if (isOver) {
            for (Player player : players) {
                player.finishGame();
            }
        }
        return isOver;
    }

    public List<GameStats> getStatistics() {
        return Arrays.stream(players)
                .map(player -> new GameStats(player.name, player.getBallsCount())).collect(Collectors.toList());
    }
}