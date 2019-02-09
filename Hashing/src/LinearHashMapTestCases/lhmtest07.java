package LinearHashMapTestCases;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import Hashing.LinearHashMap;

public class lhmtest07 {
	public static void main(String args[]) {
		System.out.println("Starting test case 7 for removing keys");
		LinearHashMap map = new LinearHashMap(10);
		Map<Integer,Integer> omap = new HashMap<Integer,Integer>();
		String fileName = "/src/LinearHashMapTestCases/testInput03.txt";
		long startTime = System.currentTimeMillis();
		map = Utilities.populateLinearHashMap(fileName);
		long stopTime = System.currentTimeMillis();
		omap = Utilities.populateOriginalHashMap(fileName);
		long elapsedTime = stopTime - startTime;
		System.out.println("Execution Time for adding:" + elapsedTime + " milli secs");
		
	    //startTime = System.nanoTime();
		
		for(int i = 0; i < 5; i++) {
			int rnd = new Random().nextInt(map.size());
			int[] tmp = map.keySet();
			int keyToBeDeleted = tmp[rnd];
			if(map.contains(keyToBeDeleted)) {
				try {
					map.remove(keyToBeDeleted);
					omap.remove(keyToBeDeleted);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		if(Utilities.compare(map,omap)) 
			System.out.println("Testcase 07 completed Successfully");
		else
			System.out.println("Testcase 07 Failed");
		
	}
}
