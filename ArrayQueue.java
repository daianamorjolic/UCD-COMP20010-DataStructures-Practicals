package projectCode20280;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayQueue<E> implements Queue<E> 
{
	private E[] queue;
	private int front;
	private int back;
	
	public ArrayQueue(int capacity) { queue = (E[]) new Object[capacity]; }
	
	@Override
	public int size() { return back - front; }

	@Override
	public boolean isEmpty() { return size() == 0; }

	@Override
	public void enqueue(E e)
	{
		if(back == queue.length) 
		{ 
			E[] newArr = (E[]) new Object[2 * queue.length];
			System.arraycopy(queue, 0, newArr, 0, queue.length);
			queue = newArr;
		}
		
		queue[back] = e;
		back++;
	}

	@Override
	public E first() 
	{
		if(isEmpty()) { throw new NoSuchElementException(); }
		
		return queue[front];
	}

	//Removes first object in the queue .
	@Override
	public E dequeue() 
	{
		if(isEmpty()) { throw new NoSuchElementException(); }
		
		E e = queue[front];
		queue[front] = null;
		front++;
		
		if(size() == 0)
		{
			front = 0;
			back = 0;
		}
		
		return e;
	}
	
	public void printQueue()
    {
        for(int i = front; i < back; i++)
        {
            System.out.println(queue[i]);
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
