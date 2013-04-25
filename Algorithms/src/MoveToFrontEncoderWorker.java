
public class MoveToFrontEncoderWorker{

	char[] alphabet;
	// alphabet size of extended ASCII
	private static final int R = 256;

	public MoveToFrontEncoderWorker() {
		alphabet = new char[R];
		for (int i = 0; i < R; i++) {
			alphabet[i] = (char) i;
		}
	}

	public int getIndexAndMoveToFront(char character) {

		int index = 0;
		while (true && index < R) {
			char currentCharacter = alphabet[index];
			if (currentCharacter == character) {
				break;
			}
			index++;
		}

		for (int i = index - 1; i >= 0; i--) {
			alphabet[i + 1] = alphabet[i];
		}
		alphabet[0] = character;

		return index;
	}
	
	
}
