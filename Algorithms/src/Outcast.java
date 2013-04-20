import java.util.SortedMap;
import java.util.TreeMap;

public class Outcast {

	private final WordNet wordnet;

	// constructor takes a WordNet object
	public Outcast(WordNet wordnet) {
		this.wordnet = wordnet;
	}

	// given an array of WordNet nouns, return an outcast
	public String outcast(String[] nouns) {

		SortedMap<Integer, String> distances = new TreeMap<Integer, String>();
		for (String noun : nouns) {
			int distance = 0;
			for (String inner : nouns) {
				distance += wordnet.distance(noun, inner);
			}
			if (!distances.containsKey(distance)) {
				distances.put(distance, noun);
			}
		}

		int highestDistance = distances.lastKey();
		return distances.get(highestDistance);
	}

	// for unit testing of this class (such as the one below)
	public static void main(String[] args) {
		WordNet wordnet = new WordNet(args[0], args[1]);
		Outcast outcast = new Outcast(wordnet);
		for (int t = 2; t < args.length; t++) {
			String[] nouns = In.readStrings(args[t]);
			StdOut.println(args[t] + ": " + outcast.outcast(nouns));
		}
	}
}
