package com.athos.MyDataStructures.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.athos.MyDataStructures.MyQueue;

public class MyQueueTest {

    private MyQueue<Integer> queue;

    @BeforeEach
    public void setUp() {
        queue = new MyQueue<>();
    }

    @Test
    public void testPushAndSize() {
        assertTrue(queue.isEmpty());
        queue.push(10);
        queue.push(20);
        assertEquals(2, queue.size());
        assertFalse(queue.isEmpty());
    }

    @Test
    public void testPop() {
        queue.push(10);
        queue.push(20);
        assertEquals(10, queue.pop());
        assertEquals(1, queue.size());
    }

    @Test
    public void testPopEmptyQueue() {
        assertThrows(IndexOutOfBoundsException.class, () -> queue.pop()); //[cite: 1]
    }

    @Test
    public void testClear() {
        queue.push(1);
        queue.clear();
        assertEquals(0, queue.size());
    }

    @Test
    public void testContainsAndIndexOf() {
        queue.push(5);
        queue.push(15);
        assertTrue(queue.contains(15));
        assertFalse(queue.contains(99));
        assertEquals(1, queue.indexOf(15));
        assertEquals(-1, queue.indexOf(99));
    }

    @Test
    public void testGetAndSet() {
        queue.push(100);
        queue.push(200);
        assertEquals(200, queue.get(1));
        
        queue.set(1, 300);
        assertEquals(300, queue.get(1));
    }

    @Test
    public void testGetOutOfBounds() {
        queue.push(10);
        assertThrows(IndexOutOfBoundsException.class, () -> queue.get(1));
    }

    @Test
    public void testCreateFromArrayAndToArray() {
        Integer[] initialArray = {1, 2, 3};
        queue.createFromArray(initialArray);
        assertEquals(3, queue.size());
        
        Integer[] newArray = new Integer[3];
        queue.toArray(newArray);
        assertArrayEquals(initialArray, newArray);
    }
}