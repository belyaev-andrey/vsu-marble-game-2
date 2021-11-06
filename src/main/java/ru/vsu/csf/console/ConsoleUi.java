package ru.vsu.csf.console;

import ru.vsu.csf.GameUi;

import java.util.Scanner;

public class ConsoleUi implements GameUi {

    @Override
    public int getBet(String player, int ballsCount) {
        System.out.print(player+", enter your bet: ");
        return new Scanner(System.in).nextInt();
    }


}