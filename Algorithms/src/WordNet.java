import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordNet {

	private static final int NOPATH = -1;
	private Map<Integer, Synset> synsetsMap;
	private Map<String, List<Integer>> nouns;
	private final SAP sap;

	private class Synset {

		private final int vertexId;
		private final List<String> synsets;
		private String gloss;

		Synset(int vertexId, List<String> synsets, String gloss) {
			this.vertexId = vertexId;
			this.synsets = new ArrayList<String>(synsets);
			this.gloss = gloss;
		}

		int getVertexId() {
			return vertexId;
		}

		List<String> getSynsets() {
			return Collections.unmodifiableList(synsets);
		}

	}

	public WordNet(String synsets, String hypernyms) {
		this(new In(synsets), new In(hypernyms));
	}

	WordNet(In synsetsStream, In hypernymsStream) {
		synsetsMap = new HashMap<Integer, Synset>();
		nouns = new HashMap<String, List<Integer>>();

		parseSynsetAndBuildSynsetMaps(synsetsStream);

		Digraph digraph = new Digraph(synsetsMap.size());
		parseHypernymAndBuildDigraph(hypernymsStream,digraph);

		if (!isRootedDigraph(digraph) || (isCyclicDigraph(digraph))) {
			throw new IllegalArgumentException();
		}
		
		sap = new SAP(digraph);
	}

	private boolean isCyclicDigraph(Digraph digraph) {
		
		DirectedCycle directedCycle = new DirectedCycle(digraph);
		return directedCycle.hasCycle();
	}

	private boolean isRootedDigraph(Digraph digraph) {

		int rootCount = 0;
		Bag<Integer> neighbors = null;
		for (int vertex = 0; vertex < digraph.V(); vertex++) {
			neighbors = (Bag<Integer>) digraph.adj(vertex);
			if (neighbors.isEmpty()) {
				rootCount++;
				if (rootCount > 1) {
					break;
				}
			}
		}
		return rootCount == 1;
	}

	private void parseHypernymAndBuildDigraph(In hypernymsStream, Digraph digraph) {
		String line = "";
		while (line != null) {
			line = hypernymsStream.readLine();
			if (line == null) {
				break;
			}
			List<Integer> hypernymIds = getHypernymIds(line);
			// TODO what if hypernymsIds are not present in synsetMap ??? throw
			// ??
			addEdgesToDigraph(hypernymIds,digraph);
		}
	}

	private void addEdgesToDigraph(List<Integer> hypernymIds, Digraph digraph) {
		int synonymId = hypernymIds.get(0);
		for (int index = 1; index < hypernymIds.size(); index++) {
			digraph.addEdge(synonymId, hypernymIds.get(index));
		}
	}

	private List<Integer> getHypernymIds(String line) {

		List<Integer> hypernymList = new ArrayList<Integer>();
		String[] hypernyms = line.split(",");
		for (String hypernym : hypernyms) {
			hypernymList.add(Integer.parseInt(hypernym));
		}
		return hypernymList;
	}

	private void parseSynsetAndBuildSynsetMaps(In synsetsStream) {
		String line = "";
		while (line != null) {
			line = synsetsStream.readLine();
			if (line == null) {
				break;
			}
			Synset synset = createSynset(line);
			synsetsMap.put(synset.getVertexId(), synset);
		}
	}

	private Synset createSynset(String line) {

		String[] synsetLine = line.split(",");
		int synsetId = Integer.parseInt(synsetLine[0]);

		String[] synonyms = synsetLine[1].split(" ");
		List<String> synonymList = new ArrayList<String>();
		for (String synonym : synonyms) {
			synonymList.add(synonym);
			List<Integer> synIds = null;
			if (nouns.containsKey(synonym)) {
				synIds = nouns.get(synonym);
			} else {
				synIds = new ArrayList<Integer>();
			}
			synIds.add(synsetId);
			nouns.put(synonym, synIds);
		}
		return new Synset(synsetId, synonymList, synsetLine[2]);
	}

	// returns all WordNet nouns
	public Iterable<String> nouns() {
		return Collections.unmodifiableCollection(nouns.keySet());
	}

	// is the word a WordNet noun?
	public boolean isNoun(String word) {
		return nouns.containsKey(word);
	}

	// distance between nounA and nounB (defined below)
	public int distance(String nounA, String nounB) {
		if (!nouns.containsKey(nounA) || !nouns.containsKey(nounB)) {
			throw new IllegalArgumentException();
		}
		List<Integer> nounAids = nouns.get(nounA);
		List<Integer> nounBids = nouns.get(nounB);

		return sap.length(nounAids, nounBids);
	}

	// a synset (second field of synsets.txt) that is the common ancestor of
	// nounA and nounB
	// in a shortest ancestral path (defined below)
	public String sap(String nounA, String nounB) {
		if (!nouns.containsKey(nounA) || !nouns.containsKey(nounB)) {
			throw new IllegalArgumentException();
		}
		List<Integer> nounAids = nouns.get(nounA);
		List<Integer> nounBids = nouns.get(nounB);
		
		int ancestor = sap.ancestor(nounAids, nounBids);
		if (ancestor == NOPATH)
			return null;
		else {
			Synset synset = synsetsMap.get(ancestor);
			List<String> ancestorNouns = synset.getSynsets();
			StringBuilder ancestors = new StringBuilder();

			for (int index = 0; index < ancestorNouns.size(); index++) {
				ancestors.append(ancestorNouns.get(index));
				if (index != ancestorNouns.size() - 1)
					ancestors.append(" ");
			}
			return ancestors.toString();
		}
	}

	// for unit testing of this class
	public static void main(String[] args) {
		WordNet wordNet = new WordNet(args[0], args[1]);
		while (!StdIn.isEmpty()) {
			String nounA = StdIn.readString();
			String nounB = StdIn.readString();
			int length = wordNet.distance(nounA, nounB);
			String ancestor = wordNet.sap(nounA, nounB);
			StdOut.printf("length = %d, ancestor = %s\n", length, ancestor);
		}
	}
}
