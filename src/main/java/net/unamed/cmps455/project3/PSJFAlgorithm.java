package net.unamed.cmps455.project3;

import java.util.Iterator;

public class PSJFAlgorithm extends DispatchAlgorithm {

    @Override
    public Task pickFromQueue(Iterator<Task> iterator) {
        Task task = null;

        while (iterator.hasNext()) {
            Task iTask = iterator.next();
            if (task == null || iTask.getRemainingBurst() < task.getCurrentBurst())
                task = iTask;
        }

        return task;
    }

    @Override
    public String getName() {
        return "PSJF Algorithm";
    }
}
