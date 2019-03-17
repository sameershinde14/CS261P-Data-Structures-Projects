package Core;

import Core.BTreeNode;

public class Entry {
	public Comparable key;
    public BTreeNode next;     // helper field to iterate over array entries
    public Entry(Comparable key, BTreeNode next) {
        this.key  = key;
        this.next = next;
    }
}
