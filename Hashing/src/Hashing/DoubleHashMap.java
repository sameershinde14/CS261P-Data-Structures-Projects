package Hashing;

import java.util.HashSet;
import java.util.Set;

//import java.util.ArrayList;

public class DoubleHashMap<K,V> extends AbstractMap<K,V> {

	public DoubleHashMap() {
		size = 0;
		for(int i=0;i<numBuckets;i++)
		{
			bucket.add(null);
		}
	}
	@Override
	public void put(K key, V value) {
		int h1 = getBucketIndex(key);
		int h2 = getSecondBucketIndex(key);
		Entry<K,V> toAdd = new Entry<>();
		toAdd.key = key;
		toAdd.value = value;
		
		int i = h1;	
		int count  = 1;
		boolean success = false;
		do
        {
			Entry<K,V>  tmp = bucket.get(i);
			if(tmp == null) {
				bucket.set(i, toAdd);
				if(!resize) size++;
				success = true;
				break;
			}
            if (bucket.get(i).key.equals(key)) 
            { 
                bucket.set(i, toAdd);
                success = true;
                return; 
            }            
            i = (h1 + h2 * count) % numBuckets;
            count++;
        } while (i != h1);
//		if(!success) {
//			boolean downScale = false;
//			resize(downScale);
//			return;
//		}
		double loadFactor = (1.0 * size/numBuckets);
		
		if(loadFactor > 0.75) {
			boolean downScale = false;
			resize(downScale);
		}
	}
	
	@Override
	public V get(Object key) {
		int h1 = getBucketIndex(key);
		int h2 = getSecondBucketIndex(key);
		int i = h1;
		int count = 1;
		do
	    {
			Entry<K,V> entry = bucket.get(i);
            if (entry!=null && entry.key.equals(key))
                return entry.value;
            i = (h1 + count*h2) % numBuckets;
            count++;
	    } while(i!=h1);   
	    throw new RuntimeException("key not found");
	}

	@Override
	public boolean containsKey(Object key) {	
		int h1 = getBucketIndex(key);
		int h2 = getSecondBucketIndex(key);
		int i = h1;
		int count = 1;
		do
	    {
			Entry<K,V> tmp = bucket.get(i);
            if (tmp!=null && tmp.key.equals(key))
                return true;
            i = (h1 + h2*count) % numBuckets;
            count++;
	    } while(i!=h1);   
	    return false;
	}
	
	@Override
	public V remove(Object key) {	
		
		
		int h1 = getBucketIndex(key);
		int h2 = getSecondBucketIndex(key);
		int i = h1;
		int count = 1;
		V ret = null;
		boolean success = false;
		do
	    {
			Entry<K,V> entry = bucket.get(i);
            if (entry!=null && entry.key.equals(key)) {
            	ret = bucket.get(i).value;
                bucket.set(i, null);
                success = true;
                break;
            }
            i = (h1 + count*h2) % numBuckets;
            count++;
	    } while(i!=h1);   
	    if(!success) throw new RuntimeException("key not found");
	    size--;    
	    double loadFactor = (1.0 * size/numBuckets);
	    if(loadFactor <= 0.25) {
			boolean downScale = true;
			resize(downScale);
		}
		return ret;
	}
	@Override
	public void display() {
		for(int i = 0; i < numBuckets;i++) {
			Entry<K,V> entry = bucket.get(i);
			if(entry!=null) {
				System.out.print(entry.key + ":" + entry.value + ", ");
			}
			else {
				System.out.print("null, ");
			}
		}
	}
	@Override
	public Set<Object> keySet() {
		
		Set<Object> res = new HashSet<Object>();
		for(int i = 0; i < numBuckets; i++) {
			Entry<K,V> entry = bucket.get(i);
			if(entry!=null) {
				res.add(entry.key);
			}
		}
		return res;
	}
	@Override
	protected int getSecondBucketIndex(Object key) {
		int prime = 7;
		int hashCode=key.hashCode();
		return prime - (hashCode%prime);
	}

}
