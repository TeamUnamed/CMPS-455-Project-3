package net.unamed.cmps455.project3.cpu.algorithm;

import net.unamed.cmps455.project3.Task;
import net.unamed.cmps455.project3.cpu.DispatchAlgorithm;

import java.util.ListIterator;

public class NSJFAlgorithm extends DispatchAlgorithm {

    @Override
    public Task pickFromQueue(ListIterator<Task> iterator){
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
