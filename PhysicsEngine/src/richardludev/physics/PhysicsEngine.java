package richardludev.physics;

import java.util.ArrayList;
import java.util.List;

public class PhysicsEngine {

    List<Entity> entities;
    
    public PhysicsEngine() {
        entities = new ArrayList<Entity>();
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
    
    public void update(int timeStep){
        
    }
    
    private void resolveAcceleration(){
        
    }
    
    private Entity createEntityFromDef(EntityDef entityDef){
        Entity entity = new Entity();
        entity.id = entityDef.getID();
        entity.mass = entityDef.getMass();
        entity.x = entityDef.getX();
        entity.y = entityDef.getY();
        entity.vx = 0;
        entity.vy = 0;
        entity.ax = 0;
        entity.ay = 0;
        
        return entity;
    }
    
    private EntityDef createDefFromEntity(Entity entity){
        EntityDef def = new EntityDef(entity.id, entity.mass, entity.x, entity.y);
        return def;
    }
}
