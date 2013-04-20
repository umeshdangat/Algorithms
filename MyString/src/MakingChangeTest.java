import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;


public class MakingChangeTest {

	@Test
	public void test() {
		
		MakingChange makingChange = new MakingChange();
		assertEquals(6,makingChange.getWays(16));
	}
	
		
	@Test
	public void gayle() {
		
		MakingChange makingChange = new MakingChange();
		assertEquals(6,MakingChange.makeChange(16, 25));
	}


}
