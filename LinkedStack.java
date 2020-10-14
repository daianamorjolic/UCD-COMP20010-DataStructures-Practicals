package projectCode20280;

import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Objects;

public class LinkedStack<E> implements Stack<E> 
{
	private LinkedList<E> stack;
	
	public LinkedStack() { stack = new LinkedList<E>(); }
	
	//Adds at the beginning of the stack .
	@Override
	public void push(E e) { stack.addFirst(e); }
	
	//Removes from the beginning of the stack .
	@Override
	public E pop() 
	{ 
		if(isEmpty()) { throw new EmptyStackException(); }
		
		return stack.removeFirst(); 
	}
	
	@Override
	public E top() { return stack.getFirst(); }

	@Override
	public int size() { return stack.size(); }

	@Override
	public boolean isEmpty() { return stack.isEmpty(); }
	
	public void printStack()
	{
		ListIterator<E> iterator = stack.listIterator();
		
		while(iterator.hasNext())
		{
			System.out.println(iterator.next());
		}
	}
	
	public String toString()
	{
		return stack.toString();
	}
	
	public static void main(String[] args) 
	{
		LinkedStack stack = new LinkedStack();
		
        stack.push(new Employee("Jane", "Jones", 123));
        stack.push(new Employee("John", "Doe", 4567));
        stack.push(new Employee("Mary", "Smith", 22));
        stack.push(new Employee("Mike", "Wilson", 3245));
        stack.push(new Employee("Bill", "End", 78));

        stack.printStack();
        System.out.println("");

        System.out.println("Top : " + stack.top());

        System.out.println("Popped : " + stack.pop());
        System.out.println("Top : " + stack.top());
        System.out.println("");

        Employee alexDunne = new Employee("Alex", "Dunne", 121);
        stack.push(alexDunne);
        stack.printStack();
        System.out.println("");
        
        System.out.println("Top : " + stack.top());
        
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.printStack();
	}
}
