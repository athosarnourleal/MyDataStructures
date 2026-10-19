package com.athos.MyDataStructures.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.athos.MyDataStructures.MyArrayList;

class MyArrayListTest {

    private MyArrayList<Integer> list;

    @BeforeEach
    void setup() {
        list = new MyArrayList<>();
    }

    @Test
    void testAddAndSize() {
        assertTrue(list.add(10));
        assertTrue(list.add(20));
        assertEquals(2, list.size());
        assertFalse(list.isEmpty());
    }

    @Test
    void testGetAndSet() {
        list.add(100);
        list.add(200);
        
        assertEquals(100, list.get(0));
        assertEquals(200, list.get(1));
        
        list.set(1, 300);
        assertEquals(300, list.get(1));
    }

    @Test
    void testRemoveByValueAndIndex() {
        list.add(10);
        list.add(20);
        list.add(30);
        
        Integer removed = list.remove(1);
        assertEquals(20, removed);
        assertEquals(2, list.size());
        
        list.remove(Integer.valueOf(30));
        assertEquals(1, list.size());
        assertEquals(10, list.get(0));
    }

    @Test
    void testContainsAndIndexOf() {
        list.add(5);
        list.add(15);
        list.add(25);
        
        assertTrue(list.contains(15));
        assertFalse(list.contains(99));
        
        assertEquals(1, list.indexOf(15));
        assertEquals(-1, list.indexOf(99));
    }

    @Test
    void testClear() {
        list.add(1);
        list.add(2);
        
        list.clear();
        
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
    }

    @Test
    void testOutOfBoundsException() {
        list.add(1);
        
        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.get(5);
        });
        
        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.remove(-1);
        });
    }

    @Test
    void testSortingAlgorithmsBubbleSort() {
        list.add(30);
        list.add(10);
        list.add(20);
        list.add(60);
        list.add(40);
        list.add(50);
        
        MyArrayList.ComparingFunction<Integer> isBigger = (a, b) -> a > b;
        
        list.bubbleSort(isBigger);
        
        for (int i = 0; i < list.size(); i++) {
        	assertEquals((i+1)*10, list.get(i));
        }
    }


    @Test
    void testSortingAlgorithmsSelectionSort() {
        list.add(30);
        list.add(10);
        list.add(20);
        list.add(60);
        list.add(40);
        list.add(50);
        
        MyArrayList.ComparingFunction<Integer> isBigger = (a, b) -> a > b;
        
        list.selectionSort(isBigger);
        
        for (int i = 0; i < list.size(); i++) {
        	assertEquals((i+1)*10, list.get(i));
        }
    }
    
    @Test
    void testSortingAlgorithmsMergeSort() {
        list.add(30);
        list.add(10);
        list.add(20);
        list.add(60);
        list.add(40);
        list.add(50);
        
        MyArrayList.ComparingFunction<Integer> isBigger = (a, b) -> a > b;
        
        list.mergeSort(isBigger);
        
        for (int i = 0; i < list.size(); i++) {
        	assertEquals((i+1)*10, list.get(i));
        }
    }
    
    @Test
    void testSortingAlgorithmsQuickSort() {
        list.add(30);
        list.add(10);
        list.add(20);
        list.add(60);
        list.add(40);
        list.add(50);
        
        MyArrayList.ComparingFunction<Integer> isBigger = (a, b) -> a > b;
        
        list.quickSort(isBigger);
        
        for (int i = 0; i < list.size(); i++) {
        	assertEquals((i+1)*10, list.get(i));
        }
    }
    
    
    @Test
    void testSortingAlgorithmsInsertionSort() {
        list.add(30);
        list.add(10);
        list.add(20);
        list.add(60);
        list.add(40);
        list.add(50);
        
        MyArrayList.ComparingFunction<Integer> isBigger = (a, b) -> a > b;
        
        list.insertionSort(isBigger);
        
        for (int i = 0; i < list.size(); i++) {
        	assertEquals((i+1)*10, list.get(i));
        }
    }
    
    @Test
    void testSortingAlgorithmsShellSort() {
        list.add(30);
        list.add(10);
        list.add(20);
        list.add(60);
        list.add(40);
        list.add(50);
        
        MyArrayList.ComparingFunction<Integer> isBigger = (a, b) -> a > b;
        
        list.shellSort(isBigger);
        
        for (int i = 0; i < list.size(); i++) {
        	assertEquals((i+1)*10, list.get(i));
        }
    }
    
    
}