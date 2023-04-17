package net.unamed.cmps455.project3;

import java.util.Iterator;

public class NSJFAlgorithm extends DispatchAlgorithm {

    @Override
    public Task pickFromQueue(Iterator<Task> iterator){
        Task task = null;
        int minBurst;

        do{
            Task currentTask = iterator.next();
            minBurst = currentTask.getMaxBurst();
            if(task == null || currentTask.maxBurst < minBurst){
                task = currentTask;
            }
        }while(iterator.hasNext());

        return task;
    }

    @Override
    public String getName() { return "NSJF Algorithm"; }
}
