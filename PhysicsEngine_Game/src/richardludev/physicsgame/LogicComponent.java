package richardludev.physicsgame;

import richardludev.utilities.componentmodel.ComponentBase;

public abstract class LogicComponent extends ComponentBase{

    public LogicComponent(long id) {
        super(id);
    }
    
    public abstract void update(double timeStep);
}
