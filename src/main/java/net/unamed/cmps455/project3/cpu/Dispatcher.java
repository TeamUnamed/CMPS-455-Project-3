package net.unamed.cmps455.project3.cpu;

import net.unamed.cmps455.project3.OperatingSystem;
import net.unamed.cmps455.project3.TaskThread;

import java.util.Iterator;
import java.util.Optional;

/**
 * Abstract representation of a Dispatcher
 */
public class Dispatcher extends Thread {

    private final OperatingSystem os;
    private final Core cpu;

    public Dispatcher(OperatingSystem os, Core cpu) {
        this.os = os;
        this.cpu = cpu;
    }

    @Override
    public void run() {
        System.out.println("Dispatcher Started!");
        ReadyQueue readyQueue = os.getReadyQueue();
        while(readyQueue.hasQueued()) {
            Optional<TaskThread> optional = readyQueue.processQueue(this::getNextTaskThread);
            optional.ifPresentOrElse(
                    (taskThread) -> System.out.println("Task Thread Found: " + taskThread.name),
                    () -> System.out.println("No Task Threads")
            );
        }

        System.out.println("Ready Queue Empty :: Bye!");
    }

    /*
     * Uses FCFS test code
     */
    /**
     * @see ReadyQueue.QueueProcessor#process(Iterator) 
     */
    private TaskThread getNextTaskThread(Iterator<TaskThread> iterator) {
        TaskThread taskThread = null;

        // Get the first TaskThread from queue and remove
        if (iterator.hasNext()) {
            taskThread = iterator.next();
            iterator.remove();
        }

        return taskThread;
    }
}
