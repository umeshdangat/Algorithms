public class MyString {
	
	 String value;
	 
	 public MyString(String value) {
		 this.value = value;
	 }
	 
	 
	 public void swap(MyString b) {
		 
		 String temp;
		 temp = this.value;
		 this.value = b.value;
		 b.value = temp;

	 }
	
	 
	 public String getValue() {
		 return this.value;
	 }
	

}
