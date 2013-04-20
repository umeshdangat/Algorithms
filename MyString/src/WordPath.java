import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class WordPath {
	
	Set<String> dictionary = new HashSet<String>();
	private String targetWord;
	
	public WordPath(String targetWord){
		
		this.targetWord = targetWord.toLowerCase();
		
		dictionary.add("damp");
		dictionary.add("lamp");
		dictionary.add("lame");
		dictionary.add("limp");
		dictionary.add("lime");
		dictionary.add("like");
		dictionary.add("lake");
		dictionary.add("dame");
	}
	
	
	
	public List<String> findPath(String word, Set<String> visited, List<String> result ) {

		String wordLowerCase = word.toLowerCase();
		
		if(wordLowerCase.equals(targetWord)) {
			result.add(wordLowerCase);
			return result;
		}
		
		if(! dictionary.contains(wordLowerCase)) {
			return new ArrayList<String>();
		}
		
		if(visited.contains(wordLowerCase)) {
			return new ArrayList<String>();
		}
		
		visited.add(wordLowerCase);
		result.add(wordLowerCase);
		
		for(int i = 0 ; i < wordLowerCase.length(); i++) {
			
			String nextWord = getNextWord(wordLowerCase,i);
			
			if (nextWord != null) {
			
			List<String> newResult = findPath(nextWord, visited, result);
			if(!newResult.isEmpty()){
				return result;
			}
			}
		}
		return new ArrayList<String>();
		
	}



	public String getNextWord(String word, int i) {
		
		if(word.charAt(i) != targetWord.charAt(i)) {

			return word.substring(0,i) + targetWord.charAt(i) + word.substring(i+1);
		}
		else {
			return null;
		}
	}

}
