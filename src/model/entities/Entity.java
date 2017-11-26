package model.entities;

public class Entity {
    
    protected final String id;
    protected final String name;

    //?? 
    protected Entity(){
        this.id = "undefined";
        this.name = "undefined";
    }
        
    protected Entity(String id){
        this.id = id;
        this.name = "undefined";
    }    
    
    protected Entity(String id, String value) {
        this.id = id;
        this.name = value;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
