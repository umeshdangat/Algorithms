import static org.junit.Assert.*;

import org.junit.Test;


public class CircularSuffixArrayComparatorTest {

	@Test
	public void testFirstStringIsSmallerThanSecond() {
		String text = "hello";
		CircularSuffixArrayComparator circularSuffixArrayComparator = new CircularSuffixArrayComparator(text);
		assertEquals(-1, circularSuffixArrayComparator.compare(1, 2));
	}
	
	@Test
	public void testFirstStringIsGreaterThanSecond() {
		String text = "hello";
		CircularSuffixArrayComparator circularSuffixArrayComparator = new CircularSuffixArrayComparator(text);
		assertEquals(1, circularSuffixArrayComparator.compare(0, 1));
	}
	
	@Test
	public void testFirstStringIsEqualToSecond() {
		String text = "bbbbbb";
		CircularSuffixArrayComparator circularSuffixArrayComparator = new CircularSuffixArrayComparator(text);
		assertEquals(0, circularSuffixArrayComparator.compare(4, 2));
	}
	
	@Test
	public void testFirstStringIsSmallerThanSecondButNotAtFirstCharacter() {
		//"llohe" "lohel"
		String text = "hello";
		CircularSuffixArrayComparator circularSuffixArrayComparator = new CircularSuffixArrayComparator(text);
		assertEquals(-1, circularSuffixArrayComparator.compare(2, 3));
	}

	@Test
	public void testGetText(){
		String text = "hello";
		CircularSuffixArrayComparator circularSuffixArrayComparator = new CircularSuffixArrayComparator(text);
		assertEquals(text, circularSuffixArrayComparator.getText());
	}
}
