import java.util.ArrayList;
import java.util.List;

public class TopologicalSort {

	private boolean marked[][];
	private Stack<GridPixelPosition> topological;
	private int height;
	private int width;

	public TopologicalSort(double[][] energyGrid) {
		this.width = energyGrid[0].length;
		this.height = energyGrid.length;
		marked = new boolean[height][width];
		topological = new Stack<GridPixelPosition>();
		dfs(energyGrid, -1, -1);
	}

	private void dfs(double[][] energyGrid, int x, int y) {

		if (!isSourcePixel(x, y)) {
			marked[x][y] = true;
		}
		for (GridPixelPosition gridPixelPosition : getNeighbors(x, y)) {
			if (!marked[gridPixelPosition.getRow()][gridPixelPosition.getCol()]) {
				dfs(energyGrid, gridPixelPosition.getRow(),
						gridPixelPosition.getCol());
			}
		}
			topological.push(new GridPixelPosition(x, y));
	}

	private List<GridPixelPosition> getNeighbors(int x, int y) {

		if (isSourcePixel(x, y)) {
			return getSourceNeighbors();
		}

		List<GridPixelPosition> neighbors = new ArrayList<GridPixelPosition>();

		if (x + 1 < height) {
			if (y - 1 >= 0) {
				neighbors.add(new GridPixelPosition(x + 1, y - 1));
			}
			neighbors.add(new GridPixelPosition(x+1, y));
			if (y + 1 < width) {
				neighbors.add(new GridPixelPosition(x + 1, y + 1));
			}
		}
		return neighbors;
	}

	private List<GridPixelPosition> getSourceNeighbors() {
		List<GridPixelPosition> neighbors = new ArrayList<GridPixelPosition>();
		for (int i = 0; i < width; i++) {
			neighbors.add(new GridPixelPosition(0, i));
		}
		return neighbors;
	}

	private boolean isSourcePixel(int x, int y) {
		return x == -1 && y == -1;
	}

	public Iterable<GridPixelPosition> getOrder() {
		return topological;
	}

}
