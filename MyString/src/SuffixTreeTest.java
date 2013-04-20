import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


public class SuffixTreeTest {

	@Test
	public void test() {
		
		SuffixTree suffixTree = new SuffixTree("bibsbibs");
	
		List<Integer> expected = new ArrayList<Integer>();
		expected.add(2);
		expected.add(6);
		assertEquals(expected , suffixTree.getIndexes("bs"));
	}

}
