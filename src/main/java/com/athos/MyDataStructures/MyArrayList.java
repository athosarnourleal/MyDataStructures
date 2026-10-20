package com.athos.MyDataStructures;

import java.util.Arrays;

public class MyArrayList<T> {
	
	private static final int CHUNK_INCREMENT_SIZE = 100;
	private int curSize = 0;
	private T[] data;

	@SuppressWarnings("unchecked")
	public MyArrayList() {
		data = (T[]) new Object[CHUNK_INCREMENT_SIZE];
	}

	public boolean add(T nval) {
		assertSizeIsValid(++this.curSize);
		this.data[this.curSize-1] = nval;
		return true; 
	}
	
	public void add(int index, T nval) {
		if (index < 0 && index >= this.curSize+1)
			throw new IndexOutOfBoundsException("index "+index+" invalido para uma lista de tamanho "+this.curSize);
		
		assertSizeIsValid(++this.curSize);
		for(int i = index+1; i <= this.curSize; i++) {
			this.data[i] = this.data[i-1];
		}
		
		this.data[index] = nval;
	}

	public void remove(T instance) {
		remove(indexOf(instance));
	}

	public T remove(int index) {
		if (index < 0 || index >= curSize) 
			throw new IndexOutOfBoundsException("index "+index+" invalido para uma lista de tamanho "+curSize);
		
		T removed = this.data[index];
		
		for (int i = index; i < this.curSize-1; i++) {
			this.data[index] = this.data[index+1];
		}
		
		this.curSize--;
		
		return removed;
	}

	public void clear() {
		this.curSize = 0;
	}

	public boolean contains(T instance) {
		for (int i = 0; i < curSize; i++) {
			if (this.data[i].equals(instance)) {
				return true;
			}
		}
		return false;
	}

	public T get(int index) {
		if (index < 0 || index >= size())
			throw new IndexOutOfBoundsException("index "+index+" invalido para uma lista de tamanho "+this.curSize);
		
		
		return this.data[index];
	}

	public T set(int index, T nval) {
		return (this.data[index] = nval);
	}

	public int indexOf(T instance) {
		for (int i = 0; i < this.curSize; i++) {
			
			if (this.data[i].equals(instance))  
				return i;
			
		}
		
		return -1;
	}

	public boolean isEmpty() {
		return (this.curSize == 0);
	}

	public int size() {
		return this.curSize;
	}
	
	public Object[] toArray() {
		return Arrays.copyOf(this.data, this.curSize);
	}
	
	@SuppressWarnings("unchecked")
	public T[] toArray(T[] narray) {
		if (narray.length < this.curSize) 
			return (T[]) Arrays.copyOf(this.data, this.curSize, narray.getClass());
		
		System.arraycopy(data, 0, narray, 0, curSize);
		for (int i = size(); i < narray.length; i++) {
			narray[i] = null;
		}
		
		return narray;
	}
	
	private void assertSizeIsValid(int nSize) {
		while (nSize > this.data.length) {
			this.data = Arrays.copyOf(this.data, this.data.length+CHUNK_INCREMENT_SIZE);
		}
	}

	public void createFromArray(T[] narray) {
		clear();
		for (int i = 0; i < narray.length; i++) {
			add(narray[i]);
		}
	}
	
	public interface ComparingFunction<T> {
		boolean isFirstBigger(T element, T other);
	}
	
	public void bubbleSort(ComparingFunction<T> func) {
		boolean sorted = false;
		
		while (sorted == false) {
			sorted = true;
			for (int i = 0; i < this.curSize-1; i++) {
				if (func.isFirstBigger(this.data[i], this.data[i+1])) {
					// if the smaller index holds the biggest value --> swap
					sorted = false;
					
					// swap
					T aux = this.data[i];
					this.data[i] = this.data[i+1];
					this.data[i+1] = aux;
				}
			}
		}
	}
	
