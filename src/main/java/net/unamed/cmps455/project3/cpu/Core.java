package net.unamed.cmps455.project3.cpu;

import net.unamed.cmps455.project3.OperatingSystem;
import net.unamed.cmps455.project3.Task;
import net.unamed.cmps455.project3.util.*;

import java.util.Optional;
import java.util.function.Consumer;

public class Core extends Thread implements CallbackCaller<CallbackEvent> {

    private final int ssid;
    private final OperatingSystem os;

    private final CallbackManager<CallbackEvent> callbackManager;

    private final SemaphoreLock sleepLock;

    private Task runningTask;
    private Thread process;

    // By nature, these are only changed while a thread has a mutex
    // Only change in 'onCallback'
    private int exitCode = -1;
    private boolean queueEmpty = false;

    public Core (int ssid, OperatingSystem os) {
        this.callbackManager = new CallbackManager<>();

        os.registerCallback(this::onCallback);
        os.getReadyQueue().registerCallback(this::onCallback);

        this.ssid = ssid;
        this.os = os;

        this.sleepLock = new SemaphoreLock();

    }

    public int getSSID() {
        return ssid;
    }

    /**
     * Schedule a Process on this Core
     * @param task Task to Schedule
     * @param quantum Allotted Quantum to run the Task for
     * @return the replaced Task if one was already running.
     */
    public Optional<Task> scheduleProcess(Task task, int quantum) {
        Task oldTask = runningTask;
        if (oldTask != null) {
            oldTask.setAllottedBurst(0);
        }

        task.setAllottedBurst(quantum);
        this.runningTask = task;

        wake();

        return Optional.ofNullable(oldTask);
    }

    @Override
    public void run() {
        while(exitCode == -1 || (exitCode == 1 && !queueEmpty) || runningTask != null) {
            if (runningTask == null) {
                // Sleep until task is scheduled
                log_debug("No Work... Sleeping");
                sleep();
            } else {
                if (process == null) {
                    log_debug("Starting Process %d", runningTask.id);
                    process = new Thread(runningTask);
                    process.start();
                    log_debug("Now checking lifecycle");
                }

                if (runningTask.getRemainingBurst() < 1) {
                    log_debug("Ending Process");
                    process = null;
                    runningTask = null;
                    log_debug("Sending Process Finished Callback");
                    callbackManager.sendCallback(new CallbackEvent("task_complete"));
                }
            }
        }

        log_debug("Core Closing");
    }


    @Override
    public void registerCallback(Consumer<CallbackEvent> consumer) {
        this.callbackManager.registerCallback(consumer);
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
            queueEmpty = true;
        }

        wake();
    }

    private void sleep() {
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
        System.out.printf("%-15s | %s%n", "Core " + ssid, String.format(msg, args));
    }

    private void log_debug(String msg, Object... args) {
        System.out.printf("%-15s | [Debug] %s%n", "Core " + ssid, String.format(msg, args));
    }
}
