package Hashing;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
public class ChainedHashMap {
	private List<LinkedList<Entry>> map;
	private int hashFactor = 10;
	private int currentSize;
	private int loadFactor = 0;
	private int maxSize = 0;
	
	public ChainedHashMap() {
		map = new ArrayList<LinkedList<Entry>>(); 
		currentSize = 0;
	}
	public void put(int key, int value) {
		if(currentSize == 0) {
			addFirstElement(key,value);
			return;
		}
		
		int h = getHashValue(key);
		
		
	}
	public int getHashValue(int key) {
		return hashCode(key) % currentSize;
	}
	
	//private helpers
	
	private int hashCode(int key) {
		return key%hashFactor;
	}
	private void addFirstElement(int key, int value) {
		LinkedList<Entry> tmp = new LinkedList<Entry>();
		Entry e =new Entry(key,value);
		tmp.add(e);
		map.add(tmp);
		currentSize++;
		return;
	}
}
