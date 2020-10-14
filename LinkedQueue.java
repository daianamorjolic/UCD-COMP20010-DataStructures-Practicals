package projectCode20280;

import java.util.LinkedList;
import java.util.ListIterator;

public class LinkedQueue<E> implements Queue<E> 
{
	private LinkedList<E> queue = new LinkedList<>();
	
	public LinkedQueue( ) {}
	
	@Override
	public int size() { return queue.size(); }

	@Override
	public boolean isEmpty() { return queue.isEmpty(); }

	//Adds ad the end of the queue .
	@Override
	public void enqueue(E e) { queue.addLast(e); }

	@Override
	public E first() { return queue.getFirst(); }

	//Removes first from queue .
	@Override
	public E dequeue() 
	{ 
		if(isEmpty()) { System.out.println("Queue is empty !"); }
		
		return queue.removeFirst(); 
	}
	
	public void printQueue()
	{
		ListIterator<E> iterator = queue.listIterator();
		
		while(iterator.hasNext())
		{
			System.out.println(iterator.next());
		}
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		  
		  sb.append("[");
		  
		  for(int i = 0; i < size(); i++)
		  {
			  if(sb.length() != 1)
			  {
				  sb.append(", ");
			  }
			  
			  sb.append(i);
		  }
		  
		  sb.append("]");
		  return sb.toString();
	}
	
	public static void main(String[] args)
	{
		LinkedQueue queue = new LinkedQueue();
		
        queue.enqueue(new Employee("Jane", "Jones", 123));
        queue.enqueue(new Employee("John", "Doe", 4567));
        queue.enqueue(new Employee("Mary", "Smith", 22));
        queue.enqueue(new Employee("Mike", "Wilson", 3245));
        queue.enqueue(new Employee("Bill", "End", 78));

        queue.printQueue();
        System.out.println("");

        System.out.println("First in queue : " + queue.first());

        System.out.println("Dequeued : " + queue.dequeue());
        System.out.println("First in queue : " + queue.first());
        System.out.println("");

        Employee alexDunne = new Employee("Alex", "Dunne", 121);
        queue.enqueue(alexDunne);
        queue.printQueue();
        System.out.println("");
        
        System.out.println("First in queue : " + queue.first());
        
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.printQueue();
	}
}
