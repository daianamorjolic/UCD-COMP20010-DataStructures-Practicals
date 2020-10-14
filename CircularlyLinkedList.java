package projectCode20280;

import java.util.Iterator;

public class CircularlyLinkedList<E> implements List<E> 
{
	private Node<E> head = null;
	private Node<E> tail = null;
	private int size = 0;
	public CircularlyLinkedList() {}
	
	private class Node<E> 
	{
		private E element;
		private Node<E> next;
		
		public Node(E e , Node<E> n)
		{
			element = e;
			next = n;
		}
		
		public E getElement()
		{
			return element;
		}
		
		public Node<E> getNext()
		{
			return next;
		}
		
		public void setNext(Node<E> n)
		{
			next = n;
		}
	}

	@Override
	public int size() 
	{
		return size;
	}

	@Override
	public boolean isEmpty() 
	{
		return size == 0;
	}

	@Override
	public E get(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(int i, E e) 
	{
		if(i == size)
		{
			addLast(e);
		}
		
		else
		{
			addFirst(e);
		}
	}

	@Override
	public E remove(int i) 
	{
		return removeFirst();
	}

	@Override
	public E removeFirst() 
	{
		if(isEmpty())
		{
			return null;
		}
		
		Node<E> head = tail.getNext();
		
		if(head == tail)
		{
			tail = null;
		}
		
		else
		{
			tail.setNext(head.getNext());
		}
		
		size--;
		return head.getElement();
	}

	@Override
	public E removeLast() 
	{
		if(isEmpty())
		{
			return null;
		}
		
		E answer = tail.getElement();
		size--;
		
		if(size == 0)
		{
			head = null;
		}
		
		return answer;
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
		
		public ListIterator()
		{
			currentNode = head;
		}
		
		public boolean hasNext()
		{
			return sentinel;
		}
		
		public E next()
		{
			E data = currentNode.getElement();
			currentNode = currentNode.getNext();
			
			if(currentNode == head)
			{
				sentinel = false;
			}
			
			return data;
		}
	}

	@Override
	public void addFirst(E e) 
	{
		if(size == 0)
		{
			tail = new Node<>(e, null);
			tail.setNext(tail);
		}
		
		else
		{
			Node<E> newest = new Node<>(e, tail.getNext());
			tail.setNext(newest);
		}
		
		size++;
	}

	@Override
	public void addLast(E e) 
	{
		addFirst(e);
		tail = tail.getNext();

	}

	public void rotate() 
	{
		if(tail != null)
		{
			tail = tail.getNext();
		}
	}
	
	
	public static void main(String[] args) {
		CircularlyLinkedList<Integer> ll = new CircularlyLinkedList<Integer>();
		for(int i = 10; i < 20; ++i) {
			ll.addLast(i);
		}

		System.out.println(ll);

		ll.removeFirst();
		System.out.println(ll);

		ll.removeLast();

		ll.rotate();
		System.out.println(ll);

		ll.removeFirst();
		ll.rotate();
		System.out.println(ll);

		ll.removeLast();
		ll.rotate();
		System.out.println(ll);

		for (Integer e : ll) {
			System.out.println("value: " + e);
		}

	}
}
