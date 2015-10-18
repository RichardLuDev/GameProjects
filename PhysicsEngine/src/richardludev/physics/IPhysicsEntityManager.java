package richardludev.physics;

import java.util.List;

public interface IPhysicsEntityManager {

    public List<PhysicsComponent> getPhysicsComponents();
    public PhysicsComponent getPhysicsComponent(long id);
}
