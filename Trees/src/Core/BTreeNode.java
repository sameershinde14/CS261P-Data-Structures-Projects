package Core;

import Core.Entry;

public class BTreeNode {
	private static final int M = 4;
	public int m;                             // number of children
    public Entry[] children = new Entry[M];   // the array of children

    // create a node with k children
    public BTreeNode(int k) {
        m = k;
    }
}
