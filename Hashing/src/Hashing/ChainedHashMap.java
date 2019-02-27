package Hashing;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ChainedHashMap<K,V> extends AbstractMap {
	private Object[] keys, values;
	public ChainedHashMap(){
		size = 0;
		for(int i=0;i<numBuckets;i++)
		{
			bucket.add(null);
		}
	}
	@Override
	public void display() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Set keySet() {
		Set<Object> res = new HashSet<Object>();
		for(int i = 0; i < numBuckets; i++) {
			Entry<K,V> entry = (Entry<K, V>) bucket.get(i);
			while(entry!=null) {
				res.add(entry.key);
				entry = entry.next;
			}
		}
		return res;
	}
}
