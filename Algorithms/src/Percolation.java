public class Percolation {

	private static final int sizeOfVirtualSites = 2;
	private final int sizeof1DUF;
	private WeightedQuickUnionUF weightedQuickUnionUF;
	private WeightedQuickUnionUF weightedQuickUnionUFWithoutVirtualBottom;
	private final int gridSize;
	private boolean[] openSites;

	// create N-by-N grid, with all sites blocked
	public Percolation(final int gridSize) {

		this.gridSize = gridSize;
		sizeof1DUF = gridSize * gridSize + sizeOfVirtualSites;

		weightedQuickUnionUF = new WeightedQuickUnionUF(sizeof1DUF);
		weightedQuickUnionUFWithoutVirtualBottom = new WeightedQuickUnionUF(sizeof1DUF-1);

		initOpenSites();
	}

	private void initOpenSites() {
		openSites = new boolean[sizeof1DUF];
		// mark virtual openSites as open
		openSites[0] = true;
		openSites[sizeof1DUF - 1] = true;
	}

	public final void open(final int i, final int j) {

		// The open() method should do three things.

		// First, it should validate the indices of the site that it receives.
		validateIndices(i, j);

		// Second, it should somehow mark the site as open.
		int openSiteId = twoDToOneD(i, j);
		openSites[openSiteId] = true;

		// if cell is from top row attach it to virtual top
		if (i == 1) {
			int id = twoDToOneD(i, j);
			weightedQuickUnionUF.union(0, id);
			weightedQuickUnionUFWithoutVirtualBottom.union(0,id);
		}

		// if cell is from bottom row attach it to virtual bottom
		if (i == gridSize) {
			int id = twoDToOneD(i, j);
			weightedQuickUnionUF.union(sizeof1DUF - 1, id);
		}

		// Third, it should perform some sequence of WeightedQuickUnionUF
		// operations that links the site in question to its open neighbors. The
		// constructor and instance variables should facilitate the open()
		// method's ability to do its job.
		if ((i + 1 <= gridSize) && (isOpen(i + 1, j))) {
			weightedQuickUnionUF.union(openSiteId, twoDToOneD(i + 1, j));
			weightedQuickUnionUFWithoutVirtualBottom.union(openSiteId, twoDToOneD(i + 1, j));
		}

		if ((i - 1 >= 1) && (isOpen(i - 1, j))) {
			weightedQuickUnionUF.union(openSiteId, twoDToOneD(i - 1, j));
			weightedQuickUnionUFWithoutVirtualBottom.union(openSiteId, twoDToOneD(i - 1, j));
		}

		if ((j + 1 <= gridSize) && (isOpen(i, j + 1))) {
			weightedQuickUnionUF.union(openSiteId, twoDToOneD(i, j + 1));
			weightedQuickUnionUFWithoutVirtualBottom.union(openSiteId, twoDToOneD(i, j + 1));
		}

		if ((j - 1 >= 1) && (isOpen(i, j - 1))) {
			weightedQuickUnionUF.union(openSiteId, twoDToOneD(i, j - 1));
			weightedQuickUnionUFWithoutVirtualBottom.union(openSiteId, twoDToOneD(i, j - 1));
		}

	}

	private void validateIndices(final int i, final int j) {
		if (i <= 0 || i > gridSize) {
			throw new IndexOutOfBoundsException("row index i out of bounds");
		}
		if (j <= 0 || j > gridSize) {
			throw new IndexOutOfBoundsException("column index j out of bounds");
		}
	}

	// is site (row i, column j) open?
	public final boolean isOpen(final int i, final int j) {
		int openSiteId = twoDToOneD(i, j);
		return openSites[openSiteId];
	}

	// is site (row i, column j) full?
	public final boolean isFull(final int i, final int j) {
		int openSiteId = twoDToOneD(i, j);
		return weightedQuickUnionUFWithoutVirtualBottom.connected(0, openSiteId);
	}

	// does the system percolate?
	public final boolean percolates() {
		return weightedQuickUnionUF.connected(0, sizeof1DUF - 1);

	}

	private int twoDToOneD(final int row, final int col) {
		validateIndices(row, col);
		return (row - 1) * gridSize + col;
	}

	public WeightedQuickUnionUF getWeightedUnionUF() {
		return weightedQuickUnionUF;
	}

}
