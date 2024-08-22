
package app.personalprojects.cats_app;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


public final class ConfigProperties {

    private static final Logger LOG = Logger.getLogger(ConfigProperties.class.getName());

    private static ConfigProperties instance;

    private final String catApiKey;
    
    private Properties properties;

    private ConfigProperties() {
        properties = new Properties();
        String path = "conf" + File.separator + "config.properties";
        File file = new File(path);

        try (FileInputStream is = new FileInputStream(file)) {
            properties.load(is);
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }

        catApiKey = properties.getProperty("catapikey");
    }

    public static ConfigProperties getInstance() {
        if (instance == null) {
            instance = new ConfigProperties();
        }
        return instance;
    }

    public String getCatApiKey() {
        return catApiKey;
    }

    public static void dispose() {
        instance = null;
    }
}
