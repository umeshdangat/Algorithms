class GridPixelPosition {
	private int col;
	private int row;

	GridPixelPosition(int x, int y) {
		this.col = y;
		this.row = x;
	}

	int getRow() {
		return row;
	}

	int getCol() {
		return col;
	}

	@Override
	public boolean equals(Object that) {
		if (this == that) {
			return true;
		}
		if (that == null) {
			return false;
		}
		if (this.getClass() != that.getClass()) {
			return false;
		}

		GridPixelPosition other = (GridPixelPosition) that;
		if (this.col == other.col && this.row == other.row) {
			return true;
		}
		return false;
	}
	
	@Override
	public int hashCode(){
		int hash = 17;
		hash = 31*hash + ((Integer)row).hashCode();
		hash = 31*hash + ((Integer)col).hashCode();
		return hash;
	}
}
