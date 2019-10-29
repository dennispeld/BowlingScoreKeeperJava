package com.bunchaservices.bowlingscorekeeperjava;

import java.io.*;

class Game {
    private Frame[] framesArray;
    private String playerName;

    Game(String playerName) {
        // The game consists of 10 frames
        framesArray = new Frame[10];
        this.playerName = playerName;

        SubmitScores();
    }

    Frame[] GetFrames() {
        return this.framesArray;
    }

    int GetGameScore() {
        int gameScore = 0;

        for (Frame frame : framesArray) {

            gameScore += frame.GetFrameScore();
        }

        return gameScore;
    }

    // The method recalculates scores for the frames with strikes
    private void RecalculateStrikes() {
        int frameScore;
        int frameLength = framesArray.length;

        for (int i = 0; i < frameLength; i++) {
            // if the first throw equals to 10, it is a strike
            // we cannot calculate the score of the last strike frame in this method
            if (framesArray[i].GetFirstThrow() == 10 && i != frameLength - 1) {

                // if its a strike, add to the frame score the score of the next frame
                frameScore = framesArray[i].GetFrameScore() + framesArray[i + 1].GetFrameScore();
                framesArray[i].SetFrameScore(frameScore);
            }
        }
    }

    // The method recalculates scores for the frames with spares
    private void RecalculateSpares() {
        int frameScore;
        int firstThrow;
        int secondThrow;
        int frameLength = framesArray.length;

        for (int i = 0; i < frameLength; i++) {
            firstThrow = framesArray[i].GetFirstThrow();
            secondThrow = framesArray[i].GetSecondThrow();

            // if summing the first and the second throws we get 10, it is a spare
            // the first throw should be less than 10, otherwise it is a strike
            // we cannot calculate the score of the last spare frame in this method
            if ((firstThrow + secondThrow == 10) && (firstThrow != 10) && i != frameLength - 1) {
                // if its spare, to the frame score I add the first throw of the next frame
                frameScore = framesArray[i].GetFrameScore() + framesArray[i + 1].GetFirstThrow();
                framesArray[i].SetFrameScore(frameScore);
            }
        }
    }

    // The method gives to the player bonus throws and returns the bonus score
    private int StrikeOnLastFrame() {
        int bonusFirstThrow;
        int bonusSecondThrow;

        BufferedReader inStream = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.print(playerName + " has a bonus throw #1: ");
            bonusFirstThrow = Integer.parseInt(inStream.readLine());

            System.out.print(playerName + " has a bonus throw #2: ");
            bonusSecondThrow = Integer.parseInt(inStream.readLine());
        } catch (NumberFormatException nfe) {
            System.out.println("Only numbers are allowed.");

            return 0;
        } catch (IOException e) {
            System.out.println("IOException: " + e);

            return 0;
        }

        return bonusFirstThrow + bonusSecondThrow;
    }

    // The method gives to the player a bonus throw and returns the bonus score
    private int SpareOnLastFrame() {
        int bonusThrow;
        BufferedReader inStream = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.print(playerName + " has a bonus throw: ");
            bonusThrow = Integer.parseInt(inStream.readLine());
        } catch (NumberFormatException nfe) {
            System.out.println("Only numbers are allowed.");
            return 0;
        } catch (IOException e) {
            System.out.println("IOException: " + e);
            return 0;
        }
        return bonusThrow;
    }

    // The method reads throws for frames
    private void SubmitScores() {
        int firstThrow;
        int secondThrow;
        int bonusScore = 0;
        int frameLength = framesArray.length;

        try {
            for (int i = 0; i < frameLength; i++) {
                BufferedReader inStream = new BufferedReader(new InputStreamReader(System.in));

                // throws are NUMBERS and should be less or equal to 10
                do {
                    System.out.print("Player " + playerName + ", please enter your score for the 1 ball of frame " + (i + 1) + ": ");
                    firstThrow = Integer.parseInt(inStream.readLine());

                    // if we've got a strike, it is not necessary to throw a second ball
                    if (firstThrow == 10) {
                        secondThrow = 0;
                        System.out.println("Player " + playerName + " has strike.");
                    }
                    else {
                        System.out.print("Player " + playerName + ", please enter your score for the 2 ball of frame " + (i + 1) + ": ");
                        secondThrow = Integer.parseInt(inStream.readLine());
                    }
                }
                while ((firstThrow > 10) && (secondThrow > 10));

                Frame frame = new Frame();
                frame.SetFirstThrow(firstThrow);
                frame.SetSecondThrow(secondThrow);
                frame.SetFrameScore(firstThrow + secondThrow);

                framesArray[i] = frame;
            }

            // if there were strikes, the score should be recalculated
            RecalculateStrikes();

            // if there were spares, the score should be recalculated
            RecalculateSpares();

            // Bonus throws for a strike or a spare on final frame
            if (framesArray[frameLength - 1].GetFirstThrow() == 10) {
                bonusScore = StrikeOnLastFrame();
            }
            else if (framesArray[frameLength - 1].GetFirstThrow() + framesArray[frameLength - 1].GetSecondThrow() == 10) {
                bonusScore = SpareOnLastFrame();
            }

        } catch (NumberFormatException nfe) {
            System.out.println("Only numbers are allowed.");
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }

        int gameScore = this.GetGameScore();
        int score = gameScore + bonusScore;

        System.out.println("GAME SCORE: " + score);
    }
}
