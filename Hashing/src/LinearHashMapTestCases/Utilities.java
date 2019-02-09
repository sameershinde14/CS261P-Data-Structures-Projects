package LinearHashMapTestCases;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import Hashing.LinearHashMap;

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
	public static LinearHashMap populateLinearHashMap(String fileName) {
		// TODO Auto-generated method stub
		LinearHashMap lhm = null;
		int n;
		BufferedReader reader;
		String filePath = new File("").getAbsolutePath();
		
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
				lhm.put(key, value);
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		return lhm;
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
