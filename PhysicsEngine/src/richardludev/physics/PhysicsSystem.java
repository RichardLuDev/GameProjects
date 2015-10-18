package richardludev.physics;

import java.util.ArrayList;
import java.util.List;

import richardludev.componentmodel.ComponentSystem;
import richardludev.componentmodel.MovementComponent;
import richardludev.componentmodel.PositionComponent;

public class PhysicsSystem implements ComponentSystem{
    
    private IPhysicsEntityManager entityManager;
    private List<TargetForce> forces;
    
    public PhysicsSystem(IPhysicsEntityManager entityManager) {
        this.entityManager = entityManager;
        this.forces = new ArrayList<TargetForce>();
    }
    
    public void addForce(long targetId, Force force){
        Force forceCopy = new Force(force);
        TargetForce targetForce = new TargetForce(targetId, forceCopy);
        this.forces.add(targetForce);
    }
    
    @Override
    public void update(double timeStep){
        clearAccelerations();
        resolveAccelerations();
        resolveVelocities(timeStep);
        resolvePositions(timeStep);
        clearForces();
    }

    void clearAccelerations(){
        List<PhysicsComponent> physicsComponents = entityManager.getPhysicsComponents();
        for (PhysicsComponent physicsComponent : physicsComponents){
            physicsComponent.getMovementComponent().setAX(0);
            physicsComponent.getMovementComponent().setAY(0);
        }
    }
    
    void resolveAccelerations(){
        for (TargetForce targetforce : forces){
            applyForceToEntity(targetforce.getTarget(), targetforce.getForce());
        }
    }

    void resolveVelocities(double timeStep){
        List<PhysicsComponent> physicsComponents = entityManager.getPhysicsComponents();
        
        for (PhysicsComponent physicsComponent : physicsComponents){
            MovementComponent moveComponent = physicsComponent.getMovementComponent();
            moveComponent.addToVX(moveComponent.getAX()*timeStep);
            moveComponent.addToVY(moveComponent.getAY()*timeStep);
        }
    }
    
    void resolvePositions(double timeStep){
        List<PhysicsComponent> physicsComponents = entityManager.getPhysicsComponents();
        
        for (PhysicsComponent physicsComponent : physicsComponents){
            MovementComponent moveComponent = physicsComponent.getMovementComponent();
            PositionComponent posComponent = physicsComponent.getPositionComponent();
            posComponent.addToX(moveComponent.getVX()*timeStep);
            posComponent.addToY(moveComponent.getVY()*timeStep);
        }
    }
    
    void clearForces(){
        this.forces.clear();
    }
    
    void applyForceToEntity(Long id, Force force){
        PhysicsComponent phyComponent = entityManager.getPhysicsComponent(id);
        MovementComponent moveComponent = phyComponent.getMovementComponent();
        moveComponent.addToAX(force.getXComp()/phyComponent.getMass());
        moveComponent.addToAY(force.getYComp()/phyComponent.getMass());
    }
    
    private class TargetForce{
        private final long target;
        private final Force force;
        
        public TargetForce(long target, Force force){
            this.target = target;
            this.force = force;
        }
        
        public long getTarget(){
            return target;
        }
        
        public Force getForce(){
            return force;
        }
    }
}
