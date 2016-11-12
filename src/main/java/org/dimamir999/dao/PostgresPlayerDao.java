package org.dimamir999.dao;

import org.dimamir999.model.TennisPlayer;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostgresPlayerDao implements PlayerDao {

    public TennisPlayer getPlayerById(Long id){
        return null;
    }

    @Override
    public List<TennisPlayer> getAllPlayers() {
        return null;
    }

    @Override
    public List<TennisPlayer> getPlayersStartsWith(String startsWith, int from, int to) {
        return null;
    }
}
