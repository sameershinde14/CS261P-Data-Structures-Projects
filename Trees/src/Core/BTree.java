package Core;

import Core.BTreeNode;
import Core.Entry;


public class BTree <T extends Comparable<T>>{
    // max children per B-tree node = M-1
    // (must be even and greater than 2)
    private static final int M = 4;

    private BTreeNode root;       // root of the B-tree
    private int height;      // height of the B-tree
    private int n;           // number of key-value pairs in the B-tree
//
//    // helper B-tree node data type
//    private static final class BTreeNode {
//        private int m;                             // number of children
//        private Entry[] children = new Entry[M];   // the array of children
//
//        // create a node with k children
//        private BTreeNode(int k) {
//            m = k;
//        }
//    }

    // internal nodes: only use key and next
    // external nodes: only use key and value
//    private static class Entry {
//        private Comparable key;
//        private BTreeNode next;     // helper field to iterate over array entries
//        public Entry(Comparable key, Object val, BTreeNode next) {
//            this.key  = key;
//            this.next = next;
//        }
//    }

    /**
     * Initializes an empty B-tree.
     */
    public BTree() {
        root = new BTreeNode(0);
    }
 
    /**
     * Returns true if this symbol table is empty.
     * @return {@code true} if this symbol table is empty; {@code false} otherwise
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns the number of key-value pairs in this symbol table.
     * @return the number of key-value pairs in this symbol table
     */
    public int size() {
        return n;
    }

    /**
     * Returns the height of this B-tree (for debugging).
     *
     * @return the height of this B-tree
     */
    public int height() {
        return height;
    }


    /**
     * Returns the value associated with the given key.
     *
     * @param  key the key
     * @return the value associated with the given key if the key is in the symbol table
     *         and {@code null} if the key is not in the symbol table
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public T get(T key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        return search(root, key, height);
    }

    private T search(BTreeNode x, T key, int ht) {
        Entry[] children = x.children;

        // external node
        if (ht == 0) {
            for (int j = 0; j < x.m; j++) {
                if (eq(key, children[j].key)) return (T) children[j].key;
            }
        }

        // internal node
        else {
            for (int j = 0; j < x.m; j++) {
                if (j+1 == x.m || less(key, children[j+1].key))
                    return search(children[j].next, key, ht-1);
            }
        }
        return null;
    }


    /**
     * Inserts the key-value pair into the symbol table, overwriting the old value
     * with the new value if the key is already in the symbol table.
     * If the value is {@code null}, this effectively deletes the key from the symbol table.
     *
     * @param  key the key
     * @param  val the value
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void add(T key) {
        if (key == null) throw new IllegalArgumentException("argument key to put() is null");
        BTreeNode u = insert(root, key, height); 
        n++;
        if (u == null) return;

        // need to split root
        BTreeNode t = new BTreeNode(2);
        t.children[0] = new Entry(root.children[0].key, root);
        t.children[1] = new Entry(u.children[0].key, u);
        root = t;
        height++;
    }

    private BTreeNode insert(BTreeNode h, T key, int ht) {
        int j;
        Entry t = new Entry(key, null);

        // external node
        if (ht == 0) {
            for (j = 0; j < h.m; j++) {
                if (less(key, h.children[j].key)) break;
            }
        }

        // internal node
        else {
            for (j = 0; j < h.m; j++) {
                if ((j+1 == h.m) || less(key, h.children[j+1].key)) {
                    BTreeNode u = insert(h.children[j++].next, key, ht-1);
                    if (u == null) return null;
                    t.key = u.children[0].key;
                    t.next = u;
                    break;
                }
            }
        }

        for (int i = h.m; i > j; i--)
            h.children[i] = h.children[i-1];
        h.children[j] = t;
        h.m++;
        if (h.m < M) return null;
        else         return split(h);
    }

    // split node in half
    private BTreeNode split(BTreeNode h) {
        BTreeNode t = new BTreeNode(M/2);
        h.m = M/2;
        for (int j = 0; j < M/2; j++)
            t.children[j] = h.children[M/2+j]; 
        return t;    
    }

    /**
     * Returns a string representation of this B-tree (for debugging).
     *
     * @return a string representation of this B-tree.
     */
    public String toString() {
        return toString(root, height, "") + "\n";
    }

    private String toString(BTreeNode h, int ht, String indent) {
        StringBuilder s = new StringBuilder();
        Entry[] children = h.children;

        if (ht == 0) {
            for (int j = 0; j < h.m; j++) {
                s.append(indent + children[j].key + " " + "\n");
            }
        }
        else {
            for (int j = 0; j < h.m; j++) {
                if (j > 0) s.append(indent + "(" + children[j].key + ")\n");
                s.append(toString(children[j].next, ht-1, indent + "     "));
            }
        }
        return s.toString();
    }


    // comparison functions - make Comparable instead of T to avoid casts
    private boolean less(Comparable k1, Comparable k2) {
        return k1.compareTo(k2) < 0;
    }

    private boolean eq(Comparable k1, Comparable k2) {
        return k1.compareTo(k2) == 0;
    }
}
