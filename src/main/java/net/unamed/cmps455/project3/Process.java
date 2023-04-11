package net.unamed.cmps455.project3;

import java.util.function.Consumer;

public class Process implements Runnable {

    public final int id;
    public final int maxBurst;
    private int burst = 0;
    private int allottedBurst;

    private Consumer<Process> callback;

    public Process(int id, int burst) {
        this.id = id;
        this.maxBurst = burst;
    }

    public void setAllottedBurst(int allottedBurst) {
        this.allottedBurst = allottedBurst;
    }

    @Override
    public void run() {
        while (burst < Math.min(maxBurst, allottedBurst)) {
            burst++;
            System.out.printf("Proc. Thread %2d | CPU 0; CB:%d; MB:%d%n", id, burst, maxBurst);
        }

        if (callback != null)
            callback.accept(this);
    }

    public void setCallback(Consumer<Process> callback) {
        this.callback = callback;
    }
}
