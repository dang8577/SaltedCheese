package linked;

import java.util.*;


public class CharLinkedList 
{
	private CharNode		head;	// Empty if head and
	private CharNode		tail;	// tail are null


	public CharLinkedList()		{ }


	public CharLinkedList(String s)
	{
		for (int i=s.length()-1; i>=0; i--)
			insertAtHead(s.charAt(i));
	}


	public void insertAtHead(char ch)
	{
		assert hasIntegrity();		// Precondition

		CharNode node = new CharNode(ch);
		node.setNext(head);
		head = node;
		if (tail == null)
			tail = node;			// Corner case: inserting into empty node

		assert hasIntegrity();		// Postcondition
	}


	public String toString()
	{
		String s = "";
		CharNode node = head;
		while (node != null)
		{
			s += node.getData();
			node = node.getNext();
		}
		return s;
	}


	//
	// Returns true if this list has emptiness integrity, has tail integrity, has no loops,  
	// and tail is reachable from head.
	//
	// Caution: this checks for most but not all common integrity problems. 
	//
	boolean hasIntegrity()
	{
		// Check emptiness. If either head or tail is null, the other must
		// also be null. Different logic from what you saw in lecture. Returns
		// immediately if this list is empty.
		if (head == null  ||  tail == null)
			return head == null  &&  tail == null;

		// Check tail integrity (tail.next must be null).
		if (tail.getNext() != null)
			return false;

		// Check for loops.
		Set<CharNode> visitedNodes = new HashSet<>();
		CharNode node = head;
		while (node != null)
		{
			if (visitedNodes.contains(node))
				return false;		// Current node has been visited before, we must have a loop
			visitedNodes.add(node); // First visit to this node
			node = node.getNext();
		}

		// Make sure tail is reachable from head.
		node = head;
		while (node != null && node != tail)
			node = node.getNext();
		return node == tail;
	}

	public CharNode find(char ch)
	{
		CharNode node = head;
		while(node != null)
		{
			if(node.getData() == ch)
			{
				return node;
			}
			
			node = node.getNext();
		}

		return null;
	}

	public void duplicate(char ch)
	{
		//try
		//{
			
			if(find(ch) == null)
			{
				throw new IllegalArgumentException("There is no node with the same data.");
			}
			
			CharNode newNode = new CharNode(ch);
			CharNode result = find(ch);
			if(result == head)
			{
				newNode.setNext(head);
				head = newNode;
				
			}
			
			else if(result == tail)
			{
				tail.setNext(newNode);
				tail = newNode;
			}
			
			else
			{
				newNode.setNext(result.getNext());
				result.setNext(newNode);
				
			}
			
		//}

		/*catch(IllegalArgumentException e)
		{
			System.out.println("There is no node with the same data.");
		}*/

		



	}
}
