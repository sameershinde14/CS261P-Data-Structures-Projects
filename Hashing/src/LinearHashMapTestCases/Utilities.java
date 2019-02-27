package LinearHashMapTestCases;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import Hashing.LinearHashMap;
import Hashing.AbstractMap;
import Hashing.ChainedHashMap;
import Hashing.CuckooHashMap;
import Hashing.CuckooHashTable;
import Hashing.DoubleHashMap;

public class Utilities {
	public static boolean compare(LinearHashMap map, Map<Integer,Integer> omap) {
		
		if(omap.size()!=map.size()) return false;
		
		for (Map.Entry<Integer, Integer> entry : omap.entrySet()) {
			Integer key = entry.getKey();
		    Integer value = entry.getValue();
		    if(!map.contains(key)) return false;
		    try {
		    	Integer nVal = map.get(key);
				if(!nVal.equals(value)) {
					return false;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
		}
		return true;
		
	}
	public static boolean compare(CuckooHashTable map, Map<Integer,Integer> omap) {
		
		if(omap.size()!=map.size()) return false;
		
		for (Map.Entry<Integer, Integer> entry : omap.entrySet()) {
			Integer key = entry.getKey();
		    Integer value = entry.getValue();
		    if(!map.containsKey(key)) return false;
		    try {
		    	Integer nVal = (Integer) map.get(key);
				if(!nVal.equals(value)) {
					return false;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
		}
		return true;
		
	}
	public static boolean compare(AbstractMap map, Map<Integer,Integer> omap) {
		
		if(omap.size()!=map.size()) return false;
		for (Map.Entry<Integer, Integer> entry : omap.entrySet()) {
		    Integer key = entry.getKey();
		    Integer value = entry.getValue();
		    if(!map.containsKey(key)) {
		    	System.out.println("key: " + key + " , value: " + value + "Not found");
		    	return false;
		    }
		    try {
		    	Integer nVal = (Integer) map.get(key);
				if(!nVal.equals(value)) {
					return false;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
		}
		
		return true;
		
	}
	public static boolean compare(ChainedHashMap map, Map<Integer,Integer> omap) {
		
		if(omap.size()!=map.size()) return false;
		for (Map.Entry<Integer, Integer> entry : omap.entrySet()) {
		    Integer key = entry.getKey();
		    Integer value = entry.getValue();
		    if(!map.containsKey(key)) return false;
		    try {
		    	Integer nVal = (Integer) map.get(key);
				if(!nVal.equals(value)) {
					return false;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
		}
		return true;
		
	}
	public static LinearHashMap populateLinearHashMap(String fileName) {
		// TODO Auto-generated method stub
		LinearHashMap lhm = null;
		int n;
		BufferedReader reader;
		String filePath = new File("").getAbsolutePath();
		long elapsedTime = 0;
		long startTime = 0;
		long stopTime = 0;
		try {
			filePath += fileName;
			reader = new BufferedReader(new FileReader(filePath));
			String line = reader.readLine();
			n = Integer.parseInt(line);
			lhm = new LinearHashMap(n);
			line = reader.readLine();
			while (line != null) {
				String [] pair = line.split(",");
				int key = Integer.parseInt(pair[0].trim());
				int value = Integer.parseInt(pair[1].trim());
				startTime = System.nanoTime();
				lhm.put(key, value);
				stopTime = System.nanoTime();
				elapsedTime = elapsedTime + (stopTime - startTime);
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		System.out.println("Time required for adding "+ lhm.size() + " unique keys:" + elapsedTime + "nano sec");
		return lhm;
	}
	
	public static ChainedHashMap populateChainedHashMap(String fileName, int k, int val) {
		// TODO Auto-generated method stub
		ChainedHashMap<Integer,Integer> chm = new ChainedHashMap<>();
		BufferedReader reader;
		String filePath = new File("").getAbsolutePath();
		long elapsedTime = 0;
		long startTime = 0;
		long stopTime = 0;
		try {
			filePath += fileName;
			reader = new BufferedReader(new FileReader(filePath));
			String line = reader.readLine();
			line = reader.readLine();
			while (line != null) {
				String [] pair = line.split(",");
				int key = Integer.parseInt(pair[0].trim());
				int value = Integer.parseInt(pair[1].trim());
				startTime = System.nanoTime();
				chm.put(key, value);
				stopTime = System.nanoTime();
				elapsedTime = elapsedTime + (stopTime - startTime);
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		System.out.println("Time required for adding "+ chm.size() + " unique keys:" + elapsedTime + "nano sec");
		return chm;
	}
	public static CuckooHashMap populateCuckooHashMap(String fileName, int k, int val) {
		// TODO Auto-generated method stub
		CuckooHashMap<Integer,Integer> chm = new CuckooHashMap<>(11);
		BufferedReader reader;
		String filePath = new File("").getAbsolutePath();
		long elapsedTime = 0;
		long startTime = 0;
		long stopTime = 0;
		try {
			filePath += fileName;
			reader = new BufferedReader(new FileReader(filePath));
			String line = reader.readLine();
			line = reader.readLine();
			while (line != null) {
				String [] pair = line.split(",");
				int key = Integer.parseInt(pair[0].trim());
				int value = Integer.parseInt(pair[1].trim());
				startTime = System.nanoTime();
				chm.put(key, value);
				stopTime = System.nanoTime();
				elapsedTime = elapsedTime + (stopTime - startTime);
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		System.out.println("Time required for adding "+ chm.size() + " unique keys:" + elapsedTime + "nano sec");
		return chm;
	}
	
	public static CuckooHashTable populateCuckooHashTable(String fileName, int k, int val) {
		// TODO Auto-generated method stub
		CuckooHashTable<Integer,Integer> chm = new CuckooHashTable<>(11);
		BufferedReader reader;
		String filePath = new File("").getAbsolutePath();
		long elapsedTime = 0;
		long startTime = 0;
		long stopTime = 0;
		try {
			filePath += fileName;
			reader = new BufferedReader(new FileReader(filePath));
			String line = reader.readLine();
			line = reader.readLine();
			while (line != null) {
				String [] pair = line.split(",");
				int key = Integer.parseInt(pair[0].trim());
				int value = Integer.parseInt(pair[1].trim());
				startTime = System.nanoTime();
				chm.put(key, value);
				stopTime = System.nanoTime();
				elapsedTime += (stopTime - startTime);
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		System.out.println("Time required for adding "+ chm.size() + " unique keys:" + elapsedTime + "nano sec");
		return chm;
	}
	
	
	public static DoubleHashMap populateDoubleHashMap(String fileName, int k, int val) {
		// TODO Auto-generated method stub
		DoubleHashMap<Integer,Integer> chm = new DoubleHashMap<>();
		BufferedReader reader;
		String filePath = new File("").getAbsolutePath();
		long elapsedTime = 0;
		long startTime = 0;
		long stopTime = 0;
		try {
			filePath += fileName;
			reader = new BufferedReader(new FileReader(filePath));
			String line = reader.readLine();
			line = reader.readLine();
			while (line != null) {
				String [] pair = line.split(",");
				int key = Integer.parseInt(pair[0].trim());
				int value = Integer.parseInt(pair[1].trim());
				startTime = System.nanoTime();
				chm.put(key, value);
				stopTime = System.nanoTime();
				elapsedTime = elapsedTime + (stopTime - startTime);
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		System.out.println("Time required for adding "+ chm.size() + " unique keys:" + elapsedTime + "nano sec");
		return chm;
	}
	
	public static HashMap<Integer,Integer> populateOriginalHashMap(String fileName) {
		// TODO Auto-generated method stub
		HashMap<Integer,Integer> mp = new HashMap<Integer, Integer>();
		BufferedReader reader;
		String filePath = new File("").getAbsolutePath();
		
		try {
			filePath += fileName;
			reader = new BufferedReader(new FileReader(filePath));
			String line = reader.readLine();
			line = reader.readLine();
			while (line != null) {
				String [] pair = line.split(",");
				int key = Integer.parseInt(pair[0].trim());
				int value = Integer.parseInt(pair[1].trim());
				mp.put(key, value);
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		return mp;
	}
}
