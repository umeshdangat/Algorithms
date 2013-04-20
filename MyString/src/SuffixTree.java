import java.util.List;


public class SuffixTree {
	
	SuffixTreeNode root = new SuffixTreeNode();
	
	public SuffixTree(String largeString){
		
		for(int index = 0; index < largeString.length(); index++){
			
			String suffix = largeString.substring(index);
			root.insertString(suffix, index);
		}
		
	}
	
	List<Integer> getIndexes(String s){
		return root.getIndexes(s);
	}


}
