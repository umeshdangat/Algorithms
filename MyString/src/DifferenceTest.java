import static org.junit.Assert.*;

import org.junit.Test;


public class DifferenceTest {

	@Test
	public void test() {
		
		ClosestDiffToZero closestDiffToZero = new ClosestDiffToZero();
		int[] actual = { -20, -1, -80, 8, 0, 18, 0, -60, 25, 30};
		
		assertEquals(0 ,closestDiffToZero.getDifference(actual).diff);
	}

}
