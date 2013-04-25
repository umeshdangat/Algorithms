import static org.junit.Assert.*;

import org.junit.Test;

public class BurrowWheelerTransformBuilderTest {

	@Test
	public void testBuildTransform() {
		String text = "ABRACADABRA!";
		BurrowWheelerTransformBuilder burrowWheelerTransformBuilder = new BurrowWheelerTransformBuilder();
		BurrowWheelerTransform burrowWheelerTransform = burrowWheelerTransformBuilder.buildTransform(text);
		
		assertEquals(3, burrowWheelerTransform.getFirstRow());
		assertEquals("ARD!RCAAAABB", burrowWheelerTransform.getEncodedText());
	}

	
	@Test
	public void testBuildTransformHello() {
		String text = "hello";
		BurrowWheelerTransformBuilder burrowWheelerTransformBuilder = new BurrowWheelerTransformBuilder();
		BurrowWheelerTransform burrowWheelerTransform = burrowWheelerTransformBuilder.buildTransform(text);
		
		assertEquals(1, burrowWheelerTransform.getFirstRow());
		assertEquals("hoell", burrowWheelerTransform.getEncodedText());
	}
	
	
	@Test
	public void testBuildTransformCadabra() {
		String text = "CADABRA!";
		BurrowWheelerTransformBuilder burrowWheelerTransformBuilder = new BurrowWheelerTransformBuilder();
		BurrowWheelerTransform burrowWheelerTransform = burrowWheelerTransformBuilder.buildTransform(text);
		
		assertEquals(3, burrowWheelerTransform.getFirstRow());
		assertEquals("ARD!RCAAAABB", burrowWheelerTransform.getEncodedText());
	}
}
