public class AcyclicShortestPath {

	private DistanceGrid distanceGrid;
	private boolean verticalState;
	private boolean horizontalState;

	public AcyclicShortestPath(/*EnergyGrid energyGrid*/) {
		verticalState = false;
		horizontalState = false;
	}

	public int[] getShortestVerticalPath(EnergyGrid energyGrid) {
		if (verticalState == false) {
			verticalState = true;
			horizontalState = false;
			distanceGrid = new DistanceGrid(energyGrid);
		}
		return distanceGrid.getShortestPath();
	}

	public int[] getShortestHorizontalPath(EnergyGrid energyGrid) {
		if (horizontalState == false) {
			horizontalState = true;
			verticalState = false;
			distanceGrid = new DistanceGrid(energyGrid.transpose());
		}
		return distanceGrid.getShortestPath();
	}

	public double reCalculateVerticalShortestPathGraph(EnergyGrid energyGrid,
			int[] verticalSeam) {
		if (verticalState) {
			distanceGrid
					.reduceGridWidthAndRecalculate(energyGrid, verticalSeam);
		} else {
			distanceGrid = new DistanceGrid(energyGrid);
			verticalState = true;
			horizontalState = false;
		}
		return 0;
	}

	public double reCalculateHorizontalShortestPathGraph(EnergyGrid energyGrid,
			int[] horizontalSeam) {
		EnergyGrid tranposedEnegryGrid = energyGrid.transpose();
		if (horizontalState) {
			distanceGrid.reduceGridWidthAndRecalculate(tranposedEnegryGrid,
					horizontalSeam);
		} else {
			distanceGrid = new DistanceGrid(tranposedEnegryGrid);
			horizontalState = true;
			verticalState = false;
		}
		return 0;
	}

}
