package org.dimamir999.dao;

import org.dimamir999.config.EmbeddedDatabaseConfig;
import org.dimamir999.model.TennisPlayer;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static org.junit.Assert.assertEquals;

@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {EmbeddedDatabaseConfig.class})
@Transactional
public class PlayerDaoTest {

    @Autowired
    private PlayerDao playerDao;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    private EntityManager entityManager;

    @PostConstruct
    public void initEntityManager(){
        entityManager = entityManagerFactory.createEntityManager();
    }

    @Test
    public void testGetPlayerById(){
        TennisPlayer player1 = new TennisPlayer();
        player1.setName("Rafael");
        player1.setSurname("Nadal");
        TennisPlayer player2 = new TennisPlayer();
        player2.setName("Milos");
        player2.setName("Raonic");
        entityManager.getTransaction().begin();
        entityManager.persist(player1);
        entityManager.persist(player2);
        entityManager.getTransaction().commit();
        TennisPlayer resultPlayer = playerDao.getPlayerById(player1.getId());
        assertEquals(resultPlayer, player1);
    }
}
