import java.util.Comparator;

public class Point implements Comparable<Point> {

	// compare points by slope to this point
	public final Comparator<Point> SLOPE_ORDER = new SlopeOrder();
	private final int x;
	private final int y;

	// construct the point (x, y)
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	// draw this point
	public void draw() {
		StdDraw.point(x, y);
	}

	// draw the line segment from this point to that point
	public void drawTo(Point that) {
		StdDraw.line(this.x, this.y, that.x, that.y);
	}

	// string representation
	public String toString() {
		return "(" + x + ", " + y + ")";
	}

	// is this point lexicographically smaller than that point?
	@Override
	public int compareTo(Point that) {
		/*
		 * the invoking point (x0, y0) is less than the argument point (x1, y1)
		 * if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
		 */
		if (this.y < that.y) {
			return -1;
		} else if (this.y > that.y) {
			return 1;
		} else {
			return breakYTies(that);
		}

	}

	private int breakYTies(Point that) {
		if (this.x < that.x) {
			return -1;
		} else if (this.x > that.x) {
			return 1;
		} else
			return 0;
	}

	// the slope between this point and that point
	public double slopeTo(Point that) {

		if (thatIsSameAsThis(that)) {
			return Double.NEGATIVE_INFINITY;
		}
		
		double dy = that.y - this.y;
		double dx = that.x - this.x;
		
		if (dx == 0) {
			return Double.POSITIVE_INFINITY;
		}
		
		if(dy==0){
			return Double.parseDouble("+0");
		}
		
		return dy / dx;
	}

	private boolean thatIsSameAsThis(Point that) {
		if (that.x == this.x && that.y == this.y) {
			return true;
		} else {
			return false;
		}
	}

	private class SlopeOrder implements Comparator<Point> {

		@Override
		public int compare(Point p1, Point p2) {
			
			double slopeP1AndThis = Point.this.slopeTo(p1);
			double slopeP2AndThis = Point.this.slopeTo(p2);
			if(slopeP1AndThis < slopeP2AndThis){
				return -1;
			}
			else if (slopeP1AndThis > slopeP2AndThis){
				return +1;
			}
			return 0;
		}

	}
}
