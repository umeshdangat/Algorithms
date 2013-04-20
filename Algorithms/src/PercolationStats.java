public class PercolationStats {

	private int T;
	private double[] percolationThreshholds;

	// perform T independent computational experiments on an N-by-N grid
	public PercolationStats(int N, int T) {

		if (N <= 0 || T <= 0) {
			throw new IllegalArgumentException(
					"Either N or T was less than or equal to zero");
		}

		this.T = T;
		percolationThreshholds = new double[T];

		for (int count = 0; count < T; count++) {
			percolationThreshholds[count] = getPercolationThreshhold(N);
		}

	}

	private double getPercolationThreshhold(int N) {

		Percolation percolation = new Percolation(N);
		double openedSites = 0;

		while (!percolation.percolates()) {
			// get a random open Site
			int idxRow = StdRandom.uniform(N) + 1;
			int idxCol = StdRandom.uniform(N) + 1;
			while (percolation.isOpen(idxRow, idxCol)) {
				idxRow = StdRandom.uniform(N) + 1;
				idxCol = StdRandom.uniform(N) + 1;
			}

			percolation.open(idxRow, idxCol);
			openedSites++;
		}
		return openedSites / (N * N);
	}

	// sample mean of percolation threshold
	public double mean() {
		return StdStats.mean(percolationThreshholds);
	}

	// sample standard deviation of percolation threshold
	public double stddev() {
		return StdStats.stddev(percolationThreshholds);
	}

	// returns lower bound of the 95% confidence interval
	public double confidenceLo() {
		double mean = mean();
		double stdDev = stddev();
		double sqRootT = Math.sqrt(T);
		return (mean - ((1.96 * stdDev) / sqRootT));
	}

	// returns upper bound of the 95% confidence interval
	public double confidenceHi() {
		double mean = mean();
		double stdDev = stddev();
		double sqRootT = Math.sqrt(T);
		return (mean + ((1.96 * stdDev) / sqRootT));
	}

	// test client, described below
	public static void main(String[] args) {
		Out out = new Out();
		PercolationStats percolationStats = new PercolationStats(
				Integer.parseInt(args[0]), Integer.parseInt(args[1]));
		out.printf("mean                          = %f \n",
				percolationStats.mean());
		out.printf("stddev                        = %f \n",
				percolationStats.stddev());
		out.printf("95%% confidence interval       = %f, %f ",
				percolationStats.confidenceLo(),
				percolationStats.confidenceHi());

	}
}
