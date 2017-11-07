/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 */
public class PropertiesInitializer {
    private FileInputStream fis;
    private Properties properties;
    
    public PropertiesInitializer(String path){
        try {
            
            fis = new FileInputStream(path);
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
