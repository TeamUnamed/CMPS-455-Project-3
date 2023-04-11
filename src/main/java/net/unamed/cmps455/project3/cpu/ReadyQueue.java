package net.unamed.cmps455.project3.cpu;

import net.unamed.cmps455.project3.Process;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Optional;
import java.util.concurrent.Semaphore;

public class ReadyQueue {

    private final LinkedList<Process> tasks;

    Semaphore queueLock;

    public ReadyQueue () {
        queueLock = new Semaphore(1);
        tasks = new LinkedList<>();
    }

    /**
     * Adds a process to the ReadyQueue.
     * @param task Process to add
     */
    public void add (Process task) {
        try {
            queueLock.acquire();
            tasks.add(task);
            queueLock.release();
        } catch (InterruptedException e) {
            System.out.println("Adding interrupted");
        }
    }

    /**
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

    public Optional<Process> processQueue(QueueProcessor processor) {
        Optional<Process> optional = Optional.empty();
        try {
            queueLock.acquire();

            optional = Optional.ofNullable(processor.process(tasks.iterator()));

            queueLock.release();
        } catch (InterruptedException e) {
            System.out.println("Processing interrupted");
        }

        return optional;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("--------------- Ready Queue ---------------\n");
        for (Process task : tasks) {
            stringBuilder.append(
                    String.format("ID:%4d, Max Burst:%2d, Current Burst:%2d%n", task.id, task.maxBurst, 0)
            );
        }
        stringBuilder.append("-------------------------------------------\n");
        return stringBuilder.toString();
    }

    /**
     * Functional Interface for processing the contents of a {@link ReadyQueue}.
     * @see QueueProcessor#process(Iterator) 
     */
    @FunctionalInterface
    public interface QueueProcessor {
        /**
         * Process the contents of a ReadyQueue.
         * @param iterator An Iterator belonging to a ReadyQueue.
         * @return A {@link Process} from processing or {@code null}.
         */
        Process process(Iterator<Process> iterator);
    }
}
