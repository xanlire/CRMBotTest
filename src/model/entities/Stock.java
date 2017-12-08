package model.entities;

public class Stock extends Entity{
    
    private int totalQty;

    public Stock(String id, String title, int totalQty){
        super(id, title);
        this.totalQty = totalQty;
    }

    public void setTotalQty(int totalQty) {
        this.totalQty = totalQty;
    }
    
    public int getTotalQty() {
        return totalQty;
    }
}
    