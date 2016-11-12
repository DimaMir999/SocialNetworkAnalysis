package org.dimamir999.service;

import org.dimamir999.dao.PlayerDao;
import org.dimamir999.model.Prediction;
import org.dimamir999.model.TennisPlayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PredictionService {

    @Autowired
    private PlayerDao playerDao;

    /**
     * @return probability of win for player1
     */
    public Prediction makePredoction(TennisPlayer player1 , TennisPlayer player2){
        return new Prediction(0.5);
    }

    /**
     * @return probability of win for player1
     */
    public Prediction makePredoction(Long player1Id, Long player2Id){
        TennisPlayer player1 = playerDao.getPlayerById(player1Id);
        TennisPlayer player2 = playerDao.getPlayerById(player2Id);
        return makePredoction(player1, player2);
    }
}
