import static org.junit.Assert.*;
import static org.junit.Assert.fail;


import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.when;

public class EnergyGridTest {

	@Mock
	private SeamCarver seamCarverMock;
	
	@Before
	public void initMocks(){
		org.mockito.MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testEnergyGridUpdateSeamRemoval() {
		
		when(seamCarverMock.height()).thenReturn(2);
		when(seamCarverMock.width()).thenReturn(3);
		when(seamCarverMock.energy(0,0)).thenReturn(3.0);
		when(seamCarverMock.energy(1,0)).thenReturn(2.0);
		when(seamCarverMock.energy(2,0)).thenReturn(1.0);
		
		when(seamCarverMock.energy(0,1)).thenReturn(4.0);
		when(seamCarverMock.energy(1,1)).thenReturn(5.0);
		when(seamCarverMock.energy(2,1)).thenReturn(6.0);
		
		EnergyGrid energyGrid = new EnergyGrid(seamCarverMock);
		int[] verticalSeam = new int[2];
		verticalSeam[0] = 2;
		verticalSeam[1] = 0;
		
		
		energyGrid.updateEnergyGridPostVerticalSeamRemoval(verticalSeam);
		assertEquals(5,energyGrid.getEnergy(1, 0),0);
		assertEquals(2,energyGrid.getHeight());
		assertEquals(2,energyGrid.getWidth());
		
		
	}

	@Test
	public void testGetEnergy() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetEnergy() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetHeight() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetHeight() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetWidth() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetWidth() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateEnergyGridPostVerticalSeamRemoval() {
		fail("Not yet implemented");
	}

}
