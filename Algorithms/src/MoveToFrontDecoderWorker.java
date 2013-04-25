public class MoveToFrontDecoderWorker {
	
	char[] alphabet;
	// alphabet size of extended ASCII
	private static final int R = 256;

	public MoveToFrontDecoderWorker() {
		alphabet = new char[R];
		for (int i = 0; i < R; i++) {
			alphabet[i] = (char) i;
		}
	}

	public char getIndexAndMoveToFront(int characterIndex) {

		char character = alphabet[characterIndex];
		for (int i = characterIndex - 1; i >= 0; i--) {
			alphabet[i + 1] = alphabet[i];
		}
		alphabet[0] = character;

		return character;
	}
}
