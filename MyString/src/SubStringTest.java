import static org.junit.Assert.*;

import org.junit.Test;


public class SubStringTest {

	@Test
	public void test() {

		String original = "UmeshDangat";
		String subString = "Dan";
		
		assertTrue(SubString.isSubstring(original, subString));

	}
	
	@Test
	public void test1() {

		String original = "Umeaaaaaaaangat";
		String subString = "aaa";
		
		assertTrue(SubString.isSubstring(original, subString));

	}
	
	@Test
	public void test3() {

		String original = "";
		String subString = "";
		
		assertFalse(SubString.isSubstring(original, subString));

	}
	
	@Test
	public void test4() {

		String original = "a";
		String subString = "a";
		
		assertTrue(SubString.isSubstring(original, subString));

	}

}
