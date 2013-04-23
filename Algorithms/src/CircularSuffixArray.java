public class CircularSuffixArray {

	private String text;
	private Integer[] sortedIndices;

	// circular suffix array of s
	public CircularSuffixArray(String s) {
		this(s,new CircularSuffixArrayIndexBuilder().
				buildSortedCircularSuffixArrayIndex(new CircularSuffixArrayComparator(s)));
	}

	CircularSuffixArray(String s, Integer[] sortedIndices) {
		text = s;
		this.sortedIndices = sortedIndices;
	}

	// length of s
	public int length() {
		return text.length();
	}

	// returns index of ith sorted suffix
	//ith row = sorted number of the row
	//index[i] = original number of the row
	public int index(int i) {
		return sortedIndices[i];
	}

}
