public class Solver {

	private final MinPQ<SearchNode> minPq;
	private final MinPQ<SearchNode> minTwinPq;
	private final boolean isSolvable;
	private final SearchNode goalSearchNode;

	private final class SearchNode implements Comparable<SearchNode> {

		private final Board currentBoard;
		private final SearchNode previousSearchNode;
		private final int moves;

		SearchNode(Board currentBoard, int movesSoFar,
				SearchNode previousSerchNode) {
			this.currentBoard = currentBoard;
			this.previousSearchNode = previousSerchNode;
			this.moves = movesSoFar;
		}

		private int getMoves() {
			return moves;
		}

		private SearchNode getPreviousSearchNode() {
			return previousSearchNode;
		}

		private Board getCurrentBoard() {
			return currentBoard;
		}

		@Override
		public int compareTo(SearchNode that) {
			int thisPriority = this.getMoves()
					+ this.getCurrentBoard().manhattan();
			int thatPriority = that.getMoves()
					+ that.getCurrentBoard().manhattan();

			return thisPriority - thatPriority;
		}

	}

	// find a solution to the initial board (using the A* algorithm)
	public Solver(Board initial) {
		boolean isSolvable = false;
		SearchNode tempSearchNode = null;

		minPq = new MinPQ<SearchNode>();
		minTwinPq = new MinPQ<SearchNode>();

		minPq.insert(new SearchNode(initial, 0, null));
		minTwinPq.insert(new SearchNode(initial.twin(), 0, null));

		while (!minPq.isEmpty()) {
			SearchNode currentSearchNode = minPq.delMin();
			Board currentBoard = currentSearchNode.getCurrentBoard();

			SearchNode currentTwinSearchNode = minTwinPq.delMin();
			Board currentTwinBoard = currentTwinSearchNode.getCurrentBoard();

			// twin is solvable so the actual board has no solution
			if (currentTwinBoard.isGoal()) {
				isSolvable = false;
				break;
			} else {
				// add neighbors for twin to see if it is solvable
				for (Board neighborOfTwin : currentTwinBoard.neighbors()) {
					if ((currentTwinSearchNode.getPreviousSearchNode() == null)
							|| (!currentTwinSearchNode.getPreviousSearchNode()
									.getCurrentBoard().equals(neighborOfTwin))) {
						minTwinPq.insert(new SearchNode(neighborOfTwin,
								currentTwinSearchNode.getMoves() + 1,
								currentTwinSearchNode));
					}
				}
			}

			// actual board is solved
			if (currentBoard.isGoal()) {
				isSolvable = true;
				tempSearchNode = currentSearchNode;
				break;
			} else {
				// add neighbors
				for (Board neighbor : currentBoard.neighbors()) {
					if ((currentSearchNode.getPreviousSearchNode() == null)
							|| (!currentSearchNode.getPreviousSearchNode()
									.getCurrentBoard().equals(neighbor))) {
						minPq.insert(new SearchNode(neighbor, currentSearchNode
								.getMoves() + 1, currentSearchNode));
					}
				}
			}
		}

		this.isSolvable = isSolvable;
		this.goalSearchNode = tempSearchNode;

	}

	// is the initial board solvable?
	public boolean isSolvable() {
		return this.isSolvable;
	}

	// min number of moves to solve initial board; -1 if no solution
	public int moves() {
		if (!isSolvable()) {
			return -1;
		} else {
			return goalSearchNode.getMoves();
		}
	}

	// sequence of boards in a shortest solution; null if no solution
	public Iterable<Board> solution() {
		if (isSolvable()) {
			SearchNode currentSearchNode = this.goalSearchNode;
			Stack<Board> boards = new Stack<Board>();

			while (currentSearchNode != null) {
				boards.push(currentSearchNode.getCurrentBoard());
				currentSearchNode = currentSearchNode.getPreviousSearchNode();

			}

			return boards;

		} else {
			return null;
		}
	}

	// solve a slider puzzle (given below)
	public static void main(String[] args) {
		// create initial board from file
		In in = new In(args[0]);
		int N = in.readInt();
		int[][] blocks = new int[N][N];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				blocks[i][j] = in.readInt();
		Board initial = new Board(blocks);

		// solve the puzzle
		Solver solver = new Solver(initial);

		// print solution to standard output
		if (!solver.isSolvable())
			StdOut.println("No solution possible");
		else {
			StdOut.println("Minimum number of moves = " + solver.moves());
			for (Board board : solver.solution())
				StdOut.println(board);
		}
	}

}