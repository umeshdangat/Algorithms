import static org.junit.Assert.*;

import org.junit.Test;


public class MedianUsingHeapsTest {

	@Test
	public void test() {
		
		
		MedianUsingHeaps medianUsingHeaps = new MedianUsingHeaps() ;
		medianUsingHeaps.addNumber(10);
		medianUsingHeaps.addNumber(20);
		medianUsingHeaps.addNumber(30);
		
		assertEquals(20,medianUsingHeaps.getMedian());
		
	}
	
	@Test
	public void test1() {
		
		
		MedianUsingHeaps medianUsingHeaps = new MedianUsingHeaps() ;
		medianUsingHeaps.addNumber(10);
		medianUsingHeaps.addNumber(1);
		medianUsingHeaps.addNumber(2);
		medianUsingHeaps.addNumber(12);
		medianUsingHeaps.addNumber(6);
		
		assertEquals(6,medianUsingHeaps.getMedian());
		
	}
	
	@Test
	public void test2() {
		
		
		MedianUsingHeaps medianUsingHeaps = new MedianUsingHeaps() ;
		medianUsingHeaps.addNumber(10);
		medianUsingHeaps.addNumber(1);
		medianUsingHeaps.addNumber(2);
		medianUsingHeaps.addNumber(12);
		
		assertEquals(6,medianUsingHeaps.getMedian());
		
	}
	
	@Test
	public void test3() {
		
		
		MedianUsingHeaps medianUsingHeaps = new MedianUsingHeaps() ;
		medianUsingHeaps.addNumber(10);
		medianUsingHeaps.addNumber(1);
		medianUsingHeaps.addNumber(-12);
		medianUsingHeaps.addNumber(20);
		medianUsingHeaps.addNumber(12);
		
		assertEquals(10,medianUsingHeaps.getMedian());
		
	}

	
	@Test
	public void test4() {
		
		
		MedianUsingHeaps medianUsingHeaps = new MedianUsingHeaps() ;
		medianUsingHeaps.addNumber(10);
		medianUsingHeaps.addNumber(10);
		medianUsingHeaps.addNumber(10);
		medianUsingHeaps.addNumber(10);
		
		assertEquals(10,medianUsingHeaps.getMedian());
		
	}


}
