package LinearHashMapTestCases;

import java.util.*;

import Hashing.LinearHashMap;

public class lhmtest08 {
	public static LinearHashMap map;
	public static Map<Integer,Integer> omap = new HashMap<Integer,Integer>();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("Starting test case 08 for adding very huge number of keys and deleting huge number of keys");
		String fileName = "/src/LinearHashMapTestCases/testInput05.txt";
		long startTime = System.currentTimeMillis();
		map = Utilities.populateLinearHashMap(fileName);
		long stopTime = System.currentTimeMillis();
		omap = Utilities.populateOriginalHashMap(fileName);
		long elapsedTime = stopTime - startTime;
		System.out.println("Execution Time for adding "+map.size() + " unique keys:"  + elapsedTime + " milli secs");
		int oldSize = map.size();
		startTime = System.currentTimeMillis();
		for(int i = 0; i < map.size(); i++) {
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
		stopTime = System.currentTimeMillis();
		elapsedTime = stopTime - startTime;
		System.out.println("Execution Time for deleting "+(oldSize-map.size()) + " unique keys:"  + elapsedTime + " milli secs");
		if(Utilities.compare(map,omap))
			System.out.println("Testcase 08 successfully completed.");
		else
			System.out.println("Testcase 08 failed.");
	}

}
