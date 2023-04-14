package net.unamed.cmps455.project3;

import net.unamed.cmps455.project3.cpu.Core;
import net.unamed.cmps455.project3.cpu.Dispatcher;
import net.unamed.cmps455.project3.cpu.ReadyQueue;
import net.unamed.cmps455.project3.util.CallbackCaller;
import net.unamed.cmps455.project3.util.CallbackEvent;
import net.unamed.cmps455.project3.util.CallbackManager;

import java.util.function.Consumer;

public class OperatingSystem implements CallbackCaller<CallbackEvent> {


    private final CallbackManager<CallbackEvent> callbackManager;

    private final ReadyQueue readyQueue;
    private final Core[] cores;
    private final Dispatcher[] dispatchers;
    private final DispatchAlgorithm algorithm;

    private boolean exit = false;

    public OperatingSystem(int cores, DispatchAlgorithm algorithm) {
        this.callbackManager = new CallbackManager<>();

        // Create Ready Queue
        readyQueue = new ReadyQueue();

        this.cores = new Core[cores];
        this.dispatchers = new Dispatcher[cores];

        this.algorithm = algorithm;

    }

    public void enter() {

        // Create Cores & Dispatchers
        for (int i = 0; i < cores.length; i++) {
            log("Forking Core Thread %d", i);
            this.cores[i] = new Core(i, this);
            log("Forking Dispatcher Thread %d", i);
            dispatchers[i] = new Dispatcher(i,this, this.cores[i], algorithm);
        }

        System.out.println();
        log("Releasing Dispatchers");
        System.out.println();

        // Start Threads
        for (int i = 0; i < cores.length; i++) {
            dispatchers[i].start();
            cores[i].start();
        }
    }

    public void scheduleTask(Task task) {
        log("Creating Process thread %d", task.id);
        readyQueue.add(task);
    }

    public ReadyQueue getReadyQueue() {
        return readyQueue;
    }

    public boolean isExit() {
        return exit;
    }

    public void exit() {
        exit(0);
    }

    public void exit(int code) {
        exit = true;
        callbackManager.sendCallback(new SystemExitEvent(code));
    }

    private void log(String msg, Object... args) {
        System.out.printf("%-15s | %s%n", "Main Thread", String.format(msg, args));
    }

    @Override
    public void registerCallback(Consumer<CallbackEvent> consumer) {
        callbackManager.registerCallback(consumer);
    }

    public static class SystemExitEvent extends CallbackEvent {

        public final int code;

        public SystemExitEvent(int code) {
            super("system_exit");
            this.code = code;
        }

    }
}
