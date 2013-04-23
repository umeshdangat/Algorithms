import static org.junit.Assert.*;

import org.junit.Test;


public class BurrowWheelerTransformTest {

	@Test
	public void testGetFirstRow() {
		int firstRow = 1;
		String encodedText = "hello";
		BurrowWheelerTransform burrowWheelerTransform = new BurrowWheelerTransform(firstRow , encodedText );
		assertEquals(1,burrowWheelerTransform.getFirstRow());
	}

	@Test
	public void testGetEncodedText() {
		int firstRow = 1;
		String encodedText = "hello";
		BurrowWheelerTransform burrowWheelerTransform = new BurrowWheelerTransform(firstRow , encodedText );
		assertEquals("hello",burrowWheelerTransform.getEncodedText());
	}

}
