package net.unamed.cmps455.project3.cpu;

import net.unamed.cmps455.project3.DispatchAlgorithm;
import net.unamed.cmps455.project3.OperatingSystem;
import net.unamed.cmps455.project3.Task;
import net.unamed.cmps455.project3.util.CallbackEvent;
import net.unamed.cmps455.project3.util.SemaphoreLock;

import java.util.Optional;

/**
 * Abstract representation of a Dispatcher
 */
public class Dispatcher extends Thread {

    private final int ssid;
    private final ReadyQueue readyQueue;
    private final Core cpu;

    // Just a class of methods to use
    private final DispatchAlgorithm algorithm;

    // Has internal mutex to protect access
    private final SemaphoreLock sleepLock;

    // These are only changed internally
    // Do not change in 'onCallback'
    private boolean waitingOnCore = false;
    private boolean waitingOnTask = false;

    // By nature, these are only changed while a thread has a mutex
    // Only change in 'onCallback'
    private int exitCode = -1;
    private boolean queueEmpty = false;

    public Dispatcher(int ssid, OperatingSystem os, Core cpu, DispatchAlgorithm algorithm) {
        this.ssid = ssid;
        this.readyQueue = os.getReadyQueue();
        this.cpu = cpu;
        this.algorithm = algorithm;

        os.registerCallback(this::onCallback);
        readyQueue.registerCallback(this::onCallback);
        cpu.registerCallback(this::onCallback);

        this.sleepLock = new SemaphoreLock();

        log("Using CPU %d",cpu.getSSID());
    }

    @Override
    public void run() {
        log("Running %s", algorithm);

        while(exitCode == -1 || (exitCode == 1 && !queueEmpty)) {
            // Early wakeup >> Go back to sleep
            if ((queueEmpty && waitingOnTask) || waitingOnCore) {
                log_debug("Early WakeUp... Nothing to Do... Sleeping");
                sleep();
                continue;
            }

            // Select next Task
            Optional<Task> task = readyQueue.processQueue(algorithm::pickFromQueue);

            if (task.isPresent()) {
                Task process = task.get();
                System.out.println();
                log_debug("Selected Process %-2d", process.id);
                log("Running Process %-2d", process.id);

                // Schedule Next Task on core
                log_debug("Sending Task to Core %d", cpu.getSSID());
                Optional<Task> interrupted = cpu.scheduleProcess(process, process.maxBurst);

                // Return any interrupted Task to the queue
                interrupted.ifPresent(readyQueue::add);

                // Sleep until Task finishes running on core.
                log_debug("Waiting for Task to complete");
                waitingOnCore = true;
                sleep();
                waitingOnCore = false;
            } else {
                // Sleep until a Task is added to ready queue.
                log_debug("No Processes... Sleeping");
                waitingOnTask = true;
                sleep();
                waitingOnTask = false;
            }

        }
        log_debug("Dispatcher %d Closing", ssid);
    }

    private void onCallback(CallbackEvent event) {
        log_debug("Callback <%s> received...", event.name);
        if (event instanceof OperatingSystem.SystemExitEvent exitEvent) {
            exitCode = exitEvent.code;
            log_debug("Exit Code <%d> received...", exitCode);
        }

        if (event.name.equalsIgnoreCase("queue_empty")) {
            queueEmpty = true;
        }

        if (event.name.equalsIgnoreCase("task_queued")) {
            queueEmpty = false;
        }

        wake();
    }

    private void sleep() {
        if (exitCode == 0)
            return;

        try {
            sleepLock.lock();
        } catch (InterruptedException e) {
            log(e.getMessage());
        }
    }

    private void wake() {
        try {
            sleepLock.unlock();
        } catch (InterruptedException e) {
            log(e.getMessage());
        }
    }

    private void log(String msg, Object... args) {
        System.out.printf("%-15s | %s%n", "Dispatcher " + ssid, String.format(msg, args));
    }

    private void log_debug(String msg, Object... args) {
        System.out.printf("%-15s | [Debug] %s%n", "Dispatcher " + ssid, String.format(msg, args));
    }
}
