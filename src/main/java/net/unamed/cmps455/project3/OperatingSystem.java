package net.unamed.cmps455.project3;

import net.unamed.cmps455.project3.cpu.Core;
import net.unamed.cmps455.project3.cpu.Dispatcher;
import net.unamed.cmps455.project3.cpu.ReadyQueue;

public class OperatingSystem {

    public ReadyQueue readyQueue;
    private Core[] cores;
    private Dispatcher[] dispatchers;

    private boolean exit = false;

    public OperatingSystem(int cores) {
        this.cores = new Core[cores];
        this.dispatchers = new Dispatcher[cores];

        // Create Ready Queue
        readyQueue = new ReadyQueue();

        // Create Cores & Dispatchers
        for (int i = 0; i < cores; i++) {
            log("Forking dispatcher %d", i);
            this.cores[i] = new Core(i, this);
            dispatchers[i] = new Dispatcher(i,this, this.cores[i]);
        }

        log("Now releasing dispatchers");

        // Start Threads
        for (int i = 0; i < cores; i++) {
            dispatchers[i].start();
        }
    }

    public void scheduleTask(Process task) {
        readyQueue.add(task);
    }

    public ReadyQueue getReadyQueue() {
        return readyQueue;
    }

    public boolean isExit() {
        return exit;
    }

    // Does not 100% work yet
    // Need to add a check for running processes
    public void exitOnQueueEmpty() {
        while (readyQueue.hasQueued()) {
            Thread.yield();
        };
        log("Exiting");
        exit = true;
    }

    private void log(String msg, Object... args) {
        System.out.printf("%-15s | %s%n", "Main Thread", String.format(msg, args));
    }
}
