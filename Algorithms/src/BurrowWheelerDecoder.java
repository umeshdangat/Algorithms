import java.util.Arrays;

public class BurrowWheelerDecoder {

	private int firstRow;
	private String encoded;
	private int[] next;

	public BurrowWheelerDecoder(int firstRow, String encoded, int[] next) {
		this.firstRow = firstRow;
		this.encoded = encoded;
		this.next = next;
	}

	public String getDecoded() {

		char[] sortedEncoded = encoded.toCharArray();
		Arrays.sort(sortedEncoded);

		StringBuilder decoded = new StringBuilder();
		decoded.append(sortedEncoded[firstRow]);
		int nextIndex = next[firstRow];

		int i = 1;
		while (i < encoded.length()) {
			decoded.append(sortedEncoded[nextIndex]);
			nextIndex = next[nextIndex];
			i++;
		}
		return decoded.toString();
	}

}
