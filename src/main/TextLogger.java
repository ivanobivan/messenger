package main;

import java.io.IOException;
import java.util.logging.*;

/**
 * Created by Ivan on 28.06.2017.
 */
public class TextLogger {

    public static void getServerLogCustoms(Logger logger) throws IOException {
        FileHandler fileHandler = new FileHandler("src/log/logServer.txt");
        fileHandler.setFormatter(new SimpleFormatter());
        logger.setLevel(Level.INFO);
        logger.addHandler(fileHandler);
    }

    public static void getClientLogCustoms(Logger logger) throws IOException {
        FileHandler fileHandler = new FileHandler("src/log/logClient/logClient.txt");
        fileHandler.setFormatter(new SimpleFormatter());
        logger.setLevel(Level.INFO);
        logger.addHandler(fileHandler);
    }

}
