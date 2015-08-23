package richardludev.physics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import richardludev.componentmodel.Acceleration2DComponent;
import richardludev.componentmodel.Position2DComponent;
import richardludev.componentmodel.Velocity2DComponent;

public class PhysicsEngine {
    
    IPhysicsEntityManager entityManager;
    
    List<IForceSourcePositionBased> forceSourcePositionBased;
    
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
        List<Acceleration2DComponent> accelComponents = entityManager.getAcceleration2DComponents();
        for (Acceleration2DComponent accelComponent : accelComponents){
            accelComponent.setAX(0);
            accelComponent.setAY(0);
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
        List<Velocity2DComponent> velComponents = entityManager.getVelocity2DComponents();
        
        for (Velocity2DComponent velComponent : velComponents){
            Acceleration2DComponent accelComponent = entityManager.getAcceleration2DComponent(velComponent.GetID());
            velComponent.addToVX(accelComponent.getAX()*timeStepSeconds);
            velComponent.addToVY(accelComponent.getAY()*timeStepSeconds);
        }
    }
    
    private void resolvePositions(int timeStep){
        double timeStepSeconds = 0.001*timeStep;
        List<Position2DComponent> posComponents = entityManager.getPosition2DComponents();
        
        for (Position2DComponent posComponent : posComponents){
            Velocity2DComponent velComponent = entityManager.getVelocity2DComponent(posComponent.GetID());
            posComponent.addToX(velComponent.getVX()*timeStepSeconds);
            posComponent.addToY(velComponent.getVY()*timeStepSeconds);
        }
    }
    
    private void applyForceToEntity(Long id, Force force){
        PhysicsComponent phyComponent = entityManager.getPhysicsComponent(id);
        Acceleration2DComponent accelComponent = entityManager.getAcceleration2DComponent(id);
        accelComponent.addToAX(force.xComp/phyComponent.getMass());
        accelComponent.addToAY(force.yComp/phyComponent.getMass());
    }
    
    public void addForceSource(IForceSourcePositionBased forceSource){
        forceSourcePositionBased.add(forceSource);
    }
    
    public IForceSourcePositionBased[] getForceSourcePositionBased(){
        return forceSourcePositionBased.toArray(new IForceSourcePositionBased [forceSourcePositionBased.size()]);
    }
}
