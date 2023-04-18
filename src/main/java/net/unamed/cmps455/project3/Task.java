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
        return maxBurst - burst;
    }

    public int getRemainingAllottedBurst() {
        return allottedBurst - burst;
    }

    public void setAllottedBurst(int allottedBurst) {
        this.allottedBurst = Math.min(maxBurst, allottedBurst);
    }

    @Override
    public final void run() {
        if (getRemainingAllottedBurst() <= 0)
            return;

        System.out.printf("Proc. Thread %-2d | On CPU 0; CB=%d; MB=%d; TB=%d; GB=%d%n", id, burst, maxBurst,
                allottedBurst-burst, allottedBurst);

        while (burst++ < Math.min(maxBurst, allottedBurst)) {
            tick();
        }
    }

    public void tick() {
        System.out.printf("Proc. Thread %-2d | Using CPU 0; On Burst %d%n", id, burst);
    }

    @Override
    public String toString() {
        return "Task " + id;
    }
}
