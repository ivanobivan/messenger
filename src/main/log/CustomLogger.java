package main.log;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.*;

/**
 * Created by Ivan on 28.06.2017.
 */
public class CustomLogger {

    public static void getServerLogCustoms(Logger logger) throws IOException {
        FileHandler fileHandler = new FileHandler("src/log/logServer.txt");
        fileHandler.setFormatter(new SimpleFormatter());
        logger.setLevel(Level.INFO);
        logger.addHandler(fileHandler);
        logger.setUseParentHandlers(false);
    }

    public static void getClientLogCustoms(Logger logger) throws IOException {
        FileHandler fileHandler = new FileHandler("src/log/logClient/logClient.txt");
        fileHandler.setFormatter(new SimpleFormatter());
        logger.setLevel(Level.INFO);
        logger.addHandler(fileHandler);
        logger.setUseParentHandlers(false);
    }

    public static void clearServerLogs(){
        File[] files = new File("src/log").listFiles();
        assert files != null;
        for(File file : files){
            if(file.isFile()){ file.delete(); }
        }
    }

    public static void clearClientLogs(){
        File[] files = new File("src/log/logClient").listFiles();
        assert files != null;
        for(File file : files){
            if(file.isFile()){ file.delete(); }
        }
    }

}
