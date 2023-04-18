package net.unamed.cmps455.project3.cpu.algorithm;

import net.unamed.cmps455.project3.Task;
import net.unamed.cmps455.project3.cpu.DispatchAlgorithm;

import java.util.Iterator;
import java.util.ListIterator;

public class PSJFAlgorithm extends DispatchAlgorithm {

    @Override
    public Task pickFromQueue(ListIterator<Task> iterator) {
        int currentIndex = -1;
        int index = -1;
        Task task = null;

        while (iterator.hasNext()) {

            currentIndex = iterator.nextIndex();
            Task iTask = iterator.next();
            if (task == null || iTask.getRemainingBurst() < task.getRemainingBurst()) {
                index = currentIndex;
                task = iTask;
            }
        }

        while (index != -1 && iterator.hasPrevious()) {
            currentIndex = iterator.previousIndex();
            iterator.previous();
            if (currentIndex == index) {
                iterator.remove();
                task.setAllottedBurst(task.getMaxBurst());
                break;
            }
        }

        return task;
    }

    @Override
    public boolean isPreemptive() {
        return true;
    }

    @Override
    public String getName() {
        return "PSJF Algorithm";
    }
}
