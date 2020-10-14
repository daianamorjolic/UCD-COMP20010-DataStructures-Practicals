package projectCode20280;

import java.util.ArrayList;
import java.util.HashMap;

/*
 * Map implementation using hash table with separate chaining.
 */

public class ChainHashMap<K, V> extends AbstractHashMap<K, V> 
{
	// a fixed capacity array of UnsortedTableMap that serve as buckets
	private UnsortedTableMap<K, V>[] table; // initialized within createTable

	/** Creates a hash table with capacity 11 and prime factor 109345121. */
	public ChainHashMap() { super(); }

	/** Creates a hash table with given capacity and prime factor 109345121. */
	public ChainHashMap(int cap) { super(cap); }

	/** Creates a hash table with the given capacity and prime factor. */
	public ChainHashMap(int cap, int p) { super(cap, p); }

	/** Creates an empty table having length equal to current capacity. */
	@Override
	@SuppressWarnings({ "unchecked" })
	protected void createTable() 
	{
		table = (UnsortedTableMap<K,V>[]) new UnsortedTableMap[capacity];
	}

	/**
	 * Returns value associated with key k in bucket with hash value h. If no such
	 * entry exists, returns null.
	 * 
	 * @param h the hash value of the relevant bucket
	 * @param k the key of interest
	 * @return associate value (or null, if no such entry)
	 */
	@Override
	protected V bucketGet(int hashedValue, K key) 
	{
		UnsortedTableMap<K,V> bucket = table[hashedValue];
		
		if(bucket == null) { return null; }
		
		return bucket.get(key);
	}

	/**
	 * Associates key k with value v in bucket with hash value h, returning the
	 * previously associated value, if any.
	 * 
	 * @param h the hash value of the relevant bucket
	 * @param k the key of interest
	 * @param v the value to be associated
	 * @return previous value associated with k (or null, if no such entry)
	 */
	@Override
	protected V bucketPut(int hashedValue, K key, V value) 
	{
		UnsortedTableMap<K,V> bucket = table[hashedValue];
		
		if(bucket == null) { bucket = table[hashedValue] = new UnsortedTableMap<>(); }
		
		int oldSize = bucket.size();
		V answer = bucket.put(key, value);
		n += (bucket.size() - oldSize);
		return answer;
	}

	/**
	 * Removes entry having key k from bucket with hash value h, returning the
	 * previously associated value, if found.
	 * 
	 * @param h the hash value of the relevant bucket
	 * @param k the key of interest
	 * @return previous value associated with k (or null, if no such entry)
	 */
	@Override
	protected V bucketRemove(int hashedValue, K key) 
	{
		UnsortedTableMap<K,V> bucket = table[hashedValue];
		
		if(bucket == null) { return null; }
		
		int oldSize = bucket.size();
		V answer = bucket.remove(key);
		n -= (oldSize - bucket.size());
		return answer;
	}

	/**
	 * Returns an iterable collection of all key-value entries of the map.
	 *
	 * @return iterable collection of the map's entries
	 */
	@Override
	public Iterable<Entry<K, V>> entrySet() 
	{
		ArrayList<Entry<K,V>> buffer = new ArrayList<>();
		
		for(int hashedValue = 0; hashedValue < capacity; hashedValue++)
		{
			if(table[hashedValue] != null)
			{
				for(Entry<K,V> entry : table[hashedValue].entrySet())
				{
					buffer.add(entry);
				}
			}
		}
		
		return buffer;
	}
	
	public static void main(String[] args) 
	{
		ChainHashMap<Integer, String> map = new ChainHashMap<Integer, String>();
		
		map.bucketPut(0, 1, "One");
		map.bucketPut(1, 10, "Ten");
		map.bucketPut(2, 11, "Eleven");
		map.bucketPut(3, 12, "Twelve");
		
		System.out.println("Map : " + map.entrySet());
		System.out.println("");
		
		map.bucketRemove(2, 11);
		System.out.println("Map : " + map.entrySet());
		System.out.println("");
		
		//Expected Answer : One
		System.out.println(map.bucketGet(0, 1));
		//Expected Answer : Ten
		System.out.println(map.bucketGet(1, 10));
		//Expected Answer : null
		System.out.println(map.bucketGet(2, 11));
		//Expected Answer : Twelve
		System.out.println(map.bucketGet(3, 12));
		
		map.bucketRemove(0, 1);
		map.bucketRemove(1, 10);
		map.bucketRemove(3, 12);
		System.out.println("Map : " + map.entrySet());
		System.out.println("");
	}
}
