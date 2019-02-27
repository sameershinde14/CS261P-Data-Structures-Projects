package LinearHashMapTestCases;

import java.util.ArrayList;

import Hashing.LinearHashMap;

public class lhmtest00 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Starting test case 00 for creation and simple add");
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
		
		ArrayList<Integer> vals = new ArrayList<Integer>();
		startTime = System.nanoTime();
		for(int key: map.keySet()) {
			vals.add(map.get(key));
		}
		stopTime = System.nanoTime();
		elapsedTime = stopTime - startTime;
		System.out.println("Execution Time for getting:" + elapsedTime + " nano secs");
		System.out.println();
		System.out.println("Test case 00 completed. Results will be examined");
	}

}
