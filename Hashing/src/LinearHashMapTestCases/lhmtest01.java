package LinearHashMapTestCases;

import java.util.ArrayList;

import Hashing.LinearHashMap;

public class lhmtest01 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Starting test case 01 for duplicates");
		LinearHashMap map = new LinearHashMap(10);
		long startTime = System.nanoTime();
		map.put(10, 5);
		map.put(3, 2);
		map.put(56, 80);
		map.put(56, 78);
		map.put(67, 89);
		map.put(5, 89);
		map.put(5, 90);
		map.put(11, 100);
		map.put(22, 200);
		map.put(33, 300);
		map.put(55, 340);
		map.put(66, 90);
		
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
		
		ArrayList<Integer> vals = new ArrayList<Integer>();
		startTime = System.nanoTime();
		for(int key: map.keySet()) {
			vals.add(map.get(key));
		}
		stopTime = System.nanoTime();
		elapsedTime = stopTime - startTime;
		System.out.println("Execution Time for getting:" + elapsedTime + " nano secs");
		
		System.out.println("Test case 01 completed. Results will be examined");
	}

}
