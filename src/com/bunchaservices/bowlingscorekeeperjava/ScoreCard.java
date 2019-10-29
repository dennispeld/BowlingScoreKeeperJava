package com.bunchaservices.bowlingscorekeeperjava;

class ScoreCard {
    ScoreCard(Player[] players) {
        for (Player player : players) {
            Game game = player.GetPlayerGame();
            Frame[] gameFrames = game.GetFrames();
            Frame[] frames = RecalculateResults(gameFrames);
            String firstScore = "";

            if (frames[0].GetFrameScore() < 10) {
                firstScore = frames[0].GetFrameScore() + " ";
            }
            else {
                firstScore = String.valueOf(frames[0].GetFrameScore());
            }

            // draw the score cards
            System.out.println();
            System.out.println("PLAYER " + player.GetPlayerName());
            System.out.println("___________ ___________ ___________ ___________ ___________ ___________ ___________ ___________ ___________ ___________");
            System.out.println("|    |    | |    |    | |    |    | |    |    | |    |    | |    |    | |    |    | |    |    | |    |    | |    |    |");
            System.out.println("| " + FormatScore(frames[0], true) + " | " + FormatScore(frames[0], false) + " | | " + FormatScore(frames[1], true) + " | " + FormatScore(frames[1], false) + " | | " + FormatScore(frames[2], true) + " | " + FormatScore(frames[2], false) + " | | " + FormatScore(frames[3], true) + " | " + FormatScore(frames[3], false) + " | | " + FormatScore(frames[4], true) + " | " + FormatScore(frames[4], false) + " | | " + FormatScore(frames[5], true) + " | " + FormatScore(frames[5], false) + " | | " + FormatScore(frames[6], true) + " | " + FormatScore(frames[6], false) + " | | " + FormatScore(frames[7], true) + " | " + FormatScore(frames[7], false) + " | | " + FormatScore(frames[8], true) + " | " + FormatScore(frames[8], false) + " | | " + FormatScore(frames[9], true) + " | " + FormatScore(frames[9], false) + " |");
            System.out.println("|    |____| |    |____| |    |____| |    |____| |    |____| |    |____| |    |____| |    |____| |    |____| |    |____|");
            System.out.println("|         | |         | |         | |         | |         | |         | |         | |         | |         | |         |");
            System.out.println("|    " + firstScore + "   | |    " + frames[1].GetFrameScore() + "   | |    " + frames[2].GetFrameScore() + "   | |    " + frames[3].GetFrameScore() + "   | |    " + frames[4].GetFrameScore() + "   | |    " + frames[5].GetFrameScore() + "   | |    " + frames[6].GetFrameScore() + "   | |    " + frames[7].GetFrameScore() + "   | |    " + frames[8].GetFrameScore() + "   | |    " + frames[9].GetFrameScore() + "   |");
            System.out.println("|_________| |_________| |_________| |_________| |_________| |_________| |_________| |_________| |_________| |_________|");
            System.out.println();
        }
    }

    private String FormatScore(Frame frame, boolean isFirstThrow) {
        String formattedString;
        int score;

        if (isFirstThrow)
            score = frame.GetFirstThrow();
        else
            score = frame.GetSecondThrow();

        if (score != 10) {
            formattedString = " " + score;
        }
        else {
            formattedString = String.valueOf(score);
        }

        if (frame.GetFirstThrow() == 10 && !isFirstThrow) {
            formattedString = " X";
        }
        else if (frame.GetFirstThrow() + frame.GetSecondThrow() == 10 && !isFirstThrow) {
            formattedString = " /";
        }

        return formattedString;
    }

    private Frame[] RecalculateResults(Frame[] frames) {
        Frame[] recalculatedFrames = new Frame[10];
        int score = 0;

        for (int i = 0; i < frames.length; i++) {
            recalculatedFrames[i] = frames[i];

            score += frames[i].GetFrameScore();
            recalculatedFrames[i].SetFrameScore(score);
        }

        return recalculatedFrames;
    }
}
