package controllers;

import java.util.logging.Level;
import java.util.logging.Logger;
import model.repositories.*;

public class BillManager {
    private Bill bill;
    
    public String getBillasString(){
        return bill == null || bill.toString().equals("") ? "Bill is not created yet" : bill.toString();
    }
    
    public void createBill(){
        if(bill == null) 
            bill = new Bill();
    }
    
    public void clearBill(){
        bill = null;
    }
            
    public void sell(){
        bill.saveBill();
    }
    
    public void deleteByIndex(int index){
        try{
            bill.getList().remove(index - 1);
        }catch(IndexOutOfBoundsException ex){
            Logger.getLogger(Bill.class.getName()).log(Level.WARNING, ex.getMessage());
        }
    }
    
    public void addPositionToBill(String id){
        bill.addDataToBill(Positions.getInstance().getEntityById(id));
    }
    
    public void addVolumeToBill(String id){
        bill.addDataToBill(Volumes.getInstance().getEntityById(id));
    }    
}
