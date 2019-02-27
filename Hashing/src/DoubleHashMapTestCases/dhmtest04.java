package DoubleHashMapTestCases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import Hashing.AbstractMap;
import Hashing.DoubleHashMap;
import LinearHashMapTestCases.Utilities;

public class dhmtest04 {

	public static void main(String[] args) {
	
		System.out.println("Starting test case 04 for getting non-existing key");
		AbstractMap<Integer,Integer> map = new DoubleHashMap<>();
		Map<Integer,Integer> omap = new HashMap<Integer,Integer>();
		String fileName = "/src/LinearHashMapTestCases/testInput04.txt";
		int k = 0;
		int v = 0;
		
		long startTime = 0;
		map = Utilities.populateDoubleHashMap(fileName, k , v);
		long stopTime = 0;
		omap = Utilities.populateOriginalHashMap(fileName);
		long elapsedTime = 0;
		//System.out.println("Execution Time for adding:" + elapsedTime + " nano secs");
		//map.display();
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
		
	    if(Utilities.compare(map,omap))
			System.out.println("Testcase 04 successfully completed.");
		else
			System.out.println("Testcase 04 failed.");
	}

}
