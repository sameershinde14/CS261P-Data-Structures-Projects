package CuckooHashMapTestCases;

import java.util.HashMap;

import Hashing.AbstractMap;
import Hashing.CuckooHashMap;
import LinearHashMapTestCases.Utilities;

public class ckhmtest02 {

	public static void main(String[] args) {
		System.out.println("Starting test case 02 for Cycle");
		AbstractMap<Integer,Integer> map = new CuckooHashMap<>(11);
		HashMap<Integer,Integer> hmap = new HashMap<>();
		
		long startTime = System.nanoTime();
		map.put(12, 5);
		map.put(26, 2);
		map.put(92, 80);
		map.put(23, 78);
		map.put(28, 89);
		map.put(94, 89);
		map.put(15, 90);
		
		long stopTime = System.nanoTime();
		hmap.put(12, 5);
		hmap.put(26, 2);
		hmap.put(92, 80);
		hmap.put(23, 78);
		hmap.put(28, 89);
		hmap.put(94, 89);
		hmap.put(15, 90);
		
		long elapsedTime = stopTime - startTime;
	    System.out.println("Execution Time for adding:" + elapsedTime + " nano secs");
	    map.display();
	    System.out.println();
	    if(Utilities.compare(map,hmap))
			System.out.println("Testcase 02 successfully completed.");
		else
			System.out.println("Testcase 02 failed.");

	}

}
