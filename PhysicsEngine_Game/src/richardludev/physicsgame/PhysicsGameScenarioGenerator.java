package richardludev.physicsgame;

import java.util.Random;

import richardludev.physics.PhysicsComponent;
import richardludev.physics.PhysicsSystem;
import richardludev.utilities.componentmodel.MovementComponent;
import richardludev.utilities.componentmodel.PositionComponent;

public class PhysicsGameScenarioGenerator {
    
    public static void GenerateOrbitScenario(EntityManager entityManager, PhysicsSystem physicsSystem){
        long i = 0;
        
        Random random = new Random(9001);
        
        double mass = 10;
        double y = GraphicsSystem.WINDOW_Y/2;
        double x, vy;
        
        for(; i < 10; i++){
            x = random.nextInt(GraphicsSystem.WINDOW_X/2);
            x += GraphicsSystem.WINDOW_X/4;
            vy = 1000/(x - GraphicsSystem.WINDOW_X/2);
            if (Math.abs(vy) > 20){ 
                vy = 15*Math.signum(vy);
            }
           
            MovementComponent moveComponent = new MovementComponent(i);
            PositionComponent posComponent = new PositionComponent(i);
            PhysicsComponent phyComponent = new PhysicsComponent(i);
            
            phyComponent.setMovementComponent(moveComponent);
            phyComponent.setPositionComponent(posComponent);
            
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
        
        gravityLogicComponent.setPath(300, GraphicsSystem.WINDOW_X/2, GraphicsSystem.WINDOW_Y/2, 5);
        posComponent.setX(GraphicsSystem.WINDOW_X/2);
        posComponent.setY(GraphicsSystem.WINDOW_Y/2);
        
        entityManager.addPositionComponent(posComponent);
        entityManager.addGameLogicComponent(gravityLogicComponent);
        
        i++;
        
        posComponent = new PositionComponent(i);
        gravityLogicComponent = new GravityLogicComponent(i, entityManager, physicsSystem);
        
        gravityLogicComponent.setPath(10, GraphicsSystem.WINDOW_X/2, GraphicsSystem.WINDOW_Y/2, 2);
        posComponent.setX(0);
        posComponent.setY(0);
        
        entityManager.addPositionComponent(posComponent);
        entityManager.addGameLogicComponent(gravityLogicComponent);
    }
    
    public static void GenerateSingleOrbitScenario(EntityManager entityManager, PhysicsSystem physicsSystem){
        long i = 0;
        
        Random random = new Random(9002);
        
        double mass = 10;
        double y = GraphicsSystem.WINDOW_Y/2;
        double x, vy;
        
        x = random.nextInt(GraphicsSystem.WINDOW_X);
        vy = 1000/(x - GraphicsSystem.WINDOW_X/2);
        if (Math.abs(vy) > 20){ 
            vy = 15*Math.signum(vy);
        }
       
        MovementComponent moveComponent = new MovementComponent(i);
        PositionComponent posComponent = new PositionComponent(i);
        PhysicsComponent phyComponent = new PhysicsComponent(i);
        
        phyComponent.setMovementComponent(moveComponent);
        phyComponent.setPositionComponent(posComponent);
        
        phyComponent.setMass(mass);
        moveComponent.setVY(vy);
        posComponent.setX(x);
        posComponent.setY(y);
        
        entityManager.addPhysicsComponent(phyComponent);
        entityManager.addMovementComponent(moveComponent);
        entityManager.addPositionComponent(posComponent);
        
        i++;
        
        posComponent = new PositionComponent(i);
        GravityLogicComponent gravityLogicComponent = new GravityLogicComponent(i, entityManager, physicsSystem);
        
        posComponent.setX(GraphicsSystem.WINDOW_X/2);
        posComponent.setY(GraphicsSystem.WINDOW_Y/2);
        
        entityManager.addPositionComponent(posComponent);
        entityManager.addGameLogicComponent(gravityLogicComponent);
    }
    
    public static void GenerateWeirdOrbitScenario(EntityManager entityManager, PhysicsSystem physicsSystem){
        long i = 0;
        
        Random random = new Random(9001);
        
        double mass = 10;
        double y = GraphicsSystem.WINDOW_Y/2;
        double x, vy;
        
        for(; i < 1; i++){
            x = random.nextInt(GraphicsSystem.WINDOW_X);
            vy = 0;
           
            MovementComponent moveComponent = new MovementComponent(i);
            PositionComponent posComponent = new PositionComponent(i);
            PhysicsComponent phyComponent = new PhysicsComponent(i);
            
            phyComponent.setMovementComponent(moveComponent);
            phyComponent.setPositionComponent(posComponent);
            
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
        
        posComponent.setX(GraphicsSystem.WINDOW_X/2 + 400);
        posComponent.setY(GraphicsSystem.WINDOW_Y/2);
        
        entityManager.addPositionComponent(posComponent);
        entityManager.addGameLogicComponent(gravityLogicComponent);
        
        i++;
        
        posComponent = new PositionComponent(i);
        gravityLogicComponent = new GravityLogicComponent(i, entityManager, physicsSystem);
        
        posComponent.setX(GraphicsSystem.WINDOW_X/2);
        posComponent.setY(GraphicsSystem.WINDOW_Y/2);
        
        entityManager.addPositionComponent(posComponent);
        entityManager.addGameLogicComponent(gravityLogicComponent);
    }
}
