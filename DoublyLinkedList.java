public class DoublyLinkedList<E>
{
	ListNode<E> root;
	ListNode<E> end;
	int count = 0;
	public DoublyLinkedList()
	{
		root = null;
		end = null;
	}

	public ListNode<E> get(int index) throws RuntimeException
	{
		if(index < 0 || index >= count)
			throw new RuntimeException("Index out of bounds");

		else if(index >= count/2)
		{
			DoublyLinkedList<E>.ListNode<E> listNode = end;
			int counter = count - 1;
			while(counter > index)
			{
				listNode = listNode.getPrevious();
				counter--;
			}
			return listNode;
		}
		else
		{
			DoublyLinkedList<E>.ListNode<E> listNode = root;
			int counter = 0;
			while(counter < index)
			{
				listNode = listNode.getNext();
				counter++;
			}
			return listNode;
		}

	}

	public void add(E val)
	{
		if(root == null)
		{
			root = new ListNode<E>(val);
			end = root;
		}
		else
		{
			ListNode<E> temp = new ListNode<E>(val);
			temp.setPrevious(end);
			end.setNext(temp);
			end = temp;

		}
		count++;
	}
	public boolean isEmpty() { return count == 0; }
	public boolean contains(E value)
	{
		ListNode<E> current = root;
		while(current != null)
		{
			if(current.getValue() == value)
				return true;
			current = current.getNext();

		}
		return false;
	}
	public boolean contains(ListNode<E> value)
	{
		ListNode<E> current = root;
		while(current != null)
		{
			if(current == value)
				return true;
			current = current.getNext();

		}
		return false;
	}
	public int size() { return count; }
	public E poll()
	{
		E val = root.getValue();
		root = root.getNext();
		if(root != null)
			root.setPrevious(null);
		count--;
		return val;
	}
	public ListNode<E> getRoot() { return root; }
	public ListNode<E> getEnd() { return end; }
	public void clear()
	{
		DoublyLinkedList<E>.ListNode<E> listNode = root;
		while(listNode.hasNext())
		{
			listNode = listNode.getNext();
			listNode.getPrevious().destroy();
		}
		listNode.destroy();
	}
	public void add(int index, E val)
	{
		if(index == count)
			add(val);

		else
		{
			count++;
			ListNode<E> before = null;
			ListNode<E> after = root;

			for(int i = index; i > 0; i--)
			{
				after = after.getNext();
				before = after.getPrevious();

			}

			ListNode<E> value = new ListNode<E>(val);
			if(before != null)
			{
				before.setNext(value);
				value.setPrevious(before);
			}
			else
				root = value;


			if(after != null)
			{
				after.setPrevious(value);
				value.setNext(after);
			}
			else
				end = value;


		}
	}
	public E remove(int index)
	{
		ListNode<E> before;
		ListNode<E> current;
		ListNode<E> after;
		if(index < count/2)
		{
			before = null;
			current = root;
			after = current.getNext();

			for(int i = 0; i < index; i++)
			{
				current = current.getNext();
				after = current.getNext();
				before = current.getPrevious();
			}
		}
		else
		{
			after = null;
			current = end;
			before = current.getPrevious();
			for(int i = count - 1; i > index; i--)
			{
				current = current.getPrevious();
				before = current.getPrevious();
				after = current.getNext();
			}
		}

		E temp = current.destroy();
		if(before != null && after != null)
		{
			before.setNext(after);
			after.setPrevious(before);
		}

		if(before == null)
			root = after;
		if(after == null)
			end = before;
		count--;
		return temp;

	}
	public String toString()
	{
		if(count == 0)
			return "[]";
		String output = "";
		ListNode<E> current = root;
		while(current != null)
		{
			output += "[" + current.toString() + "] ";
			current = current.getNext();
		}
		return output;
	}

	public String toReversedString()
	{
		if(count == 0)
			return "[]";
		String output = "";
		ListNode<E> current = end;
		while(current != null)
		{
			output += "[" + current.toString() + "] ";
			current = current.getPrevious();
		}
		return output;
	}
	public class ListNode<E>
	{
		E value;
		ListNode<E> previous = null;
		ListNode<E> next = null;
		public ListNode(){}
		public ListNode(E value) { this.value = value; }
		public ListNode<E> getNext()
		{
			if(hasNext())
				return next;
			return null;
		}
		public ListNode<E> getPrevious()
		{
			if(hasPrevious())
				return previous;
			return null;
		}
		public E getValue() { return value; }
		public void setPrevious(ListNode<E> previous)
		{
			this.previous = previous;
			if(previous != null && (!previous.hasNext() || previous.getNext() != this))
				previous.setNext(this);
		}
		public void setNext(ListNode<E> next)
		{
			this.next = next;

			if(next != null && (!next.hasPrevious() || next.getPrevious() != this))
				next.setPrevious(this);
		}
		public boolean hasPrevious() { return previous != null; }
		public boolean hasNext() { return next != null; }
		public String toString() { return "" + value; }
		public E destroy()
		{
			if(previous != null)
				previous.setNext(null);
			if(next != null)
				next.setPrevious(null);
			E temp = value;
			value = null;
			previous = null;
			next = null;
			return temp;
		}
	}
}