package projectCode20280;

import java.util.NoSuchElementException;

/**
 * Realization of a circular FIFO queue as an adaptation of a
 * CircularlyLinkedList. This provides one additional method not part of the
 * general Queue interface. A call to rotate() is a more efficient simulation of
 * the combination enqueue(dequeue()). All operations are performed in constant
 * time.
 */

public class LinkedCircularQueue<E> implements Queue<E> 
{
	private E[] queue;
	private int front;
	private int back;
	
	@Override
	public void enqueue(E e) 
	{
		if(size() == queue.length - 1)
		{
			int numItems = size();
			
			E[] newArr = (E[]) new Object[2 * queue.length];
			System.arraycopy(queue, front, newArr, 0, queue.length - front);
			System.arraycopy(queue, 0, newArr, queue.length - front, back);
			
			queue = newArr;
			front = 0;
			back = numItems;
		}
		
		queue[back] = e;
		
		if(back < queue.length - 1) { back++; }
		else { back = 0; }
	}
	
	@Override
	public int size() 
	{
		if(front <= back) { return back - front; }
		
		else { return back - front + queue.length; }
	}

	@Override
	public boolean isEmpty() { return size() == 0; }

	@Override
	public E first() 
	{
		if(size() == 0) { throw new NoSuchElementException(); }
		
		return queue[front];
	}

	@Override
	public E dequeue() 
	{
		if(size() == 0) { throw new NoSuchElementException(); }
		
		E e = queue[front];
		queue[front] = null;
		front++;
		
		if(size() == 0)
		{
			front = 0;
			back = 0;
		}
		
		else if(front == queue.length) { front = 0; }
		return e;
	}
	
	public void printQueue()
    {
        //if queue has wrapped we print in 2 pieces .
        if(front <= back)
        {
            for (int i = front; i < back; i++)
            {
                System.out.println(queue[i]);
            }
        }
        
        else
        {
            for(int i = front; i < queue.length; i++)
            {
                System.out.println(queue[i]);
            }

            for(int i = 0; i < back; i++)
            {
                System.out.println(queue[i]);
            }
        }
    }
	
	public static void main(String[] args) 
	{
		Employee janeJones = new Employee("Jane", "Jones", 123);
        Employee johnDoe = new Employee("John", "Doe", 4567);
        Employee marySmith = new Employee("Mary", "Smith", 22);
        Employee mikeWilson = new Employee("Mike", "Wilson", 3245);
        Employee billEnd = new Employee("Bill", "End", 78);
        
        ArrayQueue queue = new ArrayQueue(10);
        
        queue.enqueue(janeJones);
        queue.enqueue(johnDoe);
        queue.enqueue(marySmith);
        queue.enqueue(mikeWilson);
        
        queue.printQueue();
        System.out.println("");
        
        //Expected answer : Jane Jones 
        System.out.println("First : " + queue.first());
        System.out.println("");
        
        queue.dequeue();
        queue.printQueue();
        System.out.println("");
        
        queue.enqueue(billEnd);
        queue.printQueue();
        System.out.println("");
        
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        
        //Expected answer : No such element exists exception .
        System.out.println("First : " + queue.first());
	}
}
