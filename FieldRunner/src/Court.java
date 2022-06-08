import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Court {
    private final Map<Integer, Integer> locations;
    private final int endLine;
    
    private final List<Runner> runners;
    private final Random random;

    public Court() {
        random = new Random();
        locations = new HashMap<>();
        endLine = 100;
        runners = new ArrayList<>();
        initRunners(3);
    }

    private void initRunners(int n) {
        for (int i=0; i<n; ++i) {
            runners.add(new Runner(i, 2, this));
        }
        for (int i=0; i<n; ++i) {
            runners.get(i).start();
        }
    }

    public void run(int runner, int distance) {
        locations.computeIfAbsent(runner, i -> 0);
        int loc = locations.compute(runner, (k,v) -> v + distance);
        
    }

    public boolean isFinished(int runner) {
        return locations.getOrDefault(runner, 0) >= endLine;
    }

    public void tryPush(int cheater) {
        if (random.nextFloat() < 0.2) {
            int cheated;
            if (cheater + 1 >= runners.size()) cheated = cheater - 1;
            else if (cheater - 1 < 0) cheated = cheater + 1;
            else cheated = random.nextFloat() < 0.5 ? cheater - 1 : cheater + 1;
            System.out.println(cheater + " tries to push " + cheated);
        }
    }
}
