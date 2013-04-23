import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class CircularSuffixArrayBuilderTest {

	@Test
	public void testWithHello() {
		String text = "hello";
		CircularSuffixArrayComparator circularSuffixArrayComparator = new CircularSuffixArrayComparator(
				text);
		CircularSuffixArrayIndexBuilder circularSuffixArrayBuilder = new CircularSuffixArrayIndexBuilder();
		Integer[] expected = new Integer[text.length()];
		expected[0] = 1;
		expected[1] = 0;
		expected[2] = 2;
		expected[3] = 3;
		expected[4] = 4;
		Integer[] actual = circularSuffixArrayBuilder
				.buildSortedCircularSuffixArrayIndex(circularSuffixArrayComparator);
		assertArrayEquals(expected, actual);
	}

	@Test
	public void testWithABRACADABRA() {
		CircularSuffixArrayIndexBuilder circularSuffixArrayBuilder = new CircularSuffixArrayIndexBuilder();
		String text = "ABRACADABRA!";
		Integer[] expected = new Integer[text.length()];
		expected[0] = 11;
		expected[1] = 10;
		expected[2] = 7;
		expected[3] = 0;
		expected[4] = 3;
		expected[5] = 5;
		expected[6] = 8;
		expected[7] = 1;
		expected[8] = 4;
		expected[9] = 6;
		expected[10] = 9;
		expected[11] = 2;
		
		CircularSuffixArrayComparator circularSuffixArrayComparator = new CircularSuffixArrayComparator(
				text);
		Integer[] actual = circularSuffixArrayBuilder
				.buildSortedCircularSuffixArrayIndex(circularSuffixArrayComparator);
		assertArrayEquals(expected, actual);
	}

}
