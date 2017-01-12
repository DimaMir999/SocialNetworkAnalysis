package org.dimamir999.controller;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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

    private JsonFactory jsonFactory = new JsonFactory();

    @GetMapping(value = "players", produces = "application/json")
    public String getPlayers(@RequestParam String startsWith) throws IOException {
        List<TennisPlayer> players = playerService.getPlayersStartsWith(startsWith);
        return getJsonString(players);
    }

    @PostMapping("prediction")
    public Prediction getPrediction(@RequestBody ObjectNode json) throws JsonProcessingException {
        Long player1 = json.get("player1").asLong();
        Long player2 = json.get("player2").asLong();
        Tournament tournament = jsonMapper.treeToValue(json.get("tournament"), Tournament.class);
        return predictionService.makePrediction(player1, player2, tournament);
    }

    private String getJsonString(List<TennisPlayer> players){
        OutputStream out = new ByteArrayOutputStream();
        JsonGenerator generator = null;
        try {
            generator = jsonFactory.createGenerator(out);
            generator.writeStartArray();
            for (TennisPlayer player : players) {
                generator.writeStartObject();
                generator.writeNumberField("id", player.getId());
                generator.writeStringField("name", player.getName());
                generator.writeStringField("surname", player.getSurname());
                generator.writeNumberField("atpRank", player.getAtpRank());
                generator.writeEndObject();
            }
            generator.writeEndArray();
            generator.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }
}
