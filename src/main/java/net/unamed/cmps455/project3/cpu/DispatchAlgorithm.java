package net.unamed.cmps455.project3.cpu;

import net.unamed.cmps455.project3.Task;

import java.util.Iterator;
import java.util.ListIterator;

public abstract class DispatchAlgorithm {

    /**
     * Picks a Task from provided ReadyQueue's iterator.
     * @param iterator - the ReadyQueue's iterator.
     * @return the task that was picked.
     */
    public abstract Task pickFromQueue(ListIterator<Task> iterator);

    /**
     * Gets the name of the Algorithm.
     * @return the name of algorithm
     */
    public abstract String getName();

    public boolean isPreemptive() {
        return false;
    }

    @Override
    public final String toString() {
        return getName();
    }
}
