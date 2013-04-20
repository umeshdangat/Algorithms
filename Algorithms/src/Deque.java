import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

	private Node first;
	private Node last;
	private int size;

	private class Node {
		private Item item;
		private Node next;
		private Node prev;
	}

	// construct an empty deque
	public Deque() {
		first = null;
		last = null;
		size = 0;
	}

	// is the deque empty?
	public boolean isEmpty() {
		return first == null;
	}

	// return the number of items on the deque
	public int size() {
		return size;
	}

	// insert the item at the front
	public void addFirst(Item item) {

		if (item == null) {
			throw new NullPointerException();
		}

		if (isEmpty()) {
			first = new Node();
			first.item = item;
			first.next = null;
			first.prev = null;
			last = first;
		} else {
			Node oldFirst = first;
			first = new Node();
			first.item = item;
			first.next = oldFirst;
			first.prev = null;
			oldFirst.prev = first;
		}

		size++;

	}

	// insert the item at the end
	public void addLast(Item item) {

		if (item == null) {
			throw new NullPointerException();
		}

		if (isEmpty()) {
			last = new Node();
			last.item = item;
			last.next = null;
			last.prev = null;
			first = last;
		} else {
			Node oldLast = last;
			last = new Node();
			last.item = item;
			last.next = null;
			last.prev = oldLast;
			oldLast.next = last;
		}
		size++;
	}

	// delete and return the item at the front
	public Item removeFirst() {

		if (isEmpty()) {
			throw new NoSuchElementException();
		}

		Item item;

		if (size() == 1) {
			item = first.item;
			first = first.next;
			last = first;
		} else {
			Node oldFirst = first;
			first = first.next;
			first.prev = null;

			oldFirst.next = null;
			oldFirst.prev = null;
			item = oldFirst.item;

		}

		size--;
		return item;
	}

	// delete and return the item at the end
	public Item removeLast() {

		if (isEmpty()) {
			throw new NoSuchElementException();
		}

		Item item;
		if (size() == 1) {
			item = last.item;
			last = last.next;
			first = last;
		} else {
			Node oldLast = last;
			last = last.prev;
			last.next = null;

			oldLast.prev = null;
			oldLast.next = null;
			item = oldLast.item;
		}

		size--;
		return item;
	}

	@Override
	public Iterator<Item> iterator() {
		return new ListIterator();
	}

	// an iterator, doesn't implement remove() since it's optional
	private class ListIterator implements Iterator<Item> {
		private Node current = first;

		public boolean hasNext() {
			return current != null;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

		public Item next() {
			if (!hasNext())
				throw new NoSuchElementException();
			Item item = current.item;
			current = current.next;
			return item;
		}
	}

}
