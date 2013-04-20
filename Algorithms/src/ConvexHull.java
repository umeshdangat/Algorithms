import java.util.Arrays;
import java.util.Comparator;

public class ConvexHull {

	private Stack<Point2DForConvexHull> hull;

	public ConvexHull() {
		hull = new Stack<Point2DForConvexHull>();
	}

	public Stack<Point2DForConvexHull> getPointsOnHull(Point2DForConvexHull[] p) {

		Arrays.sort(p, new YOrder());
		Arrays.sort(p, p[0].POLAR_ORDER);

		hull.push(p[0]);
		hull.push(p[1]);

		for (int i = 2; i < p.length; i++) {
			Point2DForConvexHull top = hull.pop();
			System.out.println("Leaving out point: " + top.x + ","
					+ top.y);
			while (Point2DForConvexHull.ccw(hull.peek(), top, p[i]) <= 0) {
				top = hull.pop();
				System.out.println("Leaving out point: " + top.x + ","
						+ top.y);
			}
			System.out.println("Adding top "+ top.x + "," + top.y + " p[" + i + "]" + p[i].x +","+p[i].y);
			hull.push(top);
			hull.push(p[i]);
		}

		return hull;

	}

	private class YOrder implements Comparator<Point2DForConvexHull> {

		@Override
		public int compare(Point2DForConvexHull q1, Point2DForConvexHull q2) {

			if (q1.y < q2.y)
				return -1;
			else if (q1.y > q2.y)
				return 1;
			else
				return 0;
		}

	}

	@Override
	public String toString() {

		String result = new String();
		for (Point2DForConvexHull p : hull) {
			String x = Double.toString(p.x);
			String y = Double.toString(p.y);
			result = result +  x + "," + y + " ";
		}
		return null;

	}
}