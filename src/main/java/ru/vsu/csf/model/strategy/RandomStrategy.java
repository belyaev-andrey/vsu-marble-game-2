package ru.vsu.csf.model.strategy;

import java.util.concurrent.ThreadLocalRandom;

public class RandomStrategy implements BetStrategy {

    private final String player;

    public RandomStrategy(String player) {
        this.player = player;
    }

    public Integer getNext(int value) {
        int bet = ThreadLocalRandom.current().nextInt(value)+1;
        System.out.println(player+", bet: "+bet);
        return bet;
    }
}