package DoubleHashMapTestCases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import Hashing.AbstractMap;
import Hashing.DoubleHashMap;
import LinearHashMapTestCases.Utilities;

public class dhmtest08 {

	public static void main(String[] args) {
		
		System.out.println("Starting test case 8 for removing keys");
		AbstractMap<Integer, Integer> map = new DoubleHashMap<>();
		Map<Integer,Integer> omap = new HashMap<Integer,Integer>();
		String fileName = "/src/LinearHashMapTestCases/testInput10.txt";
		long startTime = 0;
		map = Utilities.populateDoubleHashMap(fileName, 0, 0);
		long stopTime = 0;
		omap = Utilities.populateOriginalHashMap(fileName);
		long elapsedTime = 0;
		
		int oldSize = map.size();
		
		Set<Object> keySet= map.keySet();
		ArrayList<Object> temp = new ArrayList<>();
		temp.addAll(keySet);
		elapsedTime = 0;
		
		
		for(int i = 0; i < map.size(); i++) {
			int rnd = new Random().nextInt(map.size());
			Object keyToBeDeleted = temp.get(rnd);
			if(map.containsKey(keyToBeDeleted)) {
				try {
					startTime = System.nanoTime();
					map.remove(keyToBeDeleted);
					stopTime = System.nanoTime();
					elapsedTime = elapsedTime +  (stopTime-startTime);
					omap.remove(keyToBeDeleted);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			temp.remove(keyToBeDeleted);
		}
		
		System.out.println("Execution Time for deleting "+(oldSize-map.size()) + " unique keys:"  + elapsedTime + " nano secs");
		
		if(Utilities.compare(map,omap))
			System.out.println("Testcase 08 successfully completed.");
		else
			System.out.println("Testcase 08 failed.");

	}

}
