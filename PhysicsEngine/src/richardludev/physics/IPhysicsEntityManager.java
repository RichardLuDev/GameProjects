package richardludev.physics;

import java.util.List;

import richardludev.componentmodel.MovementComponent;
import richardludev.componentmodel.PositionComponent;

public interface IPhysicsEntityManager {

    public List<PhysicsComponent> getPhysicsComponents();
    public PhysicsComponent getPhysicsComponent(long id);
}
