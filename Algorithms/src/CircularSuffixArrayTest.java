import static org.junit.Assert.*;

import org.junit.Test;


public class CircularSuffixArrayTest {

	@Test
	public void testLength() {
		String text = "ABRACADABRA!";
		CircularSuffixArray circularSuffixArray = new CircularSuffixArray(text );
		assertEquals(12,circularSuffixArray.length());
	}

	@Test
	public void testIndex() {
		String text = "ABRACADABRA!";
		CircularSuffixArray circularSuffixArray = new CircularSuffixArray(text );
		assertEquals(2,circularSuffixArray.index(11));
		assertEquals(10,circularSuffixArray.index(1));
	}

}
