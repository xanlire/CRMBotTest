package model.entities;

public class Receipt extends Entity{
    
    private String idMenu;
    private String idStock;
    private int qty;

    public Receipt(String idMenu, String idStock, int qty) {
        super();
        this.idMenu = idMenu;
        this.idStock = idStock;
        this.qty = qty;
    }    
    
    public String getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(String idMenu) {
        this.idMenu = idMenu;
    }

    public String getIdStock() {
        return idStock;
    }

    public void setIdStock(String idStock) {
        this.idStock = idStock;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
    
    
}
