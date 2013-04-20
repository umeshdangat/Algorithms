import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;


public class SAPTest {

	@Ignore
	public void testSAP() {
		fail("Not yet implemented");
	}

	@Test
	public void testLengthConnectedGraphWithCycleNoRoot() {
		int vertices = 5;
		Digraph digraph = new Digraph(5);
		digraph.addEdge(0, 1);
		digraph.addEdge(1, 2);
		digraph.addEdge(2, 3);
		digraph.addEdge(3, 1);
		digraph.addEdge(4, 3);
		
		SAP sap = new SAP(digraph);
		assertEquals(3, sap.length(0, 4));
		
	}
	
	@Test
	public void testCommonAncestorConnectedGraphWithCycleNoRoot() {
		int vertices = 5;
		Digraph digraph = new Digraph(5);
		digraph.addEdge(0, 1);
		digraph.addEdge(1, 2);
		digraph.addEdge(2, 3);
		digraph.addEdge(3, 1);
		digraph.addEdge(4, 3);
		
		SAP sap = new SAP(digraph);
		assertEquals(1, sap.ancestor(0, 4));
	}
	
	@Test
	public void testLengthDisconnectedGraph() {
		int vertices = 4;
		Digraph digraph = new Digraph(4);
		digraph.addEdge(0, 1);
		digraph.addEdge(2, 3);
		
		SAP sap = new SAP(digraph);
		assertEquals(-1, sap.length(0, 3));
		
	}
	
	@Test
	public void testCommonAncestorDisconnectedGraph() {
		int vertices = 4;
		Digraph digraph = new Digraph(4);
		digraph.addEdge(0, 1);
		digraph.addEdge(2, 3);
		
		SAP sap = new SAP(digraph);
		assertEquals(-1, sap.ancestor(0, 3));
		
	}
	
	@Test
	public void testLengthConnectedGraphWithRoot() {
		int vertices = 6;
		Digraph digraph = new Digraph(6);
		digraph.addEdge(0, 1);
		digraph.addEdge(1, 2);
		digraph.addEdge(2, 3);
		digraph.addEdge(4, 2);
		digraph.addEdge(5, 3);
		
		SAP sap = new SAP(digraph);
		assertEquals(3, sap.length(0, 3));
		assertEquals(3, sap.length(4, 5));
		assertEquals(4, sap.length(0, 5));
		assertEquals(0, sap.length(2, 2));
		
	}
	
	@Test
	public void testAncestorConnectedGraphWithRoot() {
		int vertices = 6;
		Digraph digraph = new Digraph(6);
		digraph.addEdge(0, 1);
		digraph.addEdge(1, 2);
		digraph.addEdge(2, 3);
		digraph.addEdge(4, 2);
		digraph.addEdge(5, 3);
		
		SAP sap = new SAP(digraph);
		assertEquals(3, sap.ancestor(0, 3));
		assertEquals(3, sap.ancestor(4, 5));
		assertEquals(3, sap.ancestor(0, 5));
		assertEquals(2, sap.ancestor(2, 2));
		
	}
	
	@Test
	public void testAncestorConnectedGraphWithCycle() {
		int vertices = 6;
		Digraph digraph = new Digraph(6);
		digraph.addEdge(1, 0);
		digraph.addEdge(1, 2);
		digraph.addEdge(2, 3);
		digraph.addEdge(3, 4);
		digraph.addEdge(4, 5);
		digraph.addEdge(5, 0);
		
		SAP sap = new SAP(digraph);
		assertEquals(2, sap.length(1, 5));
		assertEquals(0, sap.ancestor(1, 5));
		
	}

	@Test
	public void testLengthIterableDisconnectedGraph() {
		int vertices = 6;
		Digraph digraph = new Digraph(6);
		digraph.addEdge(0, 1);
		digraph.addEdge(1, 2);
		digraph.addEdge(2, 3);
		digraph.addEdge(4, 5);
		digraph.addEdge(5, 4);
		
		SAP sap = new SAP(digraph);
		List<Integer> verticesV = new ArrayList<Integer>();
		verticesV.add(0);verticesV.add(1);verticesV.add(2);verticesV.add(3);
		
		List<Integer> verticesW = new ArrayList<Integer>();
		verticesW.add(4);verticesW.add(5);
		
		assertEquals(-1, sap.length(verticesV, verticesW));
	}
	
