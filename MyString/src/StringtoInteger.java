
public class StringtoInteger {
	
	public static int atoi(String input){
		
		int sum =  0;
		int power = input.length() - 1;
		
		for(int index = 0; index < input.length(); index++){
			
			int currentDigit = input.charAt(index) - '0';
			
			sum += currentDigit * Math.pow(10, power);
			
			power--;
			
		}
		
		return sum;
	}

}
