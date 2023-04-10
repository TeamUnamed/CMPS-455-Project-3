package net.unamed.cmps455.project3;

import net.unamed.cmps455.project3.cpu.Core;
import net.unamed.cmps455.project3.cpu.Dispatcher;
import net.unamed.cmps455.project3.cpu.ReadyQueue;

public class OperatingSystem {

    public ReadyQueue readyQueue;
    private Core[] cores;
    private Dispatcher[] dispatchers;

    public OperatingSystem(int cores, TaskThread... tasks) {
        this.cores = new Core[cores];
        this.dispatchers = new Dispatcher[cores];

        // Create Ready Queue
        readyQueue = new ReadyQueue();
        for (TaskThread task : tasks) {
            readyQueue.add(task);
        }

        // Create Cores & Dispatchers
        for (int i = 0; i < cores; i++) {
            dispatchers[i] = new Dispatcher(this, null);
        }

        // Start Threads
        for (int i = 0; i < cores; i++) {
            dispatchers[i].start();
        }
    }

    /**
     * Get the ready queue for the CPU's task scheduling.
     * @return the ReadyQueue
     */
    public ReadyQueue getReadyQueue() {
        return readyQueue;
    }
}
