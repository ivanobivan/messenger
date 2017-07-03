package ru.messenger;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class CustomLogger {

    public static void getServerLogCustoms(Logger logger) throws IOException {
        if(!new File("src/main/java/log").exists()) {
            new File("src/main/java/log").mkdir();
        }
        FileHandler fileHandler = new FileHandler("src/main/java/log/logServer.txt");
        fileHandler.setFormatter(new SimpleFormatter());
        logger.setLevel(Level.INFO);
        logger.addHandler(fileHandler);
        logger.setUseParentHandlers(false);
    }

    public static void getClientLogCustoms(Logger logger) throws IOException {
        if(!new File("src/main/java/log/logClient").exists()) {
            new File("src/main/java/log/logClient").mkdir();
        }
        FileHandler fileHandler = new FileHandler("src/main/java/log/logClient/logClient.txt");
        fileHandler.setFormatter(new SimpleFormatter());
        logger.setLevel(Level.INFO);
        logger.addHandler(fileHandler);
        logger.setUseParentHandlers(false);
    }
}
