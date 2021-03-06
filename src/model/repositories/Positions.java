package model.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.entities.*;


public class Positions extends Repository<Position> implements IContainer{

    private static Positions instance;
    
    public static Positions getInstance(){
        return instance == null ? instance = new Positions(): instance; 
    }
    
    private Positions(){   
        super.setNameList("select * from position", resultSet -> wrapConstructor(resultSet));      
    }
    
    @Override
    public Map<String, String> getNameList() {
        Map<String, String> map = new HashMap<>();
        currentList.forEach((position) -> {
            map.put(position.getId(), position.getTitle());
        });        
        return map;
    }
        
    private Position wrapConstructor(ResultSet resultSet){
        try{
            return new Position(resultSet.getString("id"), 
                resultSet.getString("title"), 
                resultSet.getString("id_type"));
        } catch (SQLException ex){
            throw new RuntimeException("Error during creation list of Positions: " + ex.getMessage());
        }
    }    
    
    //TODO: implement this method
    @Override
    public Map<String, String> getNameListByIdList(List<String> idList) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
