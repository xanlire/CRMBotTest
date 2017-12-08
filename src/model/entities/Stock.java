package model.entities;

public class Stock extends Entity{
    
    private final int totalQty;

    public Stock(String id, String title, int totalQty){
        super(id, title);
        this.totalQty = totalQty;
    }
    
    public int getTotalQty() {
        return totalQty;
    }
}
    