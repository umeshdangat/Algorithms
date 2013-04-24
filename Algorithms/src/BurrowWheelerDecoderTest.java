import static org.junit.Assert.*;

import org.junit.Test;

public class BurrowWheelerDecoderTest {

	@Test
	public void testDecodingOfABRACADABRA() {
		int firstRow = 3;
		String encoded = "ARD!RCAAAABB";
		int[] next = new int[] { 3, 0, 6, 7, 8, 9, 10, 11, 5, 2, 1, 4 };
		BurrowWheelerDecoder burrowWheelerDecoder = new BurrowWheelerDecoder(
				firstRow, encoded, next);
		assertEquals("ABRACADABRA!", burrowWheelerDecoder.getDecoded());
	}

	@Test
	public void testDecodingOfABRACADABRAWithBuilder() {
		int firstRow = 3;
		String encoded = "ARD!RCAAAABB";
		BurrowWheelerDecoderBuilder builder = new BurrowWheelerDecoderBuilder(firstRow,encoded);
		BurrowWheelerDecoder burrowWheelerDecoder = builder.createNextArray()
				.build();
		assertEquals("ABRACADABRA!", burrowWheelerDecoder.getDecoded());
	}
}
