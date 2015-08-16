package richardludev.physics;

public interface IForceSourcePositionBased {
    public Force getForceOnEntity(double entityMass, double entityX, double entityY);
}
