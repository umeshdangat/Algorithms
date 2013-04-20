import java.util.ArrayList;
import java.util.List;


public class PascalsTriangle {
	
	List<Integer> getPascalRow(int rowNumber) {
		
		if(rowNumber==1){
			List<Integer> pascals = new ArrayList<Integer>();
			pascals.add(1);
			return pascals;
		}
		
		List<Integer> pascals = getPascalRow(rowNumber-1);
		List<Integer> result = new ArrayList<Integer>();

		result.add(1);
		for(int i=1; i<pascals.size(); i++) {
			result.add(pascals.get(i-1) + pascals.get(i));
		}
		result.add(1);
			
		return result;
	}

}
