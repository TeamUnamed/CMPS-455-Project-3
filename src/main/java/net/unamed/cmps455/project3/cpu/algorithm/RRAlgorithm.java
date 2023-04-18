package net.unamed.cmps455.project3.cpu.algorithm;

import net.unamed.cmps455.project3.Task;
import net.unamed.cmps455.project3.cpu.DispatchAlgorithm;

import java.util.ListIterator;

public class RRAlgorithm extends DispatchAlgorithm {

    private final int quantum;

    public RRAlgorithm(int quantum) {
        this.quantum = quantum;
    }

    @Override
    public Task pickFromQueue(ListIterator<Task> iterator) {
        Task task = null;
        if (iterator.hasNext()) {
            task = iterator.next();
            iterator.remove();
            task.setAllottedBurst(task.getCurrentBurst() + quantum);
        }

        return task;
    }

    @Override
    public String getName() {
        return "RR Algorithm: Quantum = " + quantum;
    }
}
