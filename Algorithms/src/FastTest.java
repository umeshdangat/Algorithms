import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class FastTest {

	private static Point[] getPoints(String filename) {
		// read in the input
		In in = new In(filename);
		int N = in.readInt();
		Point[] points = new Point[N];
		for (int i = 0; i < N; i++) {
			int x = in.readInt();
			int y = in.readInt();
			Point p = new Point(x, y);
			StdDraw.setPenColor(StdDraw.BLUE);
			StdDraw.setPenRadius(0.009);
			p.draw();
			points[i] = p;
		}
		return points;
	}

	@Test
	public void testWithMultipleSeries() {
		double[] slopes = new double[] { 1, 2, 3, 3, 3, 3, 3, 3, 4, 4, 4, 5, 5,
				5, 5, 5 };
		System.out.println("testWithMultipleSeries");
		//Fast.printColinearPoints(slopes);
	}

	@Test
	public void testWithSingleSeries() {
		double[] slopes = new double[] { 1, 2, 3, 3, 3, 3, 3, 3 };
		System.out.println("testWithSingleSeries");
		Fast.printColinearPoints(slopes);
	}

	@Test
	public void testWithNoSeries() {
		double[] slopes = new double[] { 1, 2, 3, 3, 3, 4, 4, 4 };
		System.out.println("testWithNoSeries");
		Fast.printColinearPoints(slopes);
	}

	@Test
	public void testWithNoSeriesAllUnique() {
		double[] slopes = new double[] { 1, 2, 3, 4, 5, 6, 7, 8 };
		System.out.println("testWithNoSeriesAllUnique");
		Fast.printColinearPoints(slopes);
	}

	@Test
	public void testWithSeriesAllSame() {
		double[] slopes = new double[] { 1, 1, 1, 1, 5, 6, 7, 8 };
		System.out.println("testWithNoSeriesAllSame");
		Fast.printColinearPoints(slopes);
	}

	@Test
	public void testWithSeriesSame() {
		double[] slopes = new double[] { 1, 1, 1, 1, 2, 2, 2, 2 };
		System.out.println("testWithNoSeriesAllSame");
		Fast.printColinearPoints(slopes);
	}

	private static void displayColinearPoints(Point[] points, int startIndex,
			int endIndex) {
		Arrays.sort(points, startIndex, endIndex + 1);
		boolean isPStart = false;
		boolean isPEnd = false;
		if (points[0].compareTo(points[startIndex]) < 0) {
			isPStart = true;
		}
		if (points[0].compareTo(points[endIndex]) > 0) {
			isPEnd = true;
		}

		StdDraw.setPenColor();
		StdDraw.setPenRadius();

		if (isPStart) {
			points[0].drawTo(points[endIndex]);
			System.out.print(points[0] + " -> ");
			for (int idx = startIndex; idx < endIndex; idx++) {
				System.out.print(points[idx] + " -> ");
			}
			System.out.print(points[endIndex]);
		} else if (isPEnd) {
			points[startIndex].drawTo(points[0]);
			for (int idx = startIndex; idx <= endIndex; idx++) {
				System.out.print(points[idx] + " -> ");
			}
			System.out.print(points[0]);
		} else {
			points[startIndex].drawTo(points[endIndex]);
			for (int idx = startIndex; idx <= endIndex; idx++) {
				System.out.print(points[idx] + " -> ");
			}
		}

	}

	public static void threeWaySort(Comparable[] a, int lo, int hi) {
		int lt = 0;
		int gt = hi;
		Comparable v = a[lo];
		int i = lo;
		while (i <= gt) {
			int cmp = a[i].compareTo(v);
			if (cmp < 0)
				swap(a, lt++, i++);
			else if (cmp > 0)
				swap(a, i, gt--);
			else
				i++;
		}

	}

	private static void swap(Comparable[] a, int i, int j) {
		int temp = (Integer) a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	@Test
	public void testthreeWaySort() {
		FastTest fastTest = new FastTest();
		Integer[] a = new Integer[10];
		a[0] = 54;
		a[1] = 38;
		a[2] = 22;
		a[3] = 54;
		a[4] = 54;
		a[5] = 76;
		a[6] = 54;
		a[7] = 31;
		a[8] = 97;
		a[9] = 18;
		int lo = 0;
		int hi = a.length - 1;
		fastTest.threeWaySort(a, lo, hi);
	}

}
