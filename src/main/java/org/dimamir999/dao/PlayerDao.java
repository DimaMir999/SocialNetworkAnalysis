package org.dimamir999.dao;

import org.dimamir999.model.TennisPlayer;

import java.util.List;

public interface PlayerDao {

    TennisPlayer getPlayerById(Long id);

    List<TennisPlayer> getAllPlayers();

    List<TennisPlayer> getPlayersNameStartsWith(String startsWith);

    List<TennisPlayer> getPlayersSurnameStartsWith(String startsWith);
}
