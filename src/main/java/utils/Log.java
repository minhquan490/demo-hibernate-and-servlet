package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.log4j.Logger;

public class Log {
    private static Logger logger = Logger.getLogger("");

    public static void getLog(String className, String info, Exception e) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        logger.error("\n" + dtf.format(now) + "\n" + "Error class: " + className + "\n" + info);
        logger.error("Error description", e);
    }
}