import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Boggle {

	private Set<String> dictionary = new HashSet<String>();

	public Boggle() {
		dictionary.add("WARD");
		dictionary.add("WAR");
		dictionary.add("DRAW");
		dictionary.add("RAW");
	}
	
	public Set<String> getAllWords(char[][] letters){
		
		Set<String> result = new HashSet<String>();
		Set<Node> visited = new HashSet<Node>();
		
		for(int i = 0; i < letters.length; i++){
			for(int j = 0; j < letters[0].length; j++) {
				result.addAll(getWords(letters,i,j,"",visited,result));
			}
		}
		return getWords(letters,0, 0, "",visited,result);
	}
	
	private Set<String> getWords(char[][] letters, int row, int col,String word, Set<Node> visited, Set<String> result ) {
		
		if( (row <0) ||  (col < 0) || ( row >= letters.length) || (col >= letters[0].length)) {
			return result;
		}
		
		if(dictionary.contains(word)) {
			result.add(word);
		}
		
		Node cell = new  Node(row,col);
		
		if(visited.contains(cell)) {
			return result;
		}
		
		visited.add(cell);

		String nextWord = word + letters[row][col];
		result = getWords(letters, row+1, col, nextWord, visited, result);
		result = getWords(letters, row-1, col, nextWord, visited, result);
		result = getWords(letters, row, col+1, nextWord, visited, result);
		result = getWords(letters, row, col-1, nextWord, visited, result);
		
		visited.remove(cell);
		
		return result;
	}
}
