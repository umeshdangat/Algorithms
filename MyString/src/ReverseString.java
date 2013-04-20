
public class ReverseString {
	
	public static char[] reverseString(char[] inputChars) {
		
		
		int low = 0;
		int high = inputChars.length-1;
		
		while(low < high) {
			
			char temp = inputChars[low];
			inputChars[low] = inputChars[high];
			inputChars[high] = temp;
			low++;
			high--;
		}
		
		
		return inputChars;
		
	}



}
