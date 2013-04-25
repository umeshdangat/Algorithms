import static org.junit.Assert.*;

import org.junit.Test;


public class MoveToFrontDecoderWorkerTest {

	@Test
	public void testGetIndexAndMoveToFrontChar() {
		MoveToFrontDecoderWorker moveToFrontDecoderWorker = new MoveToFrontDecoderWorker();
		assertEquals('C',moveToFrontDecoderWorker.getIndexAndMoveToFront(67));
		assertEquals('C',moveToFrontDecoderWorker.getIndexAndMoveToFront(0));
		assertEquals('B',moveToFrontDecoderWorker.getIndexAndMoveToFront(67));
	}

}
