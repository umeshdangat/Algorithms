import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;



public class SuffixTreeNode {
	
	Map<Character,SuffixTreeNode> children = new HashMap<Character,SuffixTreeNode>();
	List<Integer> indexes = new ArrayList<Integer>();
	
	public void insertString(String suffix, int index){
		indexes.add(index);
		
		if(suffix !=null && suffix.length() > 0) {
			
			SuffixTreeNode child = null;
			char value  =  suffix.charAt(0);
			
			if(children.containsKey(value)){
				child = children.get(value);
			}
			else{
				child = new SuffixTreeNode();
				children.put(value,child);
			}
			String remainder = suffix.substring(1);
			child.insertString(remainder,index);
			
		}
		
	}
	
	public List<Integer> getIndexes(String s){
		
		if(s == null || s.length() == 0) {
			return indexes;
		}
		else {
			char first = s.charAt(0);
			
			if(children.containsKey(first)) {
				String remainder = s.substring(1);
				return children.get(first).getIndexes(remainder);
			}
			
		}
		return null;
		
	}

}
