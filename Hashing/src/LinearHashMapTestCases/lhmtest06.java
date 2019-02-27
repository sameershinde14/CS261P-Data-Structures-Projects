package LinearHashMapTestCases;

import Hashing.LinearHashMap;

public class lhmtest06 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Starting test case 06 for simple remove");
		LinearHashMap map = new LinearHashMap(10);
		long startTime = System.nanoTime();
		map.put(10, 5);
		map.put(3, 2);
		map.put(56,80);
		long stopTime = System.nanoTime();
	    long elapsedTime = stopTime - startTime;
	    System.out.println("Execution Time for adding:" + elapsedTime + " nano secs");
	   
		System.out.println("Key set:");
		for(int key: map.keySet()) {
			System.out.print(key + ", ");
		}
		System.out.println();
		System.out.println("Value set:");
		for(int value: map.valueSet()) {
			System.out.print(value + ", ");
		}
		System.out.println();
		startTime = System.nanoTime();
		map.remove(10);
		stopTime = System.nanoTime();
		elapsedTime = stopTime - startTime;
		System.out.println("Time for 1 removal:"+ elapsedTime + " nano sec");
		System.out.println("After Removing 10");
		System.out.println("Key set:");
		for(int key: map.keySet()) {
			System.out.print(key + ", ");
		}
		System.out.println();
		System.out.println("Value set:");
		for(int value: map.valueSet()) {
			System.out.print(value + ", ");
		}
		System.out.println();
		System.out.println("Test case 00 completed. Results will be examined");
	}

}
