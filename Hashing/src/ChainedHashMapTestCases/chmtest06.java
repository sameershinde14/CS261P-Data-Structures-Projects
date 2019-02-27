package ChainedHashMapTestCases;

import java.util.HashMap;

import Hashing.ChainedHashMap;
import LinearHashMapTestCases.Utilities;

public class chmtest06 {
	public static void main(String[] args) throws Exception {
		
		System.out.println("Starting test case 06 for creation and simple add");
		ChainedHashMap<Integer,Integer> map = new ChainedHashMap<>();
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
	    
	    startTime = System.nanoTime();
		map.remove(10);
		stopTime = System.nanoTime();
		elapsedTime = stopTime - startTime;
		hmap.remove(10);
		System.out.println("Execution time for one removal:" + elapsedTime +" nano sec");
		if(Utilities.compare(map, hmap))
			System.out.println("Test case 06 completed. Results will be examined");
		else
			System.out.println("Test case 06 failed");
	}

}
