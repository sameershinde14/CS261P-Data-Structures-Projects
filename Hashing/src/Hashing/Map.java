package Hashing;

public interface Map<K, V> {
	public void put(K key, V value);
	public V get(Object key);
	public V remove(Object key);
	public boolean containsKey(K key);
	public int size();
	public void clear();
	public boolean isEmpty();
}
