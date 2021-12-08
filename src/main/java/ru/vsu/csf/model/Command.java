package ru.vsu.csf.model;

public enum Command {
    GAME("GAME"),
    BET("BET"),
    END("END");

    public static final String SEPARATOR = ":";

    private final String commandString;

    Command(String commandString) {
        this.commandString = commandString;
    }

    public String getCommandString() {
        return commandString;
    }
}