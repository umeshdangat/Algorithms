import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

public class PointSETTest {

	@Test
	public void testPointSET() {
		PointSET pointSET = new PointSET();
		assertEquals(0, pointSET.size());
		assertEquals(true, pointSET.isEmpty());
	}

	@Test
	public void testIsEmpty() {
		PointSET pointSET = new PointSET();
		assertEquals(true, pointSET.isEmpty());
	}

	@Test
	public void testSize() {
		PointSET pointSET = new PointSET();
		assertEquals(0, pointSET.size());
		pointSET.insert(new Point2D(0.3, 0.5));
		assertEquals(1, pointSET.size());
	}

	@Test
	public void testInsertForDuplicates() {
		PointSET pointSET = new PointSET();
		assertEquals(0, pointSET.size());
		pointSET.insert(new Point2D(0.3, 0.5));
		pointSET.insert(new Point2D(0.3, 0.5));
		assertEquals(1, pointSET.size());
	}

	@Test
	public void testInsertForMultiplePoints() {
		PointSET pointSET = new PointSET();
		assertEquals(0, pointSET.size());
		pointSET.insert(new Point2D(0.3, 0.5));
		pointSET.insert(new Point2D(0.4, 0.5));
		assertEquals(2, pointSET.size());
	}

	@Test
	public void testContains() {
		PointSET pointSET = new PointSET();
		Point2D point2D = new Point2D(0.5, 0.7);
		assertEquals(false, pointSET.contains(point2D));
		pointSET.insert(point2D);
		assertEquals(true, pointSET.contains(point2D));
	}
	
	@Test
	public void testContainsForNullSet() {
		PointSET pointSET = new PointSET();
		Point2D point2D = new Point2D(0.5, 0.7);
		assertEquals(false, pointSET.contains(point2D));
	}

	@Ignore
	public void testDraw() {
		PointSET pointSET = new PointSET();
		pointSET.insert(new Point2D(0.5, 0.5));
		pointSET.insert(new Point2D(0.3, 0.6));
		pointSET.insert(new Point2D(0.2, 0.1));
		pointSET.draw();
		assertEquals(3, pointSET.size());
	}

	@Test
	public void testRange() {
		PointSET pointSET = new PointSET();
		Point2D p1 = new Point2D(0.2, 0.3);
		pointSET.insert(p1);// out
		Point2D p2 = new Point2D(0.3, 0.3);
		pointSET.insert(p2);
		Point2D p3 = new Point2D(0.4, 0.5);
		pointSET.insert(p3);
		Point2D p4 = new Point2D(0.5, 0.6);
		pointSET.insert(p4);
		Point2D p5 = new Point2D(0.8, 0.7);
		pointSET.insert(p5);
		Point2D p6 = new Point2D(0.9, 0.5);
		pointSET.insert(p6); // out

		List<Point2D> expectedPoints = new ArrayList<Point2D>();

		expectedPoints.add(p2);
		expectedPoints.add(p3);
		expectedPoints.add(p4);
		expectedPoints.add(p5);
		Iterator<Point2D> it = expectedPoints.iterator();

		Iterable<Point2D> rangePoints = pointSET.range(new RectHV(0.3, 0.3,
				0.8, 0.8));

		for (Point2D point : rangePoints) {
			assertEquals(it.next(), point);
		}

		assertEquals(false, it.hasNext());

	}

	@Test
	public void testNearest() {
		PointSET pointSET = new PointSET();
		Point2D p1 = new Point2D(0.2, 0.3);
		pointSET.insert(p1);
		Point2D p2 = new Point2D(0.3, 0.3);
		pointSET.insert(p2);
		Point2D p3 = new Point2D(0.4, 0.5);
		pointSET.insert(p3);
		Point2D p4 = new Point2D(0.5, 0.6);
		pointSET.insert(p4);
		Point2D p5 = new Point2D(0.8, 0.7);
		pointSET.insert(p5);
		Point2D p6 = new Point2D(0.9, 0.5);
		pointSET.insert(p6);

		assertEquals(p5, pointSET.nearest(new Point2D(0.7, 0.9)));

	}

	@Test
	public void testNearestWhenSETEmpty() {
		PointSET pointSET = new PointSET();
		assertEquals(null, pointSET.nearest(new Point2D(0.7, 0.9)));
	}

}
