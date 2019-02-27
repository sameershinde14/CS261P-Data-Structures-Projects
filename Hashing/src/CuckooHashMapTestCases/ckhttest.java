package CuckooHashMapTestCases;

import java.util.HashMap;
import java.util.Map;


import Hashing.CuckooHashTable;
import LinearHashMapTestCases.Utilities;

public class ckhttest {

	public static void main(String[] args) {
		
		System.out.println("Starting test case 04 for getting non-existing key");
		CuckooHashTable<Integer,Integer> map = new CuckooHashTable<>();
		Map<Integer,Integer> omap = new HashMap<Integer,Integer>();
		String fileName = "/src/LinearHashMapTestCases/testInput05.txt";
		int k = 0;
		int v = 0;
		long startTime = System.currentTimeMillis();
		map = Utilities.populateCuckooHashTable(fileName, k , v);
		long stopTime = System.currentTimeMillis();
		omap = Utilities.populateOriginalHashMap(fileName);
		long elapsedTime = stopTime - startTime;
		System.out.println("Execution Time for adding:" + elapsedTime + " milli secs");
		//map.display();
		System.out.println();
	    if(Utilities.compare(map,omap))
			System.out.println("Testcase 04 successfully completed.");
		else
			System.out.println("Testcase 04 failed.");

	}

}
