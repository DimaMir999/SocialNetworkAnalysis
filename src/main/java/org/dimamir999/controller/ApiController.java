package org.dimamir999.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.dimamir999.model.Match;
import org.dimamir999.model.Prediction;
import org.dimamir999.model.TennisPlayer;
import org.dimamir999.model.Tournament;
import org.dimamir999.service.PlayerService;
import org.dimamir999.service.PredictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class ApiController {

    @Autowired
    public ObjectMapper jsonMapper;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private PredictionService predictionService;

    @GetMapping("players")
    public List<TennisPlayer> getPlayers(@RequestParam String startsWith) {
        return playerService.getPlayersStartsWith(startsWith);
    }

    @PostMapping("prediction")
    public Prediction getPrediction(@RequestBody ObjectNode json) throws JsonProcessingException {
        Long player1 = json.get("player1").asLong();
        Long player2 = json.get("player2").asLong();
        Tournament tournament = jsonMapper.treeToValue(json.get("tournament"), Tournament.class);
        return predictionService.makePrediction(player1, player2, tournament);
    }

}
