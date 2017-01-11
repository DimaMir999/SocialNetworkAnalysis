package org.dimamir999.service;

import org.dimamir999.dao.PlayerDao;
import org.dimamir999.model.TennisPlayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class PlayerService {

    @Autowired
    private PlayerDao playerDao;

    public List<TennisPlayer> getPlayersStartsWith(String startsWith){
        List<TennisPlayer> playersByName = playerDao.getPlayersNameStartsWith(startsWith);
        List<TennisPlayer> playersBySurname = playerDao.getPlayersSurnameStartsWith(startsWith);
        List<TennisPlayer> result = new ArrayList<>();
        result.addAll(playersByName);
        result.addAll(playersBySurname);
        return result;
    }
}
