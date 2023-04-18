package net.unamed.cmps455.project3;

import net.unamed.cmps455.project3.util.SemaphoreLock;
import net.unamed.cmps455.project3.util.SyncBool;

public abstract class SystemComponent extends Thread {

    protected final int ssid;
    protected final OperatingSystem os;
    protected final SyncBool exit;
    private final SemaphoreLock sleepLock;

    private boolean active = false;

    public SystemComponent(int ssid, OperatingSystem os) {
        this.ssid = ssid;
        this.os = os;
        this.sleepLock = new SemaphoreLock();

        this.exit = new SyncBool(false);
    }

    public final int getSSID() {
        return ssid;
    }

    public final boolean isActive() {
        return active;
    }

    public final boolean isAwake() {
        try {
            return !sleepLock.isLocked();
        } catch (InterruptedException e) {
            log(e.getMessage());
        }

        return false;
    }

    protected final void sleep() {
        if (exit.get())
            return;

        try {
            os.sendMessage(this, "system_pre_sleep");

            if (!sleepLock.isLocked() && !exit.get()) {
                os.sendMessage(this, "system_sleep");
                sleepLock.lock();
            }
        } catch(InterruptedException e) {
            log(e.getMessage());
        }
    }

    protected final void wake() {
        try {
            os.sendMessage(this, "system_pre_wake");

            if (sleepLock.isLocked()) {
                sleepLock.unlock();
                os.sendMessage(this, "system_wake");
            }
        } catch(InterruptedException e) {
            log(e.getMessage());
        }
    }

    protected final void force_wake() {
        try {
            sleepLock.unlock();
            os.sendMessage(this, "system_wake");
        } catch (InterruptedException e) {
            log(e.getMessage());
        }
    }

    @Override
    public final void run() {
        active = true;
        os.sendMessage(this, "system_enter");

        while (!exit.get()) {
            tick();
            os.sendMessage(this, "system_cycle");
        }

        os.sendMessage(this, "system_exit");
        active = false;
        log("Exiting.");
    }

    public abstract void tick();

    public void sendMessage(String message, Object... payload) {

    }

    public abstract void log(String msg, Object... args);

    public abstract void log_debug(String msg, Object... args);
}
