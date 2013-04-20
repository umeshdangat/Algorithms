import java.util.Arrays;


public class ClosestDiffToZero {
	
	public Difference getDifference(int[] a) {
		
		Arrays.sort(a);
		
		int minDiff = Integer.MAX_VALUE;
		int currDiff ;
		int i = 0;
		int j = a.length-1;
		
	   Difference difference = null;
		while(i < j) {
			
			currDiff = a[i] + a[j];
			
			if(currDiff < 0) {
				if(Math.abs(currDiff) < Math.abs(minDiff)){
					minDiff = currDiff;
					difference = new Difference(a[i],a[j], minDiff);
				}
				i++;
			}
			
			if(currDiff > 0) {
				if(Math.abs(currDiff) < Math.abs(minDiff)){
					minDiff = currDiff;
					difference = new Difference(a[i] , a[j], minDiff);
				}
				j--;
			}
			
			if (currDiff == 0) {
				minDiff = currDiff;
				return new Difference(a[i], a[j], minDiff);
			}
		}
		
		return difference;

	}

}
