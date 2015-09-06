package richardludev.physicsgame;

import java.util.Random;

import richardludev.componentmodel.Movement2DComponent;
import richardludev.componentmodel.Position2DComponent;
import richardludev.physics.PhysicsComponent;
import richardludev.physics.PhysicsSystem;

public class PhysicsGameScenarioGenerator {
    
    public static void GenerateOrbitScenario(EntityManager entityManager, PhysicsSystem physicsEngine){
        long i = 0;
        
        Random random = new Random(9001);
        
        double mass = 10;
        double y = PhysicsGameGraphicsSystem.WINDOW_Y/2;
        double x, vy;
        
        for(; i < 10; i++){
            x = random.nextInt(PhysicsGameGraphicsSystem.WINDOW_X);
            vy = 1000/(x - PhysicsGameGraphicsSystem.WINDOW_X/2);
            
            PhysicsComponent phyComponent = new PhysicsComponent(i);
            Movement2DComponent moveComponent = new Movement2DComponent(i);
            Position2DComponent posComponent = new Position2DComponent(i);
            
            phyComponent.setMass(mass);
            moveComponent.setVY(vy);
            posComponent.setX(x);
            posComponent.setY(y);
            
            entityManager.addPhysicsComponent(phyComponent);
            entityManager.addMovement2DComponent(moveComponent);
            entityManager.addPosition2DComponent(posComponent);
        }
        
        Position2DComponent posComponent = new Position2DComponent(i);
        LogicComponent logicComponent = new GravityLogicComponent(i, entityManager, physicsEngine);
        
        posComponent.setX(PhysicsGameGraphicsSystem.WINDOW_X/2);
        posComponent.setY(PhysicsGameGraphicsSystem.WINDOW_Y/2);
        
        entityManager.addPosition2DComponent(posComponent);
        entityManager.addGameLogicComponent(logicComponent);
    }
}
