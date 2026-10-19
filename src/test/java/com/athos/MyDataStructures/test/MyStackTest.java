package com.athos.MyDataStructures.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.athos.MyDataStructures.MyStack;

public class MyStackTest {

    private MyStack<Integer> stack;

    @BeforeEach
    public void setUp() {
        stack = new MyStack<>();
    }

    @Test
    public void testPushAndSize() {
        assertTrue(stack.isEmpty());
        stack.push(10);
        stack.push(20);
        assertEquals(2, stack.size());
        assertFalse(stack.isEmpty());
    }

    @Test
    public void testPop() {
        stack.push(10);
        stack.push(20);
        assertEquals(20, stack.pop());
        assertEquals(1, stack.size());
        assertEquals(10, stack.pop());
    }

    @Test
    public void testPopEmptyStack() {
        assertThrows(IndexOutOfBoundsException.class, () -> stack.pop());
    }

    @Test
    public void testClear() {
        stack.push(1);
        stack.clear();
        assertEquals(0, stack.size());
    }

    @Test
    public void testContainsAndIndexOf() {
        stack.push(5);
        stack.push(15);
        assertTrue(stack.contains(5));
        assertFalse(stack.contains(99));
        assertEquals(0, stack.indexOf(5));
        assertEquals(-1, stack.indexOf(99));
    }

    @Test
    public void testGetAndSet() {
        stack.push(100);
        stack.push(200);
        assertEquals(100, stack.get(0));
        stack.set(0, 300);
        assertEquals(300, stack.get(0));
    }

    @Test
    public void testGetOutOfBounds() {
        stack.push(10);
        assertThrows(IndexOutOfBoundsException.class, () -> stack.get(1));
        assertThrows(IndexOutOfBoundsException.class, () -> stack.get(-1));
    }

    @Test
    public void testCreateFromArrayAndToArray() {
        Integer[] initialArray = {7, 8, 9};
        stack.createFromArray(initialArray);
        assertEquals(3, stack.size());
        
        Integer[] newArray = new Integer[3];
        stack.toArray(newArray);
        assertArrayEquals(initialArray, newArray);
    }
}