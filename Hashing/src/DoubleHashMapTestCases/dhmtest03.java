package DoubleHashMapTestCases;

import java.util.HashMap;
import java.util.Map;

import Hashing.AbstractMap;
import Hashing.DoubleHashMap;
import LinearHashMapTestCases.Utilities;

public class dhmtest03 {

	public static void main(String[] args) {
		
		System.out.println("Starting test case 03 for getting non-existing key");
		AbstractMap<Integer,Integer> map = new DoubleHashMap<>();
		Map<Integer,Integer> omap = new HashMap<Integer,Integer>();
		String fileName = "/src/LinearHashMapTestCases/testInput03.txt";
		int k = 0;
		int v = 0;
		long startTime = 0;
		map = Utilities.populateDoubleHashMap(fileName, k , v);
		long stopTime = 0;
		omap = Utilities.populateOriginalHashMap(fileName);
		long elapsedTime = stopTime - startTime;
		System.out.println("Execution Time for adding:" + elapsedTime + " nano secs");
		
		startTime = System.nanoTime();
		   
	    try {
	    	map.get(78);
	    }
	    catch(Exception e) {
	    	System.out.println("Exception:" + e.getMessage());
	    }
	    stopTime = System.nanoTime();
	    elapsedTime = stopTime - startTime;
	    
	    System.out.println("Execution Time for Unsuccessful search:" + elapsedTime + " nano secs");
	    
	    startTime = System.nanoTime();
		   
	    try {
	    	map.get(5);
	    }
	    catch(Exception e) {
	    	System.out.println("Exception:" + e.getMessage());
	    }
	    stopTime = System.nanoTime();
	    elapsedTime = stopTime - startTime;
	    
	    System.out.println("Execution Time for successful search:" + elapsedTime + " nano secs");
	    
	    if(Utilities.compare(map,omap))
			System.out.println("Testcase 03 successfully completed.");
		else
			System.out.println("Testcase 03 failed.");

	}

}
