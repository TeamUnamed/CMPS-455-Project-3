package net.unamed.cmps455.project3.cpu;

import net.unamed.cmps455.project3.TaskThread;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Optional;
import java.util.concurrent.Semaphore;

public class ReadyQueue {

    private LinkedList<TaskThread> tasks;

    Semaphore queueLock;

    public ReadyQueue () {
        queueLock = new Semaphore(1);
        tasks = new LinkedList<>();
    }

    public void add (TaskThread taskThread) {
        tasks.add(taskThread);
    }

    public boolean hasQueued() {
        return tasks.size() > 0;
    }

    public Optional<TaskThread> processQueue(QueueProcessor processor) {
        Optional<TaskThread> optional = Optional.empty();
        try {
            queueLock.acquire();

            optional = Optional.ofNullable(processor.process(tasks.iterator()));

            queueLock.release();
        } catch (InterruptedException e) {
            System.out.println("Processing interrupted");
        }

        return optional;
    }

    /**
     * Functional Interface for processing the contents of a {@link ReadyQueue}.
     * @see QueueProcessor#process(Iterator) 
     */
    public interface QueueProcessor {
        /**
         * Process the contents of a ReadyQueue.
         * @param iterator An Iterator belonging to a ReadyQueue.
         * @return A {@link TaskThread} from processing or {@code null}.
         */
        TaskThread process(Iterator<TaskThread> iterator);
    }
}
