package ChainedHashMapTestCases;

import java.util.HashMap;

import Hashing.ChainedHashMap;
import LinearHashMapTestCases.Utilities;

public class chmtest02 {
	public static void main(String[] args) throws Exception {
		
		System.out.println("Starting test case 02 for getters");
		ChainedHashMap<Integer,Integer> map = new ChainedHashMap<>();
		HashMap<Integer,Integer> hmap = new HashMap<>();
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
	    
	    hmap.put(10, 5);
		hmap.put(3, 2);
		hmap.put(56, 80);
		hmap.put(56, 78);
		hmap.put(67, 89);
		hmap.put(5, 89);
		hmap.put(5, 90);
		hmap.put(11, 100);
		hmap.put(22, 200);
		hmap.put(33, 300);
		hmap.put(55, 340);
		hmap.put(66, 90);
	    
	    startTime = System.nanoTime();
	    System.out.println("Key: " + 10 + ", value:" + map.get(10));
	    System.out.println("Key: " + 3 + ", value:" + map.get(3));
	    System.out.println("Key: " + 5 + ", value:" + map.get(5));
	    System.out.println("Key: " + 11 + ", value:" + map.get(11));
	    System.out.println("Key: " + 33 + ", value:" + map.get(33));
	    System.out.println("Key: " + 66 + ", value:" + map.get(66));
	    System.out.println("Key: " + 55 + ", value:" + map.get(55));
	    System.out.println("Key: " + 67 + ", value:" + map.get(67));
	    System.out.println("Key: " + 56 + ", value:" + map.get(56));
	    System.out.println("Key: " + 22 + ", value:" + map.get(22));
	    stopTime = System.nanoTime();
	    elapsedTime = stopTime - startTime;
	    
	    System.out.println("Execution Time for getting:" + elapsedTime + " nano secs");
	   
	    if(Utilities.compare(map,hmap))
			System.out.println("Testcase 02 successfully completed.");
		else
			System.out.println("Testcase 02 failed.");
	}
}
