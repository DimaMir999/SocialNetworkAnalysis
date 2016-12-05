package org.dimamir999.service;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

@Component
public class ConsoleAppExecutor {

    private String startCommand;
    private Process process;
    private BufferedWriter processInput;
    private BufferedReader processOutput;
    private BufferedReader processOutputError;

    public ConsoleAppExecutor(String startCommand) {
        this.startCommand = startCommand;
    }

    public void startApplication() throws IOException {
        process = Runtime.getRuntime().exec(startCommand);
        processInput = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
        processOutput = new BufferedReader(new InputStreamReader(process.getInputStream()));
        processOutputError = new BufferedReader(new InputStreamReader(process.getErrorStream()));
    }

    public void stopApplication(){
        process.destroy();
    }

    public void restartAplication() throws IOException {
        stopApplication();
        startApplication();
    }

    public void sendToApplication(String data, boolean flush) throws IOException {
        processInput.write(data);
        processInput.newLine();
        if(flush) processInput.flush();
    }

    public String getLineFromApplication() throws IOException {
        return processOutput.readLine();
    }
}
