package model;

public class Player {

    private String name;
    private int playerNumber;

    public Player(String name, int playerNumber) {
        this.name = name;
        this.playerNumber = playerNumber - 1;

    }

    public String getPlayerName() {
        return name;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }
}