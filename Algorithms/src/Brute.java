import java.util.Arrays;

public class Brute {

	public static void main(String[] args) {

		// rescale coordinates and turn on animation mode
		StdDraw.clear();
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);
		StdDraw.show(0);

		// read input
		Point[] points = getPoints(args[0]);
		Arrays.sort(points);

		for (int i = 0; i < points.length; i++) {
			for (int j = i + 1; j < points.length; j++) {
				for (int k = j + 1; k < points.length; k++) {
					if (points[i].slopeTo(points[j]) == points[i]
							.slopeTo(points[k])) {
						for (int l = k + 1; l < points.length; l++) {
							if (points[i].slopeTo(points[j]) == points[i]
									.slopeTo(points[l])) {
								createAndProcessLineSegment(points, i, j, k, l);
							}

						}

					}
				}
			}
		}

		StdDraw.show(0);
	}

	private static void createAndProcessLineSegment(Point[] points, int i, int j, int k,
			int l) {
		Point[] lineSegment = new Point[4];
		lineSegment[0] = points[i];
		lineSegment[1] = points[j];
		lineSegment[2] = points[k];
		lineSegment[3] = points[l];
		Arrays.sort(lineSegment);
		drawLineSegment(lineSegment);
		displayConsoleOutput(lineSegment);
	}

	private static void displayConsoleOutput(Point[] lineSegment) {
		System.out.println(lineSegment[0] + " -> " + lineSegment[1] + " -> "
				+ lineSegment[2] + " -> " + lineSegment[3]);
	}

	private static void drawLineSegment(Point[] lineSegment) {
		Point endPoint1 = lineSegment[0];
		Point endPoint2 = lineSegment[3];
		StdDraw.setPenColor(StdDraw.BLUE);
		StdDraw.setPenRadius();
		endPoint1.drawTo(endPoint2);
	}

	private static Point[] getPoints(String filename) {
		// read in the input
		In in = new In(filename);
		int N = in.readInt();
		Point[] points = new Point[N];
		for (int i = 0; i < N; i++) {
			int x = in.readInt();
			int y = in.readInt();
			Point p = new Point(x, y);
			StdDraw.setPenColor();
			StdDraw.setPenRadius();
			p.draw();
			points[i] = p;
		}
		return points;
	}

}
