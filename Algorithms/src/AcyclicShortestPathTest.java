import static org.junit.Assert.*;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class AcyclicShortestPathTest {

	@Test
	public void testAcyclicShortestPathFor3By2() {
		
		Picture picture = new Picture(2,3);
		SeamCarver sc = new SeamCarver(picture );
		EnergyGrid energyGrid = new EnergyGrid(sc);
		energyGrid.setEnergy(0, 0, 10);
		energyGrid.setEnergy(1, 0, 5);
		energyGrid.setEnergy(1, 1, 30);
		energyGrid.setEnergy(2, 0, 15);
		energyGrid.setEnergy(2, 1, 3);

		int[] expected = new int[3];
		expected[0] = 0;
		expected[1] = 0;
		expected[2] = 1;
		AcyclicShortestPath acylicShortestPath = new AcyclicShortestPath();
		assertArrayEquals(expected,
				acylicShortestPath.getShortestVerticalPath(energyGrid));
	}

	@Test
	public void testAcyclicShortestPathFor3By4() {
		
		Picture picture = new Picture(4,3);
		SeamCarver sc = new SeamCarver(picture );
		EnergyGrid energyGrid = new EnergyGrid(sc);
		energyGrid.setEnergy(0, 0, 10);
		energyGrid.setEnergy(0, 1, 12);
		energyGrid.setEnergy(0, 2, 15);
		energyGrid.setEnergy(0, 3, 8);
		
		energyGrid.setEnergy(1, 0, 80);
		energyGrid.setEnergy(1, 1, 50);
		energyGrid.setEnergy(1, 2, 22);
		energyGrid.setEnergy(1, 3, 27);
		
		energyGrid.setEnergy(2, 0, 5);
		energyGrid.setEnergy(2, 1, 10);
		energyGrid.setEnergy(2, 2, 7);
		energyGrid.setEnergy(2, 3, 13);
		
		/*double[][] energyGrid = new double[3][4];
		energyGrid[0][0] = 10;
		energyGrid[0][1] = 12;
		energyGrid[0][2] = 15;
		energyGrid[0][3] = 8;

		energyGrid[1][0] = 80;
		energyGrid[1][1] = 50;
		energyGrid[1][2] = 22;
		energyGrid[1][3] = 27;

		energyGrid[2][0] = 5;
		energyGrid[2][1] = 10;
		energyGrid[2][2] = 7;
		energyGrid[2][3] = 13;
*/		int[] expected = new int[3];
		expected[0] = 1;
		expected[1] = 2;
		expected[2] = 2;
		AcyclicShortestPath acylicShortestPath = new AcyclicShortestPath();
		assertArrayEquals(expected,
				acylicShortestPath.getShortestVerticalPath(energyGrid));
	}

	@Test
	public void testAcyclicShortestPathFor6By4() {
		
		Picture picture = new Picture(4,6);
		SeamCarver sc = new SeamCarver(picture );
		EnergyGrid energyGrid = new EnergyGrid(sc);
		
		energyGrid.setEnergy(0, 0, 10);
		energyGrid.setEnergy(0, 1, 20);
		energyGrid.setEnergy(0, 2, 15);
		energyGrid.setEnergy(0, 3, 7);
		
		energyGrid.setEnergy(1, 0, 8);
		energyGrid.setEnergy(1, 1, 21);
		energyGrid.setEnergy(1, 2, 22);
		energyGrid.setEnergy(1, 3, 23);
		
		energyGrid.setEnergy(2, 0, 12);
		energyGrid.setEnergy(2, 1, 7);
		energyGrid.setEnergy(2, 2, 14);
		energyGrid.setEnergy(2, 3, 6);
		
		energyGrid.setEnergy(3, 0, 13);
		energyGrid.setEnergy(3, 1, 14);
		energyGrid.setEnergy(3, 2, 16);
		energyGrid.setEnergy(3, 3, 9);
		
		energyGrid.setEnergy(4, 0, 20);
		energyGrid.setEnergy(4, 1, 18);
		energyGrid.setEnergy(4, 2, 5);
		energyGrid.setEnergy(4, 3, 8);
		
		
		energyGrid.setEnergy(5, 0, 10);
		energyGrid.setEnergy(5, 1, 4);
		energyGrid.setEnergy(5, 2, 2);
		energyGrid.setEnergy(5, 3, 1);
		
		/*double[][] energyGrid = new double[6][4];
		energyGrid[0][0] = 10;
		energyGrid[0][1] = 20;
		energyGrid[0][2] = 15;
		energyGrid[0][3] = 7;

		energyGrid[1][0] = 8;
		energyGrid[1][1] = 21;
		energyGrid[1][2] = 22;
		energyGrid[1][3] = 23;

		energyGrid[2][0] = 12;
		energyGrid[2][1] = 7;
		energyGrid[2][2] = 14;
		energyGrid[2][3] = 6;

		energyGrid[3][0] = 13;
		energyGrid[3][1] = 14;
		energyGrid[3][2] = 16;
		energyGrid[3][3] = 9;
		
		energyGrid[4][0] = 20;
		energyGrid[4][1] = 18;
		energyGrid[4][2] = 5;
		energyGrid[4][3] = 8;
		
		energyGrid[5][0] = 10;
		energyGrid[5][1] = 4;
		energyGrid[5][2] = 2;
		energyGrid[5][3] = 1;*/
		int[] expected = new int[6];
		expected[5] = 3;
		expected[4] = 2;
		expected[3] = 1;
		expected[2] = 1;
		expected[1] = 0;
		expected[0] = 0;
		AcyclicShortestPath acylicShortestPath = new AcyclicShortestPath();
		assertArrayEquals(expected,
				acylicShortestPath.getShortestVerticalPath(energyGrid));
	}
	
	
	@Test
	public void testAcyclicShortestPathFor5By6() {
		
		Picture picture = new Picture(6,5);
		SeamCarver sc = new SeamCarver(picture );
		EnergyGrid energyGrid = new EnergyGrid(sc);
		
		energyGrid.setEnergy(0, 0, 195705);
		energyGrid.setEnergy(0, 1, 195705);
		energyGrid.setEnergy(0, 2, 195705);
		energyGrid.setEnergy(0, 3, 195705);
		energyGrid.setEnergy(0, 4, 195705);
		energyGrid.setEnergy(0, 5, 195705);
		energyGrid.setEnergy(1, 0, 195705);
		energyGrid.setEnergy(1, 1, 23346);
		energyGrid.setEnergy(1, 2, 51304);
		energyGrid.setEnergy(1, 3, 31519);
		energyGrid.setEnergy(1, 4, 55112);
		energyGrid.setEnergy(1, 5, 195705);
		
		energyGrid.setEnergy(2, 0, 195705);
		energyGrid.setEnergy(2, 1, 47908);
		energyGrid.setEnergy(2, 2, 61346);
		energyGrid.setEnergy(2, 3, 35919);
		energyGrid.setEnergy(2, 4, 38887);
		energyGrid.setEnergy(2, 5, 195705);
		
		
		energyGrid.setEnergy(3, 0, 195705);
		energyGrid.setEnergy(3, 1, 31400);
		energyGrid.setEnergy(3, 2, 37927);
		energyGrid.setEnergy(3, 3, 14437);
		energyGrid.setEnergy(3, 4, 63076);
		energyGrid.setEnergy(3, 5, 195705);
		
		energyGrid.setEnergy(4, 0, 195705);
		energyGrid.setEnergy(4, 1, 195705);
		energyGrid.setEnergy(4, 2, 195705);
		energyGrid.setEnergy(4, 3, 195705);
		energyGrid.setEnergy(4, 4, 195705);
		energyGrid.setEnergy(4, 5, 195705);
		
		
		int[] expected = new int[5];
		expected[0] = 2;
		expected[1] = 3;
		expected[2] = 3;
		expected[3] = 3;
		expected[4] = 2;
		AcyclicShortestPath acylicShortestPath = new AcyclicShortestPath(
				);
		assertArrayEquals(expected,
				acylicShortestPath.getShortestVerticalPath(energyGrid));
	}
	
	@Test
	public void testAcyclicShortestPathWithAllEqualDistances(){
		Picture picture = new Picture(2,2);
		SeamCarver sc = new SeamCarver(picture );
		EnergyGrid energyGrid = new EnergyGrid(sc);
		energyGrid.setEnergy(0, 0, 195705);
		energyGrid.setEnergy(0, 1, 195705);
		energyGrid.setEnergy(1, 0, 195705);
		energyGrid.setEnergy(1, 1, 195705);
		/*double[][] energyGrid = new double[2][2];
		energyGrid[0][0]=195705.0;
		energyGrid[0][1]=195705.0;
		energyGrid[1][0]=195705.0;
		energyGrid[1][1]=195705.0;*/
		int[] expected = new int[2];
		expected[0] = 0;
		expected[0] = 0;
		AcyclicShortestPath acylicShortestPath = new AcyclicShortestPath();
		assertArrayEquals(expected,
				acylicShortestPath .getShortestVerticalPath(energyGrid));
	}
	
	
	@Test
	public void testAcyclicShortestPathFor5By6WithOneRemoval() {
		
		Picture picture = new Picture(6,5);
		SeamCarver sc = new SeamCarver(picture );
		EnergyGrid energyGrid = new EnergyGrid(sc);
		
		energyGrid.setEnergy(0, 0, 195705);
		energyGrid.setEnergy(0, 1, 195705);
		energyGrid.setEnergy(0, 2, 195705);
		energyGrid.setEnergy(0, 3, 195705);
		energyGrid.setEnergy(0, 4, 195705);
		energyGrid.setEnergy(0, 5, 195705);
		
		energyGrid.setEnergy(1, 0, 195705);
		energyGrid.setEnergy(1, 1, 23346);
		energyGrid.setEnergy(1, 2, 51304);
		energyGrid.setEnergy(1, 3, 31519);
		energyGrid.setEnergy(1, 4, 55112);
		energyGrid.setEnergy(1, 5, 195705);
		
		energyGrid.setEnergy(2, 0, 195705);
		energyGrid.setEnergy(2, 1, 47908);
		energyGrid.setEnergy(2, 2, 61346);
		energyGrid.setEnergy(2, 3, 35919);
		energyGrid.setEnergy(2, 4, 38887);
		energyGrid.setEnergy(2, 5, 195705);
		
		
		energyGrid.setEnergy(3, 0, 195705);
		energyGrid.setEnergy(3, 1, 31400);
		energyGrid.setEnergy(3, 2, 37927);
		energyGrid.setEnergy(3, 3, 14437);
		energyGrid.setEnergy(3, 4, 63076);
		energyGrid.setEnergy(3, 5, 195705);
		
		energyGrid.setEnergy(4, 0, 195705);
		energyGrid.setEnergy(4, 1, 195705);
		energyGrid.setEnergy(4, 2, 195705);
		energyGrid.setEnergy(4, 3, 195705);
		energyGrid.setEnergy(4, 4, 195705);
		energyGrid.setEnergy(4, 5, 195705);
		
		int[] expected = new int[5];
		expected[0] = 2;
		expected[1] = 3;
		expected[2] = 3;
		expected[3] = 3;
		expected[4] = 2;
		AcyclicShortestPath acylicShortestPath = new AcyclicShortestPath(
				);
		assertArrayEquals(expected,
				acylicShortestPath.getShortestVerticalPath(energyGrid));
		
		acylicShortestPath.reCalculateVerticalShortestPathGraph(energyGrid,expected);
		int[] expectedAfterRemoval = new int[5];
		expectedAfterRemoval[0] = 0;
		expectedAfterRemoval[1] = 1;
		expectedAfterRemoval[2] = 1;
		expectedAfterRemoval[3] = 1;
		expectedAfterRemoval[4] = 0;
		assertArrayEquals(expected,
				acylicShortestPath.getShortestVerticalPath(energyGrid));
		
	}

}
