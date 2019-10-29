package com.bunchaservices.bowlingscorekeeperjava;

class Player {
    private String playerName;
    private Game playerGame;

    String GetPlayerName() {
        return this.playerName;
    }
    void SetPlayerName(String playerName) {
        this.playerName = playerName;
    }

    Game GetPlayerGame() {
        return this.playerGame;
    }
    void SetPlayerGame(Game playerGame) {
        this.playerGame = playerGame;
    }
}
