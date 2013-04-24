import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BurrowWheelerDecoderBuilder {

	private String encoded;
	private int firstRow;
	private int[] next;

	public BurrowWheelerDecoderBuilder(int firstRow, String encoded) {
		this.firstRow = firstRow;
		this.encoded = encoded;
		this.next = new int[encoded.length()];
	}

	public BurrowWheelerDecoderBuilder createNextArray() {
		Map<Character, List<Integer>> indicesInEncodedText = buildCharacterIndexForEncodedText();

		char[] encodedChars = encoded.toCharArray();
		Arrays.sort(encodedChars);
		int count = 0;
		for (Character character : encodedChars) {
			if (indicesInEncodedText.containsKey(character)) {
				List<Integer> index = indicesInEncodedText.get(character);
				for (int i : index) {
					next[count++] = i;
				}
				indicesInEncodedText.remove(character);
			}
		}

		return this;
	}

	private Map<Character, List<Integer>> buildCharacterIndexForEncodedText() {
		Map<Character, List<Integer>> indicesInEncodedText = new HashMap<Character, List<Integer>>();
		
		for(int i=0;i<encoded.length();i++){
			Character character = encoded.charAt(i);
			List<Integer> index;
			if(indicesInEncodedText.containsKey(character)){
				index = indicesInEncodedText.get(character);
				index.add(i);
			}
			else{
				index = new ArrayList<Integer>();
				index.add(i);
				indicesInEncodedText.put(character, index);
			}
		}
		
		return indicesInEncodedText;
	}

	public BurrowWheelerDecoder build() {
		return new BurrowWheelerDecoder(firstRow, encoded, next);
	}

	public int[] getNext() {
		return next;
	}

}