	public void selectionSort(ComparingFunction<T> func) {
		int sortedElementNum = 0;
		while (sortedElementNum < curSize) {
			
			// find smallest of the unsorted portion of the array
			int smallestFound = sortedElementNum;
			for (int i = sortedElementNum; i < curSize; i++) {
				if (func.isFirstBigger(data[smallestFound], data[i]) == true) {
					smallestFound = i;
				}
			}
			
			// put the smallest in its place by swapping with the current element in it
			T aux = this.data[sortedElementNum];
			this.data[sortedElementNum] = this.data[smallestFound];
			this.data[smallestFound] = aux;
			
			sortedElementNum++;
		}
	}
	
	public void mergeSort(ComparingFunction<T> func) {
		data = mergeSortRecursion(Arrays.copyOf(data, this.curSize), func, 0);
	}
	
	@SuppressWarnings("unchecked")
	private T[] mergeSortRecursion(T[] curArray, ComparingFunction<T> func, int level) {
		
		if (curArray.length <= 1) {
			return curArray;
		}
		
		// load data
		
		T[] firstHalf = (T[]) new Object[curArray.length / 2];
		
		for (int i = 0; i < firstHalf.length; i++) {
			firstHalf[i] = curArray[i];
		}
		
		T[] lastHalf = (T[]) new Object[curArray.length - firstHalf.length];
		
		for (int i = 0; i < lastHalf.length; i++) {
			lastHalf[i] = curArray[firstHalf.length + i];
		}
		
		// call recursion
		
		firstHalf = mergeSortRecursion(firstHalf, func, level + 1);

		lastHalf = mergeSortRecursion(lastHalf, func, level + 1);
		
		// merge arrays
		
		int count = 0, i = 0, j = 0;
		
		while(i < firstHalf.length || j < lastHalf.length) {
			if (i >= firstHalf.length) {
				curArray[count++] = lastHalf[j++];
				
			} else if(j >= lastHalf.length) {
				curArray[count++] = firstHalf[i++];
			} else {
				if (func.isFirstBigger(firstHalf[i], lastHalf[j]) == false) {
					curArray[count++] = firstHalf[i++];
				} else {
					curArray[count++] = lastHalf[j++];
				}
			}
		}
		
		return curArray;
	}
	

	public void quickSort(ComparingFunction<T> func) {
		quickSortRecursion(0, this.curSize-1, func);
	
	}
	private void quickSortRecursion(int subArrayStartIndex, int subArrayLastIndex, ComparingFunction<T> func) {
		if (subArrayLastIndex - subArrayStartIndex < 1) {
			return;
		}

		int pivotIndex = subArrayLastIndex;
		T pivotValue = this.data[pivotIndex];
		
		for (int i = subArrayStartIndex; i < pivotIndex; i++) {
			if (func.isFirstBigger(this.data[i], pivotValue)) {
				// throw values bigger than the pivot to the end
				
				T aux = this.data[i];
				
				for (int j = i; j < subArrayLastIndex; j++) {
					this.data[j] = this.data[j+1];
					
					if (j+1 == pivotIndex) { /// update pivot index if it has been moved
						pivotIndex--;
					}
				}
				
				this.data[subArrayLastIndex] = aux;
				i--;
			}
		}
		
		// sort the two missing parts by calling them recursively
		
		quickSortRecursion(subArrayStartIndex, pivotIndex-1, func);
		
		quickSortRecursion(pivotIndex+1, subArrayLastIndex, func);
	}
	
	public void insertionSort(ComparingFunction<T> func) {
		for(int i = 0; i < size(); i++) {
			// find biggest
			int smallest = i;
			for(int j = i; j < size(); j++) {
				if (func.isFirstBigger(this.data[smallest], this.data[j])) {
					smallest = j;
				}
			}
			
			// swap
			T aux = this.data[i];
			this.data[i] = this.data[smallest];
			this.data[smallest] = aux;
		}
		
	}
	
	public void shellSort(ComparingFunction<T> func) {
		
		for (int gap = this.curSize/2; gap > 0; gap /= 2) {
			
			for (int i = gap; i < this.curSize; i++) {
				T temp = this.data[i];
				int j = i;
				
				while (j >= gap && func.isFirstBigger(this.data[j - gap], temp)) {
					this.data[j] = this.data[j - gap];
                    j -= gap;
                }
				
				this.data[j] = temp;
			}
		}
		
	}
	
	
}
