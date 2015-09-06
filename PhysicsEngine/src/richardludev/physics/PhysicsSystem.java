package richardludev.physics;

import java.util.ArrayList;
import java.util.List;

import richardludev.componentmodel.ComponentSystem;
import richardludev.componentmodel.Movement2DComponent;
import richardludev.componentmodel.Position2DComponent;

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
    
    public void clearForces(){
        this.forces.clear();
    }
    
    @Override
    public void update(double timeStep){
        clearAccelerations();
        resolveAccelerations();
        resolveVelocities(timeStep);
        resolvePositions(timeStep);
    }

    private void clearAccelerations(){
        List<Movement2DComponent> moveComponents = entityManager.getMovement2DComponents();
        for (Movement2DComponent moveComponent : moveComponents){
            moveComponent.setAX(0);
            moveComponent.setAY(0);
        }
    }
    
    private void resolveAccelerations(){
        for (TargetForce targetforce : forces){
            applyForceToEntity(targetforce.getTarget(), targetforce.getForce());
        }
    }

    private void resolveVelocities(double timeStep){
        List<Movement2DComponent> moveComponents = entityManager.getMovement2DComponents();
        
        for (Movement2DComponent moveComponent : moveComponents){
            moveComponent.addToVX(moveComponent.getAX()*timeStep);
            moveComponent.addToVY(moveComponent.getAY()*timeStep);
        }
    }
    
    private void resolvePositions(double timeStep){
        List<Position2DComponent> posComponents = entityManager.getPosition2DComponents();
        
        for (Position2DComponent posComponent : posComponents){
            Movement2DComponent moveComponent = entityManager.getMovement2DComponent(posComponent.GetID());
            posComponent.addToX(moveComponent.getVX()*timeStep);
            posComponent.addToY(moveComponent.getVY()*timeStep);
        }
    }
    
    private void applyForceToEntity(Long id, Force force){
        PhysicsComponent phyComponent = entityManager.getPhysicsComponent(id);
        Movement2DComponent moveComponent = entityManager.getMovement2DComponent(id);
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
