package logger;

import java.util.logging.Level;

public class Logger {
    public static void printError(Exception e, Class value)
    {
        java.util.logging.Logger logger = java.util.logging.Logger.getLogger(value.getClass().getName());
        logger.info("Class Name: "+value.getName());
        logger.log(Level.ALL, "Exception occur:", e);
    }
    public static void printUnsupportedOption(Class value, int option)
    {
        java.util.logging.Logger logger = java.util.logging.Logger.getLogger(value.getClass().getName());
        logger.info("Class Name: "+value.getName());
        logger.info("Unsupported Option: "+ option);
    }
}
