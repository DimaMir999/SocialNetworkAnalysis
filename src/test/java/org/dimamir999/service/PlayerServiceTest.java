package org.dimamir999.service;

import org.dimamir999.dao.PlayerDao;
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
        playerService.getPlayersStartsWith("Ra");
        verify(playerDao).getPlayersNameStartsWith("Ra");
    }
}
