import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

public class KdTreeTest {

	@Test
	public void testKdTree() {
		KdTree kdTree = new KdTree();
		assertEquals(true, kdTree.isEmpty());
		assertEquals(0, kdTree.size());
	}

	@Test
	public void testIsEmpty() {
		KdTree kdTree = new KdTree();
		assertEquals(true, kdTree.isEmpty());
		kdTree.insert(new Point2D(0.3, 0.4));
		assertEquals(false, kdTree.isEmpty());
	}

	@Test
	public void testSizeForOne() {
		KdTree kdTree = new KdTree();
		assertEquals(true, kdTree.isEmpty());
		kdTree.insert(new Point2D(0.3, 0.4));
		assertEquals(1, kdTree.size());
	}

	@Test
	public void testSizeForTwo() {
		KdTree kdTree = new KdTree();
		assertEquals(true, kdTree.isEmpty());
		kdTree.insert(new Point2D(0.3, 0.4));
		kdTree.insert(new Point2D(0.3, 0.5));
		assertEquals(2, kdTree.size());
	}

	@Test
	public void testSizeForDuplicate() {
		KdTree kdTree = new KdTree();
		assertEquals(true, kdTree.isEmpty());
		kdTree.insert(new Point2D(0.3, 0.4));
		kdTree.insert(new Point2D(0.3, 0.4));
		assertEquals(1, kdTree.size());
	}

	@Test
	public void testSizeForDuplicateMany() {
		KdTree kdTree = new KdTree();
		Point2D p1 = new Point2D(0.500000, 1.000000);
		kdTree.insert(p1);
		Point2D p2 = new Point2D(0.206107, 0.095492);
		kdTree.insert(p2);
		Point2D p3 = new Point2D(0.975528, 0.654508);
		kdTree.insert(p3);
		Point2D p4 = new Point2D(0.024472, 0.345492);
		kdTree.insert(p4);
		Point2D p5 = new Point2D(0.793893, 0.095492);
		kdTree.insert(p5);
		Point2D p6 = new Point2D(0.793893, 0.904508);
		kdTree.insert(p6);
		Point2D p7 = new Point2D(0.975528, 0.345492);
		kdTree.insert(p7);
		Point2D p8 = new Point2D(0.206107, 0.904508);
		kdTree.insert(p8);
		Point2D p3Dup = new Point2D(0.975528, 0.654508);
		kdTree.insert(p3Dup);
		Point2D p9 = new Point2D(0.500000, 0.000000);
		kdTree.insert(p9);
		Point2D p10 = new Point2D(0.024472, 0.654508);
		kdTree.insert(p10);
		Point2D p6Dup = new Point2D(0.793893, 0.904508);
		kdTree.insert(p6Dup);

		assertEquals(10, kdTree.size());
	}

	@Test
	public void testInsertOne() {
		KdTree kdTree = new KdTree();
		Point2D point1 = new Point2D(0.3, 0.4);
		kdTree.insert(point1);
		kdTree.insert(point1);
		assertEquals(true, kdTree.contains(point1));

	}

	@Test
	public void testInsertTwo() {
		KdTree kdTree = new KdTree();
		Point2D point1 = new Point2D(0.3, 0.4);
		Point2D point2 = new Point2D(0.5, 0.5);
		kdTree.insert(point1);
		kdTree.insert(point2);
		assertEquals(true, kdTree.contains(point2));
	}

	@Test
	public void testInsertMany() {
		KdTree kdTree = new KdTree();
		Point2D point1 = new Point2D(0.3, 0.4);
		Point2D point2 = new Point2D(0.5, 0.5);
		Point2D point3 = new Point2D(0.7, 0.8);
		Point2D point4 = new Point2D(0.4, 0.9);
		Point2D point5 = new Point2D(0.8, 0.2);
		kdTree.insert(point1);
		kdTree.insert(point2);
		kdTree.insert(point3);
		kdTree.insert(point4);
		kdTree.insert(point5);
		assertEquals(5, kdTree.size());
		assertEquals(true, kdTree.contains(point5));
		assertEquals(true, kdTree.contains(point4));
		assertEquals(true, kdTree.contains(point2));
		assertEquals(true, kdTree.contains(point1));
		assertEquals(true, kdTree.contains(point3));
	}

	@Test
	public void testContainsForNullSet() {
		KdTree kdTree = new KdTree();
		Point2D point1 = new Point2D(0.3, 0.4);
		assertEquals(false, kdTree.contains(point1));
	}

	@Test
	public void testContainsOne() {
		KdTree kdTree = new KdTree();
		Point2D point1 = new Point2D(0.3, 0.4);
		kdTree.insert(point1);
		assertEquals(true, kdTree.contains(new Point2D(0.3, 0.4)));
	}

