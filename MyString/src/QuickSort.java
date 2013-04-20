import java.util.Arrays;


public class QuickSort {
	
	public static void sort(int[] arr, int low, int high){
		
		if(arr.length <= 1)
			return;
		
		int pivot = arr[low + (high-low)/2];
		
		int i = low;
		int j = high;
		
		System.out.println(Arrays.toString(arr)+ " low : " + low + " high: " + high + " arr[pivot] " + pivot);
		
		while(i<=j){
			
			while( arr[i] < pivot)
				i++;
			
			while (arr[j] > pivot)
				j--;
			
			if(i<=j){
				exchange(arr,i,j);
				i++;
				j--;
			}
			
		}
		
		if(low < j)
			sort(arr,low,j);
		if(i < high)
			sort(arr,i , high);
		
	}

	private static void exchange(int[] arr, int i, int j) {
		
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

}
