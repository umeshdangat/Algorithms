
public class IthItem {
	
	public int getIthLargestNumber(int[] numbers, int low, int high, int kthLargest) {
		
		int pivot = numbers[low + (high-low)/2];
		int i = low;
		int j = high;
		
		while( i <= j) {
			
			while(numbers[i] < pivot) {
				i++;
			}
			
			while(numbers[j] > pivot) {
				j--;
			}
			
			if(i <= j) {
				int temp = numbers[i];
				numbers[i]=numbers[j];
				numbers[j] = temp;
				i++;
				j--;
			}
			
		}
		
		int pivotIndex = j+1;
		int rightArraySize = high - pivotIndex;
		//right array starts pivotIndex + 1
		if(rightArraySize == kthLargest) {
			int min  = minimumItem(numbers, pivotIndex+1,high);
			return min;
		}
		
		//if right side has more than k elements
		else if(rightArraySize > kthLargest){
			return getIthLargestNumber(numbers, pivotIndex+1, high, kthLargest);
		}
		
		//if right side has less than k elements
		else {
			int newKthLargestSize = kthLargest - rightArraySize;
			//special case
			if(rightArraySize==0) {
				return numbers[pivotIndex];
			}
			return getIthLargestNumber(numbers, low, pivotIndex, newKthLargestSize);
		}
		
	}

	private int minimumItem(int[] array, int start, int end) {
		
		int min = Integer.MAX_VALUE;
		for(int index = start; index <= end; index++){
			min = Math.min(min,array[index]);
		}
		return min;
	}

	public int getIthSmallestNumber(int[] numbers, int low, int high, int ithSmallest) {
		
		
		int pivot = numbers[low + (high-low)/2];
		int iIndex = low;
		int jIndex = high;
		
		while(iIndex <= jIndex) {
			
			while(numbers[iIndex] < pivot ) {
				iIndex++;
			}
			
			while(numbers[jIndex] > pivot) {
				jIndex--;
			}
			
			if(iIndex <= jIndex) {
				int temp = numbers[iIndex];
				numbers[iIndex] = numbers[jIndex];
				numbers[jIndex] = temp;
				iIndex++;
				jIndex--;
			}
		}
		
		int pivotIndex = jIndex + 1;
		int leftSize = pivotIndex-low;

		//if leftSize == ithSmallest element the return largest element on leftside
		if(leftSize == ithSmallest) {
		  return MaxElement(numbers,0, pivotIndex-1);
		}
		
		//left side is greater do again on left side
		else if (leftSize > ithSmallest){
			return getIthSmallestNumber(numbers, 0, pivotIndex-1, ithSmallest);
		}
		  
		//left side is smaller then redo on right side for 
		else {
			int newIthSmallest = ithSmallest - leftSize;
			if(leftSize==0){
				return numbers[pivotIndex];
			}
			return getIthSmallestNumber(numbers, pivotIndex, high, newIthSmallest);
		}
	}

	private int MaxElement(int[] numbers, int start, int end) {
		
		int max = Integer.MIN_VALUE;
		
		for(int i = start; i <= end; i++) {
			max = Math.max(max,numbers[i]);
		}
		
		return max;
	}

	
}
