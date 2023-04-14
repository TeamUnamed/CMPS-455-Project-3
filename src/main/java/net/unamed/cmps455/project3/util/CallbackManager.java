package net.unamed.cmps455.project3.util;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;
import java.util.function.Consumer;

public class CallbackManager <T extends CallbackEvent> {

    private final LinkedList<Consumer<T>> consumers;

    private final Semaphore mutex;

    public CallbackManager () {
       this.consumers = new LinkedList<>();
       this.mutex = new Semaphore(1);
    }

    public void registerCallback(Consumer<T> consumer) {
        try {
            mutex.acquire();
            consumers.add(consumer);
            mutex.release();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    public void sendCallback(T event) {
        try {
            mutex.acquire();
            for (Consumer<T> consumer : consumers) {
                consumer.accept(event);
            }
            mutex.release();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
