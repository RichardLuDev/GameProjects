package richardludev.physicsgame;

import java.util.List;

public interface ILogicEntityManager {
    
    public List<LogicComponent> getGameLogicComponents();
    
    public LogicComponent getGameLogicComponent(long id);
    
}
