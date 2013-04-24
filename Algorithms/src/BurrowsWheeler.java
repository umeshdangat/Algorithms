public class BurrowsWheeler {
	private static String encoded;
	private static String decoded;
	private static int firstRow;

	// apply Burrows-Wheeler encoding, reading from standard input and writing
	// to standard output
	public static void encode() {

		BurrowWheelerTransformBuilder burrowWheelerTransformBuilder = new BurrowWheelerTransformBuilder();
		BurrowWheelerTransform burrowWheelerTransform = burrowWheelerTransformBuilder
				.buildTransform(decoded);

		BinaryStdOut.write(burrowWheelerTransform.getFirstRow());
		BinaryStdOut.write(burrowWheelerTransform.getEncodedText());
		BinaryStdOut.close();

	}

	// apply Burrows-Wheeler decoding, reading from standard input and writing
	// to standard output
	public static void decode() {
		BurrowWheelerDecoderBuilder burrowWheelerDecoderBuilder = new BurrowWheelerDecoderBuilder(
				firstRow, encoded);
		BurrowWheelerDecoder burrowWheelerDecoder = burrowWheelerDecoderBuilder.createNextArray().build();
		BinaryStdOut.write(burrowWheelerDecoder.getDecoded());
		BinaryStdOut.close();
	}

	// if args[0] is '-', apply Burrows-Wheeler encoding
	// if args[0] is '+', apply Burrows-Wheeler decoding
	public static void main(String[] args) {
		if (args[0].equals("-")) {
			decoded = BinaryStdIn.readString();
			encode();
		}
		if (args[0].equals("+")) {
			firstRow = BinaryStdIn.readInt();
			encoded = BinaryStdIn.readString();
			decode();
		}
	}
}