package org.dimamir999.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.dimamir999.dao.PlayerDao;
import org.dimamir999.model.Prediction;
import org.dimamir999.model.TennisPlayer;
import org.dimamir999.model.Tournament;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PredictionServiceTest {

    @Mock
    private PlayerService playerService;

    @Mock
    private PlayerDao playerDao;

    @Mock
    private ConsoleAppExecutor consoleAppExecutor;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private PredictionService predictionService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetPlayersWithCorrectParamsExpectOk(){
    }


    @Test
    public void makePrediction() throws Exception {
        when(playerDao.getPlayerById(1L)).thenReturn(new TennisPlayer());
        when(playerDao.getPlayerById(2L)).thenReturn(new TennisPlayer());
        doNothing().when(consoleAppExecutor).sendToApplication(anyString(), anyBoolean());
        when(consoleAppExecutor.getLineFromApplication()).thenReturn("");
        when(objectMapper.readValue("", Prediction.class)).thenReturn(new Prediction(0.2));
        Prediction prediction = predictionService.makePrediction(1L, 2L, new Tournament());
        assertEquals(0.2, prediction.getProbabilityWinPlayer1(), 0.01);
    }
}
