package DoubleHashMapTestCases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import Hashing.AbstractMap;
import Hashing.DoubleHashMap;
import LinearHashMapTestCases.Utilities;

public class dhmtest00 {

	public static void main(String[] args) {
		System.out.println("Starting test case 00 for creation and simple add");
		AbstractMap<Integer, Integer> map = new DoubleHashMap<>();
		HashMap<Integer, Integer> hmap = new HashMap<>();
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
	    System.out.println();
	    
	    ArrayList<Object> vals = new ArrayList<Object>();
		
		Set<Object> keys = new HashSet<Object>();
		Object[] keySet = keys.toArray();
		startTime = System.nanoTime();
		for(Object key: keySet) {
			vals.add(map.get(key));
		}
		stopTime = System.nanoTime();
		elapsedTime = stopTime - startTime;
		System.out.println("Execution Time for getting:" + elapsedTime + " nano secs");
	    
	    if(Utilities.compare(map,hmap))
			System.out.println("Testcase 00 successfully completed.");
		else
			System.out.println("Testcase 00 failed.");
	}

}
