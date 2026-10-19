package com.athos.MyDataStructures;

import java.util.Arrays;

public class MyStack<T> {

	private static final int CHUNK_INCREMENT_SIZE = 100;
	private int curSize;
	private T[] array;
	
	@SuppressWarnings("unchecked")
	public MyStack() {
		array = (T[]) new Object[CHUNK_INCREMENT_SIZE];
		curSize = 0;
	}
	
	public boolean push(T nval) {
		assertSizeIsValid(++curSize);
		array[curSize-1] = nval;
		return true;
	}

	public T pop() {
		if (isEmpty())
			throw new IndexOutOfBoundsException("imposivel remover itens de uma lista vazia");
		
		return array[--curSize];
	}

	public void clear() {
		curSize = 0;
	}

	public boolean contains(T instance) {
		for (int i = 0; i < curSize; i++) {
			if (instance.equals(array[i])) return true;
		}
		return false;
	}

	public T get(int index) {
		if (index < 0 || index >= size())
			throw new IndexOutOfBoundsException("index "+index+" invalido para uma lista de tamanho "+size());
		
		return array[index];
	}

	public T set(int index, T nval) {
		if (index < 0 || index >= size())
			throw new IndexOutOfBoundsException("index "+index+" invalido para uma lista de tamanho "+size());
		
		return (array[index] = nval);
	}

	public int indexOf(T instance) {
		for (int i = 0; i < curSize; i++) {
			if (instance.equals(array[i])) return i;
		}
		return -1;
	}

	public boolean isEmpty() {
		return (size() == 0);
	}

	public int size() {
		return curSize;
	}

	@SuppressWarnings("unchecked")
	public T[] toArray(T[] narray) {
		if (narray.length < curSize) 
			return (T[]) Arrays.copyOf(array, size(), narray.getClass());
		
		System.arraycopy(array, 0, narray, 0, size());
		for (int i = size(); i < narray.length; i++) {
			narray[i] = null;
		}
		
		return narray;
	}

	public void createFromArray(T[] narray) {
		clear();
		for (int i = 0; i < narray.length; i++) {
			push(narray[i]);
		}
	}

	private void assertSizeIsValid(int nSize) {
		while (nSize > array.length) {
			array = Arrays.copyOf(array, array.length+CHUNK_INCREMENT_SIZE);
		}
	}

}
