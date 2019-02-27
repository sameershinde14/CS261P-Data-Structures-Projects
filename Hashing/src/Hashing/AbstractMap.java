package Hashing;
import java.util.*;

public abstract class AbstractMap<K,V> extends Object implements Map<K,V>{

	protected ArrayList<Entry<K, V>>bucket=new ArrayList<>();
	protected int size;
	protected int numBuckets=10;
	protected boolean resize = false;
	protected int prime = 11;
	@Override
	public void put(K key, V value) {
		int index=getBucketIndex(key);
		
		Entry<K, V>head=bucket.get(index);
		Entry<K, V>toAdd=new Entry<>();
		
		toAdd.key=key;
		toAdd.value=value;
		
		if(head==null)
		{
			bucket.set(index, toAdd);
			if(!resize) size++;
			
		}
		else
		{
			while(head!=null)
			{
				if(head.key.equals(key))
				{
					head.value=value;
					break;
				}
				head=head.next;
			}
			if(head==null)
			{
				head=bucket.get(index);
				toAdd.next=head;
				bucket.set(index, toAdd);
				if(!resize) size++;
			}
		}
		
		double loadFactor = (1.0*size)/numBuckets;
		
		if(loadFactor > 0.75)
		{
			boolean downScale = false;
			resize(downScale);
		}
	}
	protected void resize(boolean downScale) {
		resize = true;
		ArrayList<Entry<K, V>>tmp=bucket;
		bucket=new ArrayList<>();
		if(downScale)
			numBuckets = numBuckets/2;
		else
			numBuckets=2*numBuckets;
		for(int i=0;i<numBuckets;i++)
		{
			bucket.add(null);
		}
		for(Entry<K, V> headNode:tmp)
		{
			while(headNode!=null)
			{
				put(headNode.key, headNode.value);
				headNode=headNode.next;
			}
		}
		resize = false;
	}
	protected int getBucketIndex(Object key) {
		int hashCode=key.hashCode();
		return hashCode%numBuckets;
	}
	protected int getSecondBucketIndex(Object key) {
		int hashCode=key.hashCode();
		hashCode = hashCode/prime;
		return hashCode%numBuckets;
	}
	@Override
	public V get(Object key) {
		int index=getBucketIndex(key);
		Entry<K, V> head=bucket.get(index);
		while(head!=null)
		{
			if(head.key.equals(key))
			{
				return head.value;
			}
			head=head.next;
		}
		throw new RuntimeException("Key not found");	
	}

	@Override
	public boolean containsKey(Object key) {
		int bucketIdx = getBucketIndex(key);
		Entry<K, V> head=bucket.get(bucketIdx);
		while(head!=null)
		{
			if(head.key.equals(key))
			{
				return true;
			}
			head=head.next;
		}
		return false;

	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void clear() {
		bucket.clear();	
	}
	
	@Override
	public boolean isEmpty() {
		return size==0;
	}
	
	@Override
	public V remove(Object key) {
		int index=getBucketIndex(key);
		Entry<K, V>head=bucket.get(index);
		if(head==null)
		{
			return null;
		}
		if(head.key.equals(key))
		{
			V val=head.value;
			head=head.next;
			bucket.set(index, head);
			size--;
			checkResize();
			return val;
		}
		else
		{
			Entry<K, V>prev=null;
			while(head!=null)
			{
				
				if(head.key.equals(key))
				{
					prev.next=head.next;
					size--;
					checkResize();
					return head.value;
				}
				prev=head;
				head=head.next;
			}
			size--;
			checkResize();
			return null;
		}
		
		
	}
	private void checkResize() {
		double loadFactor = (1.0*size)/numBuckets;
		
		if(loadFactor <= 0.25)
		{
			boolean downScale = true;
			resize(downScale);
		}
		
	}
	abstract public void display();
	abstract public Set<Object> keySet();
}
