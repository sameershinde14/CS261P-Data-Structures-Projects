package ChainedHashMapTestCases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import Hashing.ChainedHashMap;
import LinearHashMapTestCases.Utilities;

public class chmtest00 {

	public static void main(String[] args) throws Exception {
		
		System.out.println("Starting test case 00 for creation and simple add");
		ChainedHashMap<Integer, Integer> map = new ChainedHashMap<>();
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

