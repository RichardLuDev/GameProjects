package richardludev.physics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import richardludev.componentmodel.Movement2DComponent;
import richardludev.componentmodel.Position2DComponent;

public class PhysicsEngine {
    
    private IPhysicsEntityManager entityManager;
    
    private List<IForceSourcePositionBased> forceSourcePositionBased;
    
    public PhysicsEngine(IPhysicsEntityManager entityManager) {
        this.entityManager = entityManager;
        this.forceSourcePositionBased = new ArrayList<IForceSourcePositionBased>();
    }
    
    public void update(int timeStep){
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
        List<PhysicsComponent> phyComponents = entityManager.getPhysicsComponents();
        for (IForceSourcePositionBased forceSource : forceSourcePositionBased){
            for (PhysicsComponent phyComponent : phyComponents){
                Position2DComponent posComponent = entityManager.getPosition2DComponent(phyComponent.GetID());
                Force force = forceSource.getForceOnEntity(phyComponent.getMass(), posComponent.getX(), posComponent.getY());
                applyForceToEntity(phyComponent.GetID(), force);
            }
        }
    }

    private void resolveVelocities(int timeStep){
        double timeStepSeconds = 0.001*timeStep;
        List<Movement2DComponent> moveComponents = entityManager.getMovement2DComponents();
        
        for (Movement2DComponent moveComponent : moveComponents){
            moveComponent.addToVX(moveComponent.getAX()*timeStepSeconds);
            moveComponent.addToVY(moveComponent.getAY()*timeStepSeconds);
        }
    }
    
    private void resolvePositions(int timeStep){
        double timeStepSeconds = 0.001*timeStep;
        List<Position2DComponent> posComponents = entityManager.getPosition2DComponents();
        
        for (Position2DComponent posComponent : posComponents){
            Movement2DComponent moveComponent = entityManager.getMovement2DComponent(posComponent.GetID());
            posComponent.addToX(moveComponent.getVX()*timeStepSeconds);
            posComponent.addToY(moveComponent.getVY()*timeStepSeconds);
        }
    }
    
    private void applyForceToEntity(Long id, Force force){
        PhysicsComponent phyComponent = entityManager.getPhysicsComponent(id);
        Movement2DComponent moveComponent = entityManager.getMovement2DComponent(id);
        moveComponent.addToAX(force.xComp/phyComponent.getMass());
        moveComponent.addToAY(force.yComp/phyComponent.getMass());
    }
    
    public void addForceSource(IForceSourcePositionBased forceSource){
        forceSourcePositionBased.add(forceSource);
    }
    
    public IForceSourcePositionBased[] getForceSourcePositionBased(){
        return forceSourcePositionBased.toArray(new IForceSourcePositionBased [forceSourcePositionBased.size()]);
    }
}
