import java.util.PriorityQueue;

public class DistanceGrid {

	private int[][] distanceGrid;
	private int gridHeight;
	private int gridWidth;

	public DistanceGrid(EnergyGrid energyGrid) {
		this.gridHeight = energyGrid.getHeight();
		this.gridWidth = energyGrid.getWidth();
		distanceGrid = new int[gridHeight][gridWidth];
		buildDistTo(energyGrid);
	}

	private void buildDistTo(EnergyGrid energyGrid) {
		// first row
		for (int col = 0; col < gridWidth; col++) {
			distanceGrid[0][col] = 195075;
		}
		// distMap[x, y] = min(min(distMap(x-1, y-1), weightMap(x, y-1)),
		// weightMap(x+1, y-1)) + weightMap(x, y);
		for (int row = 1; row < gridHeight; row++) {
			for (int col = 0; col < gridWidth; col++) {
				int minParent = getMinParent(row, col);
				distanceGrid[row][col] = minParent
						+ energyGrid.getEnergy(row, col);
			}
		}
	}

	private int getMinParent(int row, int col) {

		// already topmost row, nothing above me
		if (row == 0) {
			return 0;
		}

		PriorityQueue<Integer> parentsDistance = new PriorityQueue<Integer>();

		if (col - 1 >= 0) {
			parentsDistance.add(distanceGrid[row - 1][col - 1]);
		}

		parentsDistance.add(distanceGrid[row - 1][col]);

		if (col + 1 < gridWidth) {
			parentsDistance.add(distanceGrid[row - 1][col + 1]);
		}

		int min = parentsDistance.peek();
		return min;
	}

	public int[] getShortestPath() {
		return createVerticalSeam();
	}

	private int[] createVerticalSeam() {

		Stack<Integer> reverseVerticalSeamOrder = new Stack<Integer>();
		int minimumEnergyColForLastRow = getMindistToColForLastRow();
		reverseVerticalSeamOrder.push(minimumEnergyColForLastRow);

		int col = minimumEnergyColForLastRow;
		for (int row = gridHeight - 1; row > 0; row--) {
			int nextCol = getMinParentCol(row, col);
			reverseVerticalSeamOrder.push(nextCol);
			col = nextCol;
		}
		int rowNo = 0;
		int[] verticalSeam = new int[gridHeight];
		while (!reverseVerticalSeamOrder.isEmpty()) {
			verticalSeam[rowNo] = reverseVerticalSeamOrder.pop();
			rowNo++;
		}
		return verticalSeam;
	}

	private int getMindistToColForLastRow() {
		int minCol = 0;
		int currentMin = Integer.MAX_VALUE;
		for (int i = 0; i < gridWidth; i++) {
			if (distanceGrid[gridHeight - 1][i] < currentMin) {
				currentMin = distanceGrid[gridHeight - 1][i];
				minCol = i;
			}
		}
		return minCol;
	}

	private class ParentDistTo implements Comparable<ParentDistTo> {

		private int col;
		private int distTo;

		private ParentDistTo(int row, int col, int distTo) {
			this.col = col;
			this.distTo = distTo;
		}

		@Override
		public int compareTo(ParentDistTo that) {
			return (int) (this.distTo - that.distTo);
		}
	}

	private int getMinParentCol(int row, int col) {

		PriorityQueue<ParentDistTo> parentsDistance = new PriorityQueue<ParentDistTo>();

		if (col - 1 >= 0) {
			parentsDistance.add(new ParentDistTo(row - 1, col - 1,
					distanceGrid[row - 1][col - 1]));
		}

		parentsDistance.add(new ParentDistTo(row - 1, col,
				distanceGrid[row - 1][col]));

		if (col + 1 < gridWidth) {
			parentsDistance.add(new ParentDistTo(row - 1, col + 1,
					distanceGrid[row - 1][col + 1]));
		}

		ParentDistTo parentDistTo = parentsDistance.peek();
		return parentDistTo.col;

	}

