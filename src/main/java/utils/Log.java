package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.log4j.Logger;

public class Log {

    final static Logger logger = Logger.getLogger(Log.class);

    public static void getLog(Object className, String errMessage, Exception ex) {
        try {

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();

            logger.info(dtf.format(now));
            logger.info(errMessage);
            logger.error(className, ex);
        } catch (Exception e) {
            logger.error(Log.class, e);
        }
    }
}