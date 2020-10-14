package projectCode20280;

import java.util.Iterator;

public class SinglyLinkedList<E> implements List<E> {

	private static class Node<E> 
	{
		private E element;
		private Node<E> next;
		
		public Node(E e , Node<E> n)
		{
			element = e;
			next = n;
		}
		
		public E getElement() { return element; }
		
		public Node<E> getNext() { return next; }
		
		public void setNext(Node<E> n) { next = n; }
	}
	
	private Node<E> head = null;
	private Node<E> tail = null;
	private int size = 0;
	
	public SinglyLinkedList() { }
	
	@Override
	public boolean isEmpty() { return size == 0; }

	@Override
	public E get(int i) { return get(i); }

	@Override
	public void add(int i, E e) { add(i, e); }

	@Override
	public E remove(int i) { return remove(i); }

	@Override
	public Iterator<E> iterator() 
	{
		return new ListIterator();
	}
	
	private class ListIterator implements Iterator<E>
	{
		Node<E> currentNode;
		boolean sentinel = true;
		
		public ListIterator() { currentNode = head; }
		
		public boolean hasNext() { return sentinel; }
		
		public E next()
		{
			E data = currentNode.getElement();
			currentNode = currentNode.getNext();
			
			if(currentNode == head) { sentinel = false; }
			
			return data;
		}
	}

	@Override
	public int size() { return size; }	
	
	@Override
	public E removeFirst() 
	{
		if(isEmpty()) { return null; }
		
		E answer = head.getElement();
		size--;
		
		if(size == 0) { tail = null; }
		
		return answer;
	}

	@Override
	public E removeLast() 
	{
		if(isEmpty()) { return null; }
		
		E answer = tail.getElement();
		size--;
		
		if(size == 0) { head = null; }
		
		return answer;
	}

	@Override
	public void addFirst(E e) 
	{
		head = new Node<E>(e , head);
		
		if(size == 0) { tail = head; }
		
		size++;
	}

	@Override
	public void addLast(E e) 
	{
		Node<E> newest = new Node<E>(e , null);
		
		if(isEmpty()) { head = newest; }
		
		else { tail.setNext(newest); }
		
		tail = newest;
		size++;
	}
	
	public E first()
	{
		if(isEmpty()) { return null; }
		
		return head.getElement();
	}
	
	public E last()
	{
		if(isEmpty()) { return null; }
		
		return tail.getElement();
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		Node<E> tmp = head;
		
		sb.append("[");
		
		while(tmp != null)
		{
			sb.append(tmp.getElement());
			
			if(tmp != tail)
			{
				sb.append(", ");
			}
			
			tmp = tmp.getNext();
		}
		
		sb.append("]");
		return sb.toString();
	}
	
	public static void main(String[] args) 
	{
		String[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");

		SinglyLinkedList<String> sll = new SinglyLinkedList<String>();
		for (String s : alphabet) 
		{
			sll.addFirst(s);
			sll.addLast(s);
		}
		System.out.println(sll.toString());

		sll.removeFirst();
		System.out.println(sll.toString());
		
		sll.removeLast();
		System.out.println(sll.toString());

		sll.remove(2);
		System.out.println(sll.toString());
		
		for (String s : sll) 
		{
			System.out.print(s + ", ");
		}
	}
}
