
public class Node {
	
	private int row;
	private int col;
	
	public Node(int row1, int col1) {
		this.row = row1;
		this.col = col1;
	}
	
	@Override
	public boolean equals(Object o) { 
		Node n = (Node) o;
		if( (this.row == n.row) && (this.col == n.col)) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	@Override
	public int hashCode() {
		return row*10 + col; 
	}

}
