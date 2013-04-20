public class PointSET {

	private SET<Point2D> pointsInSet;

	// construct an empty set of points
	public PointSET() {
		pointsInSet = new SET<Point2D>();
	}

	// is the set empty?
	public boolean isEmpty() {
		return pointsInSet.isEmpty();
	}

	// number of points in the set
	public int size() {
		return pointsInSet.size();
	}

	// add the point p to the set (if it is not already in the set)
	public void insert(Point2D p) {
		if (!contains(p)) {
			pointsInSet.add(p);
		}
	}

	// does the set contain the point p?
	public boolean contains(Point2D p) {
		return pointsInSet.contains(p);
	}
	
	// draw all of the points to standard draw
	public void draw() {
		StdDraw.clear();
		StdDraw.setXscale(0, 1);
		StdDraw.setYscale(0, 1);
		StdDraw.show(0);
		StdDraw.setPenColor(StdDraw.BLUE);
		StdDraw.setPenRadius(0.008);
		for(Point2D point: pointsInSet){
			point.draw();
		}
		StdDraw.show(0);
	} 

	// all points in the set that are inside the rectangle
	public Iterable<Point2D> range(RectHV rect) {
		SET<Point2D> pointsInRect = new SET<Point2D>();
		for (Point2D point : pointsInSet) {
			if (rect.contains(point)) {
				pointsInRect.add(point);
			}
		}
		return pointsInRect;
	}

	// a nearest neighbor in the set to p; null if set is empty
	public Point2D nearest(Point2D p) {
		double currentMinDistane = Double.POSITIVE_INFINITY;
		Point2D nearestNeighbor = null;
		for (Point2D point : pointsInSet) {
			double distance = p.distanceTo(point);
			if (distance < currentMinDistane) {
				currentMinDistane = distance;
				nearestNeighbor = point;
			}
		}
		return nearestNeighbor;
	}
}