package projectCode20280;

import java.util.LinkedList;
import java.util.ListIterator;

public class LinkedDeque<E> implements Deque<E> 
{
	private LinkedList<E> deque = new LinkedList<>();
	
	public LinkedDeque() { }
	
	@Override
	public int size() { return deque.size(); }

	@Override
	public boolean isEmpty() { return deque.isEmpty(); }

	@Override
	public E first() { return deque.getFirst(); }

	@Override
	public E last() { return deque.getLast(); }

	@Override
	public void addFirst(E e) { deque.addFirst(e); }

	@Override
	public void addLast(E e) { deque.addLast(e); }

	@Override
	public E removeFirst() 
	{ 
		if(isEmpty()) { System.out.println("Deque is empty !"); }
		
		return deque.removeFirst(); 
	}

	@Override
	public E removeLast() 
	{ 
		if(isEmpty()) { System.out.println("Deque is empty !"); }
		
		return deque.removeLast(); 
	}
	
	public void printDeque()
	{
		ListIterator<E> iterator = deque.listIterator();
		
		while(iterator.hasNext())
		{
			System.out.println(iterator.next());
		}
	}
	
	public static void main(String[] args) 
	{
		LinkedDeque deque = new LinkedDeque();
		
        deque.addFirst(new Employee("Jane", "Jones", 123));
        deque.addFirst(new Employee("John", "Doe", 4567));
        deque.addFirst(new Employee("Mary", "Smith", 22));
        deque.addFirst(new Employee("Mike", "Wilson", 3245));
        deque.addFirst(new Employee("Bill", "End", 78));

        deque.printDeque();
        System.out.println("");

        System.out.println("First : " + deque.first());
        System.out.println("Last : " + deque.last());

        System.out.println("Removed : " + deque.removeFirst());
        System.out.println("First : " + deque.first());
        System.out.println("");

        Employee alexDunne = new Employee("Alex", "Dunne", 121);
        deque.addLast(alexDunne);
        deque.printDeque();
        System.out.println("");
        
        System.out.println("Last : " + deque.last());
        
        deque.removeFirst();
        deque.removeLast();
        deque.removeFirst();
        deque.removeLast();
        deque.removeFirst();
        deque.removeLast();
        deque.printDeque();

	}

}
