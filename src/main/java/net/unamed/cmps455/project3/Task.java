package net.unamed.cmps455.project3;

public class Task implements Runnable {

    public final int id;
    public final int maxBurst;
    private int burst = 0;
    private int allottedBurst;

    public Task(int id, int burst) {
        this.id = id;
        this.maxBurst = burst;
    }

    public int getCurrentBurst() {
        return this.burst;
    }

    public int getMaxBurst() {
        return this.maxBurst;
    }

    public int getRemainingBurst() {
        return getMaxBurst() - getCurrentBurst();
    }

    public void setAllottedBurst(int allottedBurst) {
        this.allottedBurst = allottedBurst;
    }

    @Override
    public final void run() {
        while (burst++ < Math.min(maxBurst, allottedBurst)) {
            tick();
        }
    }

    public void tick() {
        System.out.printf("Proc. Thread %-2d | CPU 0; CB:%d; MB:%d; RB:%d; AB:%d%n", id, burst, maxBurst,
                maxBurst-burst, allottedBurst);
    }
}
