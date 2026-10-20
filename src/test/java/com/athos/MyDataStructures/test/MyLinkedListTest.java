package com.athos.MyDataStructures.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.athos.MyDataStructures.MyLinkedList;

class MyLinkedListTest {

    private MyLinkedList<String> list;

    @BeforeEach
    void setUp() {
        list = new MyLinkedList<>();
    }

    @Test
    void testListaInicialVazia() {
        assertEquals(0, list.size());
    }

    @Test
    void testAddStart() {
        list.addFirst("B");
        list.addFirst("A");

        assertEquals(2, list.size());
        assertEquals("A", list.get(0));
        assertEquals("B", list.get(1));
    }

    @Test
    void testAddLast() {
        list.addLast("A");
        list.addLast("B");
        list.addLast("C");

        assertEquals(3, list.size());
        assertEquals("A", list.get(0));
        assertEquals("B", list.get(1));
        assertEquals("C", list.get(2));
    }

    @Test
    void testAddInIndex() {
        list.addLast("A");
        list.addLast("C");
        list.add("B", 0); // Adiciona após a posição 0 (entre 'A' e 'C')

        assertEquals(3, list.size());
        assertEquals("A", list.get(0));
        assertEquals("B", list.get(1));
        assertEquals("C", list.get(2));
    }

    @Test
    void testAddIndexOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.add("A", 0));
        
        list.addLast("A");
        assertThrows(IndexOutOfBoundsException.class, () -> list.add("B", 1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.add("B", -1));
    }

    @Test
    void testRemoveFirst() {
        list.addLast("A");
        list.addLast("B");

        list.removeFirst();

        assertEquals(1, list.size());
        assertEquals("B", list.get(0));
    }

    @Test
    void testRemoveFirstEmptyList() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.removeFirst());
    }

    @Test
    void testRemoveLast() {
        list.addLast("A");
        list.addLast("B");
        list.addLast("C");

        list.removeLast();

        assertEquals(2, list.size());
        assertEquals("A", list.get(0));
        assertEquals("B", list.get(1));
    }

    @Test
    void testRemoveLastSingleElement() {
        list.addLast("A");
        list.removeLast();

        assertEquals(0, list.size());
    }

    @Test
    void testRemoveLastEmptyList() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.removeLast());
    }

    @Test
    void testRemoveByIndex() {
        list.addLast("A");
        list.addLast("B");
        list.addLast("C");
        list.addLast("D");

        list.remove(1); // Remove "B"

        assertEquals(3, list.size());
        assertEquals("A", list.get(0));
        assertEquals("C", list.get(1));
        assertEquals("D", list.get(2));
    }

    @Test
    void testRemoveFirstAndLastIndex() {
        list.addLast("A");
        list.addLast("B");
        list.addLast("C");

        list.remove(0); // Remove "A"
        assertEquals("B", list.get(0));

        list.remove(list.size() - 1); // Remove "C"
        assertEquals("B", list.get(0));
        assertEquals(1, list.size());
    }

    @Test
    void testRemoveIndexOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(0));

        list.addLast("A");
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
    }

    @Test
    void testGetOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));

        list.addLast("A");
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
    }

    @Test
    void testReverse() {
        list.addLast("A");
        list.addLast("B");
        list.addLast("C");

        list.reverse();

        assertEquals("C", list.get(0));
        assertEquals("B", list.get(1));
        assertEquals("A", list.get(2));
    }
}