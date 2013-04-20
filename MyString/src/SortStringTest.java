import static org.junit.Assert.*;

import org.junit.Test;


public class SortStringTest {

	@Test
	public void test() {
		SortString sortString = new SortString();
		
		assertEquals("dog", sortString.getSortedString("ogd"));
	}

}
