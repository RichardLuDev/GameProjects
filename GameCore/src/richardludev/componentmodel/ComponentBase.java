package richardludev.componentmodel;

public abstract class ComponentBase {
    private final long ID;
    
    public ComponentBase(long id){
        this.ID = id;
    }
   
    public long GetID(){
        return this.ID;
    }
}
