package net.unamed.cmps455.project3;

import java.util.Iterator;

public class FCFSAlgorithm extends DispatchAlgorithm {


    @Override
    public Task pickFromQueue(Iterator<Task> iterator) {
        Task process = null;

        if (iterator.hasNext()) {
            process = iterator.next();
            iterator.remove();
        }

        return process;
    }

    @Override
    public String getName() {
        return "FCFS Algorithm";
    }
}
