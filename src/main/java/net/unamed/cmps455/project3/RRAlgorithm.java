package net.unamed.cmps455.project3;

import java.util.Iterator;

public class RRAlgorithm extends DispatchAlgorithm {
    @Override
    public Task pickFromQueue(Iterator<Task> iterator) {
        if (iterator.hasNext())
            return iterator.next();

        return null;
    }

    @Override
    public String getName() {
        return "RR Algorithm";
    }
}
