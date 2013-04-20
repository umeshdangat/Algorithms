import static org.junit.Assert.*;

import org.junit.Test;


public class UnicodeTestGlyph {

	@Test
	public void test() {
		String str = "this is a sample glyph = \u24B6\u24D0\uFF21\uFF41\uD835\uDC00";
		String[] temp = str.split("=");
		assertEquals("this is a sample glyph ", temp[0]);
		assertEquals(" \u24B6\u24D0\uFF21\uFF41\uD835\uDC00", temp[1]);
				
	}

}
