package org.dimamir999.service;

import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class ConsoleAppExecutorTest {

    @Test
    public void testReadDataFromConsoleApp(){
        String extectedOutput = "test";
        ConsoleAppExecutor consoleAppExecutor = new ConsoleAppExecutor(System.getProperty("user.dir"),
                "echo", extectedOutput);
        String output = null;
        try {
            consoleAppExecutor.startApplication();
            output = consoleAppExecutor.getLineFromApplication();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(extectedOutput, output);
    }
}
