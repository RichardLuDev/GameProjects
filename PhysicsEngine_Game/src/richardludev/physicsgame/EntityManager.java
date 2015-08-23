package richardludev.physicsgame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import richardludev.componentmodel.Movement2DComponent;
import richardludev.componentmodel.Position2DComponent;
import richardludev.physics.IPhysicsEntityManager;
import richardludev.physics.PhysicsComponent;

public class EntityManager implements IPhysicsEntityManager {

    private List<PhysicsComponent> phyComponents;
    private List<Movement2DComponent> moveComponents;
    private List<Position2DComponent> posComponents;
    
    private Map<Long, PhysicsComponent> phyComponentsMap;
    private Map<Long, Movement2DComponent> moveComponentsMap;
    private Map<Long, Position2DComponent> posComponentsMap;
    
    public EntityManager(){
        phyComponents = new ArrayList<PhysicsComponent>();
        moveComponents = new ArrayList<Movement2DComponent>();
        posComponents = new ArrayList<Position2DComponent>();
        
        phyComponentsMap = new HashMap<Long, PhysicsComponent>();
        moveComponentsMap = new HashMap<Long, Movement2DComponent>();
        posComponentsMap = new HashMap<Long, Position2DComponent>();
    }
    
    
    @Override
    public List<PhysicsComponent> getPhysicsComponents() {
        List<PhysicsComponent> components = new ArrayList<PhysicsComponent>(phyComponents);
        return components;
    }

    @Override
    public PhysicsComponent getPhysicsComponent(long id) {
        return phyComponentsMap.get(id);
    }
    
    public void addPhysicsComponent(PhysicsComponent component){
        this.phyComponentsMap.put(component.GetID(),component);
        this.phyComponents.add(component);
        Collections.sort(this.phyComponents);
    }

    @Override
    public List<Movement2DComponent> getMovement2DComponents() {
        List<Movement2DComponent> components = new ArrayList<Movement2DComponent>(moveComponents);
        return components;
    }

    @Override
    public Movement2DComponent getMovement2DComponent(long id) {
        return moveComponentsMap.get(id);
    }
    
    public void addMovement2DComponent(Movement2DComponent component){
        this.moveComponentsMap.put(component.GetID(),component);
        this.moveComponents.add(component);
        Collections.sort(this.moveComponents);
    }

    @Override
    public List<Position2DComponent> getPosition2DComponents() {
        List<Position2DComponent> components = new ArrayList<Position2DComponent>(posComponents);
        return components;
    }

    @Override
    public Position2DComponent getPosition2DComponent(long id) {
        return posComponentsMap.get(id);
    }
    
    public void addPosition2DComponent(Position2DComponent component){
        this.posComponentsMap.put(component.GetID(),component);
        this.posComponents.add(component);
        Collections.sort(this.posComponents);
    }
}
