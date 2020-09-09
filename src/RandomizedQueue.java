import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> 
{
	private Item[] array;
	private int size;
	
	public RandomizedQueue() 
	{
		size = 0;
		array = (Item[]) new Object[1];
	}
	
	public boolean isEmpty() 
	{
		return size == 0;
	}
	
	// return the number of items on the randomized queue
    public int size()
    {
    	return size;
    }
	
	public void enqueue(Item item)
	{
		if (item == null)
    	{
    		throw new IllegalArgumentException();
    	}
		
		if (array == null)
		{
			throw new NullPointerException("The array is null. Please initialize it before pushing values.");
		}
		
		if (isFull())
		{
			resize((array.length * 3 / 2) + 1);
		}
		
		array[size++] = item;
	}
	
	public Item dequeue() 
	{
		if (isEmpty() || array == null)
		{
			throw new NoSuchElementException("The Queue is empty. Nothing to pop.");
		}
		
		int random = StdRandom.uniform(size);
		Item value = array[random];
		array[random] = array[size - 1];
		array[size - 1] = null;
		
		--size;
		
		if (size > 0 && size == array.length / 4)
		{
			resize(array.length / 2);
		}
		
		return value;
	}
	
	// return a random item (but do not remove it)
    public Item sample()
    {
    	if (isEmpty() || array == null)
		{
			throw new NoSuchElementException("The Queue is empty. Nothing to pop.");
		}
    	
    	return array[StdRandom.uniform(size)];
    }
	
	public Iterator<Item> iterator() 
	{ 
		return new randomQueueIterator(); 
	}
	
	private void resize(int capacity)
	{
		Item[] temp = (Item[]) new Object[capacity];
		
		for (int i = 0; i < size; i++)
		{
			temp[i] = array[i];
		}
		
		array = temp;
		temp = null;
	}
	
	private boolean isFull() 
	{
		return size == array.length;
	}
	
	private class randomQueueIterator implements Iterator<Item>
	{
		private Item[] temp = (Item[]) new Object[array.length];
	    private int tempSize = size;
	    
	    public randomQueueIterator()
	    {
			for (int i = 0; i < array.length; i++)
			{
			    temp[i] = array[i];
			}
	    }
	    
	    public boolean hasNext() 
	    {
	    	return tempSize > 0;
	    }

	    public Item next() 
	    {
			if (!hasNext()) 
			{
			    throw new NoSuchElementException();
			}
			
			int rd = StdRandom.uniform(tempSize);
			Item item = temp[rd];
			temp[rd] = temp[tempSize-1];
			temp[tempSize-1] = null;
			
			tempSize--;
			
			return item;
	    }
	    
	    public void remove()
	    {
	    	throw new UnsupportedOperationException();
	    }
	}
	
	// unit testing (required)
    public static void main(String[] args)
    {
    	RandomizedQueue<String> queue = new RandomizedQueue<String>();
		System.out.println("size : " + queue.size());
		
		System.out.println("===============Add to The Queue===============");
		for (int i = 0; i < 5; ++i)
		{
			queue.enqueue(String.valueOf((char) (i + 65)));
		}
		System.out.println("size : " + queue.size());
		
		Iterator<String> it = queue.iterator();
		while (it.hasNext())
		{
			System.out.print(it.next());
		}
		System.out.println();
		
		System.out.println("===============Remove Randomly===============");
		for (int i = 0; i < 5; ++i)
		{
			System.out.print(queue.dequeue());
		}
		System.out.println();
		
		System.out.println("===============Add to The Queue===============");
		for (int i = 5; i < 10; ++i)
		{
			queue.enqueue(String.valueOf((char) (i + 65)));
		}
		System.out.println("size : " + queue.size());
		
		it = queue.iterator();
		while (it.hasNext())
		{
			System.out.print(it.next());
		}
		System.out.println();
		
		System.out.println("===============Remove Randomly===============");
		for (int i = 0; i < 5; ++i)
		{
			System.out.print(queue.dequeue());
		}
	
		System.out.println("===============Exceptions===============");
		it.next();
		queue.enqueue(null);
		
		System.out.println();
		
		queue.dequeue();
    }
}
