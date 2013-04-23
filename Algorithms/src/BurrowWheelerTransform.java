
public class BurrowWheelerTransform {

	private String encodedText;
	private int firstRow;

	public BurrowWheelerTransform(int firstRow, String encodedText) {
		this.firstRow = firstRow;
		this.encodedText = encodedText;
	}

	public int getFirstRow() {
		return firstRow;
	}

	public String getEncodedText() {
		return encodedText;
	}

}
