import java.util.Arrays;

public class Fast {

	public static void main(String[] args) {

		// rescale coordinates and turn on animation mode
		StdDraw.clear();
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);
		StdDraw.show(0);

		// read inputs
		Point[] points = getPoints(args[0]);

		int lo = 0;
		int hi = points.length;
		Arrays.sort(points);
		for (Point p : points) {
			double[] slopes = getSlopesWithRespectToCurrentPoint(points, p);
			Arrays.sort(slopes, lo, hi);
			Arrays.sort(slopes, 0, lo);
			Arrays.sort(points, lo, hi, p.SLOPE_ORDER);
			Arrays.sort(points, 0, lo, p.SLOPE_ORDER);
			findAndPrintColinearPoints(slopes, points, lo);
			lo++;
		}

		StdDraw.show(0);

	}

	private static void findAndPrintColinearPoints(double[] slopes,
			Point[] points, int lo) {
		boolean newRangeStarted = false;
		int startIndex = 0;
		int endIndex = 0;
		for (int i = lo + 1; i < slopes.length; i++) {
			if (!newRangeStarted && slopes[i - 1] == slopes[i]) {
				newRangeStarted = true;
				startIndex = i - 1;
			}
			if (newRangeStarted && slopes[i - 1] != slopes[i]) {
				endIndex = i - 1;
				newRangeStarted = false;
			}

			if (newRangeStarted && i == slopes.length - 1) {
				endIndex = i;
				newRangeStarted = false;
			}

			if (endIndex - startIndex >= 2) {

				// if slope for points[endIndex] is same as with any point
				// we have already covered then its just a subsegment we can
				// ignore
				boolean subSegment = false;
				for (int prevCounter = 0; prevCounter < lo; prevCounter++) {
					if(slopes[startIndex] == slopes[prevCounter] ){
						subSegment = true;
						break;
					}
				}
				if (!subSegment) {

					displayAllColinearPoints(points, startIndex, endIndex, lo);
					System.out.println();
				}
				endIndex = 0;
				startIndex = 0;
			}
		}
	}

	private static void displayAllColinearPoints(Point[] points,
			int startIndex, int endIndex, int lo) {

		int noOfColinearPts = endIndex - startIndex + 2;
		Point[] lineSegment = new Point[noOfColinearPts];
		lineSegment[0] = points[lo];
		int curr = startIndex;
		for (int idx = 1; idx < noOfColinearPts; idx++) {
			lineSegment[idx] = points[curr++];
		}
		Arrays.sort(lineSegment);

		StdDraw.setPenColor(StdDraw.BLUE);
		StdDraw.setPenRadius();

		lineSegment[0].drawTo(lineSegment[noOfColinearPts - 1]);

		for (int count = 0; count < noOfColinearPts - 1; count++) {
			System.out.print(lineSegment[count] + " -> ");
		}
		System.out.print(lineSegment[noOfColinearPts - 1]);
	}

	private static double[] getSlopesWithRespectToCurrentPoint(Point[] points,
			Point p) {

		double[] slopes = new double[points.length];
		int i = 0;
		for (Point q : points) {
			slopes[i] = p.slopeTo(q);
			i++;
		}
		return slopes;
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

	public static void printColinearPoints(double[] slopes) {
		// TODO Auto-generated method stub
		
	}

}
