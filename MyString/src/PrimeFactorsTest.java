import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


public class PrimeFactorsTest {

	@Test
	public void test() {

		PrimeFactors primeFactors = new PrimeFactors();
		List<Integer> expected = new ArrayList<Integer>();
		expected.add(3);
		expected.add(3);
		expected.add(3);
	
		assertEquals(expected , primeFactors.getPrimeFactors(27));

	}

}
