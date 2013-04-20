import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PercolationStatsTest {

	PercolationStats underTest;

	@Before
	public void setUp() {
		underTest = new PercolationStats(4, 2000);
	}

	@Test
	public void testPercolationStats() {

	}

	@Test
	public void testMean() {

		assertEquals(0.60325, underTest.mean(), 0.1);

	}

	@Test
	public void testStddev() {
		assertEquals(0.008, underTest.stddev(), 0.001);
	}

	@Test
	public void testConfidenceLo() {
		assertEquals(0.5912, underTest.confidenceLo(), 0.001);
	}

	@Test
	public void testConfidenceHi() {
		assertEquals(0.5942, underTest.confidenceHi(), 0.001);
	}

}
