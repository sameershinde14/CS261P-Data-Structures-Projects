package LinearHashMapTestCases;

import Hashing.LinearHashMap;

public class lhmtest02 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Starting test case 02 for getters");
		LinearHashMap map = new LinearHashMap(10);
		long startTime = System.nanoTime();
		map.put(10, 5);
		map.put(3, 2);
		map.put(56, 80);
		map.put(56, 78);
		map.put(67, 89);
		map.put(5, 89);
		map.put(5, 90);
		map.put(11, 100);
		map.put(22, 200);
		map.put(33, 300);
		map.put(55, 340);
		map.put(66, 90);
		
		long stopTime = System.nanoTime();
	    long elapsedTime = stopTime - startTime;
	    System.out.println("Execution Time for adding:" + elapsedTime + " nano secs");
	    
	    startTime = System.nanoTime();
	    System.out.println("Key: " + 10 + ", value:" + map.get(10));
	    System.out.println("Key: " + 3 + ", value:" + map.get(3));
	    System.out.println("Key: " + 5 + ", value:" + map.get(5));
	    System.out.println("Key: " + 11 + ", value:" + map.get(11));
	    System.out.println("Key: " + 33 + ", value:" + map.get(33));
	    System.out.println("Key: " + 66 + ", value:" + map.get(66));
	    System.out.println("Key: " + 55 + ", value:" + map.get(55));
	    System.out.println("Key: " + 67 + ", value:" + map.get(67));
	    System.out.println("Key: " + 56 + ", value:" + map.get(56));
	    System.out.println("Key: " + 22 + ", value:" + map.get(22));
	    stopTime = System.nanoTime();
	    elapsedTime = stopTime - startTime;
	    
	    System.out.println("Execution Time for getting:" + elapsedTime + " nano secs");
	   
		System.out.println("Test case 02 completed. Results will be examined");
	}

}
