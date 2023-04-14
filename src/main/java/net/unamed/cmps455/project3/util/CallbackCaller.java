package net.unamed.cmps455.project3.util;

import java.util.function.Consumer;

/**
 * Signifies that a class can return a callback to a consumer.
 * @param <T> A type extending CallbackEvent
 */
public interface CallbackCaller<T extends CallbackEvent> {

    /**
     * Registers a consumer for callback.
     * @param consumer the Consumer
     * @implSpec the Consumer that is passed should handle thread-safety itself. <br>
     * <i>i.e.</i> if it is intended for another thread to process the passed object, it should be placed
     * into a queue while blocking other access. If the callback is only used to notify a sleeping thread
     * then it should only do that.
     */
    void registerCallback(Consumer<T> runnable);

}
