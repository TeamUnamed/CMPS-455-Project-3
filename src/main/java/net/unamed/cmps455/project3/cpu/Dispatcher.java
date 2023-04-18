package net.unamed.cmps455.project3.cpu;

import net.unamed.cmps455.project3.OperatingSystem;
import net.unamed.cmps455.project3.SystemComponent;
import net.unamed.cmps455.project3.Task;
import net.unamed.cmps455.project3.util.SyncBool;

import java.util.Optional;
import java.util.concurrent.Semaphore;

/**
 * Abstract representation of a Dispatcher
 * @implNote The Dispatcher is always aware of its assigned Core, but the Core
 * is not aware of the Dispatcher. If the Core needs to 'send' information to
 * the Dispatcher, it must be passed to the system then to the Dispatcher.
 */
public class Dispatcher extends SystemComponent {

    private final ReadyQueue readyQueue;
    private final Core core;

    // Just a class of methods to use
    private final DispatchAlgorithm algorithm;

    private final SyncBool taskRunning;
    private final SyncBool taskQueued;

    private final Semaphore mutex;

    private Task returnedTask;

    int currentBurst = Integer.MAX_VALUE;
    int maxBurst = Integer.MAX_VALUE;

    public Dispatcher(int ssid, OperatingSystem os, Core core, DispatchAlgorithm algorithm) {
        super(ssid, os);
        this.readyQueue = os.getReadyQueue();
        this.core = core;
        this.algorithm = algorithm;

        taskRunning = new SyncBool(false);
        taskQueued = new SyncBool(false);

        mutex = new Semaphore(1);

        log("Using CPU %d", core.getSSID());
        log("Running %s", algorithm);
    }

    @Override
    public void tick() {
        Task returnedTask = null;

        try {
            mutex.acquire();
            try {
                returnedTask = this.returnedTask;
                this.returnedTask = null;
            } finally {
                mutex.release();
            }
        } catch (InterruptedException e) {
            log(e.getMessage());
        }

        // Early wakeup >> Go back to sleep
        if (returnedTask == null && !taskQueued.get() && (taskRunning.get() || !readyQueue.hasQueued())) {
            sleep();

        } else if (returnedTask != null) {
            os.scheduleTask(returnedTask);

        } else {
            taskQueued.set(false);

            // Select next Task
            Optional<Task> optionalTask = readyQueue.processQueue(algorithm::pickFromQueue);

            if (optionalTask.isPresent()) {

                // Schedule Next Task on core
                Task task = optionalTask.get();
                System.out.println();
                log("Dispatching Task %-2d", task.id);
                this.currentBurst = task.getCurrentBurst();
                this.maxBurst = task.getMaxBurst();
                taskRunning.set(true);
                core.scheduleTask(task);
            }
        }
    }

    @Override
    public void sendMessage(String message, Object... payload) {
        log_debug("[Msg] Received message: \"%s\"", message);

        switch (message) {
            case "system_exit":
                int exitCode = (int) payload[0];
                if (exitCode == 1 || (exitCode == 2 && !taskRunning.get() && !readyQueue.hasQueued()))
                    exit.set(true);
                break;
            case "task_queued":
                if (!algorithm.isPreemptive())
                    return;

                int currentBurst = (int) payload[0];
                int maxBurst = (int) payload[1];
                if (currentBurst < this.currentBurst && maxBurst < this.maxBurst)
                    taskQueued.set(true);
                break;

            case "task_stop:quantum_reached":
            case "task_stop:preempted":
                try {
                    mutex.acquire();
                    try {
                        returnedTask = (Task) payload[0];
                    } finally {
                        mutex.release();
                    }
                } catch (InterruptedException e) {
                    log(e.getMessage());
                }
            case "task_stop:completed":
                taskRunning.set(false);
                break;
        }

        wake();
    }

    @Override
    public void log(String msg, Object... args) {
        System.out.printf("%-15s | %s%n", "Dispatcher " + ssid, String.format(msg, args));
    }

    @Override
    public void log_debug(String msg, Object... args) {
//        System.out.printf("%-15s | [Debug] %s%n", "Dispatcher " + ssid, String.format(msg, args));
    }

    @Override
    public String toString() {
        return "Dispatcher " + ssid;
    }
}