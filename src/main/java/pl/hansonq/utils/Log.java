package pl.hansonq.utils;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

public class Log {
    private static Logger logger;

    private static File logFile=new File("Json_Eksport.log");

    public static void log(String message){

        Handler handler = null;
        try {
            handler = new FileHandler("test.log",20000, 500);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Logger.getLogger("").addHandler(handler);
        logger.info("Logging start");


    }



}
