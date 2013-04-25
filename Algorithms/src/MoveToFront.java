public class MoveToFront {
    // apply move-to-front encoding, reading from standard input and writing to standard output
    public static void encode(){
    	MoveToFrontEncoderWorker moveToFrontEncoderWorker = new MoveToFrontEncoderWorker();
    	while(!BinaryStdIn.isEmpty()){
    		char character = BinaryStdIn.readChar();
    		int index = moveToFrontEncoderWorker.getIndexAndMoveToFront(character);
    		BinaryStdOut.write((char)index);
    	}
    	BinaryStdOut.close();
    }

    // apply move-to-front decoding, reading from standard input and writing to standard output
    public static void decode(){
    	MoveToFrontDecoderWorker moveToFrontDecoderWorker = new MoveToFrontDecoderWorker();
    	while(!BinaryStdIn.isEmpty()){
    		int currentIndex = BinaryStdIn.readChar();
    		char character = moveToFrontDecoderWorker.getIndexAndMoveToFront(currentIndex);
    		BinaryStdOut.write(character);
    	}
    	BinaryStdOut.close();
    }

    // if args[0] is '-', apply move-to-front encoding
    // if args[0] is '+', apply move-to-front decoding
    public static void main(String[] args){
    	if (args[0].equals("-")) {
			encode();
		}
		if (args[0].equals("+")) {
			decode();
		}
    }
}