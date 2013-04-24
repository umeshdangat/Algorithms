import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BurrowWheelerDecoderBuilderTest {

	@Test
	public void testCreateNextArray() {
		int firstRow = 3;
		String encoded= "ARD!RCAAAABB";
		BurrowWheelerDecoderBuilder burrowWheelerDecoderBuilder = new BurrowWheelerDecoderBuilder(firstRow,encoded);
		int[] next = new int[] { 3, 0, 6, 7, 8, 9, 10, 11, 5, 2, 1, 4 };
		burrowWheelerDecoderBuilder.createNextArray();
		assertArrayEquals(next,burrowWheelerDecoderBuilder.getNext());
	}

}
