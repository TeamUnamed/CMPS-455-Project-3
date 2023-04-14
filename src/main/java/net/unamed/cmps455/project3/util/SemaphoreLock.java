package net.unamed.cmps455.project3.util;

import java.util.concurrent.Semaphore;

public class SemaphoreLock {

    private final Semaphore mutex;
    private final Semaphore semaphore;

    private boolean locked = false;

    public SemaphoreLock() {
        this.mutex = new Semaphore(1);
        this.semaphore = new Semaphore(0);
    }

    public boolean isLocked() {
        boolean locked = false;
        try {
            mutex.acquire();
            locked = this.locked;
            mutex.release();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        return locked;
    }

    public void lock() throws InterruptedException {
        mutex.acquire();
        if (locked) {
            mutex.release();
            return;
        }

        locked = true;
        mutex.release();
        semaphore.acquire();
    }

    public void unlock() throws InterruptedException {
        mutex.acquire();
        if (!locked) {
            mutex.release();
            return;
        }

        locked = false;
        semaphore.release();
        mutex.release();
    }
}
