package model.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import model.entities.Receipt;

class Receipts extends Repository<Receipt> {
    
    private static Receipts instance;
    
    public static Receipts getInstance(){
        return instance == null ? instance = new Receipts() : instance;
    }
    
    private Receipts(){
        super.setNameList("select * from receipts", resultSet -> wrapConstructor(resultSet));
    }
    
    private Receipt wrapConstructor(ResultSet resultSet){
        try{
            return new Receipt(resultSet.getString("id_menu"), 
                    resultSet.getString("id_stock"),
                    resultSet.getInt("qty"), 
                    resultSet.getString("id"));
        }catch (SQLException ex){
            throw new RuntimeException("Error during creation receipts list " + ex.getMessage());
        }
    }
    
    public Map<String, Integer> getIdStockByMenu(List<String> idmenu){
        return list.stream()
                .filter(receipt -> idmenu.contains(receipt.getIdMenu()))
                .collect(Collectors.toMap(Receipt::getIdStock, Receipt::getQty));
    }
}
