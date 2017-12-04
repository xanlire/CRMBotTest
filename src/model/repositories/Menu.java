package model.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import model.entities.MenuItem;

public class Menu extends Repository<MenuItem>{

    private static Menu instance;
    
    private Menu(){
        super.setNameList("select * from menu", resultSet -> wrapConstructor(resultSet));
    }
  
    public static Menu getInstance(){
        return instance == null ? instance = new Menu() : instance;
    }
    
    private MenuItem wrapConstructor(ResultSet resultSet){
        try{
            return new MenuItem(resultSet.getString("id"), 
                    resultSet.getString("id_position"), 
                    resultSet.getString("id_volume"), 
                    resultSet.getInt("cost"));
        } catch (SQLException ex){
            throw new RuntimeException("Error during creation list of MenuItems: " + ex.getMessage());
        }
    }    
 
    public MenuItem getItemByIds(String idPosition, String idVolume){
        return list.stream()
                .filter(x -> x.getIdPosition().equals(idPosition) & x.getIdVolume().equals(idVolume))
                .findAny()
                .get();        
    }
    
    public float getCost(String idPosition, String idVolume){
        Optional<MenuItem> menuItem = list.stream()
                .filter(x -> x.getIdPosition().equals(idPosition) & x.getIdVolume().equals(idVolume))
                .findAny();
        return menuItem.get().getCost();
    }
    
    public List<String> getAvailableListOfVolumes(String idPosition){
        return list.stream()
                .filter(item -> idPosition.equals(item.getIdPosition()))
                .map(item -> item.getIdVolume())
                .collect(Collectors.toList());                
    }
    
    public List<MenuItem> getMenuItemListById(String idPosition){
        return list.stream()
                .filter(item -> idPosition.equals(item.getIdPosition()))
                .collect(Collectors.toList());    
    }
}
