package richardludev.physicsgame;

import java.util.List;

import richardludev.componentmodel.Position2DComponent;
import richardludev.physics.Force;
import richardludev.physics.PhysicsComponent;
import richardludev.physics.PhysicsSystem;

public class GravityLogicComponent extends LogicComponent {

    EntityManager entityManager;
    PhysicsSystem physicsEngine;
    
    private static final double MAX_FORCE = 30;
    private static final double FORCE_MAG = 100000;
    
    public GravityLogicComponent(long id, EntityManager entityManager, PhysicsSystem physicsEngine) {
        super(id);
        this.entityManager = entityManager;
        this.physicsEngine = physicsEngine;
    }

    @Override
    public void update(double timeStep) {
        
        Position2DComponent thisPosComponent = entityManager.getPosition2DComponent(this.GetID());
        if (thisPosComponent == null) {return;}
        
        //Only apply force to entities with physics components
        List<PhysicsComponent> phyComponents = entityManager.getPhysicsComponents();
        for(PhysicsComponent phyComponent : phyComponents){
            
            if(phyComponent.GetID() == this.GetID()) continue;
            
            Position2DComponent posComponent = entityManager.getPosition2DComponent(phyComponent.GetID());
            double posX = posComponent.getX();
            double posY = posComponent.getY();
            double distSq = (thisPosComponent.getX()-posX)*(thisPosComponent.getX()-posX) 
                            + (thisPosComponent.getY()-posY)*(thisPosComponent.getY()-posY);
            
            double force = FORCE_MAG*phyComponent.getMass()/distSq;
            if(force > MAX_FORCE) {force = MAX_FORCE;}
            
            double angle = Math.atan2(thisPosComponent.getY()-posY,thisPosComponent.getX()-posX);
            
            physicsEngine.addForce(phyComponent.GetID(), new Force(force*Math.cos(angle), force*Math.sin(angle)));
        }
    }
}
