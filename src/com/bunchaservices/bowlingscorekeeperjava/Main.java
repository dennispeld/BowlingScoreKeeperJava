package com.bunchaservices.bowlingscorekeeperjava;

import java.io.*;

public class Main {
    private static int numberOfPlayers = 0;
    private static Player[] playersArray;

    public static void main(String[] args) {
        SetNumberOfPlayers();

        playersArray = new Player[numberOfPlayers];

        for (int i = 0; i < numberOfPlayers; i++) {
            InitPlayer(i);
        }

        new ScoreCard(playersArray);
    }

    private static void SetNumberOfPlayers() {
        BufferedReader inStream = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.print("Type the number of players: ");
            numberOfPlayers = Integer.parseInt(inStream.readLine());
        } catch (NumberFormatException nfe) {
            System.out.println("Only numbers are allowed.");
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
    }

    private static void InitPlayer(int playerNr) {
        BufferedReader inStream = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.print("The name of the player #" + (playerNr + 1) + ": ");
            Player newPlayer = new Player();
            newPlayer.SetPlayerName(inStream.readLine());
            newPlayer.SetPlayerGame(new Game(newPlayer.GetPlayerName()));
            playersArray[playerNr] = newPlayer;

        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
    }
}
