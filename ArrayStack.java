package projectCode20280;

import java.util.EmptyStackException;
import java.util.Objects;

public class ArrayStack<E> implements Stack<E> 
{
	private E[] stack;
	private int top;
	
	public ArrayStack(int capacity) { stack = (E[]) new Object[capacity]; }
	
	@Override
	public void push(E e) 
	{
		if(top == stack.length) { throw new IllegalStateException("Stack is full"); }
		
		stack[top++] = e;
	}
	
	@Override
	public E pop() 
	{
		if(isEmpty()) { throw new EmptyStackException(); }
		
		E e = stack[--top];
		stack[top] = null;
		return e;
	}
	
	@Override
	public E top() 
	{
		if(isEmpty()) { throw new EmptyStackException(); }
		
		return stack[top - 1];
	}

	@Override
	public int size() { return top = 0; }

	@Override
	public boolean isEmpty() { return top == 0; }
	
	public void printStack()
	{
		for(int i = top - 1; i >= 0; i--)
		{
			System.out.println(stack[i]);
		}
	}
	
	public static void main(String[] args) 
	{
		ArrayStack stack = new ArrayStack(10);
		
      stack.push(new Employee("Jane", "Jones", 123));
      stack.push(new Employee("John", "Doe", 4567));
      stack.push(new Employee("Mary", "Smith", 22));
      stack.push(new Employee("Mike", "Wilson", 3245));
      stack.push(new Employee("Bill", "End", 78));

      stack.printStack();
      System.out.println("");

      //Expected Result : "Bill End" 
      System.out.println("Top : " + stack.top());
      //Expected Employee Popped : "Bill End"
      System.out.println("Popped : " + stack.pop());
      //Expected Result : "Mike Wilson"
      System.out.println("Top : " + stack.top());
      System.out.println("");

      Employee alexDunne = new Employee("Alex", "Dunne", 121);
      stack.push(alexDunne);
      stack.printStack();
      System.out.println("");
      
      //Expected Result : "Alex"
      System.out.println("Top : " + stack.top());
      
      stack.pop();
      stack.pop();
      stack.pop();
      stack.pop();
      stack.pop();
      stack.pop();
      //Expected Result : "Empty Stack Exception"
      System.out.println("Top : " + stack.top());
	}
}
