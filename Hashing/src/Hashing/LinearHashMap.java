package Hashing;

public class LinearHashMap {
	private int keys[];
	private int values[];
	private int maxSize;
	private int currentSize;
	private int hashFactor;
	private boolean occupied[];
	
	// MACROS
	private int NOT_FOUND = -1;
	
	public LinearHashMap(int n) {
		keys = new int[n];
		values= new int[n];
		occupied = new boolean[n];
		maxSize = n;
		currentSize = 0;
		hashFactor = 1;
	}
	
	public void put(int key, int value) throws Exception {
		if(isFull()) throw new Exception("Hashmap is full."); 
		int h = getHashValue(key);
		int i = h;	
		do
        {
            if (!occupied[i])
            {
                keys[i] = key;
                values[i] = value;
                occupied[i] = true;
                currentSize++;
                return;
            }
            if (keys[i] == (key)) 
            { 
                values[i] = value; 
                occupied[i] = true;
                return; 
            }            
            i = (i + 1) % maxSize;            
        } while (i != h);
		
	}
	
	public int get(int key) throws Exception {
		int h = getHashValue(key);
		if(!occupied[h]) throw new Exception("key not found");
		int i = h;
		do
	    {
            if (keys[i]==key)
                return values[h];
            i = (i + 1) % maxSize;
	    } while(i!=h);   
	    throw new Exception("key not found");
		//return NOT_FOUND;
	}
	
	public int size() {
		return currentSize;
	}
	
	public boolean isEmpty() {
		return currentSize == 0;
	}
	
	public boolean contains(int key) {
		int h = getHashValue(key);
		if(!occupied[h]) return false;
		int i = h;
		do
	    {
            if (keys[i]==key)
                return true;
            i = (i + 1) % maxSize;
	    } while(i!=h);   
	    return false;
	}
	
	public void remove(int key) throws Exception
	{
        if (!contains(key)) throw new Exception("key not found");
 
        int i = getHashValue(key);
        while (key != keys[i]) 
            i = (i + 1) % maxSize;        
        occupied[i] = false;
 
	    for (i = (i + 1) % maxSize; occupied[i] != false; i = (i + 1) % maxSize)
	    {
	        int tmp1 = keys[i], tmp2 = values[i];
	        occupied[i] = false;
	        currentSize--;  
	        put(tmp1, tmp2);            
	    }
	    currentSize--;        
	}
		
	public int[] keySet() {
		return formKeySet();
	}
	
	public int[] valueSet() {
		return formValueSet();
	}
	
	public boolean isFull() {
		return currentSize == maxSize;
	}
	
	// 	PRIVATE HELPERS
	
	private int getHashValue(int key) {
		return hashCode(key) % maxSize;
	}
	
	private int hashCode(int key) {
		return key; //% hashFactor;
	}

	private int[] formKeySet() {
		int ks[] = new int[currentSize];
		int j = 0;
		for(int i = 0; i < maxSize; i++) {
			if(occupied[i]) {
				ks[j] = keys[i];
				j++;
			}
		}
		return ks;
	}
	
	private int[] formValueSet() {
		int vs[] = new int[currentSize];
		int j = 0;
		for(int i = 0; i < maxSize; i++) {
			if(occupied[i]) {
				vs[j] = values[i];
				j++;
			}
		}
		return vs;
	}
	
}
