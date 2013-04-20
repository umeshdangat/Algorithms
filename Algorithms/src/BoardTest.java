import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.security.InvalidParameterException;

import org.junit.Test;


public class BoardTest {

	@Test(expected=InvalidParameterException.class)
	public void testBoardForInvalidParametersInBlocksLargerNumbers() {
		int[][] blocks = new int[3][3];
		blocks[0][0]=8;blocks[0][1]=7;blocks[0][2]=9;
		blocks[1][0]=5;blocks[1][1]=4;blocks[1][2]=3;
		blocks[2][0]=2;blocks[2][1]=1;blocks[2][2]=0;
		Board board = new Board(blocks);
	}
	
	@Test(expected=InvalidParameterException.class)
	public void testBoardForInvalidParametersInBlocksNegativeNumbers() {
		int[][] blocks = new int[3][3];
		blocks[0][0]=8;blocks[0][1]=7;blocks[0][2]=-9;
		blocks[1][0]=5;blocks[1][1]=4;blocks[1][2]=3;
		blocks[2][0]=2;blocks[2][1]=1;blocks[2][2]=0;
		Board board = new Board(blocks);
	}
	
	@Test(expected=InvalidParameterException.class)
	public void testBoardForInvalidParametersInBlocksDuplicateNumbers() {
		int[][] blocks = new int[3][3];
		blocks[0][0]=8;blocks[0][1]=7;blocks[0][2]=8;
		blocks[1][0]=5;blocks[1][1]=4;blocks[1][2]=3;
		blocks[2][0]=2;blocks[2][1]=1;blocks[2][2]=0;
		Board board = new Board(blocks);
	}

	@Test
	public void testDimension() {
		int[][] blocks = new int[3][3];
		blocks[0][0]=8;blocks[0][1]=7;blocks[0][2]=8;
		blocks[1][0]=5;blocks[1][1]=4;blocks[1][2]=3;
		blocks[2][0]=2;blocks[2][1]=1;blocks[2][2]=0;
		Board board = new Board(blocks);
		assertEquals(3, board.dimension());
	}

	
	@Test
	public void testHammingForThreeOutOfPlace() {
		int[][] blocks = new int[3][3];
		blocks[0][0]=0;blocks[0][1]=2;blocks[0][2]=4;
		blocks[1][0]=3;blocks[1][1]=5;blocks[1][2]=6;
		blocks[2][0]=7;blocks[2][1]=8;blocks[2][2]=1;
		
		Board board = new Board(blocks);
		assertEquals(3, board.hamming());
	}
	
	@Test
	public void testHammingForFiveOutOfPlace() {
		int[][] blocks = new int[3][3];
		blocks[0][0]=8;
		blocks[0][1]=1;
		blocks[0][2]=3;
		blocks[1][0]=4;
		blocks[1][1]=0;
		blocks[1][2]=2;
		blocks[2][0]=7;
		blocks[2][1]=6;
		blocks[2][2]=5;
		Board board = new Board(blocks);
		assertEquals(5, board.hamming());
	}
	
	@Test
	public void testHammingForAllOutOfPlace() {
		int[][] blocks = new int[3][3];
		blocks[0][0]=8;blocks[0][1]=7;blocks[0][2]=6;
		blocks[1][0]=5;blocks[1][1]=4;blocks[1][2]=3;
		blocks[2][0]=0;blocks[2][1]=3;blocks[2][2]=1;
		Board board = new Board(blocks);
		assertEquals(8, board.hamming());
	}

	@Test
	public void testHammingForNoneOutOfPlace() {
		int[][] blocks = new int[3][3];
		blocks[0][0]=1;blocks[0][1]=2;blocks[0][2]=3;
		blocks[1][0]=4;blocks[1][1]=5;blocks[1][2]=6;
		blocks[2][0]=7;blocks[2][1]=8;blocks[2][2]=0;
		Board board = new Board(blocks);
		assertEquals(0, board.hamming());
	}
	
