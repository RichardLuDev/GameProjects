package richardludev.componentmodel;

public abstract class ComponentBase implements Comparable<ComponentBase> {
    private final long ID;
    
    public ComponentBase(long id){
        this.ID = id;
    }
   
    public long GetID(){
        return this.ID;
    }
    
    public int compareTo(ComponentBase other){
        return Long.compare(this.GetID(), other.GetID());
    }
}
