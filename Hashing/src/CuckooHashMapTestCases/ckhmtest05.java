package CuckooHashMapTestCases;

import java.util.HashMap;
import java.util.Map;

import Hashing.AbstractMap;
import Hashing.CuckooHashMap;
import LinearHashMapTestCases.Utilities;

public class ckhmtest05 {

	public static AbstractMap<Integer,Integer> map;
	public static Map<Integer,Integer> omap = new HashMap<Integer,Integer>();
	public static void main(String[] args) {
		
		System.out.println("Starting test case 05 for adding very huge number of keys and causing an infinite loop");
		String fileName = "/src/LinearHashMapTestCases/testInput05.txt";
		long startTime = System.currentTimeMillis();
		map = Utilities.populateCuckooHashMap(fileName, 0, 0);
		long stopTime = System.currentTimeMillis();
		omap = Utilities.populateOriginalHashMap(fileName);
		long elapsedTime = stopTime - startTime;
		System.out.println("Execution Time for adding "+map.size() + " unique keys:"  + elapsedTime + " milli secs");
		
		if(Utilities.compare(map,omap))
			System.out.println("Testcase 05 successfully completed.");
		else
			System.out.println("Testcase 05 failed.");
	}


}
