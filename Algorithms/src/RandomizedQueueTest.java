import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Test;

public class RandomizedQueueTest {

	@Test
	public void testRandomizedQueue() {
		RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<Integer>();
		assertEquals(0, randomizedQueue.size());
		assertTrue(randomizedQueue.isEmpty());

	}

	@Test
	public void testSizeWithOneAdd() {
		RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<Integer>();
		randomizedQueue.enqueue(1);
		assertEquals(1, randomizedQueue.size());
	}

	@Test
	public void testSizeWithAddandRemove() {
		RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<Integer>();
		randomizedQueue.enqueue(1);
		randomizedQueue.dequeue();
		assertEquals(0, randomizedQueue.size());
	}

	@Test
	public void testIsEmpty() {
		RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<Integer>();
		randomizedQueue.enqueue(1);
		assertFalse(randomizedQueue.isEmpty());
	}

	@Test(expected = NullPointerException.class)
	public void testEnqueueWithNull() {
		RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<Integer>();
		randomizedQueue.enqueue(null);
	}

	@Test
	public void testEnqueueWithOneItem() {
		RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<Integer>();
		randomizedQueue.enqueue(1);
		assertEquals(1, randomizedQueue.size());
		assertFalse(randomizedQueue.isEmpty());
	}

	@Test
	public void testEnqueueWithTwoItems() {
		RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<Integer>();
		randomizedQueue.enqueue(1);
		randomizedQueue.enqueue(2);
		assertEquals(2, randomizedQueue.size());
		assertFalse(randomizedQueue.isEmpty());
	}

	@Test
	public void testEnqueueWithFourItems() {
		RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<Integer>();
		randomizedQueue.enqueue(1);
		randomizedQueue.enqueue(2);
		randomizedQueue.enqueue(3);
		randomizedQueue.enqueue(4);
		assertEquals(4, randomizedQueue.size());
		assertFalse(randomizedQueue.isEmpty());
	}

	@Test(expected = NoSuchElementException.class)
	public void testSampleWithEmptyQueue() {
		RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<Integer>();
		randomizedQueue.sample();
	}

	@Test
	public void testSampleWithOneItem() {
		RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<Integer>();
		randomizedQueue.enqueue(1);
		assertEquals(1, (int) randomizedQueue.sample());
	}

	@Test
	public void testSampleWithTwoItems() {
		RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<Integer>();
		randomizedQueue.enqueue(1);
		randomizedQueue.enqueue(2);
		randomizedQueue.sample();
		assertEquals(2, randomizedQueue.size());
	}

	@Test(expected = NoSuchElementException.class)
	public void testDequeueWithEmptyQueue() {
		RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<Integer>();
		randomizedQueue.dequeue();
	}

	@Test
	public void testDequeueWithOneItem() {
		RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<Integer>();
		randomizedQueue.enqueue(1);
		assertEquals(1, (int) randomizedQueue.dequeue());
		assertTrue(randomizedQueue.isEmpty());
	}

	@Test
	public void testDequeueWithTwoItems() {
		RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<Integer>();
		randomizedQueue.enqueue(1);
		randomizedQueue.enqueue(2);
		Integer item = randomizedQueue.dequeue();
		assertEquals(1, randomizedQueue.size());
	}

	@Test
	public void testDequeueWithFourItems() {
		RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<Integer>();
		randomizedQueue.enqueue(1);
		randomizedQueue.enqueue(2);
		randomizedQueue.enqueue(3);
		randomizedQueue.enqueue(4);

		Integer item = randomizedQueue.dequeue();
		assertEquals(3, randomizedQueue.size());
	}

//	@Test
//	public void testArrayResizingWithEnqueue() {
//		RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<Integer>();
//		randomizedQueue.enqueue(1);
//		randomizedQueue.enqueue(2);
//		randomizedQueue.enqueue(3);
//		randomizedQueue.enqueue(4);
//		randomizedQueue.enqueue(5);
//		assertEquals(8, randomizedQueue.getArrayLength());
//	}
//
//	@Test
//	public void testArrayResizingWithDequeue() {
//		RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<Integer>();
//		randomizedQueue.enqueue(1);
//		randomizedQueue.enqueue(2);
//		randomizedQueue.enqueue(3);
//		randomizedQueue.enqueue(4);
//		randomizedQueue.enqueue(5);
//
//		randomizedQueue.dequeue();
//		randomizedQueue.dequeue();
//		randomizedQueue.dequeue();
//		randomizedQueue.dequeue();
//
//		assertEquals(4, randomizedQueue.getArrayLength());
//	}
	
	@Test
	public void testIteratorShortHand() {
		RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<Integer>();
		randomizedQueue.enqueue(1);
		randomizedQueue.enqueue(2);
		randomizedQueue.enqueue(3);
		randomizedQueue.enqueue(4);
		for (Integer item : randomizedQueue) {
			System.out.println("Shorthand outer loop "+ item);
			for(Integer item1: randomizedQueue){
				System.out.println("inner loop "+ item1);
			}
		}
	}
	
	@Test
	public void testIteratorLongHand() {
		RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<Integer>();
		randomizedQueue.enqueue(1);
		randomizedQueue.enqueue(2);
		randomizedQueue.enqueue(3);
		randomizedQueue.enqueue(4);
		Iterator<Integer> iter1 = randomizedQueue.iterator();
		while(iter1.hasNext()){
			System.out.println("outer loop " +iter1.next());
			Iterator<Integer> iter2 = randomizedQueue.iterator();
			while(iter2.hasNext()){
				System.out.println("loop "+ iter2.next());
			}
			
		}
		
	}

	@Test(expected = NoSuchElementException.class)
	public void testIteratorForNextWithEmptyIterator() {
		RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<Integer>();
		Iterator<Integer> iter = randomizedQueue.iterator();
		iter.next();
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void testIteratorForRemove() {
		RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<Integer>();
		randomizedQueue.enqueue(1);
		randomizedQueue.enqueue(2);
		randomizedQueue.enqueue(3);
		randomizedQueue.enqueue(4);
		Iterator<Integer> iter = randomizedQueue.iterator();
		iter.remove();
	}
	
}
