package net.unamed.cmps455.project3.cpu;

import net.unamed.cmps455.project3.OperatingSystem;
import net.unamed.cmps455.project3.Process;

import java.util.Optional;

public class Core {

    private final int ssid;
    private final OperatingSystem os;

    private Process process;
    private Thread processThread;

    private Runnable callback;

    public Core (int ssid, OperatingSystem os) {
        this.ssid = ssid;
        this.os = os;
    }

    public int getSSID() {
        return ssid;
    }

    public Optional<Process> scheduleProcess(Process process) {
        Process oldProcess = this.process;
        if (this.process != null) {
            synchronized (callback) {
                callback.notifyAll();
            }
        }

        this.process = process;
        this.process.setCallback(this::onTaskFinish);
        processThread = new Thread(process);
        processThread.start();

        return Optional.ofNullable(oldProcess);
    }

    public void registerCallback(Runnable runnable) {
        this.callback = runnable;
    }

    private void onTaskFinish(Process task) {
        callback.run();
    }
}
