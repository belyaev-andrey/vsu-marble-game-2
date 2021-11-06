package ru.vsu.csf.model.strategy;

import ru.vsu.csf.GameUi;

public class ManualInputStrategy implements BetStrategy {

    private final String player;

    private final GameUi gameUi;

    public ManualInputStrategy(String player, GameUi gameUi) {
        this.player = player;
        this.gameUi = gameUi;
    }

    public Integer getNext(int i) {
        return gameUi.getBet(player, i);
    }

}