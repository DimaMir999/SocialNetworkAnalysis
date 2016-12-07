package org.dimamir999.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.dimamir999.dao.PlayerDao;
import org.dimamir999.model.Match;
import org.dimamir999.model.Prediction;
import org.dimamir999.model.TennisPlayer;
import org.dimamir999.model.Tournament;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.annotation.PostConstruct;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PredictionServiceTest {

    @Mock
    private PlayerDao playerDao;

    @Mock
    private ConsoleAppExecutor consoleAppExecutor;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private PredictionService predictionService;

    @PostConstruct
    public void init(){

    }

    @Test
    public void testGetPlayersWithCorrectParamsExpectOk() throws IOException {
        Tournament tournament = new Tournament();
        Long player1Id = 1L;
        Long player2Id = 2L;
        TennisPlayer player1 = new TennisPlayer();
        player1.setId(player1Id);
        player1.setName("name1");
        player1.setSurname("surname1");
        TennisPlayer player2 = new TennisPlayer();
        player2.setName("name2");
        player2.setSurname("surname2");
        player2.setId(player2Id);
        Match match = new Match();
        match.setTournament(tournament);
        match.setPlayer1(player1);
        match.setPlayer2(player2);
        Prediction prediction = new Prediction(0.2);
        prediction.setPlayer1(player1);
        prediction.setPlayer2(player2);
        String predictionJson = "JSON1";
        String matchJson = "JSON2";

        when(objectMapper.writeValueAsString(any(Match.class))).thenReturn(matchJson);
        when(consoleAppExecutor.getLineFromApplication()).thenReturn(predictionJson);
        when(objectMapper.readValue(anyString(), eq(Prediction.class))).thenReturn(new Prediction(0.2));

        Prediction returnedPrediction = predictionService.makePrediction(match);

        verify(objectMapper).writeValueAsString(match);
        verify(consoleAppExecutor).sendToApplication(matchJson, true);
        verify(consoleAppExecutor).getLineFromApplication();
        verify(objectMapper).readValue(eq(predictionJson), eq(Prediction.class));
        assertEquals(prediction, returnedPrediction);
        assertEquals(returnedPrediction.getPlayer1(), match.getPlayer1());
        assertEquals(returnedPrediction.getPlayer2(), match.getPlayer2());
    }

    @Test
    public void testMakePredictionExpectedRightParamsInnerMakePrediction() throws Exception {
        Tournament tournament = new Tournament();
        Long player1Id = 1L;
        Long player2Id = 2L;
        TennisPlayer player1 = new TennisPlayer();
        player1.setId(player1Id);
        player1.setName("name");
        player1.setSurname("surname");
        TennisPlayer player2 = new TennisPlayer();
        player2.setId(player2Id);
        player2.setName("name");
        player2.setSurname("surname");
        Match match = new Match();
        match.setTournament(tournament);
        match.setPlayer1(player1);
        match.setPlayer2(player2);
        Prediction prediction = new Prediction(0.2);

        PredictionService mock = spy(predictionService);
        when(playerDao.getPlayerById(player1Id)).thenReturn(player1);
        when(playerDao.getPlayerById(player2Id)).thenReturn(player2);
        when(mock.makePrediction(match)).thenReturn(prediction);

        Prediction returnedPrediction = predictionService.makePrediction(player1Id, player2Id, tournament);

        verify(mock).makePrediction(eq(match));
        assertEquals(prediction, returnedPrediction);
    }

    @Test(expected = NullPointerException.class)
    public void testMakePredictionWithNonExistedExpectedException() {
        Long player1Id = 1L;
        Long player2Id = 2L;
        TennisPlayer player1 = new TennisPlayer();
        player1.setId(player1Id);
        TennisPlayer player2 = new TennisPlayer();
        player2.setId(player2Id);

        when(playerDao.getPlayerById(player1Id)).thenReturn(player1);
        when(playerDao.getPlayerById(player2Id)).thenReturn(null);

        predictionService.makePrediction(player1Id, player2Id, new Tournament());
    }

    @Test(expected = NullPointerException.class)
    public void testMakePredictionWithNullTournamentExpectedException() {
        Long player1Id = 2L;
        Long player2Id = 3L;
        TennisPlayer player1 = new TennisPlayer();
        player1.setId(player1Id);
        TennisPlayer player2 = new TennisPlayer();
        player2.setId(player2Id);

        predictionService.makePrediction(player1Id, player2Id, null);
    }

    @Test(expected = NullPointerException.class)
    public void testMakePredictionWithNullPlayerIdExpectedException() {
        Long player1Id = 1L;
        Long player2Id = null;

        predictionService.makePrediction(player1Id, player2Id, new Tournament());
    }
}