	@Test
	public void testContainsSameXCord() {
		KdTree kdTree = new KdTree();
		Point2D point1 = new Point2D(0.3, 0.4);
		kdTree.insert(point1);
		assertEquals(false, kdTree.contains(new Point2D(0.3, 0.3)));
	}

	@Test
	public void testContainsSameYCord() {
		KdTree kdTree = new KdTree();
		Point2D point1 = new Point2D(0.3, 0.4);
		kdTree.insert(point1);
		assertEquals(false, kdTree.contains(new Point2D(0.5, 0.4)));
	}

	@Test
	public void testContainsDuplicate() {
		KdTree kdTree = new KdTree();
		Point2D point1 = new Point2D(0.3, 0.4);
		Point2D point2 = new Point2D(0.3, 0.5);
		Point2D point3 = new Point2D(0.5, 0.4);
		Point2D point4 = new Point2D(0.3, 0.5);
		Point2D point5 = new Point2D(0.3, 0.3);
		kdTree.insert(point1);
		kdTree.insert(point2);
		kdTree.insert(point3);
		kdTree.insert(point4);
		kdTree.insert(point5);
		assertEquals(false, kdTree.contains(new Point2D(0.3, 0.6)));
		assertEquals(false, kdTree.contains(new Point2D(0.5, 0.3)));
		assertEquals(true, kdTree.contains(new Point2D(0.3, 0.4)));
		assertEquals(true, kdTree.contains(new Point2D(0.3, 0.5)));
		assertEquals(true, kdTree.contains(new Point2D(0.3, 0.3)));
		assertEquals(true, kdTree.contains(new Point2D(0.5, 0.4)));
		assertEquals(4, kdTree.size());
	}

	@Test
	public void testDraw() {
		KdTree kdTree = new KdTree();
		Point2D point1 = new Point2D(0.3, 0.4);
		Point2D point2 = new Point2D(0.5, 0.5);
		Point2D point3 = new Point2D(0.7, 0.8);
		Point2D point4 = new Point2D(0.4, 0.9);
		Point2D point5 = new Point2D(0.8, 0.2);
		kdTree.insert(point1);
		kdTree.insert(point2);
		kdTree.insert(point3);
		kdTree.insert(point4);
		kdTree.insert(point5);
		kdTree.draw();
		assertEquals(5, kdTree.size());
	}

	@Test
	public void testDrawcircl10txt() {
		KdTree kdTree = new KdTree();

		Point2D p1 = new Point2D(0.206107, 0.095492);
		kdTree.insert(p1);
		assertEquals(1, kdTree.size());

		Point2D p2 = new Point2D(0.975528, 0.654508);
		kdTree.insert(p2);
		assertEquals(2, kdTree.size());

		Point2D p3 = new Point2D(0.024472, 0.345492);
		kdTree.insert(p3);
		assertEquals(3, kdTree.size());

		Point2D p4 = new Point2D(0.793893, 0.095492);
		kdTree.insert(p4);
		assertEquals(4, kdTree.size());

		Point2D p5 = new Point2D(0.793893, 0.904508);
		kdTree.insert(p5);
		assertEquals(5, kdTree.size());

		Point2D p6 = new Point2D(0.975528, 0.345492);
		kdTree.insert(p6);
		assertEquals(6, kdTree.size());

		Point2D p7 = new Point2D(0.206107, 0.904508);
		kdTree.insert(p7);
		assertEquals(7, kdTree.size());

		Point2D p8 = new Point2D(0.500000, 0.000000);
		kdTree.insert(p8);
		assertEquals(8, kdTree.size());

		Point2D p9 = new Point2D(0.024472, 0.654508);
		kdTree.insert(p9);
		assertEquals(9, kdTree.size());

		Point2D p10 = new Point2D(0.500000, 1.000000);
		kdTree.insert(p10);
		assertEquals(10, kdTree.size());

		kdTree.draw();
		assertEquals(p7, kdTree.nearest(new Point2D(0.206107, 0.904511)));

		Point2D query = new Point2D(0.81, 0.3);
		assertEquals(p6, kdTree.nearest(query));
	}

	@Test
	public void testNearestNeighborCloseXCoordinate() {
		KdTree kdTree = new KdTree();
		Point2D p1 = new Point2D(0.206107, 0.095492);
		kdTree.insert(p1);
		Point2D p2 = new Point2D(0.975528, 0.654508);
		kdTree.insert(p2);
		Point2D p7 = new Point2D(0.206107, 0.904508);
		kdTree.insert(p7);
		Point2D p9 = new Point2D(0.024472, 0.654508);
		kdTree.insert(p9);
		assertEquals(p7, kdTree.nearest(new Point2D(0.206107, 0.904511)));
	}

