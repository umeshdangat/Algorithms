import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;


public class IthItemTest {

	@Test
	public void rightSideisLess() {

		IthItem ithSmallestItem = new IthItem();
		int[] numbers = {5 , 4 ,8 ,7 ,3 , 10};
		int actual = ithSmallestItem.getIthLargestNumber(numbers, 0, numbers.length-1, 3);
		assertEquals(7, actual);
	}

    @Test
	public void rightSideisMore() {

		IthItem ithSmallestItem = new IthItem();
		int[] numbers = {5 , 4 ,1 ,8,7 ,3 , 10};
		int actual = ithSmallestItem.getIthLargestNumber(numbers, 0, numbers.length-1, 3);
		assertEquals(7, actual);
	}
    
    @Test
    public void rightSideisEqualToKthSmallestItem() {

		IthItem ithSmallestItem = new IthItem();
		int[] numbers = {5 , 4 ,6 ,7 ,9 , 10};
		int actual = ithSmallestItem.getIthLargestNumber(numbers, 0, numbers.length-1, 3);
		assertEquals(7, actual);
	}

    
    @Test
    public void rightSideisLessToKthSmallestItem() {

		IthItem ithSmallestItem = new IthItem();
		int[] numbers = {5 , 3,2,4 ,6 ,7 ,9 , 11,10};
		int actual = ithSmallestItem.getIthLargestNumber(numbers, 0, numbers.length-1, 3);
		assertEquals(9, actual);
	}
    
    @Test
    public void getIthLargest3() {

		IthItem ithSmallestItem = new IthItem();
		int[] numbers = {3,4,5,8};
		int actual = ithSmallestItem.getIthLargestNumber(numbers, 0, numbers.length-1, 4);
		assertEquals(3, actual);
	}
    
    @Test
    public void getIthLargest8() {

		IthItem ithSmallestItem = new IthItem();
		int[] numbers = {3,4,5,8};
		int actual = ithSmallestItem.getIthLargestNumber(numbers, 0, numbers.length-1, 1);
		assertEquals(8, actual);
	}

    
    
    
    @Test
    public void getIthSmallestRandom() {

		IthItem ithSmallestItem = new IthItem();
		int[] numbers = {3,4,5,8,9};
		int actual = ithSmallestItem.getIthSmallestNumber(numbers, 0, numbers.length-1, 4);
		assertEquals(8, actual);
	}
    
    @Test
    public void getIthSmallestLeast() {

		IthItem ithSmallestItem = new IthItem();
		int[] numbers = {5,3,2,4 ,6 ,7 ,9 , 11,10};
		int actual = ithSmallestItem.getIthSmallestNumber(numbers, 0, numbers.length-1, 1);
		assertEquals(2, actual);
	}
    
    @Test
    public void getIthSmallestBiggest() {

		IthItem ithSmallestItem = new IthItem();
		int[] numbers = {5,3,2,4 ,6 ,7 ,9 , 11,10};
		int actual = ithSmallestItem.getIthSmallestNumber(numbers, 0, numbers.length-1, 9);
		assertEquals(11, actual);
	}


}
