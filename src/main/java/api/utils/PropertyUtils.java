package api.utils;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PropertyUtils {

    private static Properties prop;
    private final static Logger logger = Logger.getLogger("PropertyUtils");

    public static void loadPropertyFile() {
        FileInputStream fis;
        prop = new Properties();
        try {
            fis = new FileInputStream("./config.properties");
            prop.load(fis);
        } catch (java.io.IOException e) {
            logger.log(Level.INFO, "Could not load Property File : " + e.getMessage());
        }
    }

    public static String getValue(String key){
        return prop.getProperty(key);
    }
}
