
public class SubString {
	
	public static boolean isSubstring(String original, String subString){
		
		if((original == null) || (subString == null))
			 return false;
		
		for(int i = 0 ; i < original.length(); i++){
			int j;
			for( j = 0; j < subString.length() && i+j < original.length(); j++){

				if(original.charAt(i+j) != subString.charAt(j))
					break;
			}
			
			if(j == subString.length())
				return true;
		}
		return false;
		
		
	}

}
