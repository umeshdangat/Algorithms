import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {

	private final short[][] blocks;
	private final int N;
	private final int hammingNumber;
	private final int manhattanNumber;
	private final int originalBlankRow;
	private final int originalBlankCol;

	// construct a board from an N-by-N array of blocks
	// (where blocks[i][j] = block in row i, column j)
	public Board(int[][] blocks) {
		this.N = blocks.length;
		this.blocks = new short[N][N];
		int initBlankRow = 0;
		int initBlankCol = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				this.blocks[i][j] = (short) blocks[i][j];
				if (blocks[i][j] == 0) {
					initBlankRow = i;
					initBlankCol = j;
				}
			}
		}

		hammingNumber = setHammingNumber();
		manhattanNumber = setManhattanNumber();
		originalBlankRow = initBlankRow;
		originalBlankCol = initBlankCol;

	}

	private int setManhattanNumber() {
		int currentManhattanSum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (blocks[i][j] < 0 || blocks[i][j] > (N * N) - 1) {
					throw new InvalidParameterException(
							"Invalid entry in matrix " + blocks[i][j]
									+ "is out of bounds");
				}
				if (blocks[i][j] == 0) {
					continue;
				}

				currentManhattanSum += getCurrentManhattan(i, j);

			}
		}
		return currentManhattanSum;
	}

	private int getCurrentManhattan(int i, int j) {
		int expectedRow = blocks[i][j] / N;
		int expectedCol = blocks[i][j] % N - 1;
		// row end elements,
		if (expectedCol < 0) {
			expectedCol = N - 1;
			expectedRow--;
		}

		return Math.abs(expectedRow - i) + Math.abs(expectedCol - j);
	}

	private int setHammingNumber() {
		int outOfPlace = 0;
		int expected = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (blocks[i][j] < 0 || blocks[i][j] > (N * N) - 1) {
					throw new InvalidParameterException(
							"Invalid entry in matrix " + blocks[i][j]
									+ "is out of bounds");
				}
				if (blocks[i][j] == 0) {
					expected++;
					continue;
				}

				if (this.blocks[i][j] != expected) {
					outOfPlace++;
				}
				expected++;
			}
		}
		return outOfPlace;
	}

	// board dimension N
	public int dimension() {
		return this.N;
	}

	// number of blocks out of place
	public int hamming() {
		return hammingNumber;
	}

	// sum of Manhattan distances between blocks and goal
	public int manhattan() {
		return manhattanNumber;
	}

	// is this board the goal board?
	public boolean isGoal() {

		return hammingNumber == 0 && manhattanNumber == 0;

	}

	// a board obtained by exchanging two adjacent blocks in the same row
	public Board twin() {
		int tiles[][] = new int[N][N];
		tiles = getTilesAsPerCurrentBoard();
		Board twin = null;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N - 1; j++) {
				if ((blocks[i][j] == 0) || (blocks[i][j + 1] == 0)) {
					continue;
				}
				// we got two adjacent non zero blocks in same row
				tiles[i][j] = blocks[i][j + 1];
				tiles[i][j + 1] = blocks[i][j];
				twin = new Board(tiles);
				return twin;
			}
		}

		return twin;
	}

	// does this board equal y?
	public boolean equals(Object that) {
		if (this == that)
			return true;
		if (that == null)
			return false;
		if (this.getClass() != that.getClass())
			return false;
		Board thatRef = (Board) that;

		if ((this.N == thatRef.dimension())
				&& (this.manhattan() == thatRef.manhattan())
				&& (this.hamming() == thatRef.hamming())
				&& (Arrays.deepEquals(this.blocks, thatRef.blocks))) {
			return true;
		}
		return false;
	}

	// all neighboring boards
	public Iterable<Board> neighbors() {

		List<Board> neighbors = new ArrayList<Board>();
		int[][] tiles = getTilesAsPerCurrentBoard();

		if (swapBlankWithRight(tiles, originalBlankRow, originalBlankCol)) {
			neighbors.add(new Board(tiles));
			swapBlankWithLeft(tiles, originalBlankRow, originalBlankCol + 1); // to
																				// get
																				// tiles
																				// to
			// original state
		}
		if (swapBlankWithLeft(tiles, originalBlankRow, originalBlankCol)) {
			neighbors.add(new Board(tiles));
			swapBlankWithRight(tiles, originalBlankRow, originalBlankCol - 1); // to
																				// get
																				// tiles
																				// to
			// original state
		}

		if (swapBlankWithUp(tiles, originalBlankRow, originalBlankCol)) {
			neighbors.add(new Board(tiles));
			swapBlankWithDown(tiles, originalBlankRow - 1, originalBlankCol); // to
																				// get
																				// tiles
																				// to
			// original state
		}

		if (swapBlankWithDown(tiles, originalBlankRow, originalBlankCol)) {
			neighbors.add(new Board(tiles));
			swapBlankWithUp(tiles, originalBlankRow + 1, originalBlankCol); // to
																			// get
																			// tiles
																			// to
			// original state
		}

		return neighbors;
	}

	private boolean swapBlankWithUp(int[][] tiles, int i, int j) {
		if (i == 0) {
			return false;
		} else {
			int temp = tiles[i][j];
			tiles[i][j] = tiles[i - 1][j];
			tiles[i - 1][j] = temp;
			return true;
		}

	}

	private boolean swapBlankWithLeft(int[][] tiles, int i, int j) {
		if (j == 0) {
			return false;
		} else {
			int temp = tiles[i][j];
			tiles[i][j] = tiles[i][j - 1];
			tiles[i][j - 1] = temp;
			return true;
		}
	}

	private boolean swapBlankWithDown(int[][] tiles, int i, int j) {
		if (i == N - 1) {
			return false;
		} else {
			int temp = tiles[i][j];
			tiles[i][j] = tiles[i + 1][j];
			tiles[i + 1][j] = temp;
			return true;
		}

	}

	private boolean swapBlankWithRight(int[][] tiles, int i, int j) {
		if (j == N - 1) {
			return false;
		} else {
			int temp = tiles[i][j];
			tiles[i][j] = tiles[i][j + 1];
			tiles[i][j + 1] = temp;
			return true;
		}

	}

	private int[][] getTilesAsPerCurrentBoard() {
		int[][] tiles = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				tiles[i][j] = blocks[i][j];
			}
		}
		return tiles;
	}

	// string representation of the board (in the output format specified below)
	public String toString() {

		StringBuilder s = new StringBuilder();
		s.append(N + "\n");
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				s.append(String.format("%2d ", blocks[i][j]));
			}
			s.append("\n");
		}
		return s.toString();
	}
}
