/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.PropertiesInitializer;

/**
 *
 * @author HP
 */
public class DBUtils {
//    private final String url = "jdbc:mysql://localhost:3306/crmsystem";    
//    private final String username = "root";
//    private final String password = "admin";
    private Connection connection;
    private static DBUtils instance;
    private PropertiesInitializer properties;

    private DBUtils(){
        properties = new PropertiesInitializer("res/DB_config.properties");
    }
    
    public Connection getConnection() {
        try {
            if(connection == null || connection.isClosed())
                connect();
        } catch (SQLException ex) {
//            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }
    
    public static DBUtils getInstance(){
        if(instance == null)
            instance = new DBUtils();
        return instance;
    }
    
    private void connect(){
          try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            connection = DriverManager.getConnection(
                    properties.getProperty("url"),
                    properties.getProperty("username"),
                    properties.getProperty("password")); 
            System.out.println("Connection established succesfull");
        } catch (SQLException ex) {            
            Logger.getLogger(DBUtils.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
    public void closeConnection(){
        
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}


