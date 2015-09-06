package richardludev.physics;

import richardludev.componentmodel.ComponentBase;
import richardludev.componentmodel.MovementComponent;
import richardludev.componentmodel.PositionComponent;

public class PhysicsComponent extends ComponentBase{
    
    private MovementComponent moveComponent;
    private PositionComponent posComponent;
    
    private double mass;
    
    public PhysicsComponent(long id, MovementComponent movementComponent, PositionComponent positionComponent) {
        super(id);

        this.moveComponent = movementComponent;
        this.posComponent = positionComponent;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }
    
    public MovementComponent getMovementComponent(){
        return this.moveComponent;
    }
    
    public PositionComponent getPositionComponent(){
        return this.posComponent;
    }
}
