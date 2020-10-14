package projectCode20280;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 */

import java.util.ArrayList;
import java.util.Comparator;

/**
 * An implementation of a priority queue using an array-based heap.
 */

public class HeapPriorityQueue<K,V> extends AbstractPriorityQueue<K,V> 
{
	protected ArrayList<Entry<K,V>> heap = new ArrayList<>();

	/** Creates an empty priority queue based on the natural ordering of its keys. */
  public HeapPriorityQueue() { super(); }

  /**
   * Creates an empty priority queue using the given comparator to order keys.
   * @param comp comparator defining the order of keys in the priority queue
   */
  public HeapPriorityQueue(Comparator<K> comp) { super(comp); }

  /**
   * Creates a priority queue initialized with the respective
   * key-value pairs.  The two arrays given will be paired
   * element-by-element. They are presumed to have the same
   * length. (If not, entries will be created only up to the length of
   * the shorter of the arrays)
   * @param keys an array of the initial keys for the priority queue
   * @param values an array of the initial values for the priority queue
   */
  public HeapPriorityQueue(K[] keys, V[] values)
  {
	  super();
	  
	  for(int j = 0; j < Math.min(keys.length,  values.length); j++)
	  {
		  heap.add(new PQEntry<>(keys[j], values[j]));
	  }
	  
	  heapify();
  }

  // protected utilities
  protected int parent(int j) { return (j - 1) / 2; }
  protected int left(int j) { return 2 * j + 1; }
  protected int right(int j) { return 2 * j + 2; }
  protected boolean hasLeft(int j) { return left(j) < heap.size(); }
  protected boolean hasRight(int j) { return right(j) < heap.size(); }

  /** Exchanges the entries at indices i and j of the array list. */
  protected void swap(int i, int j)
  {
	  Entry<K,V> tmp = heap.get(i);
	  heap.set(i, heap.get(j));
	  heap.set(j, tmp);
  }

  /** Moves the entry at index j higher, if necessary, to restore the heap property. */
  protected void upheap(int j)
  {
	  while(j > 0)
	  {
		  int p = parent(j);
		  
		  if(compare(heap.get(j), heap.get(p)) >= 0) { break; }
		  
		  swap(j, p);
		  j = p;
	  }
  }
  
  /** Moves the entry at index j lower, if necessary, to restore the heap property. */
  protected void downheap(int j)
  {
	  while(hasLeft(j))
	  {
		  int leftIndex = left(j);
		  int smallChildIndex = leftIndex;
		  
		  if(hasRight(j))
		  {
			  int rightIndex = right(j);
			  if(compare(heap.get(leftIndex), heap.get(j)) >= 0) { smallChildIndex = rightIndex; }
		  }
		  
		  if(compare(heap.get(smallChildIndex), heap.get(j)) >= 0) { break; }
		  
		  swap(j, smallChildIndex);
		  j = smallChildIndex;
	  }
  }

  /** Performs a bottom-up construction of the heap in linear time. */
  protected void heapify()
  {
	  int startIndex = parent(size() - 1);
	  
	  for(int j = startIndex; j >= 0; j--)
	  {
		  downheap(j);
	  }
  }

  // public methods

  /**
   * Returns the number of items in the priority queue.
   * @return number of items
   */
  @Override
  public int size() { return heap.size(); }

  /**
   * Returns (but does not remove) an entry with minimal key.
   * @return entry having a minimal key (or null if empty)
   */
  @Override
  public Entry<K,V> min()
  {
	  if(heap.isEmpty()) { return null; }
	  
	  return heap.get(0);
  }

  /**
   * Inserts a key-value pair and return the entry created.
   * @param key     the key of the new entry
   * @param value   the associated value of the new entry
   * @return the entry storing the new key-value pair
   * @throws IllegalArgumentException if the key is unacceptable for this queue
   */
  @Override
  public Entry<K,V> insert(K key, V value) throws IllegalArgumentException
  {
	  checkKey(key);
	  
	  Entry<K,V> newValue = new PQEntry<>(key, value);
	  
	  heap.add(newValue);
	  upheap(heap.size() - 1);
	  
	  return newValue;
  }

  /**
   * Removes and returns an entry with minimal key.
   * @return the removed entry (or null if empty)
   */
  @Override
  public Entry<K,V> removeMin()
  {
	  if(heap.isEmpty()) { return null; }
	  
	  Entry<K,V> min = heap.get(0);
	  
	  swap(0, heap.size() - 1);
	  heap.remove(heap.size() - 1);
	  downheap(0);
	  
	  return min;
  }
  
  public String toString()
  {
	  return heap.toString();
  }
  
  public String toBinaryTreeString()
  {
	  LinkedBinaryTree<Entry<K,V>> bt = new LinkedBinaryTree<>();
	  bt.createLevelOrder(heap);
	  
	  BinaryTreePrinter<Entry<K,V>> btp = new BinaryTreePrinter<Entry<K,V>>(bt);
	  
	  return btp.print();
  }

  /** Used for debugging purposes only */
  private void sanityCheck() {
    for (int j=0; j < heap.size(); j++) {
      int left = left(j);
      int right = right(j);
      if (left < heap.size() && compare(heap.get(left), heap.get(j)) < 0)
        System.out.println("Invalid left child relationship");
      if (right < heap.size() && compare(heap.get(right), heap.get(j)) < 0)
        System.out.println("Invalid right child relationship");
    }
  }
  
  public static void main(String[] args)
  {
	  Integer[] arr = new Integer[] {35,26,15,24,33,4,12,1,23,21,2,5};
		HeapPriorityQueue<Integer, String> pq = new HeapPriorityQueue<>();

		for(Integer i : arr) pq.insert(i, Integer.toString(i));

		assertEquals("[1, 2, 5, 23, 4, 12, 15, 35, 24, 33, 21, 26]", pq.toString());
  }
}

