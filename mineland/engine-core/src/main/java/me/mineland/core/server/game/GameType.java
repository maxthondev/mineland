package me.mineland.core.server.game;

public enum GameType {

    UNKNOWN("Unknown"),
    SOLO("Solo"),
    DUO("Duo");

    private final String name;

    GameType(final String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}