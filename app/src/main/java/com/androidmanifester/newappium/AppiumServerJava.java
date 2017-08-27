package com.androidmanifester.newappium;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;

import java.io.IOException;

public class AppiumServerJava {
    Runtime runtime = Runtime.getRuntime();
    public void startServer() {
        CommandLine cmd = new CommandLine("C:\\Program Files\\nodejs\\node.exe");
        cmd.addArgument("C:\\Users\\Ranjith\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js");
        cmd.addArgument("--address");
        cmd.addArgument("127.0.0.1");
        cmd.addArgument("--port");
        cmd.addArgument("4723");

        DefaultExecuteResultHandler handler = new DefaultExecuteResultHandler();
        DefaultExecutor executor = new DefaultExecutor();
        executor.setExitValue(1);
        try {
            runtime.exec("cmd.exe /c start cmd.exe /k \"appium -a 127.0.0.1 -p 4723 --session-override -dc \"{\"\"noReset\"\": \"\"false\"\"}\"\"");
            //executor.execute(cmd, handler);
            Thread.sleep(20000);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void stopServer() {
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec("taskkill /F /IM node.exe");
            runtime.exec("taskkill /F /IM cmd.exe");
           // runtime.exec("taskkill /F /IM node.exe");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        AppiumServerJava appiumServer = new AppiumServerJava();

        appiumServer.startServer();

        appiumServer.stopServer();
    }
}