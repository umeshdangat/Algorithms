import static org.junit.Assert.*;

import org.junit.Test;


public class RabinKarpMyTest {

	@Test
	public void test() {
		String pattern = "reap";
		String text = "appleonemoreapple";
		RabinKarpMy rk  = new RabinKarpMy(pattern);
		assertEquals(10,rk.search(text));
	}

}
