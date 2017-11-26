package model.entities;

public class MenuItem extends Entity {
    
    private final String idPosition;
    private final String idVolume;
    private final float cost;

    public MenuItem(String id, String idPosition, String idVolume, float cost) {
        super(id);
        this.idPosition = idPosition;
        this.idVolume = idVolume;
        this.cost = cost;
    }

    public float getCost() {
        return cost;        
    }
    
    public String getIdPosition(){
        return idPosition;
    }
    
    public String getIdVolume(){
        return idVolume;
    }
}
