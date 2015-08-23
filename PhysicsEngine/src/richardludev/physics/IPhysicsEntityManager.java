package richardludev.physics;

import java.util.List;
import java.util.Map;

import richardludev.componentmodel.Acceleration2DComponent;
import richardludev.componentmodel.Position2DComponent;
import richardludev.componentmodel.Velocity2DComponent;

public interface IPhysicsEntityManager {

    public List<PhysicsComponent> getPhysicsComponents();
    public List<Position2DComponent> getPosition2DComponents();
    public List<Velocity2DComponent> getVelocity2DComponents();
    public List<Acceleration2DComponent> getAcceleration2DComponents();
    
    public PhysicsComponent getPhysicsComponent(long id);
    public Position2DComponent getPosition2DComponent(long id);
    public Velocity2DComponent getVelocity2DComponent(long id);
    public Acceleration2DComponent getAcceleration2DComponent(long id);
}
