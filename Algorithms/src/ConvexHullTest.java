import static org.junit.Assert.*;

import org.junit.Test;


public class ConvexHullTest {

	@Test
	public void testGetPointsOnHull() {
		Point2DForConvexHull[] p = new Point2DForConvexHull[10];
		p[0] = new Point2DForConvexHull(9.0, 0.0);
		p[1] = new Point2DForConvexHull(3.0, 2.0);
		p[2] = new Point2DForConvexHull(1.0, 6.0);
		p[3] = new Point2DForConvexHull(4.0, 8.0);
		p[4] = new Point2DForConvexHull(7.0, 9.0);
		p[5] = new Point2DForConvexHull(6.0, 4.0);
		p[6] = new Point2DForConvexHull(5.0, 5.0);
		p[7] = new Point2DForConvexHull(0.0, 7.0);
		p[8] = new Point2DForConvexHull(8.0, 1.0);
		p[9] = new Point2DForConvexHull(2.0, 3.0);
		
		ConvexHull hull = new ConvexHull();
		Stack<Point2DForConvexHull> convexHullPoints = hull.getPointsOnHull(p);
		StringBuilder result = new StringBuilder();
		for(Point2DForConvexHull p1: convexHullPoints){
			result.append(p1.x);
			result.append(",");
			result.append(p1.y);
			result.append(" ");
		}
		
		String expected = new String();
		assertEquals(expected, result.toString());
		
	}

}
