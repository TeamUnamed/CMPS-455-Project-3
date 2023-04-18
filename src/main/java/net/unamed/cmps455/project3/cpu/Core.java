package net.unamed.cmps455.project3.cpu;

import net.unamed.cmps455.project3.OperatingSystem;
import net.unamed.cmps455.project3.SystemComponent;
import net.unamed.cmps455.project3.Task;

import java.util.concurrent.Semaphore;

public class Core extends SystemComponent {

    private Task nextTask;
    private Task currentTask;
    private Thread process;

    private final Semaphore mutex;

    public Core (int ssid, OperatingSystem os) {
        super(ssid, os);
        this.mutex = new Semaphore(1);
    }

    public void scheduleTask(Task task) {
        try {
            mutex.acquire();
            try {
                this.nextTask = task;
                wake();
            } finally {
                mutex.release();
            }
        } catch (InterruptedException e) {
            log(e.getMessage());
        }
    }

    @Override
    public void tick() {
        Task nextTask = null;

        try {
            mutex.acquire();
            try {
                nextTask = this.nextTask;
                this.nextTask = null;
            } finally {
                mutex.release();
            }
        } catch (InterruptedException e) {
            log(e.getMessage());
        }

        if (currentTask == null && nextTask == null) // No Tasks
            sleep();

        else if (nextTask != null) { // Start the next task
            if (currentTask != null) {
                currentTask.setAllottedBurst(0);
                os.sendMessage(this, "task_stop:preempted", currentTask);
            }

            currentTask = nextTask;
            log("Forking Process Thread %d for Task %d", nextTask.id, nextTask.id);
            process = new Thread(currentTask);
            process.start();
        } else { // Monitor running task
            if (currentTask.getRemainingAllottedBurst() <= 0) {
                if (currentTask.getCurrentBurst() >= currentTask.getMaxBurst())
                    os.sendMessage(this, "task_stop:completed", currentTask);
                else
                    os.sendMessage(this, "task_stop:quantum_reached", currentTask);

                currentTask = null;
                process = null;
            }
        }
    }

    @Override
    public void sendMessage(String message, Object... payload) {
        log_debug("[Msg] Received message: \"%s\"", message);

        if (message.equals("system_exit")) {
            int exitCode = (int) payload[0];
            log_debug("exit::"+exitCode);
            if (exitCode == 1 || exitCode == 21) {
                exit.set(true);
                force_wake();
                return;
            }
        }

        wake();
    }

    @Override
    public void log(String msg, Object... args) {
        System.out.printf("%-15s | %s%n", "Core " + ssid, String.format(msg, args));
    }

    @Override
    public void log_debug(String msg, Object... args) {
//        System.out.printf("%-15s | [Debug] %s%n", "Core " + ssid, String.format(msg, args));
    }

    @Override
    public String toString() {
        return "Core " + ssid;
    }
}
