package org.dimamir999.service;

import org.dimamir999.dao.PlayerDao;
import org.dimamir999.model.TennisPlayer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PlayerServiceTest {

    @InjectMocks
    private PlayerService playerService = new PlayerService();

    @Mock
    private PlayerDao playerDao;

    @Test
    public void testGetPlayersExpectInvocationOfDaoWithCorrectParams(){
        playerService.getPlayersStartsWith("Ra", 0 , 10);
        verify(playerDao).getPlayersStartsWith("Ra", 0, 10);
    }
}
