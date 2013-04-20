import java.util.Comparator;


public class MaxHeapComparator implements Comparator<Integer> {

	@Override
	public int compare(Integer a, Integer b) {
		if(b > a)
			return 1;
		else if(b < a )
			return -1;
		else return 0; 
	}

}
