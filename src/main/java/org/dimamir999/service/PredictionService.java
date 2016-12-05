package org.dimamir999.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.dimamir999.dao.PlayerDao;
import org.dimamir999.model.Match;
import org.dimamir999.model.Prediction;
import org.dimamir999.model.TennisPlayer;
import org.dimamir999.model.Tournament;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PredictionService {

    private final static String PYTHON_PREDICTION_APP_NAME = "python3 predict.py";

    @Autowired
    private PlayerDao playerDao;

    @Autowired
    private ConsoleAppExecutor consoleAppExecutor;
    private ObjectMapper jsonMapper = new ObjectMapper();

    public Prediction makePrediction(Match match){
        String matchJSON, predictionJSON;
        Prediction prediction = null;
        try {
            matchJSON = jsonMapper.writeValueAsString(match);
            consoleAppExecutor.sendToApplication(matchJSON, true);
            predictionJSON = consoleAppExecutor.getLineFromApplication();
            prediction = jsonMapper.readValue(predictionJSON, Prediction.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        return prediction;
    }

    public Prediction makePrediction(Long player1Id, Long player2Id, Tournament tournament){
        TennisPlayer player1 = playerDao.getPlayerById(player1Id);
        TennisPlayer player2 = playerDao.getPlayerById(player2Id);
        Match match = new Match();
        match.setPlayer1(player1);
        match.setPlayer2(player2);
        match.setTournament(tournament);
        Prediction prediction = makePrediction(match);
        prediction.setPlayer1(player1);
        prediction.setPlayer2(player2);
        return prediction;
    }
}
