package richardludev.physics;

import richardludev.utilities.componentmodel.ComponentBase;
import richardludev.utilities.componentmodel.MovementComponent;
import richardludev.utilities.componentmodel.PositionComponent;

public class PhysicsComponent extends ComponentBase{
    
    private MovementComponent moveComponent;
    private PositionComponent posComponent;
    
    private double mass;
    
    public PhysicsComponent(long id) {
        super(id);

        this.moveComponent = null;
        this.posComponent = null;
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
    
    public void setMovementComponent(MovementComponent movementComponent){
        this.moveComponent = movementComponent;
    }
    
    public PositionComponent getPositionComponent(){
        return this.posComponent;
    }
    
    public void setPositionComponent(PositionComponent positionComponent){
        this.posComponent = positionComponent;
    }
}
