import static org.junit.Assert.*;

import org.junit.Test;


public class QuickSortUTest {

	@Test
	public void test() {

		int[] arr = { 8, 3, 5 ,7 , 9};
		int[] expected = {3,5,7,8,9};
		
		QuickSort.sort(arr, 0, arr.length-1);
		
		assertArrayEquals(expected, arr);

	}

}
