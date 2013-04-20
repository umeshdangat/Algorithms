import java.util.ArrayList;
import java.util.List;


public class PrimeFactors {
	
	public List<Integer> getPrimeFactors(int number) {
		int n = number;
		List<Integer> factors = new ArrayList<Integer>();
		
		for(int i=2; i < n ; i++) {
			
			while(n % i == 0) {
				factors.add(i);
				n /= i;
			}
		}
		return factors;
		 
	}

}
