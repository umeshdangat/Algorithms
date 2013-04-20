import static org.junit.Assert.*;

import org.junit.Test;


public class SolverTest {

	@Test
	public void testSolverForOneMove() {
		int[][] blocks = new int[3][3];
		blocks[0][0]=1;blocks[0][1]=2;blocks[0][2]=3;
		blocks[1][0]=4;blocks[1][1]=5;blocks[1][2]=6;
		blocks[2][0]=7;blocks[2][1]=0;blocks[2][2]=8;
		Board initial = new Board(blocks);
		
		Solver underTest = new Solver(initial);
		assertEquals(1,underTest.moves());
		assertEquals(true,underTest.isSolvable());
	}
	
	@Test
	public void testSolverForSevenMove() {
		int[][] blocks = new int[3][3];
		blocks[0][0]=1;blocks[0][1]=2;blocks[0][2]=3;
		blocks[1][0]=0;blocks[1][1]=7;blocks[1][2]=6;
		blocks[2][0]=5;blocks[2][1]=4;blocks[2][2]=8;
		Board initial = new Board(blocks);
		
		Solver underTest = new Solver(initial);
		assertEquals(7,underTest.moves());
		assertEquals(true,underTest.isSolvable());
	}

	@Test
	public void testSolverForUnsolvable() {
		int[][] blocks = new int[3][3];
		blocks[0][0]=1;blocks[0][1]=2;blocks[0][2]=3;
		blocks[1][0]=4;blocks[1][1]=6;blocks[1][2]=5;
		blocks[2][0]=7;blocks[2][1]=8;blocks[2][2]=0;
		Board initial = new Board(blocks);
		
		Solver underTest = new Solver(initial);
		assertEquals(-1,underTest.moves());
		assertEquals(false,underTest.isSolvable());
	}

}
