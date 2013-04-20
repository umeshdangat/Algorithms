import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

	private Item[] items;
	private int size;

	// construct an empty randomized queue
	public RandomizedQueue() {
		items = (Item[]) new Object[1];
		size = 0;
	}

	// is the queue empty?
	public boolean isEmpty() {
		return size == 0;
	}

	// return the number of items on the queue
	public int size() {
		return size;
	}

	// add the item
	public void enqueue(Item item) {

		if (item == null) {
			throw new NullPointerException();
		}

		// double size of array when array is full
		if (size == items.length) {
			resize(2 * items.length);
		}

		items[size] = item;
		size++;

	}

	// delete and return a random item
	public Item dequeue() {

		if (isEmpty()) {
			throw new NoSuchElementException();
		}

		// half the size of array when array is 1/4 full
		if (size > 0 && size == items.length / 4) {
			resize(items.length / 2);
		}

		// get randomIndex, swap it with the end item, decrease size
		int randomIndex = StdRandom.uniform(size);
		Item item = items[randomIndex];
		swap(randomIndex);
		items[size - 1] = null; // avoid loitering
		size--;
		return item;
	}

	private void swap(int randomIndex) {
		Item temp = items[size - 1];
		items[size - 1] = items[randomIndex];
		items[randomIndex] = temp;
	}

	// return (but do not delete) a random item
	public Item sample() {

		if (isEmpty()) {
			throw new NoSuchElementException();
		}

		return items[StdRandom.uniform(size)];
	}

	// return an independent iterator over items in random order
	@Override
	public Iterator<Item> iterator() {
		return new ArrayListIterator();
	}

	private class ArrayListIterator implements Iterator<Item> {

		private int currentIterIndex;
		private Item[] iterItems;

		private ArrayListIterator() {
			currentIterIndex = 0;
			iterItems = (Item[]) new Object[size];
			for (int i = 0; i < size; i++) {
				iterItems[i] = items[i];
			}
		}

		@Override
		public boolean hasNext() {
			return currentIterIndex != size;
		}

		@Override
		public Item next() {

			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			
			int randomIndex = StdRandom.uniform(currentIterIndex, size);
			Item item  = iterItems[randomIndex];
			swapCurrentIter(randomIndex,currentIterIndex);
			currentIterIndex++;
			return item;
		}

		private void swapCurrentIter(int randomIndex, int currentIndex) {

			Item tempItem = iterItems[randomIndex];
			iterItems[randomIndex] = iterItems[currentIndex];
			iterItems[currentIndex] = tempItem;

		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

	}

	// resize the underlying array holding the elements
	private void resize(int capacity) {
		assert capacity >= size;
		Item[] temp = (Item[]) new Object[capacity];
		for (int i = 0; i < size; i++) {
			temp[i] = items[i];
		}
		items = temp;
	}

}
