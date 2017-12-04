package model.repositories;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.entities.*;

import utils.DB.DBUtils;
import utils.DateUtils;


public class Bill {
    
    //TODO: add cash/cashless    
    private float totalCost = 0;
    
    private final List<BillPosition> list = new ArrayList<>();
        
    public void addDataToBill(Entity entity){        
        if(entity instanceof Position){
            list.add(new BillPosition());
            list.get(list.size() - 1).setPosition((Position)entity);
        }
        if(entity instanceof Volume){
            BillPosition position = list.get(list.size() - 1);
            position.setVolume((Volume)entity);   
            MenuItem menuItem = Menu.getInstance().getItemByIds(position.getPosition().getId(),
                    position.getVolume().getId());
            position.setCost(menuItem.getCost());
            position.setIdMenu(menuItem.getId());
            updateTotalCost();
        }        
    }
    
    public void updateTotalCost(){
        totalCost = list.stream().map(posititon -> posititon.getCost()).reduce((m, n) -> m + n).get();
    }
    
    public List<BillPosition> getList(){
        return list;
    }           
    
    public void saveBill(){      
        try (Connection connection = DBUtils.getInstance().getConnection()) {            
            
//            if(DateUtils.compareDate(date))
            /*
            *   Default parameters are:
            *   'idBill' autogenerated
            *   'creation_date' current_timestamp
            */
            PreparedStatement insertBill = connection
                    .prepareStatement("INSERT INTO bill (id_user, total_cost) VALUES (?,?)");
            insertBill.setInt(1, 1);
            insertBill.setInt(2, (int)totalCost);
            insertBill.execute();
            /*
            *   Default parameter for 'positionBill'.'idBill' is 0.
            *   And it was used database before insert trigger, that selects
            *   max 'idBill' parameter in the 'bill' table  
            *   and sets it to the 'positionBill'.'idBill' column.
            *   That's because passes only one parameter into the INSERT query below.
            */
            PreparedStatement insertPositionBill = connection
                    .prepareStatement("INSERT INTO menu_bill (idMenu) VALUES (?)");
            for(BillPosition position : list){                
                insertPositionBill.setString(1, position.getIdMenu());
                insertPositionBill.execute();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Bill.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        resultString.append("\nTotal: ")
                    .append(totalCost/100);
        return resultString.toString();
    }
}