	public double reduceGridWidthAndRecalculate(EnergyGrid energyGrid,
			int[] verticalSeam) {
		udpateDistToGridForLesserWidth(verticalSeam);
		fixDistToVertical(verticalSeam,energyGrid);
		return 0;
	}

	public double reduceGridHeightAndRecalculate(EnergyGrid energyGrid,
			int[] horizontalSeam) {
		udpateDistToGridForLesserHeight(horizontalSeam);
		fixDistToHorizontal(horizontalSeam,energyGrid);
		return 0;
	}

	private double udpateDistToGridForLesserWidth(int[] verticalSeam) {

		double arrCopyTime = 0;
		for (int row = 0; row < gridHeight; row++) {
			int col = verticalSeam[row];
			if (col + 1 < gridWidth) {
				int length = gridWidth - 1 - col;
				/*
				 * System.out.println("row: " + row + " col:" + col + " length:"
				 * + length);
				 */
				// Stopwatch sw = new Stopwatch();
				System.arraycopy(distanceGrid[row], col + 1, distanceGrid[row],
						col, length);
				// arrCopyTime += sw.elapsedTime();
			}
		}
		gridWidth--;
		return arrCopyTime;
	}

	private double udpateDistToGridForLesserHeight(int[] horizontalSeam) {

		double arrCopyTime = 0;
		for (int col = 0; col < gridWidth; col++) {
			int row = horizontalSeam[col];
			if (row + 1 < gridHeight) {
				// Stopwatch sw = new Stopwatch();
				for (int j = row; j < gridHeight - 1; j++) {
					distanceGrid[j][col] = distanceGrid[j + 1][col];
				}
				// arrCopyTime += sw.elapsedTime();
			}
		}
		gridHeight--;
		return arrCopyTime;
	}

	private void fixDistToVertical(int[] verticalSeam, EnergyGrid energyGrid) {

		int colStart = verticalSeam[0];
		if (colStart - 1 > 0) {
			colStart--;
		} else {
			colStart = 0;
		}
		int colEnd = verticalSeam[0];
		if (colEnd + 1 < gridWidth) {
			colEnd++;
		} else {
			colEnd = gridWidth - 1;
		}

		for (int row = 1; row < gridHeight; row++) {
			for (int col = colStart; col <= colEnd; col++) {
				int minParent = getMinParent(row, col);
				distanceGrid[row][col] = minParent
						+ energyGrid.getEnergy(row, col);
			}
			if (colStart - 1 > 0) {
				colStart--;
			} else {
				colStart = 0;
			}
			if (colEnd + 1 < gridWidth) {
				colEnd++;
			} else {
				colEnd = gridWidth - 1;
			}
		}

	}

	private void fixDistToHorizontal(int[] horizontalSeam, EnergyGrid energyGrid) {
		fixDistToVertical(horizontalSeam, energyGrid);
		PriorityQueue<Integer> rowsInSeam = new PriorityQueue<Integer>();
		for (int row : horizontalSeam) {
			rowsInSeam.add(row);
		}

		int minRow = rowsInSeam.peek();
		// recompute distances below the topmost row, for all columns
		if (minRow < gridHeight) {
			for (int row = minRow; row < gridHeight; row++) {
				for (int col = 0; col < gridWidth; col++) {
					int minParent = getMinParent(row, col);
					distanceGrid[row][col] = minParent
							+ energyGrid.getEnergy(row, col);
				}
			}
		}
	}

	private void transpose() {
		int[][] transposedGrid = new int[gridWidth][gridHeight];
		for (int i = 0; i < gridWidth; i++) {
			for (int j = 0; j < gridHeight; j++) {
				transposedGrid[i][j] = distanceGrid[j][i];
			}
		}
		distanceGrid = transposedGrid;
		gridWidth = distanceGrid[0].length;
		gridHeight = distanceGrid.length;
	}

}
