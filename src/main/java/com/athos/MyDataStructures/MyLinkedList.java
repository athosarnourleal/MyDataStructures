package com.athos.MyDataStructures;


public class MyLinkedList<T> {
	
	private class Node {
		public T data;
		public Node next;
		
		public Node(T data, Node next) {
			this.data = data;
			this.next = next;
		}
	}
	
	private int size;
	private Node firstNode;
	
	
	public MyLinkedList() {
		this.firstNode = null;
		this.size = 0;
	}
	
	public void addFirst(T value) {
		if (this.firstNode == null) {
			this.firstNode = new Node(value, null);
			size++;
			return;
		}
		
		Node aux = this.firstNode;
		this.firstNode = new Node(value, aux);
		
		this.size++;
	}
	
	public void addLast(T value) {
		if (this.firstNode == null) {
			this.firstNode = new Node(value, null);
			size++;
			return;
		}
		
		// find last
		Node last = this.firstNode;
		while(last.next != null) {
			last = last.next;
		}
		
		last.next = new Node(value, null);
		
		this.size++;
	}
	
	public void add(T value, int index) {
		if (index < 0 || index >= this.size) {
			throw new IndexOutOfBoundsException("index "+index+" does not exist in a list of size "+size);
		}
		
		Node current = this.firstNode;
		
		int i = 0;
		while (i < index) {
			if (current == null) {
				throw new IndexOutOfBoundsException("DEBUG.");
			}
			current = current.next;
			i++;
		}
		
		current.next = new Node(value, current.next);
		
		this.size++;
	}
	
	
	public void removeFirst() {
		if (this.firstNode == null) {
			throw new IndexOutOfBoundsException("you cannot remove nodes froms a empty list");
		}
		
		this.firstNode = this.firstNode.next;
		this.size--;
	}
		
	public void removeLast() {
		if (this.firstNode == null) {
			throw new IndexOutOfBoundsException("you cannot remove nodes froms a empty list");
		} else if (this.size == 1)  {
			removeFirst();
			return;
		}
		
		Node secondTolast = this.firstNode;
		while(secondTolast.next.next != null) {
			secondTolast = secondTolast.next;
		}
		
		secondTolast.next = null;
		this.size--;
	}
	
	public void remove(int index) {
		if (index < 0 || index >= this.size) {
			throw new IndexOutOfBoundsException("index "+index+" does not exist in a list of size "+size);
			
		} else if (index == 0) {
			removeFirst();
			return;
		} else if (index == this.size-1) {
			removeLast();
			return;
		}
		
		Node previousToIndex = this.firstNode;
		int i = 1;
		while(i++ < index) {
			previousToIndex = previousToIndex.next;
		}
		
		previousToIndex.next = previousToIndex.next.next; // unlink
		
		size--;
	}
	
	public T get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("index "+index+" does not exist in a list of size "+size);
		}
		
		int i = 0;
		Node cur = this.firstNode;
		while (i < index) {
			cur = cur.next;
			i++;
		}
		
		return cur.data;
	}
	
	public void reverse() {
		Node previous = null;
		Node cur = this.firstNode;
		Node next = this.firstNode.next;
		
		while (next != null) {
			cur.next = previous; // link to previous
			
			// move
			previous = cur;
			cur = next;
			next = next.next;
		}
		
		cur.next = previous; // link to previous
		
		this.firstNode = cur;
	}
	
	public int size() {
		return size;
	}
	
}
