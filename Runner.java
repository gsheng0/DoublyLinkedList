public class Runner
{
	String str = "";
	DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
	DoublyLinkedList<String> sentence = new DoublyLinkedList<>();
	public Runner()
	{
		for(int i = 0; i < 30; i++)
			list.add((int)(Math.random() * 1000));

		System.out.println("List: " + list.toString());
		System.out.println("Reversed List: " + list.toReversedString());
		System.out.println("Size: " + list.size());
		System.out.println("Sum: " + getSum());
		System.out.println("Even Sum: " + getEvenSum());
		System.out.println("Odd Sum: " + getOddSum());
		addEvenIndexes();
		System.out.println("List: " + list.toString());
		System.out.println("Size: " + list.size());
		remove3();
		System.out.println("List: " + list.toString());
		list.add(0, 55555);
		System.out.println("List: " + list.toString());
		sort();
		System.out.println("List: " + list);
		System.out.println("Size: " + list.size());
		System.out.println("Median: " + median());

		String[] words = "I want to finish this project already".split(" ");
		for(String word : words)
			sentence.add(word);
		System.out.println("Sentence: " + sentence.toString());
		removeLength3();
		System.out.println("Sentence: " + sentence.toString());
		sortStrings();
		System.out.println("Sentence: " + sentence.toString());
		System.out.println("Average Word Length: " + averageWordLength());





	}
	public double averageWordLength()
	{
		double total = 0;
		DoublyLinkedList<String>.ListNode<String> listNode = sentence.getRoot();
		while(listNode != null)
		{
			total += listNode.getValue().length();
			listNode = listNode.getNext();
		}
		return total/sentence.size();
	}
	public void removeLength3()
	{
		DoublyLinkedList<String>.ListNode<String> listNode = sentence.getRoot();
		int index = 0;
		while(listNode != null)
		{
			boolean remove = false;
			if(listNode.getValue().length() < 3)
				remove = true;
			listNode = listNode.getNext();
			if(remove)
				sentence.remove(index);
			else
				index++;
		}
	}
	public double median()
	{
		if(list.size() % 2 == 0)
		{
			DoublyLinkedList<Integer>.ListNode<Integer> lowMed = list.getRoot();
			DoublyLinkedList<Integer>.ListNode<Integer> highMed = list.getEnd();
			for(int i = 0; i < list.size()/2; i++)
			{
				lowMed = lowMed.getNext();
				highMed = highMed.getPrevious();
			}
			return (lowMed.getValue() + highMed.getValue())/2;
		}
		else
		{
			DoublyLinkedList<Integer>.ListNode<Integer> mid = list.getRoot();
			for(int i = 0; i < list.size()/2; i++)
				mid = mid.getNext();
			return mid.getValue();
		}
	}
	public void sort()
	{
		int index = 0;
		while(index < list.size() - 1)
		{
			DoublyLinkedList<Integer>.ListNode<Integer> listNode = list.getRoot();
			for(int i = 0; i < index; i++)
				listNode = listNode.getNext();

			int min = listNode.getValue();
			int i = index;
			int counter = index;
			while(listNode != null)
			{
				if(listNode.getValue() < min)
				{
					min = listNode.getValue();
					i = counter;
				}
				listNode = listNode.getNext();
				counter++;
			}

			list.add(index, list.remove(i));
			index++;
		}
	}
	public void sortStrings()
	{
		int index = 0;
		while(index < sentence.size() - 1)
		{
			DoublyLinkedList<String>.ListNode<String> sentenceNode = sentence.getRoot();
			for(int i = 0; i < index; i++)
				sentenceNode = sentenceNode.getNext();

			int min = sentenceNode.getValue().length();
			int i = index;
			int counter = index;
			while(sentenceNode != null)
			{
				if(sentenceNode.getValue().length() < min)
				{
					min = sentenceNode.getValue().length();
					i = counter;
				}
				sentenceNode = sentenceNode.getNext();
				counter++;
			}

			sentence.add(index, sentence.remove(i));
			index++;
		}
	}
	public void remove3()
	{
		int index = 0;
		DoublyLinkedList<Integer>.ListNode<Integer> listNode = list.getRoot();
		while(listNode != null)
		{
			boolean remove = false;
			if(listNode.getValue() % 3 == 0)
				remove = true;
			listNode = listNode.getNext();
			if(remove)
				list.remove(index);
			else
				index++;
		}
	}
	public void printStr() { System.out.println(str); }
	public void printList() { System.out.println(list); }
	public void addEvenIndexes()
	{
		DoublyLinkedList<Integer> templist = new DoublyLinkedList<>();
		DoublyLinkedList<Integer>.ListNode<Integer> listNode = list.getRoot();
		while(listNode != null)
		{
			templist.add(listNode.getValue());
			listNode = listNode.getNext();
			if(listNode == null || listNode.equals(list.getEnd()))
				break;
			listNode = listNode.getNext();

		}
		listNode = templist.getRoot();
		while(listNode != null)
		{
			list.add(listNode.getValue());
			listNode = listNode.getNext();
		}
	}
	public int getSum()
	{
		int sum = 0;
		DoublyLinkedList<Integer>.ListNode<Integer> listNode = list.getRoot();
		while(listNode != null)
		{
			sum += (int)listNode.getValue();
			listNode = listNode.getNext();
		}
		return sum;
	}
	public int getEvenSum()
	{
		int sum = 0;
		DoublyLinkedList<Integer>.ListNode<Integer> listNode = list.getRoot();
		while(listNode != null)
		{
			sum += (int)listNode.getValue();
			listNode = listNode.getNext();
			if(listNode == null || listNode.equals(list.getEnd()))
				break;
			listNode = listNode.getNext();

		}
		return sum;
	}
	public int getOddSum()
	{
		int sum = 0;
		DoublyLinkedList<Integer>.ListNode<Integer> listNode = list.getRoot().getNext();
		while(listNode != null)
		{
			sum += (int)listNode.getValue();
			listNode = listNode.getNext();
			if(listNode == null || listNode.equals(list.getEnd()))
				break;
			listNode = listNode.getNext();
		}
		return sum;
	}
	public static void main(String args[])
	{
		Runner app = new Runner();
	}
}