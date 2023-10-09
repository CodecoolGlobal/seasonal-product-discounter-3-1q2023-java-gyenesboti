package com.codecool.seasonalproductdiscounter.service.logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoggerBase implements Logger{
    String ANSI_RESET = "\u001B[0m";
    String ANSI_GREEN = "\u001B[32m";

    public void logInfo(String message){
        logMessage(message, "INFO");
    };

    public void logError(String message){
        logMessage(message, "ERROR");
    };

    private void logMessage(String message, String type) {
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timestamp = date.format(format);
        String entry = ANSI_GREEN + "" + "\n" + "System " + type + " [" + timestamp + "]: " + message + ANSI_RESET;
        System.out.println(entry);
    }
}
