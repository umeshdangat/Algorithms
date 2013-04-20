import static org.junit.Assert.*;

import org.junit.Test;


public class ReverseStringTest {

	@Test
	public void testReverseString() {
		
		String actual = "abcdef";
		String expected = "fedcba";
		
		assertArrayEquals(expected.toCharArray(), ReverseString.reverseString(actual.toCharArray()));
	}

}
