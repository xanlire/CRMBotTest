package model.entities;

public class BillPosition{        

    private Position position;
    private Volume volume;
    private String idMenu;

    public String getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(String idMenu) {
        this.idMenu = idMenu;
    }
    private float cost;

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setVolume(Volume volume) {
        this.volume = volume;
    }

    public Position getPosition() {
        return position;
    }
    
    public Volume getVolume() {
        return volume;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
            return (position != null ? position.getTitle() + " " : "") 
                    + (volume != null ? volume.getTitle() + " " : "") + cost/100;                
    }
}
