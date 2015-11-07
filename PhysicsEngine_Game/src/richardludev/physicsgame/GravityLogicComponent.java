package richardludev.physicsgame;

import java.util.List;

import richardludev.physics.Force;
import richardludev.physics.PhysicsComponent;
import richardludev.physics.PhysicsSystem;
import richardludev.utilities.componentmodel.PositionComponent;

public class GravityLogicComponent extends LogicComponent {

    EntityManager entityManager;
    PhysicsSystem physicsSystem;
    
    private static final double MAX_MOVE_DIST = 0.15;
    private static final double MAX_FORCE = 15;
    private static final double FORCE_MAG = 50000;
    
    private double timeAccumlation;
    private double pathRadius, pathCenterX, pathCenterY, pathSpeed;
    
    public GravityLogicComponent(long id, EntityManager entityManager, PhysicsSystem physicsSystem) {
        super(id);
        this.entityManager = entityManager;
        this.physicsSystem = physicsSystem;
        
        this.timeAccumlation = 0;
    }

    @Override
    public void update(double timeStep) {
        moveThisEntity(timeStep);
        AttractOtherEntities(timeStep);
    }
    
    public void setPath(double pathRadius, double pathCenterX, double pathCenterY, double pathSpeed){
        this.pathRadius = pathRadius;
        this.pathCenterX = pathCenterX;
        this.pathCenterY = pathCenterY;
        this.pathSpeed = pathSpeed;
    }
    
    private void moveThisEntity(double timeStep){
        timeAccumlation += timeStep;
        
        if (pathRadius == 0) return;
        
        double pathProgress = pathSpeed*timeAccumlation;
        double radian = pathProgress/pathRadius;
        
        double pathLocationX = pathRadius*Math.cos(radian) + pathCenterX;
        double pathLocationY = pathRadius*Math.sin(radian) + pathCenterY;
        
        PositionComponent posComponent = entityManager.getPositionComponent(this.getID());
        double xDiff = pathLocationX - posComponent.getX();
        double yDiff = pathLocationY - posComponent.getY();
        
        double Diff = Math.sqrt(xDiff*xDiff + yDiff*yDiff);
        if(Diff > MAX_MOVE_DIST){
            xDiff = MAX_MOVE_DIST*(xDiff/Diff);
            yDiff = MAX_MOVE_DIST*(yDiff/Diff);
        }
        
        posComponent.addToX(xDiff);
        posComponent.addToY(yDiff);
    }
    
    private void AttractOtherEntities(double timeStep){
        PositionComponent thisPosComponent = entityManager.getPositionComponent(this.getID());
        if (thisPosComponent == null) {return;}
        
        //Only apply force to entities with physics components
        List<PhysicsComponent> phyComponents = entityManager.getPhysicsComponents();
        for(PhysicsComponent phyComponent : phyComponents){
            
            if(phyComponent.getID() == this.getID()) continue;
            
            PositionComponent posComponent = entityManager.getPositionComponent(phyComponent.getID());
            double posX = posComponent.getX();
            double posY = posComponent.getY();
            double distSq = (thisPosComponent.getX()-posX)*(thisPosComponent.getX()-posX) 
                            + (thisPosComponent.getY()-posY)*(thisPosComponent.getY()-posY);
            
            double force = FORCE_MAG*phyComponent.getMass()/distSq;
            if(force > MAX_FORCE) {force = MAX_FORCE;}
            
            double angle = Math.atan2(thisPosComponent.getY()-posY,thisPosComponent.getX()-posX);
            
            physicsSystem.addForce(phyComponent.getID(), new Force(force*Math.cos(angle), force*Math.sin(angle)));
        }
    }
}
