package org.dimamir999.dao;

import org.dimamir999.model.TennisPlayer;

import java.util.List;

public interface PlayerDao {

    TennisPlayer getPlayerById(Long id);

    List<TennisPlayer> getAllPlayers();

    List<TennisPlayer> getPlayersStartsWith(String startsWith, int from, int to);
}
