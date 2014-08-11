package com.keyao.test.string;

public class ReverseString {
	private String value;
	ReverseString(String input) {
		value = input;
	}
	
	void doReverse(char[] charArray, int start, int end) {
		if (start >= end) return;  // I made a mistake here
		if (end < 1) return;
	    for (int i=start,j=end; i<j; i++,j--) {
	    	char temp = charArray[i];
	    	charArray[i] = charArray[j];
	    	charArray[j] = temp;  // I had a typo here
	    }
	    return;
	}
	
	void doReverse() {
		char[] charArray = value.toCharArray();
		int i = 0;
		int length = charArray.length - 1;
		int start = 0;
		while (i <= length){
			if (charArray[i] == ' ') {
				doReverse(charArray, start, i-1);
				start = i+1;
			}
			i++;
		}
		/* do not forget the boundary condition */
		if (start < length)
			doReverse(charArray, start, length);
			
		doReverse(charArray, 0, length);
		value = new String(charArray);
		System.out.println(value);
	}
	
	static public void main(String[] arg) throws Exception {
		ReverseString inputString = new ReverseString("I am   Jack  ");
		inputString.doReverse();
	}
}
