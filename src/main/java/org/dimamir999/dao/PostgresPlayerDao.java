package org.dimamir999.dao;

import org.dimamir999.model.TennisPlayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class PostgresPlayerDao implements PlayerDao {

    @PersistenceContext
    private EntityManager entityManager;

    public TennisPlayer getPlayerById(Long id){
        TennisPlayer player = entityManager.find(TennisPlayer.class, id);
        return player;
    }

    @Override
    public List<TennisPlayer> getAllPlayers() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<TennisPlayer> query = criteriaBuilder.createQuery(TennisPlayer.class);
        Root<TennisPlayer> rootEntry = query.from(TennisPlayer.class);
        CriteriaQuery<TennisPlayer> all = query.select(rootEntry);
        TypedQuery<TennisPlayer> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
    }

    @Override
    public List<TennisPlayer> getPlayersNameStartsWith(String startsWith) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<TennisPlayer> query = criteriaBuilder.createQuery(TennisPlayer.class);
        Root<TennisPlayer> rootEntry = query.from(TennisPlayer.class);
        CriteriaQuery<TennisPlayer> all = query.select(rootEntry)
                .where(criteriaBuilder.like(rootEntry.get("name"),startsWith + "%"));
        TypedQuery<TennisPlayer> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
    }

    @Override
    public List<TennisPlayer> getPlayersSurnameStartsWith(String startsWith) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<TennisPlayer> query = criteriaBuilder.createQuery(TennisPlayer.class);
        Root<TennisPlayer> rootEntry = query.from(TennisPlayer.class);
        CriteriaQuery<TennisPlayer> all = query.select(rootEntry)
                .where(criteriaBuilder.like(rootEntry.get("surname"),startsWith + "%"));
        TypedQuery<TennisPlayer> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
    }
}
