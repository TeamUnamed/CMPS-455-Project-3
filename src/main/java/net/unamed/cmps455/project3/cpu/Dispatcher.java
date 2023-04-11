package net.unamed.cmps455.project3.cpu;

import net.unamed.cmps455.project3.OperatingSystem;
import net.unamed.cmps455.project3.Process;

import java.util.Iterator;
import java.util.Optional;

/**
 * Abstract representation of a Dispatcher
 */
public class Dispatcher extends Thread {

    private final int ssid;
    private final OperatingSystem os;
    private final Core cpu;

    private final Object callback;

    public Dispatcher(int ssid, OperatingSystem os, Core cpu) {
        this.ssid = ssid;
        this.os = os;
        this.cpu = cpu;

        this.callback = new Object();

        log("Using CPU %d",cpu.getSSID());
    }

    private void log(String msg, Object... args) {
        System.out.printf("%-15s | %s%n", "Dispatcher " + ssid, String.format(msg, args));
    }

    @Override
    public void run() {
        ReadyQueue readyQueue = os.getReadyQueue();

        while(!os.isExit()) {
            Optional<Process> optional = readyQueue.processQueue(this::getNextTaskThread);
            if (optional.isPresent()) {
                cpu.scheduleProcess(optional.get());
                cpu.registerCallback(this::onCallback);
                waitForCallback();
            }
        }
    }

    private void onCallback() {
        synchronized (callback) {
            callback.notify();
        }
    }

    private void waitForCallback() {
        synchronized (callback) {
            try {
                callback.wait();
            } catch (InterruptedException e) {
                log(e.getMessage());
            }
        }
    }

    /*
     * Uses FCFS test code
     */
    /**
     * @see ReadyQueue.QueueProcessor#process(Iterator) 
     */
    private Process getNextTaskThread(Iterator<Process> iterator) {
        Process task = null;

        // Get the first TaskThread from queue and remove
        if (iterator.hasNext()) {
            task = iterator.next();
            iterator.remove();
        }

        return task;
    }
}
