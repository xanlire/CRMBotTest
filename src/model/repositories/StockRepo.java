package model.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.entities.Stock;
import utils.DB.DBUtils;

public class StockRepo extends Repository<Stock>{
    
    private static StockRepo instance;
    
    public StockRepo getInstance(){
        return instance == null ? instance = new StockRepo() : instance;
    }
    
    private StockRepo(){
        updateStockCache();
    }
    
    public final void updateStockCache(){
        super.setNameList("select * from stock", resultSet -> wrapConstructor(resultSet));
    }
    
    private Stock wrapConstructor(ResultSet resultSet){
        try{
            return new Stock(resultSet.getString("id"),
                    resultSet.getString("title"),
                    resultSet.getShort("total_qty"));
        }catch (SQLException ex){
            throw new RuntimeException("Error during creation stock list " + ex.getMessage());
        }
    }
    
    public void update(){
        try(Connection connection = DBUtils.getInstance().getConnection()){
            PreparedStatement updateStatement = connection.prepareStatement("update stock set total_qty = ?, where id = ?");
            for(Stock stock : list){
                updateStatement.setInt(0, stock.getTotalQty());
                updateStatement.setInt(1, Integer.parseInt(stock.getId()));
                updateStatement.execute();
            }            
        } catch(SQLException ex){
            Logger.getLogger(StockRepo.class.getName()).log(Level.WARNING, ex.getMessage());
        }
        updateStockCache();
    }
}
