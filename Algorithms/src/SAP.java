import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

public class SAP {

	private class CommonAncestor {

		private final int vertex;
		private final int distance;

		CommonAncestor(int vertex, int distance) {
			this.vertex = vertex;
			this.distance = distance;
		}

		int getCommonAncestorVertex() {
			return this.vertex;
		}

		int getMinDistance() {
			return this.distance;
		}
	}
	
	private static final int NOPATH = -1;
	private final Digraph digraph;

	// constructor takes a digraph (not necessarily a DAG)
	public SAP(Digraph G) {
		this.digraph = new Digraph(G);
	}

	// length of shortest ancestral path between v and w; -1 if no such path
	public int length(int v, int w) {

		if ((v < 0 || v >= digraph.V()) || (w < 0 || w >= digraph.V())) {
			throw new IndexOutOfBoundsException();
		}

		CommonAncestor commonAncestor = getCommonAncestor(v, w);

		return commonAncestor.getMinDistance();

	}
	
	// a common ancestor of v and w that participates in a shortest ancestral
	// path; -1 if no such path
	public int ancestor(int v, int w) {

		if ((v < 0 || v >= digraph.V()) || (w < 0 || w >= digraph.V())) {
			throw new IndexOutOfBoundsException();
		}
		

		CommonAncestor commonAncestor = getCommonAncestor(v, w);

		return commonAncestor.getCommonAncestorVertex();

	}

	private CommonAncestor getCommonAncestor(int v, int w) {
		DeluxeBFS deluxeBFSFromV = new DeluxeBFS(digraph, v);
		Map<Integer, Integer> reachableVerticesFromVWithDistance = getReachableVerticesWithDistances(deluxeBFSFromV);

		DeluxeBFS deluxeBFSFromW = new DeluxeBFS(w, deluxeBFSFromV);
		Map<Integer, Integer> reachableVerticesFromWWithDistance = getReachableVerticesWithDistances(deluxeBFSFromW);

		SortedMap<Integer, Integer> resultVertexWithDistances = getReachableVerticesSortedByDistance(
				reachableVerticesFromVWithDistance,
				reachableVerticesFromWWithDistance);

		CommonAncestor commonAncestor = new CommonAncestor(NOPATH, NOPATH);

		if (!resultVertexWithDistances.isEmpty()) {
			int ancestorVertexDistance = resultVertexWithDistances.firstKey();
			int ancestorVertex = resultVertexWithDistances.get(ancestorVertexDistance);
			commonAncestor = new CommonAncestor(ancestorVertex,ancestorVertexDistance);
		}
		return commonAncestor;
	}

	private SortedMap<Integer, Integer> getReachableVerticesSortedByDistance(
			Map<Integer, Integer> reachableVerticesFromVWithDistance,
			Map<Integer, Integer> reachableVerticesFromWWithDistance) {
		SortedMap<Integer, Integer> resultVertexWithDistances = new TreeMap<Integer, Integer>();
		for (Entry<Integer, Integer> entryInV : reachableVerticesFromVWithDistance
				.entrySet()) {
			int vertexIdFromV = entryInV.getKey();
			int vertexDistanceFromV = entryInV.getValue();

			if (reachableVerticesFromWWithDistance.containsKey(vertexIdFromV)) {

				int vertexDistanceFromW = reachableVerticesFromWWithDistance
						.get(vertexIdFromV);

				resultVertexWithDistances.put(vertexDistanceFromV
						+ vertexDistanceFromW, vertexIdFromV);

			}
		}
		return resultVertexWithDistances;
	}

	private Map<Integer, Integer> getReachableVerticesWithDistances(
			DeluxeBFS deluxeBFS) {
		Map<Integer, Integer> reachableVerticesWithDistances = new HashMap<Integer, Integer>();
		List<Integer> reachableVertices = deluxeBFS.getReachableVertices();
		for (int reachableVertex : reachableVertices) {
			reachableVerticesWithDistances.put(reachableVertex,
					deluxeBFS.distTo(reachableVertex));
		}
		return reachableVerticesWithDistances;
	}

	// length of shortest ancestral path between any vertex in v and any vertex
	// in w; -1 if no such path
	public int length(Iterable<Integer> v, Iterable<Integer> w) {

		for (int eachV : v) {
			if (eachV < 0 || eachV >= digraph.V()) {
				throw new IndexOutOfBoundsException();
			}
		}

		for (int eachW : w) {
			if (eachW < 0 || eachW >= digraph.V()) {
				throw new IndexOutOfBoundsException();
			}
		}

		CommonAncestor commonAncestor = getCommonAncestor(v, w);

		return commonAncestor.getMinDistance();
	}

	// a common ancestor that participates in shortest ancestral path; -1 if no
	// such path
	public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {

		for (int eachV : v) {
			if (eachV < 0 || eachV >= digraph.V()) {
				throw new IndexOutOfBoundsException();
			}
		}

		for (int eachW : w) {
			if (eachW < 0 || eachW >= digraph.V()) {
				throw new IndexOutOfBoundsException();
			}
		}

		CommonAncestor commonAncestor = getCommonAncestor(v, w);

		return commonAncestor.getCommonAncestorVertex();
	}

	private CommonAncestor getCommonAncestor(Iterable<Integer> v,
			Iterable<Integer> w) {
		DeluxeBFS deluxeBFSFromV = new DeluxeBFS(digraph, v);
		Map<Integer, Integer> reachableVerticesFromVWithDistance = getReachableVerticesWithDistances(deluxeBFSFromV);

		DeluxeBFS deluxeBFSFromW = new DeluxeBFS(w, deluxeBFSFromV);
		Map<Integer, Integer> reachableVerticesFromWWithDistance = getReachableVerticesWithDistances(deluxeBFSFromW);

		SortedMap<Integer, Integer> resultVertexWithDistances = getReachableVerticesSortedByDistance(
				reachableVerticesFromVWithDistance,
				reachableVerticesFromWWithDistance);

		CommonAncestor commonAncestor = new CommonAncestor(NOPATH, NOPATH);

		if (!resultVertexWithDistances.isEmpty()) {
			int ancestorVertexDistance = resultVertexWithDistances.firstKey();
			int ancestorVertex = resultVertexWithDistances.get(ancestorVertexDistance);
			commonAncestor = new CommonAncestor(ancestorVertex,ancestorVertexDistance);
		}
		return commonAncestor;
	}

	// for unit testing of this class (such as the one below)
	public static void main(String[] args) {
		In in = new In(args[0]);
		Digraph G = new Digraph(in);
		SAP sap = new SAP(G);
		while (!StdIn.isEmpty()) {
			int v = StdIn.readInt();
			int w = StdIn.readInt();
			int length = sap.length(v, w);
			int ancestor = sap.ancestor(v, w);
			StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
		}
	}
}
