import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.junit.BeforeClass;
import org.junit.Test;

public class ResourceTest {

	private static BufferedReader br;
	
	@BeforeClass
	public static void setUp() {
		InputStream in = ResourceTest.class.getResourceAsStream("resourceName");
		InputStreamReader inReader = new InputStreamReader(in);
		br = new BufferedReader(inReader);

	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
