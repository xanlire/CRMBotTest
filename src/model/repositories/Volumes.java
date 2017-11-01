/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import model.entities.Volume;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author HP
 */
public class Volumes extends Repository<Volume> implements IContainer{

    private static Volumes instance;
    
    public static Volumes getInstance(){
        return instance == null ? instance = new Volumes() : instance;
    }
    
    public Volumes(){
        super.setNameList("select * from volume", resultSet -> wrapConstructor(resultSet));
    }
    
    private Volume wrapConstructor(ResultSet resultSet){
        try{
            return new Volume(resultSet.getString("idVolume"), 
                resultSet.getString("valueVolume"));
        } catch (SQLException ex){
            throw new RuntimeException("Error during creation list of Volumes: " + ex.getMessage());
        }            
    }
    
    @Override
    public Map<String, String> getNameList() {
//        if (listVolumes.isEmpty()) setNameList();
        Map<String, String> map = new HashMap<>();
        list.forEach((volume) -> {
            map.put(volume.getId(), volume.getName());
        });        
        return map;
    }  

    @Override
    public Map<String, String> getNameListByIdList(List<String> idList) {
        Map<String, String> map = new HashMap<>();
        List<Volume> newList = list.stream()
                .filter(entity -> idList.stream().anyMatch(id -> id.equals(entity.getId())))
                .collect(Collectors.toList());
        newList.forEach(volume -> map.put(volume.getId(), volume.getName()));
        return map;
    }
    
}
