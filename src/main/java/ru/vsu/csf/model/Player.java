package ru.vsu.csf.model;

import ru.vsu.csf.model.strategy.BetStrategy;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Player {

    public final static int INITIAL_BALLS_COUNT = 10;

    public final String name;

    private final BetStrategy betStrategy;

    private final List<Ball> ballList;

    private final List<Ball> betList;

    public Player(String name, Color color, BetStrategy betStrategy) {
        this.name = name;
        this.betStrategy = betStrategy;
        ballList = new ArrayList<>(INITIAL_BALLS_COUNT);
        betList = new ArrayList<>(INITIAL_BALLS_COUNT);
        for (int i = 0; i < INITIAL_BALLS_COUNT; i++) {
            ballList.add(new Ball(color));
        }
    }

    public void takeBalls(List<Ball> balls) {
        ballList.addAll(balls);
        ballList.addAll(betList);
        betList.clear();
    }

    public List<Ball> giveBalls() {
        List<Ball> balls = new ArrayList<>(betList);
        betList.clear();
        return balls;
    }

    public int getBallsCount() {
        return ballList.size();
    }

    public void makeBet() {
        int r = betStrategy.getNext(ballList.size());
        for (int i = 0; (i < r && i < ballList.size()); i++) {
            betList.add(ballList.remove(0));
        }
    }

    public boolean guess(Guess guess) {
        return guess.isCorrect(betList.size());
    }

    public Guess makeGuess() {
        return Guess.returnRandom();
    }

    @Override
    public String toString() {
        return "Player{" +
                "name = '" + name + '\'' +
                " ball count ='" + ballList.size() + '\'' +
                '}';
    }
}