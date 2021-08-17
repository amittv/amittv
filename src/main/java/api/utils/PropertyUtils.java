package api.utils;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;


public class PropertyUtils {

    private Properties configProps;
    private Properties extensionsProps;
    private final static Logger logger = Logger.getLogger("PropertyUtils");

    public void loadPropertyFile() {
        FileInputStream fis;
        configProps = new Properties();
        try {
            fis = new FileInputStream("./config.properties");
            configProps.load(fis);
        } catch (java.io.IOException e) {
            logger.log(Level.INFO, "Could not load Property File : " + e.getMessage());
        }
    }

    public void loadExtensionsFile() {
        FileInputStream fis;
        extensionsProps = new Properties();
        try {
            fis = new FileInputStream("./src/main/resources/APIExtensions.properties");
            extensionsProps.load(fis);
        } catch (java.io.IOException e) {
            logger.log(Level.INFO, "Could not load Property File : " + e.getMessage());
        }
    }

    public String getValue(String key){
        return configProps.getProperty(key.trim());
    }

    public String getExtension(String actionType) {
        return extensionsProps.getProperty(actionType.trim());
    }

    public void setPropertyValue(String key, Object value){
        PropertiesConfiguration conf = null;
        try {
            conf = new PropertiesConfiguration("config.properties");
            conf.setProperty(key, value);
            conf.save();
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }
}
