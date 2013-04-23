public class BurrowWheelerTransformBuilder {

	public BurrowWheelerTransform buildTransform(String text) {
		
		CircularSuffixArray cicularSuffixArray = new CircularSuffixArray(text);

		StringBuilder encodedText = new StringBuilder();
		int firstRow = 0;
		for (int i = 0; i < cicularSuffixArray.length(); i++) {
			int originalIndex = cicularSuffixArray.index(i);
			if (originalIndex == 0) {
				firstRow = i;
				encodedText.append(text.charAt(text.length()-1));
			} else {
				encodedText.append(text.charAt(originalIndex-1));
			}
		}
		return new BurrowWheelerTransform(firstRow, encodedText.toString());
	}

}
