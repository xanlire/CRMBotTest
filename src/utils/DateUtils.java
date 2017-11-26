package utils;

import java.util.Date;

public class DateUtils{
    
    private final static PropertiesInitializer PROPERTIES = new PropertiesInitializer("res/date_config.properties");
    
    public static Date addDay(Date date){
        // 86400000 miliseconds = 24 hours
        return new Date(date.getTime() + 86400000);
    }
    
    public static boolean compareDate(Date date){
        // shift_length in hours    
        return new Date().getTime() > date.getTime() 
                + Long.parseLong(PROPERTIES.getProperty("shift_length")) * 60 * 60 * 1000;
    }
    
}
