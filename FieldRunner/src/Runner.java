public class Runner {
    private final Thread thread;
    private final Court court;
    private final int speed;
    private final int id;
    private float speedMultiplier = 1f;

    public Runner(int id, int speed, Court court) {
        thread = new Thread(this::run);
        this.court = court;
        this.speed = speed;
        this.id = id;
    }

    private void run() {
        int dt = 250;
        while (!isFinished()) {
            try {
                Thread.sleep(dt);
                runInCourt(dt);
                cheat();
                accelerate();
            } catch (InterruptedException e) {
                speedMultiplier *= 0.5;
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("runner " + id + " finished");
    }

    private int calcDistance(int dt) {
        return (int) (speed * speedMultiplier * dt / 1000);
    }

    private boolean isFinished() {
        return court.isFinished(id);
    }

    private void runInCourt(int dt) {
        court.run(id, calcDistance(dt));
    }

    private void accelerate() {
        if (speedMultiplier > 1) {
            speedMultiplier = 1;
        } else {
            speedMultiplier *= 1.1;
        }
    }
    
    public void start() {
        thread.start();
    }

    public void interrupt() {
        thread.interrupt();
    }

    public void cheat() {
        court.tryPush(id);
    }
}
