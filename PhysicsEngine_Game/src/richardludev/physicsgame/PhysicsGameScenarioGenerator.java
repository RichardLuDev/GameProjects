package richardludev.physicsgame;

import java.util.Random;

import richardludev.componentmodel.Acceleration2DComponent;
import richardludev.componentmodel.Position2DComponent;
import richardludev.componentmodel.Velocity2DComponent;
import richardludev.physics.ForceSourceGravity;
import richardludev.physics.IForceSourcePositionBased;
import richardludev.physics.PhysicsComponent;
import richardludev.physics.PhysicsEngine;

public class PhysicsGameScenarioGenerator {
    
    public static void GenerateOrbitScenario(EntityManager entityManager, PhysicsEngine physicsEngine){
        Random random = new Random(9001);
        
        double mass = 10;
        double y = PhysicsGameGraphicsSystem.WINDOW_Y/2;
        double x, vy;
        
        for(long i = 0; i < 10; i++){
            x = random.nextInt(PhysicsGameGraphicsSystem.WINDOW_X);
            vy = 1000/(x - PhysicsGameGraphicsSystem.WINDOW_X/2);
            
            PhysicsComponent phyComponent = new PhysicsComponent(i);
            Acceleration2DComponent accelComponent = new Acceleration2DComponent(i);
            Velocity2DComponent velComponent = new Velocity2DComponent(i);
            Position2DComponent posComponent = new Position2DComponent(i);
            
            phyComponent.setMass(mass);
            velComponent.setVY(vy);
            posComponent.setX(x);
            posComponent.setY(y);
            
            entityManager.addPhysicsComponent(phyComponent);
            entityManager.addAcceleration2DComponent(accelComponent);
            entityManager.addVelocity2DComponent(velComponent);
            entityManager.addPosition2DComponent(posComponent);
        }
        
        IForceSourcePositionBased forceSource = new ForceSourceGravity(PhysicsGameGraphicsSystem.WINDOW_X/2, PhysicsGameGraphicsSystem.WINDOW_Y/2, 100000);
        physicsEngine.addForceSource(forceSource);
    }
}
