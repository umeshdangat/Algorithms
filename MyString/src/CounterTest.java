import static org.junit.Assert.*;

import org.junit.Test;


public class CounterTest {

	@Test
	public void test() {
		
		assertEquals(45, Counter.getCounter());
	}
	

}
