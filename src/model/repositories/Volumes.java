package model.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import model.entities.Volume;
import java.util.Map;
import java.util.stream.Collectors;

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
            return new Volume(resultSet.getString("id"), 
                resultSet.getString("title"));
        } catch (SQLException ex){
            throw new RuntimeException("Error during creation list of Volumes: " + ex.getMessage());
        }            
    }
    
    @Override
    public Map<String, String> getNameList() {
        Map<String, String> map = new HashMap<>();
        currentList.forEach((volume) -> {
            map.put(volume.getId(), volume.getTitle());
        });        
        return map;
    }  

    @Override
    public Map<String, String> getNameListByIdList(List<String> idList) {
        Map<String, String> map = new HashMap<>();
        List<Volume> newList = list.stream()
                .filter(entity -> idList.stream().anyMatch(id -> id.equals(entity.getId())))
                .collect(Collectors.toList());
        newList.forEach(volume -> map.put(volume.getId(), volume.getTitle()));
        return map;
    }

    public void setCurrentListByPosition(String id) {
        currentList = list;        
        currentList = list.stream()
                        .filter(volume -> Menu.getInstance().getCurrentList().stream()
                            .filter(menuItem -> menuItem.getIdPosition().equals(id))
                            .map(menuItem -> menuItem.getIdVolume())
                            .collect(Collectors.toList())
                            .contains(volume.getId()))
                        .collect(Collectors.toList());
    }    
    
    public List<Volume> getVolumeListForCurrentPosition(List<String> idVolume){
        return list.stream()
                .filter(volume -> idVolume.contains(volume.getId()))
                .collect(Collectors.toList());
    }
}
