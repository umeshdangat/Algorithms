import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class CircularSuffixArrayBuilderTest {

	@Test
	public void testWithHello() {
		String text = "hello";
		CircularSuffixArrayComparator circularSuffixArrayComparator = new CircularSuffixArrayComparator(
				text);
		CircularSuffixArrayIndexBuilder circularSuffixArrayBuilder = new CircularSuffixArrayIndexBuilder();
		Integer[] expected = new Integer[]{1,0,2,3,4};
		Integer[] actual = circularSuffixArrayBuilder
				.buildSortedCircularSuffixArrayIndex(circularSuffixArrayComparator);
		assertArrayEquals(expected, actual);
	}

	@Test
	public void testWithABRACADABRA() {
		CircularSuffixArrayIndexBuilder circularSuffixArrayBuilder = new CircularSuffixArrayIndexBuilder();
		String text = "ABRACADABRA!";
		Integer[] expected = new Integer[]{11,10,7,0,3,5,8,1,4,6,9,2};
		
		CircularSuffixArrayComparator circularSuffixArrayComparator = new CircularSuffixArrayComparator(
				text);
		Integer[] actual = circularSuffixArrayBuilder
				.buildSortedCircularSuffixArrayIndex(circularSuffixArrayComparator);
		assertArrayEquals(expected, actual);
	}

}
