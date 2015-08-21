package richardludev.physicsgame;

import java.util.Random;

import richardludev.physics.EntityDef;
import richardludev.physics.ForceSourceGravity;
import richardludev.physics.IForceSourcePositionBased;
import richardludev.physics.PhysicsEngine;

public class PhysicsGameScenarioGenerator {
    
    public static void GenerateOrbitScenario(PhysicsEngine physicsEngine){
        Random random = new Random(9001);
        double mass = 10;
        double y = PhysicsGameGraphicsSystem.WINDOW_Y/2;
        double vx = 0;
        for(long i = 0; i < 10; i++){
            double x = random.nextInt(PhysicsGameGraphicsSystem.WINDOW_X);
            double vy = 1000/(x - PhysicsGameGraphicsSystem.WINDOW_X/2);
            EntityDef entityDef = new EntityDef(i, mass, x, y, vx, vy);
            physicsEngine.addEntity(entityDef);
        }
        
        IForceSourcePositionBased forceSource = new ForceSourceGravity(PhysicsGameGraphicsSystem.WINDOW_X/2, PhysicsGameGraphicsSystem.WINDOW_Y/2, 100000);
        physicsEngine.addForceSource(forceSource);
    }
}
