package net.unamed.cmps455.project3.cpu;

import net.unamed.cmps455.project3.Task;
import net.unamed.cmps455.project3.util.CallbackCaller;
import net.unamed.cmps455.project3.util.CallbackEvent;
import net.unamed.cmps455.project3.util.CallbackManager;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Optional;
import java.util.concurrent.Semaphore;
import java.util.function.Consumer;
import java.util.function.Function;

public class ReadyQueue implements CallbackCaller<CallbackEvent> {

    private final LinkedList<Task> tasks;
    private final Semaphore queueLock;

    private final CallbackManager<CallbackEvent> callbackManager;

    public ReadyQueue () {
        tasks = new LinkedList<>();
        queueLock = new Semaphore(1);

        this.callbackManager = new CallbackManager<>();
    }

    /**
     * Adds a process to the ReadyQueue.
     * @param task Process to add
     */
    public void add (Task task) {
        try {
            queueLock.acquire();
            tasks.add(task);
            callbackManager.sendCallback(new CallbackEvent("task_queued"));
            queueLock.release();
        } catch (InterruptedException e) {
            System.out.println("Adding interrupted");
        }
    }

    /**`
     * Gets if processes are in the queue or not
     * @return {@code True} if at least one process is in queue;
     *         {@code False} otherwise
     */
    public boolean hasQueued() {
        int size = 0;
        try {
            queueLock.acquire();
            size = tasks.size();
            queueLock.release();
        } catch (InterruptedException e) {
            System.out.println("Getting interrupted");
        }

        return size > 0;
    }

    /**
     * Processes the ReadyQueue using the passed function.
     * @param processor a function that processes the ReadyQueue's iterator
     *                  and <i>optionally</i> returns a Task contained within.
     * @return an optional wrapping the process that may or may not have been returned
     *         by the function.
     */
    public Optional<Task> processQueue(Function<Iterator<Task>, Task> processor) {
        Optional<Task> optional = Optional.empty();
        try {
            queueLock.acquire();

            optional = Optional.ofNullable(processor.apply(tasks.iterator()));

            if (tasks.isEmpty())
                callbackManager.sendCallback(new CallbackEvent("queue_empty"));

            queueLock.release();
        } catch (InterruptedException e) {
            System.out.println("Processing interrupted");
        }

        return optional;
    }

    @Override
    public void registerCallback(Consumer<CallbackEvent> consumer) {
        callbackManager.registerCallback(consumer);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("--------------- Ready Queue ---------------\n");
        for (Task task : tasks) {
            stringBuilder.append(
                    String.format("ID:%4d, Max Burst:%2d, Current Burst:%2d%n", task.id, task.maxBurst, 0)
            );
        }
        stringBuilder.append("-------------------------------------------\n");
        return stringBuilder.toString();
    }
}
