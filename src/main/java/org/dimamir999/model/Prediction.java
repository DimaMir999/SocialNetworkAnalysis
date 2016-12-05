package org.dimamir999.model;

public class Prediction {

    private double probabilityFirstWin;

    public Prediction() {
    }

    public Prediction(double probabilityFirstWin) {
        if(probabilityFirstWin < 0) {
            throw new IllegalArgumentException("Negative probability.");
        }
        if (probabilityFirstWin > 1) {
            throw new IllegalArgumentException("Probability is above 1.");
        }
        this.probabilityFirstWin = probabilityFirstWin;
    }

    public double getProbabilityFirstWin() {
        return  probabilityFirstWin;
    }

    public double getProbabilitySecondWin() {
        return 1 - probabilityFirstWin;
    }
}