	@Test
	public void testNearestNeighborCloseXCoordinateCircle100txt() {
		KdTree kdTree = new KdTree();

		Point2D p1 = new Point2D(0.999013, 0.531395);
		kdTree.insert(p1);
		Point2D p2 = new Point2D(0.922164, 0.232087);
		kdTree.insert(p2);
		Point2D p3 = new Point2D(0.975528, 0.654508);
		kdTree.insert(p3);
		Point2D p4 = new Point2D(1.000000, 0.500000);
		kdTree.insert(p4);
		assertEquals(p2, kdTree.nearest(new Point2D(0.922164, 0.234087)));
	}

	@Test
	public void testRange() {
		KdTree kdTree = new KdTree();
		Point2D p1 = new Point2D(0.2, 0.3);
		kdTree.insert(p1);// out
		Point2D p2 = new Point2D(0.3, 0.3);
		kdTree.insert(p2);
		Point2D p3 = new Point2D(0.4, 0.5);
		kdTree.insert(p3);
		Point2D p4 = new Point2D(0.5, 0.6);
		kdTree.insert(p4);
		Point2D p5 = new Point2D(0.8, 0.7);
		kdTree.insert(p5);
		Point2D p6 = new Point2D(0.9, 0.5);
		kdTree.insert(p6); // out

		List<Point2D> expectedPoints = new ArrayList<Point2D>();

		expectedPoints.add(p2);
		expectedPoints.add(p3);
		expectedPoints.add(p4);
		expectedPoints.add(p5);
		Iterator<Point2D> it = expectedPoints.iterator();

		Iterable<Point2D> rangePoints = kdTree.range(new RectHV(0.3, 0.3, 0.8,
				0.8));

		for (Point2D point : rangePoints) {
			assertEquals(it.next(), point);
		}

		assertEquals(false, it.hasNext());

	}

	@Test
	public void testRangeForEmptySet() {
		KdTree kdTree = new KdTree();
		Iterable<Point2D> rangePoints = kdTree.range(new RectHV(0.3, 0.3, 0.8,
				0.8));
		assertEquals(false, rangePoints.iterator().hasNext());
	}
	
	@Test
	public void testNearestForEmptySet() {
		KdTree kdTree = new KdTree();
		Point2D point = kdTree.nearest(new Point2D(0.4323,0.6723));
		assertEquals(null,point);
	}

	@Test
	public void testNearest() {
		KdTree kdTree = new KdTree();
		Point2D p1 = new Point2D(0.2, 0.3);
		kdTree.insert(p1);
		Point2D p2 = new Point2D(0.3, 0.3);
		kdTree.insert(p2);
		Point2D p3 = new Point2D(0.4, 0.5);
		kdTree.insert(p3);
		Point2D p4 = new Point2D(0.5, 0.6);
		kdTree.insert(p4);
		Point2D p5 = new Point2D(0.8, 0.7);
		kdTree.insert(p5);
		Point2D p6 = new Point2D(0.9, 0.5);
		kdTree.insert(p6);

		assertEquals(p5, kdTree.nearest(new Point2D(0.7, 0.9)));

	}

	@Test
	public void testNearestCircle10txt() {
		KdTree kdTree = new KdTree();
		Point2D p1 = new Point2D(0.500000, 1.000000);
		kdTree.insert(p1);
		Point2D p2 = new Point2D(0.206107, 0.095492);
		kdTree.insert(p2);
		Point2D p3 = new Point2D(0.975528, 0.654508);
		kdTree.insert(p3);
		Point2D p4 = new Point2D(0.024472, 0.345492);
		kdTree.insert(p4);
		Point2D p5 = new Point2D(0.793893, 0.095492);
		kdTree.insert(p5);
		Point2D p6 = new Point2D(0.793893, 0.904508);
		kdTree.insert(p6);
		Point2D p7 = new Point2D(0.975528, 0.345492);
		kdTree.insert(p7);
		Point2D p8 = new Point2D(0.206107, 0.904508);
		kdTree.insert(p8);
		Point2D p9 = new Point2D(0.500000, 0.000000);
		kdTree.insert(p9);
		Point2D p10 = new Point2D(0.024472, 0.654508);
		kdTree.insert(p10);

		assertEquals(p7, kdTree.nearest(new Point2D(0.81, 0.30)));
		assertEquals(false, kdTree.contains(new Point2D(0.81, 0.3)));

	}
}
