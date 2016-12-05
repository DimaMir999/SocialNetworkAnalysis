package org.dimamir999.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Prediction {

    private TennisPlayer player1;
    private TennisPlayer player2;
    @JsonProperty(value = "winFirstProbability")
    private double probabilityWinPlayer1;

    public TennisPlayer getPlayer1() {
        return player1;
    }

    public void setPlayer1(TennisPlayer player1) {
        this.player1 = player1;
    }

    public TennisPlayer getPlayer2() {
        return player2;
    }

    public void setPlayer2(TennisPlayer player2) {
        this.player2 = player2;
    }

    public double getProbabilityWinPlayer1() {
        return probabilityWinPlayer1;
    }

    public void setProbabilityWinPlayer1(double probabilityWinPlayer1) {
        this.probabilityWinPlayer1 = probabilityWinPlayer1;
    }
}