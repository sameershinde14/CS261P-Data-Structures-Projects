package LinearHashMapTestCases;

import java.util.*;

import Hashing.LinearHashMap;

public class lhmtest03 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Starting test case 03 for getting non-existing key");
		LinearHashMap map = new LinearHashMap(10);
		Map<Integer,Integer> omap = new HashMap<Integer,Integer>();
		String fileName = "/src/LinearHashMapTestCases/testInput03.txt";
		long startTime = 0;
		map = Utilities.populateLinearHashMap(fileName);
		long stopTime = 0;
		omap = Utilities.populateOriginalHashMap(fileName);
		long elapsedTime = 0;
		
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
