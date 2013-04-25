import static org.junit.Assert.*;

import org.junit.Test;


public class MoveToFrontEncoderWorkerTest {


	@Test
	public void testGetIndexAndMoveToFront() {
		MoveToFrontEncoderWorker moveToFrontEncoderWorker = new MoveToFrontEncoderWorker();
		assertEquals('c',moveToFrontEncoderWorker.getIndexAndMoveToFront('c'));
		assertEquals(0,moveToFrontEncoderWorker.getIndexAndMoveToFront('c'));
		assertEquals(66,moveToFrontEncoderWorker.getIndexAndMoveToFront('A'));
		assertEquals(67,moveToFrontEncoderWorker.getIndexAndMoveToFront('B'));
		assertEquals(1,moveToFrontEncoderWorker.getIndexAndMoveToFront('A'));
		assertEquals(2,moveToFrontEncoderWorker.getIndexAndMoveToFront('c'));
	}
	

}
