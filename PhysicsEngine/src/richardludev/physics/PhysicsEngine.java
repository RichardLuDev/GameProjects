package richardludev.physics;

import java.util.ArrayList;
import java.util.List;

public class PhysicsEngine {

    List<Entity> entities;
    List<IForceSourcePositionBased> forceSourcePositionBased;
    
    public PhysicsEngine() {
        entities = new ArrayList<Entity>();
        forceSourcePositionBased = new ArrayList<IForceSourcePositionBased>();
    }
    
    //TODO: Need to refactor big time
    public void update(int timeStep){
        clearAccelerations();
        resolveAccelerations();
        resolveVelocities(timeStep);
        resolvePositions(timeStep);
    }
    
    private void clearAccelerations(){
        for (Entity entity : entities){
            entity.ax = 0;
            entity.ay = 0;
        }
    }
    
    private void resolveAccelerations(){
        for (IForceSourcePositionBased forceSource : forceSourcePositionBased){
            for (Entity entity : entities){
                Force force = forceSource.getForceOnEntity(entity.mass, entity.x, entity.y);
                applyForceToEntity(entity, force);
            }
        }
    }

    private void resolveVelocities(int timeStep){
        double timeStepSeconds = 0.001*timeStep;
        
        for (Entity entity : entities){
            entity.vx += entity.ax*timeStepSeconds;
            entity.vy += entity.ay*timeStepSeconds;
        }
    }
    
    private void resolvePositions(int timeStep){
        double timeStepSeconds = 0.001*timeStep;
        
        for (Entity entity : entities){
            entity.x += entity.vx*timeStepSeconds;
            entity.y += entity.vy*timeStepSeconds;
        }
    }
    
    private void applyForceToEntity(Entity entity, Force force){
        entity.ax += force.xComp/entity.mass;
        entity.ay += force.yComp/entity.mass;
    }

    
    public void addEntity(EntityDef entityDef){
        entities.add(createEntityFromDef(entityDef));
    }
    
    public void addEntities(EntityDef[] entityDefs){
        for(EntityDef entityDef: entityDefs){
            entities.add(createEntityFromDef(entityDef));
        }
    }
    
    public EntityDef[] getEntities(){
        List<EntityDef> entityDefs = new ArrayList<EntityDef>();
        for(Entity entity: entities){
            entityDefs.add(createDefFromEntity(entity));
        }
        return entityDefs.toArray(new EntityDef[entityDefs.size()]);
    }
    
    public void addForceSource(IForceSourcePositionBased forceSource){
        forceSourcePositionBased.add(forceSource);
    }
    
    public IForceSourcePositionBased[] getForceSourcePositionBased(){
        return forceSourcePositionBased.toArray(new IForceSourcePositionBased [forceSourcePositionBased.size()]);
    }
    
    private Entity createEntityFromDef(EntityDef entityDef){
        Entity entity = new Entity();
        entity.id = entityDef.getID();
        entity.mass = entityDef.getMass();
        entity.x = entityDef.getX();
        entity.y = entityDef.getY();
        entity.vx = entityDef.getVX();
        entity.vy = entityDef.getVY();
        entity.ax = 0;
        entity.ay = 0;
        
        return entity;
    }
    
    private EntityDef createDefFromEntity(Entity entity){
        EntityDef def = new EntityDef(entity.id, entity.mass, entity.x, entity.y);
        return def;
    }
}
