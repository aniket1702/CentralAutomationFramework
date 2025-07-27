package com.caf.automation.web.utils;

import com.caf.automation.web.enums.loggers.LogType;
import com.caf.automation.web.loggers.LogUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class InitializeSeleniumServer {
    private InitializeSeleniumServer() {
    }

    private static Process process = null;


    public static void startSeleniumServer() {
        if (isPortInUse()) {
            LogUtils.log(LogType.PASS, "Port " + 4444 + " is already in use. Stopping the existing server...");
            killProcessByPort(4444); // Ensure the port is free
        }



        String serverPath = System.getProperty("user.dir") + "/src/test/resources/server/selenium-server-4.16.1.jar";


        try {
            ProcessBuilder processBuilder = new ProcessBuilder("java", "-jar", serverPath, "standalone");
            processBuilder.redirectErrorStream(true);
            process = processBuilder.inheritIO().start();
            process.waitFor(20, TimeUnit.SECONDS);
            LogUtils.log(LogType.PASS, "Server Started...");
        } catch (IOException | InterruptedException e) {
            LogUtils.log(LogType.FAIL, e.getMessage());
            Thread.currentThread().interrupt();
        }


    }


    private static boolean isPortInUse() {
        AtomicBoolean isAlive = new AtomicBoolean(false);
        try {
            ProcessHandle.allProcesses()
                    .filter(process -> process.info().command().map(cmd -> cmd.contains(String.valueOf(4444))).orElse(false))
                    .findFirst()
                    .ifPresent(process -> {
                        isAlive.set(true);
                        System.out.println("Process using port " + 4444 + " found with PID: " + process.pid());
                    });
        } catch (UnsupportedOperationException e) {
            System.err.println("ProcessHandle API is not supported on this platform.");
        }
        return isAlive.get();
    }


    public static void stopServer() {
        if (process != null && process.isAlive()) {
            LogUtils.log(LogType.PASS, "Stopping Server");
            process.destroyForcibly();
            try {
                process.waitFor();
                LogUtils.log(LogType.PASS, "Server Stopped!");
            } catch (InterruptedException e) {
                LogUtils.log(LogType.FAIL, "Exception occurred while waiting for process termination: " + e.getMessage());
                Thread.currentThread().interrupt();
            }
        } else {
            LogUtils.log(LogType.PASS, "Server Stopped...");
        }

    }

    public static void killProcessByPort(int port) {
        ProcessBuilder processBuilder = new ProcessBuilder();
        // Use the appropriate command based on the operating system
        String command = getKillCommandByPort(port);
        processBuilder.command("bash", "-c", command);

        try {
            Process process = processBuilder.inheritIO().start();
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                LogUtils.log(LogType.PASS, "Process using port " + port + " killed successfully.");
            } else {
                LogUtils.log(LogType.FAIL, "Failed to kill process using port " + port);
            }
        } catch (IOException | InterruptedException e) {
            LogUtils.log(LogType.FAIL, "Error executing command: " + e.getMessage());

        }
    }

    public static String getKillCommandByPort(int port) {
        // Use different commands based on the operating system
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            // Windows command to kill process using port
            return "for /f \"tokens=5\" %a in ('netstat -aon ^| findstr \":" + port + "\"') do taskkill /f /pid %a";
        } else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
            // Linux/Unix command to kill process using port
            return "kill -9 $(lsof -t -i:" + port + ")";
        } else {
            throw new UnsupportedOperationException("Unsupported operating system.");
        }
    }


}
