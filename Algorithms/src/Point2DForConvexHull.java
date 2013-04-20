import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Point2DForConvexHull {

	final double x;
	final double y;
	public final Comparator<Point2DForConvexHull> POLAR_ORDER = new PolarOrder();

	public Point2DForConvexHull(double x, double y) {
		this.x = x;
		this.y = y;
	}

	static int ccw(Point2DForConvexHull a, Point2DForConvexHull b, Point2DForConvexHull c) {

		double area2 = (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
		if (area2 < 0)
			return -1;
		else if (area2 > 0)
			return +1;
		else
			return 0;

	}

	private class PolarOrder implements Comparator<Point2DForConvexHull> {

		public int compare(Point2DForConvexHull q1, Point2DForConvexHull q2) {
			double dy1 = q1.y - y;
			double dy2 = q2.y - y;

			if (dy1 == 0 && dy2 == 0) {
				return 0;
			} else if (dy1 >= 0 && dy2 < 0)
				return -1;
			else if (dy2 >= 0 && dy1 < 0)
				return 1;
			else
				return -ccw(Point2DForConvexHull.this, q1, q2);
		}
	}

}
