package log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
    private static Date date = new Date();
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss");

    public static void log(String string ){
        System.out.println(simpleDateFormat.format(date) + " - " + string);
    }
    public static void log(int i){
        System.out.println(simpleDateFormat.format(date) + " - " + i);
    }
}
