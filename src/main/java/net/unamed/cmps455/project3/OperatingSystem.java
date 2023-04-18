package net.unamed.cmps455.project3;

import net.unamed.cmps455.project3.cpu.Core;
import net.unamed.cmps455.project3.cpu.DispatchAlgorithm;
import net.unamed.cmps455.project3.cpu.Dispatcher;
import net.unamed.cmps455.project3.cpu.ReadyQueue;

public class OperatingSystem {

    private final ReadyQueue readyQueue;
    private final Core[] cores;
    private final Dispatcher[] dispatchers;
    private final DispatchAlgorithm algorithm;

    private boolean active = false;
    private int exitFlag = 0;

    public OperatingSystem(int cores, DispatchAlgorithm algorithm) {
        // Create Ready Queue
        readyQueue = new ReadyQueue();

        this.cores = new Core[cores];
        this.dispatchers = new Dispatcher[cores];

        this.algorithm = algorithm;

    }

    public void enter() {
        active = true;
        System.out.println("\n"+readyQueue);

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

    /**
     * Adds a Task to the ready queue to be schedules on a {@link Core} by its complementary {@link Dispatcher}.
     * @param task the Task to add to the queue
     */
    public void scheduleTask(Task task) {

        readyQueue.add(task);
        if (active) {
            log("Scheduling Task %d", task.id);

            System.out.println("\n" + readyQueue);

            for (Dispatcher dispatcher : dispatchers) {
                if (dispatcher != null) {
                    dispatcher.sendMessage("task_queued", task.getCurrentBurst(), task.getMaxBurst());
                }
            }

        }
    }

    public ReadyQueue getReadyQueue() {
        return readyQueue;
    }

    public int getExitFlag() {
        return this.exitFlag;
    }

    public void exit() {
        exit(1);
    }

    public void exit(int flag) {
        this.exitFlag = flag;
    }

    private void log(String msg, Object... args) {
        System.out.printf("%-15s | %s%n", "Main Thread", String.format(msg, args));
    }

    public void sendMessage(SystemComponent from, String message, Object... payload) {
        if (from == null) return;
        StringBuilder s = new StringBuilder();
        for (Object o : payload)
            s.append(o.toString()).append("; ");

//        log("[Debug] [Msg] Received message from [%s]:: \"%s\" <%s>", from, message, s.toString());

        SystemComponent target = (from instanceof Core) ? dispatchers[from.getSSID()]
                         : (from instanceof Dispatcher) ? cores[from.getSSID()]
                         : null;

        switch (message){
            case "system_pre_sleep":
                if (target == null)
                    from.sendMessage("system_exit", 21);
            case "system_sleep":
                if (target == null)
                    return;
                target.wake();
                break;
            case "system_cycle":
                if (exitFlag != 0)
                    from.sendMessage("system_exit", exitFlag);
                break;
            case "system_exit":

                if (from instanceof Core)
                    cores[from.getSSID()] = null;
                else if (from instanceof Dispatcher)
                    dispatchers[from.getSSID()] = null;

                if (target == null)
                    return;

                target.sendMessage("system_exit", exitFlag);
                break;
            case "task_stop:completed":
            case "task_stop:quantum_reached":
            case "task_stop:preempted":
                if (target == null) return;
                target.sendMessage(message, payload);
                break;
            default:
                break;
        }
    }
}
