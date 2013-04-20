import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;


public class TopologicalSortTest {

	@Test
	public void testTopologicalSort2By2() {
		double[][] energyGrid = new double[2][2];
		TopologicalSort topologicalSort = new TopologicalSort(energyGrid);
		
		Iterable<GridPixelPosition> pixels = topologicalSort.getOrder();
		Iterator<GridPixelPosition> it = pixels.iterator();
		assertEquals(new GridPixelPosition(-1,-1),it.next());
		assertEquals(new GridPixelPosition(0,1),it.next());
		assertEquals(new GridPixelPosition(0,0),it.next());
		assertEquals(new GridPixelPosition(1,1),it.next());
		assertEquals(new GridPixelPosition(1,0),it.next());
		
		assertEquals(false,it.hasNext());
	}
	
	@Test
	public void testTopologicalSort3By2() {
		double[][] energyGrid = new double[3][2];
		TopologicalSort topologicalSort = new TopologicalSort(energyGrid);
		
		Iterable<GridPixelPosition> pixels = topologicalSort.getOrder();
		Iterator<GridPixelPosition> it = pixels.iterator();
		assertEquals(new GridPixelPosition(-1,-1),it.next());
		assertEquals(new GridPixelPosition(0,1),it.next());
		assertEquals(new GridPixelPosition(0,0),it.next());
		assertEquals(new GridPixelPosition(1,1),it.next());
		assertEquals(new GridPixelPosition(1,0),it.next());
		assertEquals(new GridPixelPosition(2,1),it.next());
		assertEquals(new GridPixelPosition(2,0),it.next());
		
		assertEquals(false,it.hasNext());
	}
	
	
	@Test
	public void testTopologicalSort3By3() {
		double[][] energyGrid = new double[3][3];
		TopologicalSort topologicalSort = new TopologicalSort(energyGrid);
		
		Iterable<GridPixelPosition> pixels = topologicalSort.getOrder();
		Iterator<GridPixelPosition> it = pixels.iterator();
		assertEquals(new GridPixelPosition(-1,-1),it.next());
		assertEquals(new GridPixelPosition(0,2),it.next());
		assertEquals(new GridPixelPosition(0,1),it.next());
		assertEquals(new GridPixelPosition(0,0),it.next());
		assertEquals(new GridPixelPosition(1,2),it.next());
		assertEquals(new GridPixelPosition(1,1),it.next());
		assertEquals(new GridPixelPosition(2,2),it.next());
		assertEquals(new GridPixelPosition(1,0),it.next());
		assertEquals(new GridPixelPosition(2,1),it.next());
		assertEquals(new GridPixelPosition(2,0),it.next());
		assertEquals(false,it.hasNext());
	}
	
	@Test
	public void testTopologicalSort3By4() {
		double[][] energyGrid = new double[3][4];
		TopologicalSort topologicalSort = new TopologicalSort(energyGrid);
		
		Iterable<GridPixelPosition> pixels = topologicalSort.getOrder();
		Iterator<GridPixelPosition> it = pixels.iterator();
		assertEquals(new GridPixelPosition(-1,-1),it.next());
		assertEquals(new GridPixelPosition(0,3),it.next());
		assertEquals(new GridPixelPosition(0,2),it.next());
		assertEquals(new GridPixelPosition(1,3),it.next());
		assertEquals(new GridPixelPosition(0,1),it.next());
		assertEquals(new GridPixelPosition(1,2),it.next());
		assertEquals(new GridPixelPosition(2,3),it.next());
		assertEquals(new GridPixelPosition(0,0),it.next());
		assertEquals(new GridPixelPosition(1,1),it.next());
		assertEquals(new GridPixelPosition(2,2),it.next());
		assertEquals(new GridPixelPosition(1,0),it.next());
		assertEquals(new GridPixelPosition(2,1),it.next());
		assertEquals(new GridPixelPosition(2,0),it.next());
		assertEquals(false,it.hasNext());
	}


}
