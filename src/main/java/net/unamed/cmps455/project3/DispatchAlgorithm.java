package net.unamed.cmps455.project3;

import java.util.Iterator;

public abstract class DispatchAlgorithm {

    /**
     * Picks a Task from provided ReadyQueue's iterator.
     * @param iterator - the ReadyQueue's iterator.
     * @return the task that was picked.
     */
    public abstract Task pickFromQueue(Iterator<Task> iterator);

    /**
     * Gets the name of the Algorithm.
     * @return the name of algorithm
     */
    public abstract String getName();

    @Override
    public final String toString() {
        return getName();
    }
}
