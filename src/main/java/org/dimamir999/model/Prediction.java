package org.dimamir999.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Prediction {

    @JsonIgnore
    private TennisPlayer player1;
    @JsonIgnore
    private TennisPlayer player2;
    @JsonProperty(value = "winFirstProbability")
    private double probabilityWinPlayer1;

    public Prediction() {}

    public Prediction(double probabilityFirstWin) {
        if(probabilityFirstWin < 0) {
            throw new IllegalArgumentException("Negative probability.");
        }
        if (probabilityFirstWin > 1) {
            throw new IllegalArgumentException("Probability is above 1.");
        }
        this.probabilityWinPlayer1 = probabilityFirstWin;
    }

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

    public double getProbabilityWinPlayer2() {
        return 1 - probabilityWinPlayer1;
    }

    public void setProbabilityWinPlayer1(double probabilityWinPlayer1) {
        this.probabilityWinPlayer1 = probabilityWinPlayer1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Prediction that = (Prediction) o;

        if (Double.compare(that.probabilityWinPlayer1, probabilityWinPlayer1) != 0) return false;
        if (!player1.equals(that.player1)) return false;
        return player2.equals(that.player2);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = player1.hashCode();
        result = 31 * result + player2.hashCode();
        temp = Double.doubleToLongBits(probabilityWinPlayer1);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
