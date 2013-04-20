import static org.junit.Assert.*;

import org.junit.Test;


public class MyStringUTest {

	@Test
	public void test() {
		
		String a = "abc";
		String b = "def";
		
		MyString myStringA = new MyString(a);
		MyString myStringB = new MyString(b);
		
		myStringA.swap(myStringB);
		
		assertEquals( b, myStringA.getValue());
		assertEquals( a, myStringB.getValue());

	}
	
	

}
