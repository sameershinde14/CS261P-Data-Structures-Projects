package DoubleHashMapTestCases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import Hashing.AbstractMap;
import Hashing.DoubleHashMap;
import LinearHashMapTestCases.Utilities;

public class dhmtest01 {

	public static void main(String[] args) {
		System.out.println("Starting test case 01 for duplicates");
		AbstractMap<Integer,Integer> map = new DoubleHashMap<>();
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
	    long elapsedTime = stopTime - startTime;
	    
	    
	    System.out.println("Execution Time for adding:" + elapsedTime + " nano secs");
	    
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
			System.out.println("Testcase 01 successfully completed.");
		else
			System.out.println("Testcase 01 failed.");

	}

}
