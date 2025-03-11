package com.example.ap_2022602_project;

class ScoreSystem {
    private int bestScore;
    private int currentScore;
    private int noOfCherry;
    private int initCherry;

    public int getCurrentScore() {
        return currentScore;
    }
    public void setCurrentScore(int currentScore) {
        this.currentScore = currentScore;
    }
    public int getBestScore() {
        return bestScore;
    }
    public int getNoOfCherry() {
        return noOfCherry;
    }
    public void setNoOfCherry(int noOfCherry) {
        this.noOfCherry = noOfCherry;
    }
    public void update(){
        if (currentScore>bestScore){
            bestScore=currentScore;
            initCherry=noOfCherry;
        }
        currentScore=0;
    }
    public ScoreSystem() {
        this.bestScore = 0;
        this.currentScore = 0;
        this.noOfCherry = 0;
        this.initCherry = 0;
    }

}