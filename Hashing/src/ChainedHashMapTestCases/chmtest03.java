package ChainedHashMapTestCases;

import java.util.HashMap;
import java.util.Map;

import Hashing.ChainedHashMap;
import LinearHashMapTestCases.Utilities;

public class chmtest03 {
	public static void main(String[] args) throws Exception {
		
		System.out.println("Starting test case 03 for getting non-existing key");
		ChainedHashMap<Integer,Integer> map = new ChainedHashMap<>();
		Map<Integer,Integer> omap = new HashMap<Integer,Integer>();
		String fileName = "/src/LinearHashMapTestCases/testInput03.txt";
		int k = 0;
		int v = 0;
		long startTime = 0;
		map = Utilities.populateChainedHashMap(fileName, k , v);
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
