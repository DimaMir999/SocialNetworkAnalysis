package org.dimamir999.controller;

import org.dimamir999.model.Prediction;
import org.dimamir999.model.TennisPlayer;
import org.dimamir999.service.PlayerService;
import org.dimamir999.service.PredictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class ApiController {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private PredictionService predictionService;

    @PostMapping("players")
    public List<TennisPlayer> getPlayers(@RequestBody String startsWith, @RequestBody int from, @RequestBody int to) {
        return null;
    }

    @PostMapping("prediction")
    public Prediction getPrediction(@RequestBody Long player1Id, @RequestBody Long player2Id){
        return predictionService.makePredoction(player1Id, player2Id);
    }

}
