import java.util.Arrays;

public class CircularSuffixArrayIndexBuilder {

	public Integer[] buildSortedCircularSuffixArrayIndex(
			CircularSuffixArrayComparator circularSuffixArrayComparator) {

		String text = circularSuffixArrayComparator.getText();
		Integer[] index = new Integer[text.length()];
		for (int i = 0; i < text.length(); i++) {
			index[i] = i;
		}

		Arrays.sort(index, circularSuffixArrayComparator);
		return index;
	}
}
