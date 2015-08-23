package richardludev.physics;

import java.util.List;

import richardludev.componentmodel.Movement2DComponent;
import richardludev.componentmodel.Position2DComponent;

public interface IPhysicsEntityManager {

    public List<PhysicsComponent> getPhysicsComponents();
    public List<Position2DComponent> getPosition2DComponents();
    public List<Movement2DComponent> getMovement2DComponents();
    
    public PhysicsComponent getPhysicsComponent(long id);
    public Position2DComponent getPosition2DComponent(long id);
    public Movement2DComponent getMovement2DComponent(long id);
}
