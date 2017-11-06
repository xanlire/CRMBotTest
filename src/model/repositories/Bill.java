/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.repositories;

import java.util.ArrayList;
import java.util.List;
import model.entities.*;

/**
 *
 * @author HP
 */
public class Bill {
    private final List<BillPosition> list = new ArrayList<>();
    
    public Bill(){
        createBillPosition();
    }

    public void createBillPosition(){
        list.add(new BillPosition());        
    }
    
    public void addDataToBill(Entity entity){
        
        if(entity instanceof Position){
            list.get(list.size() - 1).setPosition((Position)entity);
        }
        if(entity instanceof Volume){
            list.get(list.size() - 1).setVolume((Volume)entity);
        }
    }
    
    public List<BillPosition> getList(){
        return list;
    }
            
    
    public void insertBill(){
        
    }
    
    @Override
    public String toString(){
        StringBuilder resultString = new StringBuilder();      
        for(int i = 0; i < list.size(); i++){
            resultString.append("/")
                    .append(i + 1)
                    .append(" ")
                    .append(list.get(i))
                    .append("\n");
        }
        return resultString.toString();
    }
}
