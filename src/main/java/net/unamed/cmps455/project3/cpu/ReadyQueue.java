package net.unamed.cmps455.project3.cpu;

import net.unamed.cmps455.project3.Task;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Optional;
import java.util.concurrent.Semaphore;
import java.util.function.Function;

/**
 * ReadyQueue for scheduling Tasks on the Operating System.
 * <p>
 *      {@link Dispatcher Dispatchers} ready from the queue to schedule tasks on designated {@link Core}
 * </p>
 */
public class ReadyQueue {

    private final LinkedList<Task> tasks;
    private final Semaphore queueLock;

    public ReadyQueue () {
        tasks = new LinkedList<>();
        queueLock = new Semaphore(1);
    }

    /**
     * Adds a process to the ReadyQueue.
     * @param task Process to add
     */
    public void add (Task task) {
        try {
            queueLock.acquire();
            try {
                tasks.add(task);
            } finally {
                queueLock.release();
            }
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
            try {
                size = tasks.size();
            } finally {
                queueLock.release();
            }
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
    public Optional<Task> processQueue(Function<ListIterator<Task>, Task> processor) {
        Optional<Task> optional = Optional.empty();
        try {
            queueLock.acquire();

            try {
                optional = Optional.ofNullable(processor.apply(tasks.listIterator()));
            } finally {
                queueLock.release();
            }
        } catch (InterruptedException e) {
            System.out.println("Processing interrupted");
        }

        return optional;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("--------------- Ready Queue ---------------\n");
        try {
            queueLock.acquire();
            try {
                for (Task task : tasks) {
                    stringBuilder.append(
                            String.format("ID: %4d, Max Burst: %2d, Current Burst: %2d%n", task.id, task.maxBurst, 0)
                    );
                }
            } finally {
                queueLock.release();
            }
        } catch (InterruptedException e){
            System.out.println("toString interrupted");
        }
        stringBuilder.append("-------------------------------------------\n");
        return stringBuilder.toString();
    }
}
