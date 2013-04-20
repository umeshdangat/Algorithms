import static org.junit.Assert.assertArrayEquals;

import org.junit.Ignore;
import org.junit.Test;


public class MergeSortWithInversionPairsTest {

	@Ignore
	public void sortRandom() {
		
		Integer[] a = new Integer[10];
		a[0]=6;a[1]=26;a[2]=2;a[3]=34;a[4]=64;
		a[5]=16;a[6]=262;a[7]=22;a[8]=354;a[9]=4;
		MergeSortWithInversionPairs.sort(a );
		
		Integer[] expected = new Integer[10]; 
		expected[0]=2;expected[1]=4;expected[2]=6;expected[3]=16;expected[4]=22;
		expected[5]=26;expected[6]=34;expected[7]=64;expected[8]=262;expected[9]=354;
		
		assertArrayEquals(expected, a);
	}
	
	@Test
	public void sortRandomFour() {
		
		Integer[] a = new Integer[4];
		a[0]=6;a[1]=26;a[2]=2;a[3]=34;
		MergeSortWithInversionPairs.sort(a);
		
		Integer[] expected = new Integer[4]; 
		expected[0]=2;expected[1]=6;expected[2]=26;expected[3]=34;
		
		assertArrayEquals(expected, a);
	}

	@Test
	public void sortFinal() {
		
		Integer[] a = new Integer[10];
		a[0]=88;a[1]=47;a[2]=20;a[3]=64;
		a[4]=89;a[5]=75;a[6]=19;a[7]=78;
		a[8]=65;a[9]=71;
		MergeSortWithInversionPairs.sort(a);
		
		Integer[] expected = new Integer[10]; 
		expected[0]=19;expected[1]=20;expected[2]=47;expected[3]=64;
		expected[4]=65;expected[5]=71;expected[6]=75;expected[7]=78;
		expected[8]=88;expected[9]=89;
		
		assertArrayEquals(expected, a);
	}
	 
}
