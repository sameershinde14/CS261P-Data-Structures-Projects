package CuckooHashMapTestCases;

import java.util.HashMap;

import Hashing.AbstractMap;
import Hashing.CuckooHashMap;
import LinearHashMapTestCases.Utilities;

public class ckhmtest06 {

	public static void main(String[] args) {
		
		System.out.println("Starting test case 06 for creation and simple add");
		AbstractMap<Integer,Integer> map = new CuckooHashMap<>();
		HashMap<Integer,Integer> hmap = new HashMap<>();
		long startTime = System.nanoTime();
		map.put(10, 5);
		map.put(3, 2);
		map.put(56,80);
		long stopTime = System.nanoTime();
		hmap.put(10, 5);
		hmap.put(3, 2);
		hmap.put(56,80);
	    long elapsedTime = stopTime - startTime;
	    System.out.println("Execution Time for adding:" + elapsedTime + " nano secs");
	    map.display();
	    System.out.println("");
	    startTime = System.nanoTime();
		map.remove(10);
		stopTime = System.nanoTime();
		elapsedTime = stopTime - startTime;
		hmap.remove(10);
		System.out.println("Execution time for one removal:" + elapsedTime +" nano sec");
		map.display();
		System.out.println("");
		if(Utilities.compare(map, hmap))
			System.out.println("Test case 06 completed. Results will be examined");
		else
			System.out.println("Test case 06 failed");

	}

}
