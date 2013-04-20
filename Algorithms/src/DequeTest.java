import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Test;

public class DequeTest {

	@Test
	public void testDeque() {
		Deque<String> deque = new Deque<String>();
		assertTrue(deque.isEmpty());
		assertEquals(0, deque.size());
	}

	@Test
	public void testSizeWithOne() {
		Deque<Integer> deque = new Deque<Integer>();
		deque.addFirst(1);
		assertEquals(1, deque.size());
	}

	@Test
	public void testSizeWithTwo() {
		Deque<Integer> deque = new Deque<Integer>();
		deque.addFirst(1);
		deque.addFirst(2);
		assertEquals(2, deque.size());
	}

	@Test
	public void testSizeWithZeroWhenAddedToFirst() {
		Deque<Integer> deque = new Deque<Integer>();
		assertEquals(0, deque.size());
		deque.addFirst(1);
		deque.removeFirst();
		assertEquals(0, deque.size());
	}

	@Test
	public void testSizeWithZeroWhenAddedToLast() {
		Deque<Integer> deque = new Deque<Integer>();
		assertEquals(0, deque.size());
		deque.addLast(1);
		deque.removeLast();
		assertEquals(0, deque.size());
	}

	@Test
	public void testAddFirstWithOneItem() {
		Deque<Integer> deque = new Deque<Integer>();
		deque.addFirst(1);
		assertEquals(1, deque.size());
		assertFalse(deque.isEmpty());
	}

	@Test
	public void testAddFirstWithTwoItems() {
		Deque<Integer> deque = new Deque<Integer>();
		deque.addFirst(1);
		deque.addFirst(2);
		assertEquals(2, deque.size());
		assertFalse(deque.isEmpty());
	}

	@Test(expected = java.lang.NullPointerException.class)
	public void testAddFirstWithNullItem() {
		Deque<Integer> deque = new Deque<Integer>();
		deque.addFirst(null);
	}

	@Test(expected = NullPointerException.class)
	public void testAddLastWithNullItem() {
		Deque<Integer> deque = new Deque<Integer>();
		deque.addLast(null);
	}

	@Test
	public void testAddLastWithOneItem() {
		Deque<Integer> deque = new Deque<Integer>();
		deque.addLast(1);
		assertEquals(1, deque.size());
		assertFalse(deque.isEmpty());
	}

	@Test
	public void testAddLastWithTwoItems() {
		Deque<Integer> deque = new Deque<Integer>();
		deque.addLast(1);
		deque.addLast(2);
		assertEquals(2, deque.size());
		assertFalse(deque.isEmpty());
	}

	@Test
	public void testAddFirstAndLast() {
		Deque<Integer> deque = new Deque<Integer>();
		deque.addFirst(1);
		deque.addLast(2);
		assertEquals(2, deque.size());
		assertFalse(deque.isEmpty());
	}

	@Test(expected = NoSuchElementException.class)
	public void testRemoveFirstOnEmptyQueue() {
		Deque<Integer> deque = new Deque<Integer>();
		deque.removeFirst();
	}

	@Test
	public void testRemoveFirstWithOneItem() {
		Deque<Integer> deque = new Deque<Integer>();
		deque.addFirst(1);
		assertEquals(1, (int) deque.removeFirst());
		assertEquals(0, deque.size());
		assertTrue(deque.isEmpty());
	}

	@Test
	public void testRemoveFirstWithTwoItems() {
		Deque<Integer> deque = new Deque<Integer>();
		deque.addFirst(1);
		deque.addFirst(2);

		assertEquals(2, (int) deque.removeFirst());
	}

	@Test(expected = NoSuchElementException.class)
	public void testRemoveLastOnEmptyQueue() {
		Deque<Integer> deque = new Deque<Integer>();
		deque.removeLast();
	}

	@Test
	public void testRemoveLastWithOneItem() {
		Deque<Integer> deque = new Deque<Integer>();
		deque.addLast(1);
		assertEquals(1, (int) deque.removeLast());
		assertEquals(0, deque.size());
		assertTrue(deque.isEmpty());
	}

	@Test
	public void testRemoveLastWithTwoItems() {
		Deque<Integer> deque = new Deque<Integer>();
		deque.addLast(1);
		deque.addLast(2);

		assertEquals(2, (int) deque.removeLast());
		assertEquals(1, deque.size());
	}

	@Test
	public void testMixFirstLastAddRemove() {
		Deque<Integer> deque = new Deque<Integer>();
		deque.addFirst(2);
		deque.addFirst(1);
		deque.addLast(3);
		deque.addLast(4);

		assertEquals(4, (int) deque.removeLast());
		assertEquals(1, (int) deque.removeFirst());
		assertEquals(3, (int) deque.removeLast());
		assertEquals(2, (int) deque.removeFirst());
		assertTrue(deque.isEmpty());

	}

	@Test
	public void testIteratorShortHand() {
		Deque<Integer> deque = new Deque<Integer>();
		deque.addFirst(2);
		deque.addFirst(1);
		deque.addLast(3);
		deque.addLast(4);
		for (Integer item : deque) {
			System.out.println(item);
			for(Integer item1: deque){
				System.out.println("inner loop " + item1);
			}
		}
	}
	
	@Test
	public void testIteratorLongHand() {
		Deque<Integer> deque = new Deque<Integer>();
		deque.addFirst(2);
		deque.addFirst(1);
		deque.addLast(3);
		deque.addLast(4);
		Iterator<Integer> iter = deque.iterator();
		while(iter.hasNext()){
			System.out.println(iter.next());
		}
		
	}

	@Test(expected = NoSuchElementException.class)
	public void testIteratorForNextWithEmptyIterator() {
		Deque<Integer> deque = new Deque<Integer>();
		Iterator<Integer> iter = deque.iterator();
		iter.next();
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void testIteratorForRemove() {
		Deque<Integer> deque = new Deque<Integer>();
		deque.addFirst(2);
		deque.addFirst(1);
		deque.addLast(3);
		deque.addLast(4);
		Iterator<Integer> iter = deque.iterator();
		iter.remove();
	}
}
