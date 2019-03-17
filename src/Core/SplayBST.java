package Core;

import java.util.logging.Logger;

public class SplayBST <T extends Comparable<T>> extends Tree<T>{
	
	public SplayBST(Logger LOG) {	
		LOGGER = LOG;
	}
	public SplayBST() {	
		root = null;
	}
   
    /**
     * Insert into the tree.
     * @param x the item to insert.
     * @throws DuplicateItemException if x is already present.
     */
    public void add(T key) {
    	long start = System.nanoTime();
		TreeNode<T> n;
		int c;
		if (root == null) {
		    root = new TreeNode<T>(key);
		    long stop = System.nanoTime();
			long elapsedTime = stop - start;
			noOfNodes++;
	        LOGGER.info("SUCCESSFULLY INSERTED: SIZE= "+ noOfNodes +",TIME REQUIRED= "+ elapsedTime);
		    return;
		}
		splay(key);
		if ((c = key.compareTo(root.value)) == 0) {
		    //	    throw new DuplicateItemException(x.toString());	 
			long stop = System.nanoTime();
			long elapsedTime = stop - start;
			noOfNodes++;
	        LOGGER.info("SUCCESSFULLY INSERTED: SIZE= "+ noOfNodes +",TIME REQUIRED= "+ elapsedTime);
		    return;
		}
		n = new TreeNode<T>(key);
		if (c < 0) {
		    n.left = root.left;
		    n.right = root;
		    root.left = null;
		} else {
		    n.right = root.right;
		    n.left = root;
		    root.right = null;
		}
		root = n;
		long stop = System.nanoTime();
		long elapsedTime = stop - start;
		noOfNodes++;
        LOGGER.info("SUCCESSFULLY INSERTED: SIZE= "+ noOfNodes +",TIME REQUIRED= "+ elapsedTime);
    }

    /**
     * Remove from the tree.
     * @param x the item to remove.
     * @throws ItemNotFoundException if x is not found.
     */
    public void remove(T key) {
    	long start = System.nanoTime();
		TreeNode<T> x;
		splay(key);
		if (key.compareTo(root.value) != 0) {
		    //            throw new ItemNotFoundException(x.toString());
		    return;
		}
		// Now delete the root
		if (root.left == null) {
		    root = root.right;
		} else {
		    x = root.right;
		    root = root.left;
		    splay(key);
		    root.right = x;
		}
		long stop = System.nanoTime();
		long elapsedTime = stop - start;
		noOfNodes--;
		LOGGER.info("SUCCESSFULLY REMOVED: SIZE= "+ noOfNodes +",TIME REQUIRED= "+ elapsedTime);
    }

    /**
     * Find the smallest item in the tree.
     */
    public Comparable findMin() {
        TreeNode<T> x = root;
        if(root == null) return null;
        while(x.left != null) x = x.left;
        splay(x.value);
        return x.value;
    }

    /**
     * Find the largest item in the tree.
     */
    public Comparable findMax() {
        TreeNode<T> x = root;
        if(root == null) return null;
        while(x.right != null) x = x.right;
        splay(x.value);
        return x.value;
    }

    /**
     * Find an item in the tree.
     */
    public boolean contains(T key) {
    	if (root == null) return false;
    	splay(key);
        if(root.value.compareTo(key) != 0) return false;
        return true;
    }

    /**
     * Test if the tree is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty() {
        return root == null;
    }

    /** this method just illustrates the top-down method of
     * implementing the move-to-root operation 
     */
    private void moveToRoot(T key) {
		TreeNode<T> l, r, t, y;
		l = r = header;
		t = root;
		header.left = header.right = null;
		for (;;) {
		    if (key.compareTo(t.value) < 0) {
				if (t.left == null) break;
					r.left = t;                                 /* link right */
					r = t;
					t = t.left;
			    } else if (key.compareTo(t.value) > 0) {
					if (t.right == null) break;
					l.right = t;                                /* link left */
					l = t;
					t = t.right;
			    } else {
			    	break;
			    }
		}
		l.right = t.left;                                   /* assemble */
		r.left = t.right;
		t.left = header.right;
		t.right = header.left;
		root = t;
    }

    private static TreeNode header = new TreeNode();
    
    /**
     * Internal method to perform a top-down splay.
     * 
     *   splay(key) does the splay operation on the given key.
     *   If key is in the tree, then the TreeNode<T> containing
     *   that key becomes the root.  If key is not in the tree,
     *   then after the splay, key.root is either the greatest key
     *   < key in the tree, or the lest key > key in the tree.
     *
     *   This means, among other things, that if you splay with
     *   a key that's larger than any in the tree, the rightmost
     *   node of the tree becomes the root.  This property is used
     *   in the delete() method.
     */

    private void splay(T key) {
		TreeNode<T> l, r, t, y;
		l = r = header;
		t = root;
		header.left = header.right = null;
		for (;;) {
		    if (key.compareTo(t.value) < 0) {
				if (t.left == null) break;
				if (key.compareTo(t.left.value) < 0) {
				    y = t.left;                            /* rotate right */
				    t.left = y.right;
				    y.right = t;
				    t = y;
				    if (t.left == null) break;
				}
				r.left = t;                                 /* link right */
				r = t;
				t = t.left;
		    } else if (key.compareTo(t.value) > 0) {
				if (t.right == null) break;
				if (key.compareTo(t.right.value) > 0) {
				    y = t.right;                            /* rotate left */
				    t.right = y.left;
				    y.left = t;
				    t = y;
				    if (t.right == null) break;
				}
				l.right = t;                                /* link left */
				l = t;
				t = t.right;
		    } else {
		    	break;
		    }
		}
		l.right = t.left;                                   /* assemble */
		r.left = t.right;
		t.left = header.right;
		t.right = header.left;
		root = t;
    }

}
