package ChainedHashMapTestCases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import Hashing.ChainedHashMap;
import LinearHashMapTestCases.Utilities;

public class chmtest05 {
	public static ChainedHashMap map;
	public static Map<Integer,Integer> omap = new HashMap<Integer,Integer>();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("Starting test case 05 for adding very huge number of keys");
		String fileName = "/src/LinearHashMapTestCases/testInput10.txt";
		long startTime = 0;
		map = Utilities.populateChainedHashMap(fileName, 0, 0);
		long stopTime = 0;
		omap = Utilities.populateOriginalHashMap(fileName);
		long elapsedTime = 0;
		
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
			System.out.println("Testcase 05 successfully completed.");
		else
			System.out.println("Testcase 05 failed.");
	}
}
