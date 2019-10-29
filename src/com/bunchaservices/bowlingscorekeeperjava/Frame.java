package com.bunchaservices.bowlingscorekeeperjava;

class Frame {
    private int firstThrow;
    private int secondThrow;
    private int frameScore;

    int GetFirstThrow() {
        return firstThrow;
    }
    void SetFirstThrow(int fThrow) {
        this.firstThrow = fThrow;
    }

    int GetSecondThrow() {
        return secondThrow;
    }
    void SetSecondThrow(int secondThrow) {
        this.secondThrow = secondThrow;
    }

    int GetFrameScore() {
        return this.frameScore;
    }
    void SetFrameScore(int frameScore) {
        this.frameScore = frameScore;
    }

    Frame() {
        this.firstThrow = 0;
        this.secondThrow = 0;
        this.frameScore = 0;
    }
}
