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

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Objects;

@Service
public class PredictionService {

    private final static String PYTHON_PREDICTION_APP_NAME = "python3 predict.py";

    @Autowired
    private PlayerDao playerDao;

    private ConsoleAppExecutor consoleAppExecutor = new ConsoleAppExecutor(PYTHON_PREDICTION_APP_NAME);

    @Autowired
    private ObjectMapper jsonMapper;

    @PostConstruct
    public void init() throws IOException {
        consoleAppExecutor.startApplication();
    }

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
        if(prediction != null) {
            prediction.setPlayer1(match.getPlayer1());
            prediction.setPlayer2(match.getPlayer2());
        }
        return prediction;
    }

    public Prediction makePrediction(Long player1Id, Long player2Id, Tournament tournament){
        Objects.requireNonNull(tournament);
        Objects.requireNonNull(player1Id);
        Objects.requireNonNull(player2Id);
        TennisPlayer player1 = playerDao.getPlayerById(player1Id);
        Objects.requireNonNull(player1);
        TennisPlayer player2 = playerDao.getPlayerById(player2Id);
        Objects.requireNonNull(player2);
        Match match = new Match();
        match.setPlayer1(player1);
        match.setPlayer2(player2);
        match.setTournament(tournament);
        Prediction prediction = makePrediction(match);
        return prediction;
    }
}
