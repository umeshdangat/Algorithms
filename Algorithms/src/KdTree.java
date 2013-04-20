import java.util.ArrayList;
import java.util.List;

public class KdTree {

	private int size;
	private Node root;

	private static class Node {
		private Point2D point;
		private RectHV rect;
		private Node left;
		private Node right;

		private Node(Point2D point, RectHV rectHV) {
			this.point = point;
			this.rect = rectHV;
		}
	}

	// construct an empty set of points
	public KdTree() {
		size = 0;
		root = null;
	}

	// is the set empty?
	public boolean isEmpty() {
		return (root == null);
	}

	// number of points in the set
	public int size() {
		return size;
	}

	// add the point p to the set (if it is not already in the set)
	public void insert(Point2D p) {
		size++;
		root = put(root, p, true, 0, 0, 1, 1);
	}

	private Node put(Node x, Point2D key, boolean xCoordinateOrientation,
			double xmin, double ymin, double xmax, double ymax) {

		if (x != null && x.point.equals(key)) {
			size--;
			return x;
		}
		if (x == null) {
			RectHV rectHV = new RectHV(xmin, ymin, xmax, ymax);
			Node n = new Node(key, rectHV);
			return n;
		}

		int cmp = 0;
		// key on x-cord, even level
		if (xCoordinateOrientation) {
			cmp = Point2D.X_ORDER.compare(key, x.point);
			if (cmp < 0) {
				x.left = put(x.left, key, !xCoordinateOrientation, xmin, ymin,
						x.point.x(), ymax);
			} else {
				x.right = put(x.right, key, !xCoordinateOrientation,
						x.point.x(), ymin, xmax, ymax);
			}
		} else {
			cmp = Point2D.Y_ORDER.compare(key, x.point);
			if (cmp < 0) {
				x.left = put(x.left, key, !xCoordinateOrientation, xmin, ymin,
						xmax, x.point.y());
			} else {
				x.right = put(x.right, key, !xCoordinateOrientation, xmin,
						x.point.y(), xmax, ymax);
			}
		}

		return x;

	}

	// does the set contain the point p?
	public boolean contains(Point2D p) {
		return get(p) != null;
	}

	private Node get(Point2D key) {
		Node x = root;
		boolean xCoOrdinateOrientation = true;

		while (x != null) {

			if (x.point.equals(key)) {
				return x;
			}

			int cmp = 0;
			if (xCoOrdinateOrientation) {
				cmp = Point2D.X_ORDER.compare(key, x.point);
			} else {
				cmp = Point2D.Y_ORDER.compare(key, x.point);
			}
			if (cmp < 0) {
				x = x.left;
				xCoOrdinateOrientation = !xCoOrdinateOrientation;
			} else {
				x = x.right;
				xCoOrdinateOrientation = !xCoOrdinateOrientation;
			}

		}
		return null;
	}

	// draw all of the points to standard draw
	public void draw() {

		StdDraw.clear();
		StdDraw.setXscale(0, 1);
		StdDraw.setYscale(0, 1);
		StdDraw.show(0);
		// draw Boundary Rectangle
		Point2D pointXMinYMin = new Point2D(0, 0);
		Point2D pointXMaxYMin = new Point2D(1, 0);
		Point2D pointXMinYMax = new Point2D(0, 1);
		Point2D pointXMaxYMax = new Point2D(1, 1);
		pointXMinYMin.drawTo(pointXMaxYMin);
		pointXMaxYMin.drawTo(pointXMaxYMax);
		pointXMaxYMax.drawTo(pointXMinYMax);
		pointXMinYMax.drawTo(pointXMinYMin);
		Node x = root;
		draw(x, true);
		StdDraw.show(0);
	}

