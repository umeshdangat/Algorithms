import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;


public class PointTest {

	@Test
	public void testPoint() {
		fail("Not yet implemented");
	}

	@Test
	public void testDraw() {
		fail("Not yet implemented");
	}

	@Test
	public void testDrawTo() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}
	
//	//todo-remove only for testing
//	@Override
//	public boolean equals(Object obj) {
//
//		Point that = (Point) obj;
//		if (this.y < that.y || this.y > that.y) {
//			return false;
//		} else {
//			int value = breakYTies(that);
//			if (value != 0) {
//				return false;
//			} else {
//				return true;
//			}
//		}
//	}


	@Test
	public void testCompareToWithEqualYs() {
		Point[] points = new Point[5];
		points[0] = new Point(5,7);
		points[1] = new Point(2,3);
		points[2] = new Point(3,2);
		points[3] = new Point(1,2);
		points[4] = new Point(0,2);
		Arrays.sort(points);
		
		Point[] expecteds = new Point[5];
		expecteds[0] = new Point(0,2);
		expecteds[1] = new Point(1,2);
		expecteds[2] = new Point(3,2);
		expecteds[3] = new Point(2,3);
		expecteds[4] = new Point(5,7);
		assertArrayEquals(expecteds, points);
	}

	@Test
	public void testSlopeToWithIntReturn() {
		Point point1 = new Point(2,3);
		Point point2 = new Point(3,4);
		double expected = 1;
		assertEquals(expected,point1.slopeTo(point2),0.00001);
	}
	
	@Test
	public void testSlopeToWithDoubleReturn() {
		Point point1 = new Point(2,3);
		Point point2 = new Point(6,6);
		double expected = 0.75;
		assertEquals(expected,point1.slopeTo(point2),0.00001);
	}
	
	@Test
	public void testSlopeToWithNegativeDoubleReturn() {
		Point point1 = new Point(2,6);
		Point point2 = new Point(6,1);
		double expected = -1.25;
		assertEquals(expected,point1.slopeTo(point2),0.00001);
	}
	
	@Test
	public void testSlopeToWithSlopeZero() {
		Point point1 = new Point(2,6);
		Point point2 = new Point(6,6);
		double expected = 0.0;
		assertEquals(expected,point1.slopeTo(point2),0.00001);
	}
	
	@Test
	public void testSlopeToWithSlopePositiveInfinity() {
		Point point1 = new Point(2,6);
		Point point2 = new Point(2,1);
		double expected = Double.POSITIVE_INFINITY;
		assertEquals(expected,point1.slopeTo(point2),0.00001);
	}
	
	@Test
	public void testSlopeToWithSlopeNegativeInfinity() {
		Point point1 = new Point(2,1);
		Point point2 = new Point(2,1);
		double expected = Double.NEGATIVE_INFINITY;
		assertEquals(expected,point1.slopeTo(point2),0.00001);
	}
	
	@Test
	public void testSlopeOrder() {
		Point[] points = new Point[3];
		
		points[0] = new Point(5,7);
		points[1] = new Point(3,2);
		points[2] = new Point(2,3);
		Arrays.sort(points,points[0].SLOPE_ORDER);
		
		Point[] expecteds = new Point[3];
		expecteds[0] = new Point(5,7);
		expecteds[1] = new Point(2,3);
		expecteds[2] = new Point(3,2);
		assertArrayEquals(expecteds, points);
	}
	
	@Test
	public void testSlopeOrderWithHorizontalLine() {
		Point[] points = new Point[3];
		
		points[0] = new Point(5,7);
		points[1] = new Point(6,8);
		points[2] = new Point(3,7);
		
		Arrays.sort(points,points[0].SLOPE_ORDER);
		
		Point[] expecteds = new Point[3];
		expecteds[0] = new Point(5,7);
		expecteds[1] = new Point(3,7);
		expecteds[2] = new Point(6,8);
		assertArrayEquals(expecteds, points);
	}
	
	@Test
	public void testSlopeOrderWithVerticalLine() {
		Point[] points = new Point[3];
		
		points[0] = new Point(5,7);
		points[1] = new Point(5,8);
		points[2] = new Point(3,7);
		
		Arrays.sort(points,points[0].SLOPE_ORDER);
		
		Point[] expecteds = new Point[3];
		expecteds[0] = new Point(5,7);
		expecteds[1] = new Point(3,7);
		expecteds[2] = new Point(5,8);
		assertArrayEquals(expecteds, points);
	}
	
	@Test
	public void testSlopeOrderWithSamePoint() {
		Point[] points = new Point[4];
		
		points[0] = new Point(5,7);
		points[1] = new Point(5,8);
		points[2] = new Point(6,3);
		points[3] = new Point(5,7);
		
		Arrays.sort(points,points[0].SLOPE_ORDER);
		
		Point[] expecteds = new Point[4];
		expecteds[0] = new Point(5,7);
		expecteds[1] = new Point(5,7);
		expecteds[2] = new Point(6,3);
		expecteds[3] = new Point(5,8);
		assertArrayEquals(expecteds, points);
	}

}
