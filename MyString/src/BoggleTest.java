import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


public class BoggleTest {

	@Test
	public void test() {
		
		char[][] letters = {{'W', 'A'}, {'D','R'}};
		
		List<String> expected = new ArrayList<String>();
		Boggle boggle = new Boggle();
		
		assertEquals(expected,boggle.getAllWords(letters));
	}

}
