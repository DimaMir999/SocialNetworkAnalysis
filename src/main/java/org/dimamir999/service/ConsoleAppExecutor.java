package org.dimamir999.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class ConsoleAppExecutor {

    private String processDirectory;
    private String[] startCommand;
    private Process process;
    private BufferedWriter processInput;
    private BufferedReader processOutput;
    private BufferedReader processOutputError;

    public ConsoleAppExecutor(String processDirectory, String ... startCommand) {
        this.processDirectory = processDirectory;
        this.startCommand = startCommand;
    }

    public void startApplication() throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder(startCommand).redirectInput(ProcessBuilder.Redirect.PIPE)
                .redirectOutput(ProcessBuilder.Redirect.PIPE)
                .redirectError(ProcessBuilder.Redirect.PIPE)
                .directory(new File(processDirectory));
        process = processBuilder.start();
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
