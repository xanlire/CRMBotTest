package model.entities;

public class Position extends Entity{

    private final String idType;
    
    public Position(String id, String title, String idType){
        super(id, title);
        this.idType = idType;
    }

    public String getidType() {
        return idType;
    }
}
