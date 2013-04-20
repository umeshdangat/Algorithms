import static org.junit.Assert.*;

import org.junit.Test;


public class StringtoIntegerTest {

	@Test
	public void test() {
		

		String input = "1234";
		assertEquals(1234, StringtoInteger.atoi(input));	
	}

}
