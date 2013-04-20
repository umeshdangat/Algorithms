import static org.junit.Assert.*;

import org.junit.Test;


public class TicTacToeTest {

	@Test
	public void test() {
		
		TicTacToe ticTacToe = new TicTacToe();
		char maze[][] = {{' ',' ','X'},{' ','X',' '},{'X',' ','X'}};
		
		assertTrue(ticTacToe.isWinner(maze, 0, 0, "Umesh"));
	}

}
