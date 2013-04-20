
public class Counter {

	public  static int getCounter() {
		
		int counter = 0;
		
		for(int i= 0;i<10;i++) {
			for(int j = i+1; j < 10; j++){
				counter++;
			}
		}
		return counter;
	}
}