	@Test
	public void testLengthIterableDisconnectedGraphMixedVertices() {
		int vertices = 6;
		Digraph digraph = new Digraph(6);
		digraph.addEdge(0, 1);
		digraph.addEdge(1, 2);
		digraph.addEdge(2, 3);
		digraph.addEdge(4, 5);
		digraph.addEdge(5, 4);
		digraph.addEdge(4, 3);
		
		SAP sap = new SAP(digraph);
		List<Integer> verticesV = new ArrayList<Integer>();
		verticesV.add(0);verticesV.add(1);
		
		List<Integer> verticesW = new ArrayList<Integer>();
		verticesW.add(4);verticesW.add(5);
		
		assertEquals(3, sap.length(verticesV, verticesW));
	}
	

	@Test
	public void testAncestorIterableOfIntegerCyleNoRoot() {
		int vertices = 5;
		Digraph digraph = new Digraph(5);
		digraph.addEdge(0, 1);
		digraph.addEdge(1, 2);
		digraph.addEdge(2, 3);
		digraph.addEdge(3, 1);
		digraph.addEdge(4, 3);
		
		SAP sap = new SAP(digraph);
		List<Integer> verticesV = new ArrayList<Integer>();
		verticesV.add(0);verticesV.add(1);verticesV.add(2);
		
		List<Integer> verticesW = new ArrayList<Integer>();
		verticesW.add(3);verticesW.add(4);
		
		assertEquals(3, sap.ancestor(verticesV, verticesW));
	}

	@Test
	public void testAncestorIterableDisconnectedGraph() {
		int vertices = 6;
		Digraph digraph = new Digraph(6);
		digraph.addEdge(0, 1);
		digraph.addEdge(1, 2);
		digraph.addEdge(2, 3);
		digraph.addEdge(4, 5);
		digraph.addEdge(5, 4);
		
		SAP sap = new SAP(digraph);
		List<Integer> verticesV = new ArrayList<Integer>();
		verticesV.add(0);verticesV.add(1);verticesV.add(2);verticesV.add(3);
		
		List<Integer> verticesW = new ArrayList<Integer>();
		verticesW.add(4);verticesW.add(5);
		
		assertEquals(-1, sap.ancestor(verticesV, verticesW));
	}
	
	@Test
	public void testAncestorIterableDisconnectedGraphMixedVertices() {
		int vertices = 6;
		Digraph digraph = new Digraph(6);
		digraph.addEdge(0, 1);
		digraph.addEdge(1, 2);
		digraph.addEdge(2, 3);
		digraph.addEdge(4, 5);
		digraph.addEdge(5, 4);
		digraph.addEdge(4, 3);
		
		SAP sap = new SAP(digraph);
		List<Integer> verticesV = new ArrayList<Integer>();
		verticesV.add(0);verticesV.add(1);
		
		List<Integer> verticesW = new ArrayList<Integer>();
		verticesW.add(4);verticesW.add(5);
		
		assertEquals(3, sap.ancestor(verticesV, verticesW));
	}
	

	@Test
	public void testLengthIterableOfIntegerCyleNoRoot() {
		int vertices = 5;
		Digraph digraph = new Digraph(5);
		digraph.addEdge(0, 1);
		digraph.addEdge(1, 2);
		digraph.addEdge(2, 3);
		digraph.addEdge(3, 1);
		digraph.addEdge(4, 3);
		
		SAP sap = new SAP(digraph);
		List<Integer> verticesV = new ArrayList<Integer>();
		verticesV.add(0);verticesV.add(1);verticesV.add(2);
		
		List<Integer> verticesW = new ArrayList<Integer>();
		verticesW.add(3);verticesW.add(4);
		
		assertEquals(1, sap.length(verticesV, verticesW));
	}

	

}
