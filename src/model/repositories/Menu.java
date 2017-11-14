/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import model.entities.BillPosition;
import model.entities.MenuItem;

/**
 *  
 * @author HP
 */
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
            return new MenuItem(resultSet.getString("idMenu"), 
                    resultSet.getString("idPosition"), 
                    resultSet.getString("idVolume"), 
                    resultSet.getFloat("cost"));
        } catch (SQLException ex){
            throw new RuntimeException("Error during creation list of MenuItems: " + ex.getMessage());
        }
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
    
//    public float getCost(BillPosition position){
//        return list.stream().filter(item -> item.getIdPosition().equals(position.getPosition().getId()) &&
//                item.getIdVolume().equals(position.getVolume().getId()))
//                .findAny()
//                .get()
//                .getCost();
//    }
}
