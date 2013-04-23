import java.util.Comparator;

public class CircularSuffixArrayComparator implements Comparator<Integer> {

	private String text;

	public CircularSuffixArrayComparator(String text) {
		this.text = text;
	}

	@Override
	public int compare(Integer first, Integer second) {
		int indexFirst = first;
		int indexSecond = second;

		int i = 0;
		while (i < text.length()) {
			indexFirst = resetIndexToFirstCharInTextIfNeeded(indexFirst);
			indexSecond = resetIndexToFirstCharInTextIfNeeded(indexSecond);
			if (text.charAt(indexFirst) < text.charAt(indexSecond)) {
				return -1;
			}
			if (text.charAt(indexFirst) > text.charAt(indexSecond)) {
				return 1;
			}
			//both chars are equal go to next char and increment i
			indexFirst++;
			indexSecond++;
			i++;
		}
		return 0;
	}

	public String getText(){
		return text;
	}
	
	private int resetIndexToFirstCharInTextIfNeeded(int indexFirst) {
		if (indexFirst / text.length() == 1) {
			indexFirst = 0;
		}
		return indexFirst;
	}

}
