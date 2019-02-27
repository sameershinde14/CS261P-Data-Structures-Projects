package CuckooHashMapTestCases;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import Hashing.AbstractMap;
import Hashing.CuckooHashMap;
import LinearHashMapTestCases.Utilities;

public class ckhmtest07 {

	public static void main(String[] args) {
		System.out.println("Starting test case 7 for removing keys");
		AbstractMap<Integer,Integer> map = new CuckooHashMap<>();
		Map<Integer,Integer> omap = new HashMap<Integer,Integer>();
		String fileName = "/src/LinearHashMapTestCases/testInput03.txt";
		long startTime = 0;
		map = Utilities.populateCuckooHashMap(fileName, 0, 0);
		long stopTime = 0;
		omap = Utilities.populateOriginalHashMap(fileName);
		long elapsedTime = 0;
		
	    
	    Set<Object> keySet= map.keySet();
		Object[] tmp = keySet.toArray();
		for(int i = 0; i < 5; i++) {
			int rnd = new Random().nextInt(map.size());
			
			Object keyToBeDeleted = tmp[rnd];
			if(map.containsKey(keyToBeDeleted)) {
				try {
					startTime = System.nanoTime();
					map.remove(keyToBeDeleted);
					stopTime = System.nanoTime();
					elapsedTime = elapsedTime + (stopTime - startTime);
					omap.remove(keyToBeDeleted);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		System.out.println("Time required for 5 removal:" + elapsedTime + " nano sec");
		
		if(Utilities.compare(map,omap)) 
			System.out.println("Testcase 07 completed Successfully");
		else
			System.out.println("Testcase 07 Failed");
	}

}
