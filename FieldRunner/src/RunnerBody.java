import java.util.function.Consumer;

public class RunnerBody {

    private final int maxSpeed;
    private int speed;
    private int acceleration;

    public RunnerBody(int maxSpeed, int fatigue) {
        this.maxSpeed = maxSpeed;
        this.acceleration = fatigue;
        speed = 0;
    }

    public void decreaseSpeed() {
        speed -= acceleration;
    }

    public void startRunning() {
        speed = maxSpeed;
    }
    
    public int getSpeed() {
        return speed;
    }
}
