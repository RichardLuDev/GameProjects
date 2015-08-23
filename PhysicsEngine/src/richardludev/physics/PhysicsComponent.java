package richardludev.physics;

import richardludev.componentmodel.ComponentBase;

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
