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
import javax.persistence.FlushModeType;
import javax.persistence.PersistenceContext;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {EmbeddedDatabaseConfig.class})
@Transactional
public class PlayerDaoTest {

    @Autowired
    private PlayerDao playerDao;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void testGetPlayerById(){
        TennisPlayer player1 = new TennisPlayer();
        player1.setName("Rafael");
        player1.setSurname("Nadal");
        TennisPlayer player2 = new TennisPlayer();
        player2.setName("Milos");
        player2.setName("Raonic");
        player1 = entityManager.merge(player1);
        entityManager.merge(player2);

        TennisPlayer resultPlayer = playerDao.getPlayerById(player1.getId());

        assertEquals(player1, resultPlayer);
    }

    @Test
    public void testGetPlayerNameStartsWithExpectOk(){
        String startsWith = "Ra";
        TennisPlayer milosRaonic = new TennisPlayer();
        milosRaonic.setName("Milos");
        milosRaonic.setSurname("Raonic");
        TennisPlayer rafaelNadal = new TennisPlayer();
        rafaelNadal.setName("Rafael");
        rafaelNadal.setSurname("Nadal");
        TennisPlayer radekStepanek = new TennisPlayer();
        radekStepanek.setName("Radek");
        radekStepanek.setSurname("Stepanek");
        TennisPlayer someOtherPlayer = new TennisPlayer();
        someOtherPlayer.setName("Some");
        someOtherPlayer.setName("Other");
        List<TennisPlayer> expectedList = Arrays.asList(radekStepanek, rafaelNadal);

        milosRaonic = entityManager.merge(milosRaonic);
        rafaelNadal = entityManager.merge(rafaelNadal);
        radekStepanek = entityManager.merge(radekStepanek);
        someOtherPlayer = entityManager.merge(someOtherPlayer);

        List<TennisPlayer> resultList = playerDao.getPlayersNameStartsWith(startsWith);

        assertTrue(expectedList.size() == resultList.size());
        assertTrue(expectedList.containsAll(resultList));
    }

    @Test
    public void testGetPlayerSurnameStartsWithExpectOk(){
        String startsWith = "Ra";
        TennisPlayer milosRaonic = new TennisPlayer();
        milosRaonic.setName("Milos");
        milosRaonic.setSurname("Raonic");
        TennisPlayer rafaelNadal = new TennisPlayer();
        rafaelNadal.setName("Rafael");
        rafaelNadal.setSurname("Nadal");
        TennisPlayer someOtherPlayer = new TennisPlayer();
        someOtherPlayer.setName("Some");
        someOtherPlayer.setName("Other");
        List<TennisPlayer> expectedList = Arrays.asList(milosRaonic);

        milosRaonic = entityManager.merge(milosRaonic);
        rafaelNadal = entityManager.merge(rafaelNadal);
        someOtherPlayer = entityManager.merge(someOtherPlayer);

        List<TennisPlayer> resultList = playerDao.getPlayersSurnameStartsWith(startsWith);

        assertTrue(expectedList.size() == resultList.size());
        assertTrue(expectedList.containsAll(resultList));
    }
}
