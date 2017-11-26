package model.entities;

public class Position extends Entity{

    private final String idType;
    
    public Position(String id, String name, String idType){
        super(id, name);
        this.idType = idType;
    }

    public String getidType() {
        return idType;
    }
}
