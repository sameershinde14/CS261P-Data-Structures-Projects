package LinearHashMapTestCases;
import java.util.*;

import Hashing.LinearHashMap;

public class lhmtest04 {
	public static LinearHashMap map;
	public static Map<Integer,Integer> omap = new HashMap<Integer,Integer>();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("Starting test case 04 for adding huge number of keys");
		String fileName = "/src/LinearHashMapTestCases/testInput04.txt";
		long startTime = System.currentTimeMillis();
		map = Utilities.populateLinearHashMap(fileName);
		long stopTime = System.currentTimeMillis();
		omap = Utilities.populateOriginalHashMap(fileName);
		long elapsedTime = stopTime - startTime;
		System.out.println("Execution Time for adding:" + elapsedTime + " milli secs");
		
		if(Utilities.compare(map,omap))
			System.out.println("Testcase 04 successfully completed.");
		else
			System.out.println("Testcase 04 failed.");
	}

}
