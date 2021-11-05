package ru.vsu.csf.model;

import ru.vsu.csf.stats.GameStats;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Game {

    private final Player[] players = new Player[2];

    int currentPlayer = 0;

    public Game(String p1, String p2) {
        players[0] = new Player(p1, Color.BLACK);
        players[1] = new Player(p2, Color.BLUE);
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
        return players[0].getBallsCount() == 0 || players[1].getBallsCount() == 0;
    }

    public List<GameStats> getStatistics() {
        return Arrays.stream(players)
                .map(player -> new GameStats(player.name, player.getBallsCount())).collect(Collectors.toList());
    }
}