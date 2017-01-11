package org.dimamir999.service;

import org.dimamir999.dao.PlayerDao;
import org.dimamir999.model.TennisPlayer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PlayerServiceTest {

    @InjectMocks
    private PlayerService playerService = new PlayerService();

    @Mock
    private PlayerDao playerDao;

    @Test
    public void testGetPlayersExpectInvocationOfDaoWithCorrectParams(){
        String startsWith = "Ra";

        playerService.getPlayersStartsWith(startsWith);

        verify(playerDao).getPlayersNameStartsWith(startsWith);
        verify(playerDao).getPlayersSurnameStartsWith(startsWith);
    }

    @Test
    public void testGetPlayersExpectUnionOfNameAndSurnameList(){
        String startsWith = "Ra";
        TennisPlayer milosRaonic = new TennisPlayer();
        milosRaonic.setName("Milos");
        milosRaonic.setName("Raonic");
        TennisPlayer rafaelNadal = new TennisPlayer();
        rafaelNadal.setName("Rafael");
        rafaelNadal.setSurname("Nadal");
        TennisPlayer radekStepanek = new TennisPlayer();
        radekStepanek.setName("Radek");
        radekStepanek.setSurname("Stepanek");
        List<TennisPlayer> byNameList = Arrays.asList(radekStepanek, rafaelNadal);
        List<TennisPlayer> bySurnameList = Arrays.asList(milosRaonic);
        when(playerDao.getPlayersNameStartsWith(startsWith)).thenReturn(byNameList);
        when(playerDao.getPlayersSurnameStartsWith(startsWith)).thenReturn(bySurnameList);

        List<TennisPlayer> result = playerService.getPlayersStartsWith(startsWith);
    }
}