	@Test
	public void testManhattanForZeroDistance() {
		int[][] blocks = new int[8][8];
		short counter =1;
		for(int i=0;i<8;i++){
			for(int j=0; j<8;j++){
				blocks[i][j] = counter++;
			}
		}
		blocks[7][7] = 0;
		Board board = new Board(blocks);
		assertEquals(0, board.manhattan());
	}
	
	@Test
	public void testManhattanForSomeDistance() {
		int[][] blocks = new int[3][3];
		blocks[0][0]=8;
		blocks[0][1]=1;
		blocks[0][2]=3;
		blocks[1][0]=4;
		blocks[1][1]=0;
		blocks[1][2]=2;
		blocks[2][0]=7;
		blocks[2][1]=6;
		blocks[2][2]=5;
		Board board = new Board(blocks);
		assertEquals(10, board.manhattan());
	}
	
	@Test
	public void testManhattanForReverseOrder() {
		int[][] blocks = new int[3][3];
		blocks[0][0]=8;blocks[0][1]=7;blocks[0][2]=6;
		blocks[1][0]=5;blocks[1][1]=4;blocks[1][2]=3;
		blocks[2][0]=2;blocks[2][1]=1;blocks[2][2]=0;
		Board board = new Board(blocks);
		assertEquals(16, board.manhattan());
	}
	
	@Test(expected=InvalidParameterException.class)
	public void testManhattanForInvalidInputs() {
		int[][] blocks = new int[3][3];
		blocks[0][0]=8;blocks[0][1]=7;blocks[0][2]=9;
		blocks[1][0]=5;blocks[1][1]=4;blocks[1][2]=3;
		blocks[2][0]=2;blocks[2][1]=1;blocks[2][2]=0;
		Board board = new Board(blocks);
		board.manhattan();
	}

