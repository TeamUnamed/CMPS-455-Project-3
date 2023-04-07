package net.unamed.cmps455.project3.cpu;

import net.unamed.cmps455.project3.OperatingSystem;

/**
 * Abstract representation of a Dispatcher
 */
public abstract class Dispatcher extends Thread {

    private final OperatingSystem os;
    private final Core cpu;

    public Dispatcher(OperatingSystem os, Core cpu) {
        this.os = os;
        this.cpu = cpu;
    }

    @Override
    public void run() {

    }
}
