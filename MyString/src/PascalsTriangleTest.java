import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


public class PascalsTriangleTest {

	@Test
	public void testwith2() {

		PascalsTriangle pascalsTriangle = new PascalsTriangle();
		List<Integer> expected = new ArrayList<Integer>();
		expected.add(1);
		expected.add(1);
		assertEquals(expected, pascalsTriangle.getPascalRow(2));
	}
	
	@Test
	public void testwith4() {

		PascalsTriangle pascalsTriangle = new PascalsTriangle();
		List<Integer> expected = new ArrayList<Integer>();
		expected.add(1);
		expected.add(3);
		expected.add(3);
		expected.add(1);
		assertEquals(expected, pascalsTriangle.getPascalRow(4));
	}

}
