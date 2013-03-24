package myTrials;

public class ReversingArray {
	
	public static void main(String args[]) {
		
		
		int[] array = { 2, 3};
		reverseArray(array);
		System.out.println(array[0]);
	}
	
	
	
	
	private static void reverseArray(int[] array) {
		
		if (array.length == 2 ) {
			
			int temp = array[0];
			array[0] = array[1];
			array[1] = temp;
		}
		
	}

}
