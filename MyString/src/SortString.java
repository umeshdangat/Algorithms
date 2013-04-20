import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SortString {

	Map<String, List<String>> anagrams = new HashMap<String, List<String>>();
	public  String getSortedString(String word) {
		
		char[] chars = word.toCharArray();
		
		Arrays.sort(chars);
		
		return new String(chars);
		
		
	}
	

	
	
	

}
