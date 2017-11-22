package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PropertiesInitializer {
    
    private Properties properties;
    
    public PropertiesInitializer(String path){
        try(FileInputStream fis = new FileInputStream(path)) {

            properties = new Properties();
            properties.load(fis);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PropertiesInitializer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PropertiesInitializer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getProperty(String key){
        return properties.getProperty(key);
    }
    
}
