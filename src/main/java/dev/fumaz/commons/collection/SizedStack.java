package dev.fumaz.commons.collection;

import java.util.Stack;

/**
 * Represents a fixed-size stack.<br>
 * This stack can only ever have at most N elements,<br>
 * and if it reaches that number it will then remove elements<br>
 * until it can fit new ones.
 *
 * @param <T> the type of the elements
 * @see <a href="https://stackoverflow.com/questions/7727919/creating-a-fixed-size-stack">https://stackoverflow.com/questions/7727919/creating-a-fixed-size-stack</a>
 */
public class SizedStack<T> extends Stack<T> {

    private final int maxSize;

    public SizedStack(int size) {
        super();
        this.maxSize = size;
    }

    @Override
    public T push(T object) {
        // If the stack is too big, remove elements until it's the right size
        while (this.size() >= maxSize) {
            this.remove(0);
        }

        return super.push(object);
    }

    /**
     * @return the maximum possible size of the stack
     */
    public int getMaxSize() {
        return maxSize;
    }

    /**
     * @return the current size of the stack, which may be different from its maximum size
     */
    @Override
    public synchronized int size() {
        return super.size();
    }

}