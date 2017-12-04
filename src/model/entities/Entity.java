package model.entities;

public class Entity{
    
    protected final String id;
    protected final String title;

    //?? 
    protected Entity(){
        this.id = "undefined";
        this.title = "undefined";
    }
        
    protected Entity(String id){
        this.id = id;
        this.title = "undefined";
    }    
    
    protected Entity(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