	private Node draw(Node node, boolean xCoordinateOrientation) {

		if (node == null) {
			return null;
		}
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.setPenRadius(.01);
		node.point.draw();
		StdDraw.setPenRadius();
		if (xCoordinateOrientation) {
			StdDraw.setPenColor(StdDraw.RED);
			Point2D low = new Point2D(node.point.x(), node.rect.ymin());
			Point2D high = new Point2D(node.point.x(), node.rect.ymax());
			low.drawTo(high);
		} else {
			StdDraw.setPenColor(StdDraw.BLUE);
			Point2D start = new Point2D(node.rect.xmin(), node.point.y());
			Point2D end = new Point2D(node.rect.xmax(), node.point.y());
			start.drawTo(end);
		}

		draw(node.left, !xCoordinateOrientation);
		draw(node.right, !xCoordinateOrientation);
		return node;

	}

	// all points in the set that are inside the rectangle
	public Iterable<Point2D> range(RectHV rect) {
		List<Point2D> rangePoints = new ArrayList<Point2D>();
		Node x = root;
		if (!isEmpty()) {
			rangePoints = getRange(x, rangePoints, rect);
		}
		return rangePoints;
	}

	private List<Point2D> getRange(Node x, List<Point2D> rangePoints,
			RectHV rect) {

		if (rect.contains(x.point)) {
			rangePoints.add(x.point);
		}

		if (x.left != null && x.left.rect.intersects(rect)) {
			rangePoints = getRange(x.left, rangePoints, rect);
		}
		if (x.right != null && x.right.rect.intersects(rect)) {
			rangePoints = getRange(x.right, rangePoints, rect);
		}

		return rangePoints;

	}

	// a nearest neighbor in the set to p; null if set is empty
	public Point2D nearest(Point2D p) {
		if (isEmpty()) {
			return null;
		}
		Node x = root;
		NearestNeighbor nearestNeighbor = getNearestNeighbor(x, p,
				new NearestNeighbor(Double.POSITIVE_INFINITY, null));
		return nearestNeighbor.currentNearestPoint;
	}

	private class NearestNeighbor {

		public NearestNeighbor(double currentMinimumSquaredDistance,
				Point2D currentNearestPoint) {
			this.currentMinimumSquaredDistance = currentMinimumSquaredDistance;
			this.currentNearestPoint = currentNearestPoint;
		}

		private Point2D currentNearestPoint;
		private double currentMinimumSquaredDistance;
	}

	private NearestNeighbor getNearestNeighbor(Node x, Point2D queryPoint,
			NearestNeighbor nearestNeighbor) {

		double dist = x.point.distanceSquaredTo(queryPoint);
		if (dist < nearestNeighbor.currentMinimumSquaredDistance) {
			nearestNeighbor.currentMinimumSquaredDistance = dist;
			nearestNeighbor.currentNearestPoint = x.point;
		}

		if (x.left != null && x.left.rect.contains(queryPoint)) {
			nearestNeighbor = goLeft(x, queryPoint, nearestNeighbor);
			nearestNeighbor = goRight(x, queryPoint, nearestNeighbor);
		} else {
			nearestNeighbor = goRight(x, queryPoint, nearestNeighbor);
			nearestNeighbor = goLeft(x, queryPoint, nearestNeighbor);
		}

		return nearestNeighbor;
	}

	private NearestNeighbor goRight(Node x, Point2D queryPoint,
			NearestNeighbor nearestNeighbor) {
		if (x.right != null
				&& canContain(queryPoint, x.right,
						nearestNeighbor.currentMinimumSquaredDistance)) {
			nearestNeighbor = getNearestNeighbor(x.right, queryPoint,
					nearestNeighbor);
		}
		return nearestNeighbor;
	}

	private NearestNeighbor goLeft(Node x, Point2D queryPoint,
			NearestNeighbor nearestNeighbor) {
		if (x.left != null
				&& canContain(queryPoint, x.left,
						nearestNeighbor.currentMinimumSquaredDistance)) {
			nearestNeighbor = getNearestNeighbor(x.left, queryPoint,
					nearestNeighbor);

		}
		return nearestNeighbor;
	}

	private boolean canContain(Point2D queryPoint, Node node,
			double currentMinimumSquaredDistance) {
		return node.rect.distanceSquaredTo(queryPoint) < currentMinimumSquaredDistance;
	}

}
