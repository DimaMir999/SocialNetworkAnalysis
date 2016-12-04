package org.dimamir999.controller;

import org.dimamir999.config.AppConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class})
@WebAppConfiguration
public class ApiControllerTest {

    @Test
    public void testGetPlayersWithCorrectParamsExpectOk(){

    }
}
