package richardludev.physics;

import richardludev.componentmodel.Acceleration2DComponent;
import richardludev.componentmodel.ComponentBase;
import richardludev.componentmodel.Position2DComponent;
import richardludev.componentmodel.Velocity2DComponent;

public class PhysicsComponent extends ComponentBase{
    
    private double mass;
    
    public PhysicsComponent(long id) {
        super(id);
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }
}
