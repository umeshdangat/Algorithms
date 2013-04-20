import static org.junit.Assert.*;

import org.junit.Test;

public class GridPixelPositionTest {

	@Test
	public void testGetRow() {
		GridPixelPosition gridPixelPosition = new GridPixelPosition(1, 2);
		assertEquals(1, gridPixelPosition.getRow());
	}

	@Test
	public void testGetCol() {
		GridPixelPosition gridPixelPosition = new GridPixelPosition(1, 2);
		assertEquals(2, gridPixelPosition.getCol());
	}

	@Test
	public void testEqualsObject() {
		GridPixelPosition gridPixelPosition1 = new GridPixelPosition(1, 2);
		GridPixelPosition gridPixelPosition2 = new GridPixelPosition(1, 2);
		assertEquals(true, gridPixelPosition1.equals(gridPixelPosition2));
	}

	@Test
	public void testNotEqualsObject() {
		GridPixelPosition gridPixelPosition1 = new GridPixelPosition(1, 2);
		GridPixelPosition gridPixelPosition2 = new GridPixelPosition(2, 1);
		assertEquals(false, gridPixelPosition1.equals(gridPixelPosition2));
	}

}
