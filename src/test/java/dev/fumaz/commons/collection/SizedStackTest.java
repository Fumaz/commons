package dev.fumaz.commons.collection;

import static org.junit.jupiter.api.Assertions.*;

class SizedStackTest {

    // test push method
    @org.junit.jupiter.api.Test
    void push() {
        SizedStack<Integer> stack = new SizedStack<>(3);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertEquals(3, stack.size());
        stack.push(4);
        assertEquals(3, stack.size());
    }

    // test pop method
    @org.junit.jupiter.api.Test
    void pop() {
        SizedStack<Integer> stack = new SizedStack<>(3);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertEquals(3, stack.size());
        assertEquals(3, stack.pop());
        assertEquals(2, stack.size());
    }

    // test peek method
    @org.junit.jupiter.api.Test
    void peek() {
        SizedStack<Integer> stack = new SizedStack<>(3);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertEquals(3, stack.size());
        assertEquals(3, stack.peek());
        assertEquals(3, stack.size());
    }

    // test isEmpty method
    @org.junit.jupiter.api.Test
    void isEmpty() {
        SizedStack<Integer> stack = new SizedStack<>(3);
        assertTrue(stack.isEmpty());
        stack.push(1);
        assertFalse(stack.isEmpty());
    }

}