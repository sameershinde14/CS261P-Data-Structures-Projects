package Hashing;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class CuckooHashMap<K,V> extends AbstractMap<K,V> {
	protected ArrayList<Entry<K, V>> bucket2 = new ArrayList<>();
	
	public CuckooHashMap() {
		for(int i = 0; i < numBuckets;i++) {
			bucket.add(null);
			bucket2.add(null);
		}
	}
	public CuckooHashMap(int n) {
		numBuckets = n;
		for(int i = 0; i < numBuckets;i++) {
			bucket.add(null);
			bucket2.add(null);
		}
	}
	
	@Override
	public void put(K key, V value) {
		int side = 0;
		Entry<K, V>toAdd=new Entry<>();	
		toAdd.key=key;
		toAdd.value=value;
		int i = 0;
		
		int index = getBucketIndex(key);
		Entry<K,V> entry = bucket.get(index);
		if(entry == null) {
			bucket.set(index, toAdd);
		}
		else if(entry!=null && entry.key == key) {
			bucket.set(index, toAdd);
			return ;
		} else {
			bucket.set(index, toAdd);
			i++;
			while(entry != null) {
				if(entry.key.equals(key)) {
					if(i==2) {
						System.out.println("Cycle Detected");
						boolean downScale = false;
						resize(downScale);
						put(key,value);
						return;
					}
					i++;
				}
				if(side == 0) {
					int index2 = getSecondBucketIndex(entry.key);
					Entry<K,V> tmp = bucket2.get(index2);
					bucket2.set(index2, entry);
					entry = tmp; 
				}
				else {
					int index2 = getBucketIndex(entry.key);
					Entry<K,V> tmp = bucket.get(index2);
					bucket.set(index2, entry);
					entry = tmp;
				}
				side = 1 - side;
			}
			
		}
			
		if(!resize) size++;
		double loadFactor = (1.0* (size/2))/numBuckets;
		if(loadFactor > 0.75) {
			boolean downScale = false;
			resize(downScale);
		}
	}
	
	@Override
	public void resize(boolean downScale) {
		resize = true;
		ArrayList<Entry<K, V>>tmp=bucket;
		ArrayList<Entry<K, V>>tmp2=bucket2;
		bucket = new ArrayList<>();
		bucket2 = new ArrayList<>();
		if(downScale)
			numBuckets = numBuckets/2;
		else
			numBuckets=2*numBuckets;
		for(int i=0;i<numBuckets;i++)
		{
			bucket.add(null);
			bucket2.add(null);
		}
		for(Entry<K, V> entry:tmp)
		{
			if(entry!=null)
				put(entry.key, entry.value);
		}
		for(Entry<K, V> entry:tmp2)
		{
			if(entry!=null)
				put(entry.key, entry.value);
		}
		resize = false;
	}
	
	@Override
	public V get(Object key) {
		int index1 = getBucketIndex(key);
		int index2 = getSecondBucketIndex(key);
		Entry<K,V> first = bucket.get(index1);
		Entry<K,V> second = bucket2.get(index2);
		if(first!=null && first.key.equals(key)) return first.value;
		if(second!=null && second.key.equals(key)) return second.value;
		throw new RuntimeException("Element not found");
	}

	@Override
	public boolean containsKey(Object key) {
		int index1 = getBucketIndex(key);
		int index2 = getSecondBucketIndex(key);
		Entry<K,V> first = bucket.get(index1);
		Entry<K,V> second = bucket2.get(index2);
		if((first!=null && first.key.equals(key)) || (second!=null && second.key.equals(key))) return true;;
		return false;
	}

	
	@Override
	public void clear() {
		bucket.clear();
		bucket2.clear();
	}
	
	@Override
	public boolean isEmpty() {
		return size==0;
	}
	
	@Override
	public V remove(Object key) {
		int index1 = getBucketIndex(key);
		int index2 = getSecondBucketIndex(key);
		Entry<K,V> first = bucket.get(index1);
		Entry<K,V> second = bucket2.get(index2);
		if(first.key.equals(key)) {
			V ret = bucket.get(index1).value;
			bucket.set(index1, null);
			size--;
			//checkResize();
			return ret;
		}
		if(second.key.equals(key)) {
			V ret = bucket2.get(index2).value;
			bucket2.set(index2, null);
			size--;
			//checkResize();
			return ret;
		}
		return  null;
		
	}
	private void checkResize() {
		double loadFactor = (1.0* (size/2))/numBuckets;
		if(loadFactor <= 0.25) {
			boolean downScale = true;
			resize(downScale);
		}
	}
	@Override
	public void display() {
		System.out.println("HashTable 1:");
		for(Entry<K,V> en: bucket) {
			if(en!=null)
				System.out.print(en.key + ":" + en.value + ", ");
			else
				System.out.print("null, ");
		}
		System.out.println();
		System.out.println("HashTable 2:");
		for(Entry<K,V> en: bucket2) {
			if(en!=null)
				System.out.print(en.key + ":" + en.value + ", ");
			else
				System.out.print("null, ");
		}
	}



	@Override
	public Set<Object> keySet() {
		Set<Object> res = new HashSet<Object>();
		for(Entry<K,V> entry: bucket) {
			if(entry!=null) {
				res.add(entry.key);
			}
		}
		for(Entry<K,V> entry: bucket2) {
			if(entry!=null) {
				res.add(entry.key);
			}
		}
		return res;
	}


}