	@Test
	public void testIsGoal() {
		int[][] blocks = new int[3][3];
		short count=0;
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				count++;
				blocks[i][j]= count;
			}
		}
		blocks[2][2] = 0;
		Board board = new Board(blocks);
		assertEquals(true, board.isGoal());
	}

	@Test
	public void testTwinFirsCol() {
		int[][] blocks1 = new int[3][3];
		blocks1[0][0]=8;blocks1[0][1]=7;blocks1[0][2]=6;
		blocks1[1][0]=5;blocks1[1][1]=4;blocks1[1][2]=3;
		blocks1[2][0]=2;blocks1[2][1]=0;blocks1[2][2]=1;
		Board boardUnderTest = new Board(blocks1);
		
		blocks1[0][0]= 7;
		blocks1[0][1]= 8;
		
		Board expected = new Board(blocks1);
		
		
		assertEquals(expected.toString(), boardUnderTest.twin().toString());
	}
	
	@Test
	public void testTwinLastCol() {
		int[][] blocks1 = new int[3][3];
		blocks1[0][0]=8;blocks1[0][1]=0;blocks1[0][2]=6;
		blocks1[1][0]=5;blocks1[1][1]=4;blocks1[1][2]=3;
		blocks1[2][0]=2;blocks1[2][1]=7;blocks1[2][2]=1;
		Board boardUnderTest = new Board(blocks1);
		
		blocks1[1][0]= 4;
		blocks1[1][1]= 5;
		
		Board expected = new Board(blocks1);
		
		
		assertEquals(expected.toString(), boardUnderTest.twin().toString());
	}
	
	@Test
	public void testTwinLastColZero() {
		int[][] blocks1 = new int[3][3];
		blocks1[0][0]=8;blocks1[0][1]=6;blocks1[0][2]=0;
		blocks1[1][0]=5;blocks1[1][1]=4;blocks1[1][2]=3;
		blocks1[2][0]=2;blocks1[2][1]=7;blocks1[2][2]=1;
		Board boardUnderTest = new Board(blocks1);
		
		blocks1[0][0]= 6;
		blocks1[0][1]= 8;
		
		Board expected = new Board(blocks1);
		
		
		assertEquals(expected.toString(), boardUnderTest.twin().toString());
	}

	@Test
	public void testEqualsObjectTrue() {
		int[][] blocks1 = new int[3][3];
		blocks1[0][0]=8;blocks1[0][1]=7;blocks1[0][2]=6;
		blocks1[1][0]=5;blocks1[1][1]=4;blocks1[1][2]=3;
		blocks1[2][0]=2;blocks1[2][1]=1;blocks1[2][2]=0;
		Board board1 = new Board(blocks1);
		Board board2 = new Board(blocks1);
		assertTrue(board1.equals(board2));
		
	}
	
	@Test
	public void testEqualsObjectFalse() {
		int[][] blocks1 = new int[3][3];
		int[][] blocks2 = new int[3][3];
		blocks1[0][0]=8;blocks1[0][1]=7;blocks1[0][2]=6;
		blocks1[1][0]=5;blocks1[1][1]=4;blocks1[1][2]=3;
		blocks1[2][0]=2;blocks1[2][1]=1;blocks1[2][2]=0;
		
		blocks2[0][0]=8;blocks2[0][1]=7;blocks2[0][2]=6;
		blocks2[1][0]=5;blocks2[1][1]=4;blocks2[1][2]=3;
		blocks2[2][0]=2;blocks2[2][1]=0;blocks2[2][2]=1;
		Board board1 = new Board(blocks1);
		Board board2 = new Board(blocks2);
		assertFalse(board1.equals(board2));
		
	}

	@Test
	public void testFourNeighbors() {
		System.out.println("testFourNeighbors");
		int[][] blocks = new int[3][3];
		blocks[0][0]=8;blocks[0][1]=7;blocks[0][2]=6;
		blocks[1][0]=5;blocks[1][1]=0;blocks[1][2]=3;
		blocks[2][0]=2;blocks[2][1]=1;blocks[2][2]=4;
		Board board = new Board(blocks);
		
		int count=0;
		System.out.println("original "+ board.toString());
		System.out.println("neighbors");

		for(Board neighbor: board.neighbors()){
			System.out.println(neighbor.toString());
			count++;
		}
		assertEquals(4,count );
	}
	
	@Test
	public void testThreeNeighbors() {
		System.out.println("testThreeNeighbors");
		int[][] blocks = new int[3][3];
		blocks[0][0]=8;blocks[0][1]=7;blocks[0][2]=6;
		blocks[1][0]=5;blocks[1][1]=1;blocks[1][2]=3;
		blocks[2][0]=2;blocks[2][1]=0;blocks[2][2]=4;
		Board board = new Board(blocks);
		
		int count=0;
		System.out.println("original "+ board.toString());
		System.out.println("neighbors");
		for(Board neighbor: board.neighbors()){
			System.out.println(neighbor.toString());
			count++;
		}
		assertEquals(3,count );
	}
	
	@Test
	public void testWithTwoNeighbors() {
		System.out.println("testTwoNeighbors");
		int[][] blocks = new int[3][3];
		blocks[0][0]=8;blocks[0][1]=7;blocks[0][2]=6;
		blocks[1][0]=5;blocks[1][1]=1;blocks[1][2]=3;
		blocks[2][0]=2;blocks[2][1]=4;blocks[2][2]=0;
		Board board = new Board(blocks);
		System.out.println("original "+ board.toString());
		System.out.println("neighbors");

		int count=0;
		for(Board neighbor: board.neighbors()){
			System.out.println(neighbor.toString());
			count++;
		}
		assertEquals(2,count );
	}

	@Test
	public void testToString() {
		int[][] blocks = new int[3][3];
		blocks[0][0]=8;blocks[0][1]=7;blocks[0][2]=6;
		blocks[1][0]=5;blocks[1][1]=4;blocks[1][2]=3;
		blocks[2][0]=2;blocks[2][1]=1;blocks[2][2]=0;
		Board board1 = new Board(blocks);
		Board board2 = new Board(blocks);
		assertEquals(board1.toString(), board2.toString());
	}

}
