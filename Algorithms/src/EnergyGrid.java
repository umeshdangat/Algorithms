public class EnergyGrid {

	private int height;
	private int width;
	private SeamCarver seamCarver;
	private int[][] energyGrid;

	public EnergyGrid(SeamCarver seamCarver) {
		this.seamCarver = seamCarver;
		this.energyGrid = createEnergyGrid();
	}

	public EnergyGrid(SeamCarver seamCarver, int height, int width) {
		this.seamCarver = seamCarver;
		this.energyGrid = new int[height][width];
		this.height = height;
		this.width = width;
	}

	public int getEnergy(int row, int col) {

		return energyGrid[row][col];
	}

	public void setEnergy(int row, int col, int energy) {
		energyGrid[row][col] = energy;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	private int[][] createEnergyGrid() {
		int[][] energyGrid = new int[seamCarver.height()][seamCarver.width()];
		for (int i = 0; i < seamCarver.height(); i++) {
			for (int j = 0; j < seamCarver.width(); j++) {
				int energyForThisPixel = (int) seamCarver.energy(j, i);
				energyGrid[i][j] = energyForThisPixel;
			}
		}
		height = energyGrid.length;
		width = energyGrid[0].length;
		return energyGrid;
	}

	public double updateEnergyGridPostVerticalSeamRemoval(int[] verticalSeam) {

		// Stopwatch sw = new Stopwatch();
		udpateEnergyGridForLesserWidth(verticalSeam);
		for (int row = 0; row < height; row++) {
			int col = verticalSeam[row];
			if (col < width) {
				if (col - 1 >= 0) {
					energyGrid[row][col - 1] = (int) seamCarver.energy(col - 1,
							row);
				}

				energyGrid[row][col] = (int) seamCarver.energy(col, row);
			}
		}
		// sw.elapsedTime();
		return 0;
	}

	public double updateEnergyGridPostHorizontalSeamRemoval(int[] horizontalSeam) {

		// Stopwatch sw = new Stopwatch();
		udpateEnergyGridForLesserHeight(horizontalSeam);
		for (int col = 0; col < width; col++) {
			int row = horizontalSeam[col];
			if (row < height) {
				if (row - 1 >= 0) {
					energyGrid[row - 1][col] = (int) seamCarver.energy(col,
							row - 1);
				}

				energyGrid[row][col] = (int) seamCarver.energy(col, row);
			}
		}
		// sw.elapsedTime();
		return 0;
	}

	private void udpateEnergyGridForLesserWidth(int[] verticalSeam) {
		for (int row = 0; row < height; row++) {
			int col = verticalSeam[row];
			if (col + 1 < width) {
				int length = width - 1 - col;
				/*
				 * System.out.println("row: " + row + " col:" + col + " length:"
				 * + length);
				 */
				// Stopwatch sw = new Stopwatch();
				System.arraycopy(energyGrid[row], col + 1, energyGrid[row],
						col, length);

			}
		}
		width--;
	}

	private double udpateEnergyGridForLesserHeight(int[] horizontalSeam) {

		double arrCopyTime = 0;
		for (int col = 0; col < width; col++) {
			int row = horizontalSeam[col];
			if (row + 1 < height) {
				// Stopwatch sw = new Stopwatch();
				for (int j = row; j < height - 1; j++) {
					energyGrid[j][col] = energyGrid[j + 1][col];
				}
				// arrCopyTime += sw.elapsedTime();
			}
		}
		height--;
		return arrCopyTime;
	}

	public EnergyGrid transpose() {

		// Stopwatch sw = new Stopwatch();
		EnergyGrid transposedEnergyGrid = new EnergyGrid(seamCarver, width,
				height);

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				transposedEnergyGrid.setEnergy(i, j, getEnergy(j, i));
			}
		}

		// transposedEnergyGrid.setTime(sw.elapsedTime());
		return transposedEnergyGrid;
	}

}
