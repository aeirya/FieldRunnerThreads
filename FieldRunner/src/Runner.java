public class Runner {
    private static final int SLEEP_INTERVAL = 250;

    private final Thread thread;
    private final RunnerBody body;
    private final int id;
    private final Court court;

    public Runner(int id, int speed, int fatigue, Court court) {
        this.id = id;
        this.court = court;
        
        body = new RunnerBody(speed, fatigue);
        thread = new Thread(this::run);
    }

    private void run() {
        while (!isFinished()) {
            try {
                Thread.sleep(SLEEP_INTERVAL);
                runInCourt(SLEEP_INTERVAL);
            } catch (InterruptedException e) {
                body.startRunning();
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("runner " + id + " finished");
    }

    private int calcDistance(int dt) {
        return body.getSpeed() * dt / 1000;
    }

    private boolean isFinished() {
        return court.isFinished(id);
    }

    private void runInCourt(int dt) {
        court.run(id, calcDistance(dt));
        accelerate();
    }

    private void accelerate() {
        body.decreaseSpeed();
    }
    
    public void start() {
        body.startRunning();
        thread.start();
    }

    public void interrupt() {
        thread.interrupt();
    }

    public void onPassingAnotherRunner(Runner other) {
        other.interrupt();
    }
}
