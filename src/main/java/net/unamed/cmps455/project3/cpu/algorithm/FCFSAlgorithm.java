package net.unamed.cmps455.project3.cpu.algorithm;

import net.unamed.cmps455.project3.Task;
import net.unamed.cmps455.project3.cpu.DispatchAlgorithm;

import java.util.ListIterator;

public class FCFSAlgorithm extends DispatchAlgorithm {


    @Override
    public Task pickFromQueue(ListIterator<Task> iterator) {
        Task task = null;

        if (iterator.hasNext()) {
            task = iterator.next();
            iterator.remove();

            task.setAllottedBurst(task.getMaxBurst());
        }

        return task;
    }

    @Override
    public String getName() {
        return "FCFS Algorithm";
    }
}
