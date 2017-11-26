package model.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DB.DBUtils;
import utils.DateUtils;

public class ZRepository {
    
    private Date zDate;
    
    public ZRepository(){
        updateZdate();
    }
    
    public final void updateZdate(){
        try(Connection connection = DBUtils.getInstance().getConnection()){
            PreparedStatement updateDate = connection.prepareStatement("SELECT max(create_time) date from z");
            ResultSet rs = updateDate.executeQuery();
            rs.next();
            Date temp = rs.getDate("date");            
            zDate = temp != null ? temp : new Date();
        }catch (SQLException ex){
            Logger.getLogger(ZRepository.class.getName()).log(Level.WARNING, ex.getMessage());
        }
    }
    
    public boolean isLarger(){
        return DateUtils.compareDate(zDate);
    }
    
    public void createNewZ(){
        try(Connection connection = DBUtils.getInstance().getConnection()){
            PreparedStatement insertZ = connection.prepareStatement("INSERT INTO z (sumZ) value (0)");
            insertZ.executeUpdate();
        }catch(SQLException ex){
            Logger.getLogger(ZRepository.class.getName()).log(Level.WARNING, ex.getMessage());
        }
    }
}
