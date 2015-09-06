package richardludev.physicsgame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import richardludev.componentmodel.MovementComponent;
import richardludev.componentmodel.PositionComponent;
import richardludev.physics.IPhysicsEntityManager;
import richardludev.physics.PhysicsComponent;

public class EntityManager implements IPhysicsEntityManager, ILogicEntityManager {

    private List<PhysicsComponent> phyComponents;
    private List<MovementComponent> moveComponents;
    private List<PositionComponent> posComponents;
    private List<LogicComponent> logicComponents;
    
    private Map<Long, PhysicsComponent> phyComponentsMap;
    private Map<Long, MovementComponent> moveComponentsMap;
    private Map<Long, PositionComponent> posComponentsMap;
    private Map<Long, LogicComponent> logicComponentsMap;
    
    public EntityManager(){
        phyComponents = new ArrayList<PhysicsComponent>();
        moveComponents = new ArrayList<MovementComponent>();
        posComponents = new ArrayList<PositionComponent>();
        logicComponents = new ArrayList<LogicComponent>();
        
        phyComponentsMap = new HashMap<Long, PhysicsComponent>();
        moveComponentsMap = new HashMap<Long, MovementComponent>();
        posComponentsMap = new HashMap<Long, PositionComponent>();
        logicComponentsMap = new HashMap<Long, LogicComponent>();
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
        this.phyComponentsMap.put(component.getID(),component);
        this.phyComponents.add(component);
        Collections.sort(this.phyComponents);
    }

    public List<MovementComponent> getMovementComponents() {
        List<MovementComponent> components = new ArrayList<MovementComponent>(moveComponents);
        return components;
    }

    public MovementComponent getMovementComponent(long id) {
        return moveComponentsMap.get(id);
    }
    
    public void addMovementComponent(MovementComponent component){
        this.moveComponentsMap.put(component.getID(),component);
        this.moveComponents.add(component);
        Collections.sort(this.moveComponents);
    }

    public List<PositionComponent> getPositionComponents() {
        List<PositionComponent> components = new ArrayList<PositionComponent>(posComponents);
        return components;
    }

    public PositionComponent getPositionComponent(long id) {
        return posComponentsMap.get(id);
    }
    
    public void addPositionComponent(PositionComponent component){
        this.posComponentsMap.put(component.getID(),component);
        this.posComponents.add(component);
        Collections.sort(this.posComponents);
    }
    
    @Override
    public List<LogicComponent> getGameLogicComponents() {
        List<LogicComponent> components = new ArrayList<LogicComponent>(logicComponents);
        return components;
    }

    @Override
    public LogicComponent getGameLogicComponent(long id) {
        return logicComponentsMap.get(id);
    }
    
    public void addGameLogicComponent(LogicComponent component){
        this.logicComponentsMap.put(component.getID(),component);
        this.logicComponents.add(component);
        Collections.sort(this.logicComponents);
    }
}
