package myTrials;


public class Pointers {

	public static void main(String args[]) {

		
		//this is how it works with primitive data types
		int originalPrimitiveType = 5;
		int newPrimitiveType = originalPrimitiveType;
		newPrimitiveType = newPrimitiveType - 2;
		System.out.println(originalPrimitiveType);
		System.out.println(newPrimitiveType);
	
	//how it works with objects
		
		
	
		
		

	//this is how it works with arrays
	
	
	int[] originalArray = {2, 3};
	int[] newArray = originalArray;
	int[] clonedArray = originalArray.clone();
	
	newArray[0] = 5;
	clonedArray[0]= 6;
	System.out.println(originalArray[0]);
	System.out.println(newArray[0]);
	System.out.println(clonedArray[0]);
	
	
	//how it works with dynamic type (Python)
	
	Lamp originalLamp = new Lamp(true, "red");
	Lamp newLamp = originalLamp;
	newLamp.color = "blue";
	newLamp.state = false;
	
	System.out.println(originalLamp.color + " " + originalLamp.state);
	System.out.println(newLamp.color + " " + newLamp.state);
	
	
	/**
	 * Summary
	 * When you do newObject = originalObject they point to the same memory
	 * When you do newPrimive = originalPromitive theyDoNotPointToTheSameMemory
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 **/
	//sprawdz czy arrayLists jak przekazuje z metody na metode to moge je tak manipulawac w przeciwienstwie do innych objects
	 
	// how is it in dynamic languages? (praca) // how is it with python?
	}
	
}