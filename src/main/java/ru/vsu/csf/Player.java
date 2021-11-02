package ru.vsu.csf;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player {

    public final static int INITIAL_BALLS_COUNT = 10;

    public final String name;

    private final List<Ball> ballList;

    private final List<Ball> betList;

    public Player(String name, Color color) {
        this.name = name;
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
        int r = new Random().nextInt(ballList.size()+1);
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