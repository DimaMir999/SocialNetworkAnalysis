package org.dimamir999.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PredictionServiceTest {

    @InjectMocks
    private PlayerService playerService = new PlayerService();


    @Test
    public void testGetPlayersWithCorrectParamsExpectOk(){
    }
}
