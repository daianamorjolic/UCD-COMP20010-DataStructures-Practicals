package projectCode20280;

import java.util.Iterator;

public class DoublyLinkedList<E> implements List<E> 
{

	private class Node<E> 
	{
		private E element;
		private Node<E> prev;
		private Node<E> next;
		
		public Node(E e , Node<E> p , Node<E> n)
		{
			element = e;
			prev = p;
			next = n;
		}
		
		public E getElement() { return element; }
		
		public Node<E> getPrev() { return prev; }
		
		public Node<E> getNext() { return next; }
		
		public void setPrev(Node<E> p) { prev = p; }
		
		public void setNext(Node<E> n) { next = n; }
	}
	
	private Node<E> head;
	private Node<E> tail;
	private int size = 0;
	
	public DoublyLinkedList()
	{
		head = new Node<>(null, null, null);
		tail = new Node<>(null, head, tail);
		head.setNext(tail);
	}
	
	private void addBetween(E e, Node<E> predecessor, Node<E> successor) 
	{
		Node<E> newNode = new Node<>(e, predecessor, successor);
		predecessor.setNext(newNode);
		successor.setPrev(newNode);
		size++;
	}
	
	@Override
	public int size() { return size; }

	@Override
	public boolean isEmpty() { return size == 0; }

	@Override
	public E get(int i) { return get(i); }

	@Override
	public void add(int i, E e) 
	{
//		if(i == size) { addLast(e); }
//		
//		else { addFirst(e); }
		
		add(i, e);
	}

	public E remove(Node<E> node) 
	{
		Node<E> predecessor = node.getPrev();
		Node<E> successor = node.getNext();
		predecessor.setNext(successor);
		successor.setPrev(predecessor);
		size--;
		
		return node.getElement();
	}

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
	public E removeFirst() 
	{
		if(isEmpty()) { return null; }
		
		return remove(head.getNext());
	}

	@Override
	public E removeLast() 
	{
		if(isEmpty()) { return null; }
		
		return remove(tail.getPrev());
	}
	

	@Override
	public void addFirst(E e) 
	{
		addBetween(e, head, head.getNext());
	}

	@Override
	public void addLast(E e) 
	{
		addBetween(e, tail.getPrev(), tail);
	}
	
	public E first()
	{
		if(isEmpty()) { return null; }
		
		return head.getNext().getElement();
	}
	
	public E last()
	{
		if(isEmpty()) { return null; }
		
		return tail.getPrev().getElement();
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		Node<E> tmp = head.getNext();
		
		sb.append("[");
		
		while(tmp != tail)
		{
			sb.append(tmp.getElement());
			tmp = tmp.getNext();
			
			if(tmp != tail)
			{
				sb.append(", ");
			}
		}
		
		sb.append("]");
		return sb.toString();
	}
	
	public static void main(String[] args) {
		   DoublyLinkedList<Integer> ll = new DoublyLinkedList<Integer>();
           ll.addFirst(0);
           ll.addFirst(1);
           ll.addFirst(2);
           ll.addLast(-1);
           System.out.println(ll);
           
           ll.removeFirst();
           System.out.println(ll);

           ll.removeLast();
           System.out.println(ll);
           
           for(Integer e: ll) {
                   System.out.println("value: " + e);
           }
	}

	@Override
	public E remove(int i) { return remove(i); }	
}
