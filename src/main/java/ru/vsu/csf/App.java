package ru.vsu.csf;

import ru.vsu.csf.console.ConsoleUi;
import ru.vsu.csf.model.Game;
import ru.vsu.csf.stats.GameStats;

import java.util.List;

public class App {

    public static void main(String[] args) {
        Game game = new Game("Player1", "Player2", new ConsoleUi());

        printStatistics(game.getStatistics());

        while (!game.gameOver()){
            game.makeStep();
            printStatistics(game.getStatistics());
        }
    }

    private static void printStatistics(List<GameStats> stats) {
        stats.forEach(System.out::println);
    }

}