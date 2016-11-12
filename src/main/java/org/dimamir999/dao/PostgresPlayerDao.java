package org.dimamir999.dao;

import org.dimamir999.model.TennisPlayer;
import org.springframework.stereotype.Repository;

@Repository
public class PostgresPlayerDao implements PlayerDao {

    public TennisPlayer getPlayerById(Long id){
        return null;
    }
}
