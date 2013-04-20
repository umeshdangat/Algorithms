
public class TicTacToe {
	
	private boolean[][] visited = {{false,false,false},{false,false,false}, {false,false,false}};; 
	
	
	public boolean isWinner(char[][] maze, int i, int j, String PlayerName){
		
		if ((i > 2) || (i < 0) || (j <0) || (j > 2))
			return false;
		
		if (RowWinner(maze,i,j)) 
			return true;
		
		if (ColumnWinner(maze, i,j))
			return true;
		
		if (DiagonalWinner(maze,i,j))
			return true;

		if(visited[i][j])
			return false;
		
		visited[i][j]=true;

		if(isWinner(maze, i+1, j, PlayerName)) 
			return true;
		
		if(isWinner(maze, i-1, j, PlayerName))
			return true;
		
		if(isWinner(maze, i, j+1, PlayerName))
			return true;
		
		if(isWinner(maze, i, j-1, PlayerName))
			return true;
		
		if(isWinner(maze, i+1, j+1, PlayerName))
			return true;
		
		if(isWinner(maze, i-1, j-1, PlayerName))
			return true;
		
		return false;		
		
	}


	private boolean DiagonalWinner(char[][] maze, int i, int j) {

		if(i==j){
			char currentSign = maze[i][j];
			if((currentSign != 'X') && (currentSign != '0'))
				return false;
			
			if( maze[0][0] == maze[1][1])
				if(maze[1][1] == maze[2][2])
					return true;
			
			if( maze[2][0] == maze[1][1])
				if(maze[1][1] == maze[0][2])
					return true;
		
		}
		return false;
	}


	private boolean RowWinner(char[][] maze, int i, int j) {

		char currentSign = maze[i][j];
		
		if((currentSign != 'X') && (currentSign != '0'))
			return false;
		
		for(int column = 0; column < 3; column++)
			if(maze[i][column] != currentSign)
				return false;
				
		return true;
	}
	
	private boolean ColumnWinner(char[][] maze, int i, int j) {

		char currentSign = maze[i][j];
		
		if((currentSign != 'X') && (currentSign != '0'))
			return false;
		
		for(int row = 0; row < 3; row++)
			if(maze[row][j] != currentSign)
				return false;
				
		return true;
	}

}
