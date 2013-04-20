import java.util.Iterator;

public class Subset {

	public static void main(String[] args) {

		RandomizedQueue<String> randomizedQueue = new RandomizedQueue<String>();
		int k = Integer.parseInt(args[0]);
		while (!StdIn.isEmpty()) {
			randomizedQueue.enqueue(StdIn.readString());
		}
		
		StdOut.println();
		int i=0;
		Iterator<String> it = randomizedQueue.iterator();
		while(it.hasNext() && i<k){
			StdOut.printf("%s\n", it.next());
			i++;
		}
	}
}
