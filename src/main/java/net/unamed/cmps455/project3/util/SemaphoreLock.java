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

    public boolean isLocked() throws InterruptedException {
        mutex.acquire();
        try {
            return this.locked;
        } finally {
            mutex.release();
        }
    }

    public boolean lock() throws InterruptedException {
        mutex.acquire();

        try {
            if (locked)
                return false;

            locked = true;
            
        } finally {
            mutex.release();
        }

        semaphore.acquire();
        return true;
    }

    public boolean unlock() throws InterruptedException {
        mutex.acquire();

        try {
            if (!locked)
                return false;
            
            locked = false;
            semaphore.release();
            
        } finally {
            mutex.release();
        }

        return true;
    }
}
