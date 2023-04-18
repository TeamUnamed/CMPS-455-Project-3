package net.unamed.cmps455.project3.util;

import java.util.concurrent.Semaphore;

public class SyncBool {

    private boolean value;
    private final Semaphore mutex;

    public SyncBool(boolean value) {
        this.value = value;
        this.mutex = new Semaphore(1, true);
    }

    public void set(boolean value) {
        try {
            mutex.acquire();
            try {
                this.value = value;
            } finally {
                mutex.release();
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean get() {
        try {
            mutex.acquire();
            try {
                return this.value;
            } finally {
                mutex.release();
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    @Override
    public String toString() {
        try {
            mutex.acquire();

            try {
                return Boolean.toString(value);
            } finally {
                mutex.release();
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        return "NULL";
    }
}
