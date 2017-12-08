package model.repositories;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DB.DBUtils;
import model.entities.Entity;


public abstract class Repository<T extends Entity> {
    
    protected List<T> list = new ArrayList<>();
    protected List<T> currentList = new ArrayList<>();
    
    public void setNameList(String query, Function<ResultSet, T> fun){
        
        try(Connection connection = DBUtils.getInstance().getConnection()) {
            ResultSet rs = connection.createStatement().executeQuery(query);
            list = null;
            while(rs.next()){                
                list.add(fun.apply(rs));
            }
            currentList = list;
        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    public T getEntityById(String id){
        return list.stream().
                filter(entity -> id.equals(entity.getId())).
                findFirst().
                get();
    }
    
    public List<T> getCurrentList(){
        return currentList;
    }
}
