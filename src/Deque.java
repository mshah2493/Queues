import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> 
{
	private Node first;
	private Node last;
	private int size;
	
	private class Node
	{
		Item item;
		Node next;
		Node previous;
	}

    public Deque() 
    { 
    	size = 0;
    	first = null;
    	last = null;
    }
    
    public boolean isEmpty()
    {
    	return size == 0;
    }

    public int size()
    {
    	return size;
    }

    public void addFirst(Item item)
    {
    	if (item == null)
    	{
    		throw new IllegalArgumentException();
    	}
    	
    	Node node = new Node();
    	node.item = item;
    	
    	if (isEmpty())
    	{
    		node.next = null;
    		node.previous = null;
    		first = node;
    		last = node;
    	}
    	else
    	{
    		first.previous = node;
    		node.next = first;
    		node.previous = null;
    		first = node;
    	}
    	
    	++size;
    }

    public void addLast(Item item)
    {
    	if (item == null)
    	{
    		throw new IllegalArgumentException();
    	}
    	
    	if (isEmpty())
    	{
    		addFirst(item);
    	}
    	else
    	{
    		Node node = new Node();
    		node.item = item;
    		node.next = null;
    		node.previous = last;
    		last.next = node;
    		last = node;
    		
    	 	++size;
    	}
    }

    public Item removeFirst()
    {
    	if (isEmpty())
    	{
    		throw new NoSuchElementException();
    	}
    	
    	Item item = first.item;
    	
    	if (size == 1)
    	{
    		first = null;
    		last = null;
    	}
    	else
    	{
    		first = first.next;
        	first.previous = null;
    	}
    	
    	--size;
    	
    	return item;
    }

    public Item removeLast()
    {
    	if (isEmpty())
    	{
    		throw new NoSuchElementException();
    	}
    	
    	Item item = last.item;
    	
    	if (size == 1)
    	{
    		first = null;
    		last = null;
    	}
    	else
    	{
    		last = last.previous;
        	last.next = null;
    	}
    	
    	--size;
    	
    	return item;
    }

    public Iterator<Item> iterator()
    {
    	return new DequeIterator();
    }
    
    private class DequeIterator implements Iterator<Item>
	{
		Node node = first;
		
		public boolean hasNext() 
		{
			return node != null;
		}

		public Item next() 
		{
			if (!hasNext())
			{
				throw new NoSuchElementException();
			}
			
			Item value = node.item;
			node = node.next;
			
			return value;
		}
		
		public void remove()
		{
			throw new UnsupportedOperationException();
		}
	}

    public static void main(String[] args)
    {
    	Deque<String> deque = new Deque<String>();
		System.out.println("size : " + deque.size());
		
		System.out.println("===============Add from Front===============");
		for (int i = 0; i < 5; ++i)
		{
			deque.addFirst(String.valueOf((char) (i + 65)));
		}
		System.out.println("size : " + deque.size());
		
		Iterator<String> it = deque.iterator();
		while (it.hasNext())
		{
			System.out.print(it.next());
		}
		System.out.println();
		
		System.out.println("===============Add from Back===============");
		for (int i = 5; i < 10; ++i)
		{
			deque.addLast(String.valueOf((char) (i + 65)));
		}
		System.out.println("size : " + deque.size());
		
		it = deque.iterator();
		while (it.hasNext())
		{
			System.out.print(it.next());
		}
		System.out.println();
		
		System.out.println("===============Remove from Front===============");
		for (int i = 0; i < 5; ++i)
		{
			System.out.print(deque.removeFirst());
		}
		System.out.println();
		
		
		System.out.println("===============Remove from back===============");
		for (int i = 0; i < 5; ++i)
		{
			System.out.print(deque.removeLast());
		}
		System.out.println();
		
		System.out.println("===============Add from Back===============");
		for (int i = 5; i < 10; ++i)
		{
			deque.addLast(String.valueOf((char) (i + 65)));
		}
		System.out.println("size : " + deque.size());
		
		it = deque.iterator();
		while (it.hasNext())
		{
			System.out.print(it.next());
		}
		System.out.println();
		
		System.out.println("===============Add from Front===============");
		for (int i = 0; i < 5; ++i)
		{
			deque.addFirst(String.valueOf((char) (i + 65)));
		}
		System.out.println("size : " + deque.size());
		
		it = deque.iterator();
		while (it.hasNext())
		{
			System.out.print(it.next());
		}
		System.out.println();
		
		Deque<String> s = new Deque<String>();
		s.addLast("TUZLSMVSJB");
		s.addLast("IVBVGJCADL");
		System.out.println(s.removeLast());
		System.out.println(s.removeLast());
		
		
		System.out.println("===============Integer===============");
		Deque<Integer> d = new Deque<Integer>();
		System.out.println("size : " + d.size());       
		d.addLast(2);
		System.out.println("size : " + d.removeLast());
		
		System.out.println("===============Exceptions===============");
		deque.addFirst(null);
		deque.removeFirst();
		deque.removeLast();
		it.next();
    }
}