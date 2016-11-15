package org.dimamir999.dao;

import org.dimamir999.model.TennisPlayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Repository
public class PostgresPlayerDao implements PlayerDao {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    private EntityManager entityManager;

    @PostConstruct
    public void initEntityManager(){
        entityManager = entityManagerFactory.createEntityManager();
    }

    public TennisPlayer getPlayerById(Long id){
        TennisPlayer player = entityManager.find(TennisPlayer.class, id);
        return player;
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
