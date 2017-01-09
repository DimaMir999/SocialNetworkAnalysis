package org.dimamir999.service;

import org.dimamir999.dao.PlayerDao;
import org.dimamir999.model.TennisPlayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    @Autowired
    private PlayerDao playerDao;

    public List<TennisPlayer> getPlayersStartsWith(String startsWith, int from, int to){
        return playerDao.getPlayersStartsWith(startsWith, from, to);
    }
}
