package richardludev.physicsgame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import richardludev.componentmodel.Acceleration2DComponent;
import richardludev.componentmodel.Position2DComponent;
import richardludev.componentmodel.Velocity2DComponent;
import richardludev.physics.IPhysicsEntityManager;
import richardludev.physics.PhysicsComponent;

public class EntityManager implements IPhysicsEntityManager {

    private List<PhysicsComponent> phyComponents;
    private List<Acceleration2DComponent> accelComponents;
    private List<Velocity2DComponent> velComponents;
    private List<Position2DComponent> posComponents;
    
    private Map<Long, PhysicsComponent> phyComponentsMap;
    private Map<Long, Acceleration2DComponent> accelComponentsMap;
    private Map<Long, Velocity2DComponent> velComponentsMap;
    private Map<Long, Position2DComponent> posComponentsMap;
    
    public EntityManager(){
        phyComponents = new ArrayList<PhysicsComponent>();
        accelComponents = new ArrayList<Acceleration2DComponent>();
        velComponents = new ArrayList<Velocity2DComponent>();
        posComponents = new ArrayList<Position2DComponent>();
        
        phyComponentsMap = new HashMap<Long, PhysicsComponent>();
        accelComponentsMap = new HashMap<Long, Acceleration2DComponent>();
        velComponentsMap = new HashMap<Long, Velocity2DComponent>();
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
    public List<Acceleration2DComponent> getAcceleration2DComponents() {
        List<Acceleration2DComponent> components = new ArrayList<Acceleration2DComponent>(accelComponents);
        return components;
    }

    @Override
    public Acceleration2DComponent getAcceleration2DComponent(long id) {
        return accelComponentsMap.get(id);
    }
    
    public void addAcceleration2DComponent(Acceleration2DComponent component){
        this.accelComponentsMap.put(component.GetID(),component);
        this.accelComponents.add(component);
        Collections.sort(this.accelComponents);
    }

    @Override
    public List<Velocity2DComponent> getVelocity2DComponents() {
        List<Velocity2DComponent> components = new ArrayList<Velocity2DComponent>(velComponents);
        return components;
    }

    @Override
    public Velocity2DComponent getVelocity2DComponent(long id) {
        return velComponentsMap.get(id);
    }
    
    public void addVelocity2DComponent(Velocity2DComponent component){
        this.velComponentsMap.put(component.GetID(),component);
        this.velComponents.add(component);
        Collections.sort(this.velComponents);
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
