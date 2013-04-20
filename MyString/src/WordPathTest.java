import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;


public class WordPathTest {

	@Test
	public void getNextWordAAt1() {

		WordPath wordPath = new WordPath("LIKE");
		
		assertEquals("dimp", wordPath.getNextWord("damp", 1));

	}
	
	@Test
	public void getNextWordAAt0() {

		WordPath wordPath = new WordPath("LIKE");
		
		assertEquals("lamp", wordPath.getNextWord("damp", 0));

	}
	
	@Test
	public void getNextWordAAt3() {

		WordPath wordPath = new WordPath("LIKE");
		
		assertEquals("dame", wordPath.getNextWord("damp", 3));

	}

	
	@Test
	public void findPathGood() {

		WordPath wordPath = new WordPath("LIKE");
		
		List<String> expected = new ArrayList<String>();
		expected.add("damp");
		expected.add("lamp");
		expected.add("limp");
		expected.add("lime");
		expected.add("like");
		Set<String> visited = new HashSet<String>();
		
		List<String> result = new ArrayList<String>();
		
		assertEquals(expected, wordPath.findPath("DAMP", visited , result ));

	}
	
	@Test
	public void findPathBad() {

		WordPath wordPath = new WordPath("BIKE");
		
		List<String> expected = new ArrayList<String>();
		Set<String> visited = new HashSet<String>();
		
		List<String> result = new ArrayList<String>();
		
		assertEquals(expected, wordPath.findPath("DAMP", visited , result ));

	}
}
