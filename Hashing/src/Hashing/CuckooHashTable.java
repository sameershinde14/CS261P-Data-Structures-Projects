package Hashing;


public class CuckooHashTable<Key, Value> {

	protected Key[] keys;
	protected Value[] values;
	protected int PRIME_NUMBER = 11;
	protected int capacity = PRIME_NUMBER * 2;
	protected UnionFind cellSet;
	protected boolean[] cycleList;
	private int size = 0;

	@SuppressWarnings("unchecked")
	public CuckooHashTable() {
		keys = (Key[]) new Object[capacity];
		values = (Value[]) new Object[capacity];
		cellSet = new UnionFind(capacity);
		cycleList = new boolean[capacity];
	}

	@SuppressWarnings("unchecked")
	public CuckooHashTable(int capacity) {
		this.capacity = capacity;
		keys = (Key[]) new Object[capacity];
		values = (Value[]) new Object[capacity];
		cellSet = new UnionFind(capacity);
		cycleList = new boolean[capacity];
	}

	public int size() {
		return size;
	}

	public int capacity() {
		return capacity;
	}

	/**
	 * 
	 * @param key
	 * @param i   : returns cell for the ith table
	 * @return
	 */
	private int hash(Key key, int i) {
		if (i < 0 || i > 1 || key == null)
			throw new IllegalArgumentException();

		int cell = key.hashCode();
		cell = cell < 0 ? -1 * cell : cell;

		int half = capacity / 2;
		int cellNo = cell % half;
		cellNo = i == 0 ? cell % half : (cell / PRIME_NUMBER) % half + half;

		return cellNo;
	}
	public void put(Key key, Value val) {

		int cell1 = hash(key, 0);
		int cell2 = hash(key, 1);
		int parent1 = cellSet.find(cell1);
		int parent2 = cellSet.find(cell2);

		if (cycleList[parent1] && cycleList[parent2]) {
			resize(capacity * 2);
			put(key, val);
			return;

		}

		int newparent = cellSet.union(cell1, cell2);
		if (parent1 == parent2) {
			cycleList[parent1] = false;
			cycleList[parent2] = false;
			cycleList[newparent] = true;
		} else if (cycleList[parent1] || cycleList[parent2]) {
			cycleList[parent2] = false;
			cycleList[parent1] = false;
			cycleList[newparent] = true;
		}

		int t, cellNo;
		for (t = 0; (cellNo = hash(key, t)) >= 0 && !key.equals(keys[cellNo])
				&& values[cellNo] != null; t = t ^ 1) {
			Key tempkey = keys[cellNo];
			Value tempval = values[cellNo];
			keys[cellNo] = key;
			values[cellNo] = val;
			key = tempkey;
			val = tempval;
		}
		if (!key.equals(keys[cellNo]))
			size++;
		keys[cellNo] = key;
		values[cellNo] = val;
	}

	private void resize(int cap) {
//		System.out.println("Calling resize");
		CuckooHashTable<Key, Value> newTable = new CuckooHashTable<>(cap);
		for (int i = 0; i < keys.length; i++)
			if (keys[i] != null)
				newTable.put(keys[i], values[i]);

		this.capacity = newTable.capacity;
		this.cellSet = newTable.cellSet;
		this.cycleList = newTable.cycleList;
		this.keys = (Key[]) newTable.keys;
		this.values = (Value[]) newTable.values;
		this.size = newTable.size;

	}

	public Value get(Key key) {
		if (key == null)
			throw new IllegalArgumentException();

		int cellNo;
		for (int t = 0; t < 2 && (cellNo = hash(key, t)) >= 0; t++) {
			if (key.equals(keys[cellNo]))
				return values[cellNo];
		}

		return null;
	}

	public void remove(Key key) {
		if (key == null)
			throw new IllegalArgumentException();

		int cellNo;
		for (int t = 0; t < 2 && (cellNo = hash(key, t)) >= 0; t++) {
			if (key.equals(keys[cellNo])) {
				keys[cellNo] = null;
				values[cellNo] = null;
				size--;
				break;
			}
		}

	}

	public String toString() {
		StringBuffer repr = new StringBuffer("[");
		for (int i = 0; i < keys.length / 2; i++) {
			repr.append(String.format("{%s,%s}", keys[i], values[i]));
			if (i != keys.length / 2 - 1)
				repr.append(", ");
		}
		repr.append("\n");
		for (int i = keys.length / 2; i < keys.length; i++) {
			repr.append(String.format("{%s,%s}", keys[i], values[i]));
			if (i != keys.length - 1)
				repr.append(", ");
		}

		return repr.toString();
	}

	public double loadFactor() {
		return size * 1.0 / capacity;
	}
	public boolean containsKey(Key k) {
		int cell1 = hash(k, 0);
		int cell2 = hash(k, 1);
		if(k.equals(keys[cell1]) || k.equals(keys[cell2])) return true;
		return false;
	}

}
