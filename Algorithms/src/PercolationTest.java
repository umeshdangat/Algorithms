import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

public class PercolationTest {

	final int gridSize = 5;
	final int sizeof1DUF = gridSize * gridSize + 2;
	

	@Test
	public void testPercolationForVirtualConnectivity() {

		Percolation percolation = new Percolation(gridSize);

		for (int col = 1; col <= gridSize; col++) {
			assertFalse(percolation.getWeightedUnionUF().connected(0, rowColIn2DTo1D(1, col)));
			assertFalse(percolation.getWeightedUnionUF().connected(sizeof1DUF-1, rowColIn2DTo1D(gridSize, col)));
		}
	}

	@Test
	public void testOpenForOpenSite() {
		Percolation percolation = new Percolation(gridSize);
		percolation.open(2, 3);
		assertTrue(percolation.isOpen(2, 3));
	}

	@Test
	public void testOpenForConnectedWithNeighbors() {
		Percolation percolation = new Percolation(gridSize);
		percolation.open(2, 3);
		percolation.open(3, 3);
		percolation.open(3, 4);
		
		int id1 = rowColIn2DTo1D(2, 3);
		int id2 = rowColIn2DTo1D(3, 3);
		int id3 = rowColIn2DTo1D(3, 4);
		
		assertTrue(percolation.getWeightedUnionUF().connected(id1, id2));
		assertTrue(percolation.getWeightedUnionUF().connected(id1, id3));
	}
	
	@Test
	public void testIsOpenAfterOpeningASite() {
		Percolation percolation  = new Percolation(gridSize);
		percolation.open(3, 5);
		assertTrue(percolation.isOpen(3, 5));
	}

	@Test
	public void testIsFullForFalse() {
		Percolation percolation = new Percolation(gridSize);
		percolation.open(2, 3);
		percolation.open(3, 3);
		percolation.open(3, 4);
		assertFalse(percolation.isFull(3, 4));
		
	}
	
	
	@Test
	public void testIsFullForTrue() {
		Percolation percolation = new Percolation(gridSize);
		percolation.open(1, 3);
		percolation.open(2, 3);
		percolation.open(2, 2);
		percolation.open(3, 2);
		assertTrue(percolation.isFull(3, 2));
		
	}

	@Test
	public void testPercolatesAtInitialization() {
		
		Percolation percolation = new Percolation(gridSize);
		assertFalse(percolation.percolates());
	}
	
	@Test
	public void testPercolates1() {
		Percolation percolation = new Percolation(gridSize);
		percolation.open(1, 2);
		percolation.open(2, 2);
		percolation.open(2, 3);
		percolation.open(3, 3);
		percolation.open(4, 3);
		percolation.open(5, 3);
		assertTrue(percolation.percolates());
		
	}
	
	
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testIndexOutOfBoundsLowRow(){
		Percolation percolation = new Percolation(gridSize);
		percolation.open(0, 2);
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testIndexOutOfBoundsHighRow(){
		Percolation percolation = new Percolation(gridSize);
		percolation.open(6, 2);
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testIndexOutOfBoundsLowCol(){
		Percolation percolation = new Percolation(gridSize);
		percolation.open(4, 0);
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testIndexOutOfBoundsHighCol(){
		Percolation percolation = new Percolation(gridSize);
		percolation.open(3, 6);
	}
	
	@Test
	public void testBackwash(){
		Percolation percolation = new Percolation(7);
		percolation.open(6, 1);
		percolation.open(7, 1);
		percolation.open(7, 2);
		percolation.open(7, 4);
		percolation.open(1, 1);
		percolation.open(1, 5);
		percolation.open(2, 5);
		percolation.open(3, 5);
		percolation.open(4, 5);
		percolation.open(5, 5);
		percolation.open(6, 5);
		assertFalse(percolation.isFull(6, 1));
		percolation.open(7, 5);
		assertFalse(percolation.isFull(6, 1));
		
	}

	private int rowColIn2DTo1D(int row, int col) {
		validateIndices(row, col);
		return (row - 1) * gridSize + col;
	}

	private void validateIndices(int i, int j) {
		if (i <= 0 || i > gridSize)
			throw new IndexOutOfBoundsException("row index i out of bounds");
		if (j <= 0 || j > gridSize)
			throw new IndexOutOfBoundsException("column index j out of bounds");
	}
}
