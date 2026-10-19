package com.athos.MyDataStructures;


@SuppressWarnings("unused") // remove later
public class MyLinkedList<T> {
	
	private class Node {
		public Node previous;
		public T data;
		public Node next;
		
		public Node(T data, Node next) {
			this.previous = previous;
			this.data = data;
			this.next = next;
		}
	}
	
	private int size;
	
	private Node firstNode;
	
	
	public MyLinkedList() {
		firstNode = null;
		size = 0;
	}
	
	public void addStart(T value) {
		if (firstNode == null) {
			firstNode = new Node(value, null);
			
		} else {
			firstNode = new Node(value, firstNode);
		}
		size++;
	}
	
	public void addLast(T value) {
		if (firstNode == null) {
			firstNode = new Node(value, null);
		}
		
		// find last
		Node last = firstNode;
		while(last.next != null) {
			last = last.next;
		}
		last.next = new Node(value, null);
		
		size++;
	}
	
	public void add(T value, int index) {
		
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("indice "+index+" não existe em lista de tamanho "+size);
		}
		
		Node current = firstNode;
		
		int i = 0;
		while (i < index) {
			if (current == null) {
				throw new IndexOutOfBoundsException("DEBUG.");
			}
			current = current.next;
			i++;
		}
		
		current.next = new Node(value, current.next);
		size++;
	}
	
	
	public int size() {
		return size;
	}
	
}
