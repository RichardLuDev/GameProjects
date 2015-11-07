package richardludev.physicsgame;

import java.util.List;

import richardludev.utilities.componentmodel.ComponentSystem;

public class LogicSystem implements ComponentSystem{

    ILogicEntityManager entityManager;
    
    public LogicSystem(ILogicEntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    @Override
    public void update(double timestep) {
        List<LogicComponent> logicComponents  = this.entityManager.getGameLogicComponents();
        for(LogicComponent logicComponent : logicComponents){
            logicComponent.update(timestep);
        }
    }
}
