package org.dimamir999.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Sajel
 */
public class PredictionTest {

    private Prediction prediction = new Prediction(0.2);

    @Test
    public void probabilityFirstWin() throws Exception {
        assertEquals(0.2, prediction.getProbabilityWinPlayer1(), 0.01);
    }

    @Test
    public void probabilitySecondWin() throws Exception {
        assertEquals(0.8, prediction.getProbabilityWinPlayer2(), 0.01);
    }

    @Test(expected = IllegalArgumentException.class)
    public void negativeProbability() throws Exception {
        new Prediction(-2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void probabilityAboveOne() throws Exception {
        new Prediction(1.3);
    }
}