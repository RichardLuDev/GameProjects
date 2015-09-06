package richardludev.physicsgame;

import java.util.Random;

import richardludev.componentmodel.MovementComponent;
import richardludev.componentmodel.PositionComponent;
import richardludev.physics.PhysicsComponent;
import richardludev.physics.PhysicsSystem;

public class PhysicsGameScenarioGenerator {
    
    public static void GenerateOrbitScenario(EntityManager entityManager, PhysicsSystem physicsSystem){
        long i = 0;
        
        Random random = new Random(9001);
        
        double mass = 10;
        double y = GraphicsSystem.WINDOW_Y/2;
        double x, vy;
        
        for(; i < 10; i++){
            x = random.nextInt(GraphicsSystem.WINDOW_X);
            vy = 1000/(x - GraphicsSystem.WINDOW_X/2);
            if (Math.abs(vy) > 20){ 
                vy = 15*Math.signum(vy);
            }
           
            MovementComponent moveComponent = new MovementComponent(i);
            PositionComponent posComponent = new PositionComponent(i);
            PhysicsComponent phyComponent = new PhysicsComponent(i, moveComponent, posComponent);
            
            phyComponent.setMass(mass);
            moveComponent.setVY(vy);
            posComponent.setX(x);
            posComponent.setY(y);
            
            entityManager.addPhysicsComponent(phyComponent);
            entityManager.addMovementComponent(moveComponent);
            entityManager.addPositionComponent(posComponent);
        }
        
        PositionComponent posComponent = new PositionComponent(i);
        GravityLogicComponent gravityLogicComponent = new GravityLogicComponent(i, entityManager, physicsSystem);
        
        gravityLogicComponent.setPath(200, GraphicsSystem.WINDOW_X/2, GraphicsSystem.WINDOW_Y/2, 5);
        posComponent.setX(GraphicsSystem.WINDOW_X/2);
        posComponent.setY(GraphicsSystem.WINDOW_Y/2);
        
        entityManager.addPositionComponent(posComponent);
        entityManager.addGameLogicComponent(gravityLogicComponent);
    }
}
