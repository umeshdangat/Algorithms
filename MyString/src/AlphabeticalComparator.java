import java.util.Comparator;


public class AlphabeticalComparator implements Comparator<Character> {

	WeightedQuickUnionUF wf;
	
	@Override
	public int compare(Character s1, Character s2){
		
		if(s1 < s2) return -1;
		if(s2 > s1) return 1;
		return 0;
	}
}
