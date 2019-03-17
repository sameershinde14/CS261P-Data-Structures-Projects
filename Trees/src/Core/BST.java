package Core;

import java.util.NoSuchElementException;
import java.util.logging.Logger;
import java.util.*;

public class BST<T extends Comparable<T>> extends Tree<T> {
	
	public BST() {
		root = null;
		noOfNodes = 0;
	}
	public BST(Logger LOG) {	
		LOGGER = LOG;
	}
	@Override
	public void add(T key) {
		if (key == null) throw new IllegalArgumentException("calls put() with a null key");
        long start =  System.nanoTime();
		root = add(root, key);
		long stop =  System.nanoTime();
		long elapsedTime = stop - start;
		noOfNodes++;
		LOGGER.info("SUCCESSFULLY INSERTED: SIZE= "+ noOfNodes +",TIME REQUIRED= "+ elapsedTime);
	}

	@Override
	public void remove(T key) {
		if (key == null) throw new IllegalArgumentException("calls delete() with a null key");
		long start =  System.nanoTime();
		root = remove(root, key);
		long stop =  System.nanoTime();
		long elapsedTime = stop - start;
        noOfNodes--;
        LOGGER.info("SUCCESSFULLY REMOVED: SIZE= "+ noOfNodes +",TIME REQUIRED= "+ elapsedTime);
        
	}
	
	public T min() {
        if (isEmpty()) throw new NoSuchElementException("calls min() with empty symbol table");
        long start =  System.nanoTime();
        T res = min(root).value;
        long stop =  System.nanoTime();
		long elapsedTime = stop - start;
		LOGGER.info("SUCCESSFULLY FOUND MIN: SIZE= "+ noOfNodes +",TIME REQUIRED= "+ elapsedTime);
        return res;
    }
	public T max() {
        if (isEmpty()) throw new NoSuchElementException("calls max() with empty symbol table");
        long start =  System.nanoTime();
        T res = max(root).value;
        long stop =  System.nanoTime();
        long elapsedTime = stop - start;
		LOGGER.info("SUCCESSFULLY FOUND MAX: SIZE= "+ noOfNodes +",TIME REQUIRED= "+ elapsedTime);
        return res;
    } 
  
    public boolean isEmpty() {
        return size() == 0;
    }
    
    public int size() {
        return size(root);
    }
    
    public void deleteMin() {
        if (isEmpty()) throw new NoSuchElementException("Symbol table underflow");
        long start =  System.nanoTime();
        root = deleteMin(root);
        long stop =  System.nanoTime();
        long elapsedTime = stop - start;
        LOGGER.info("SUCCESSFULLY DELETED MIN: SIZE= "+ noOfNodes +",TIME REQUIRED= "+ elapsedTime);
        assert check();
    }
    
    public boolean contains(T key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        long start =  System.nanoTime();
        boolean res =  get(key) != null;
        long stop =  System.nanoTime();
        long elapsedTime = stop - start;
        LOGGER.info("SUCCESSFULLY COMPLETED CONTAINS: SIZE= "+ noOfNodes +",TIME REQUIRED= "+ elapsedTime);
        return res;
    }
    
    public T get(T key) {
        return get(root, key);
    }
    
    public void deleteMax() {
        if (isEmpty()) throw new NoSuchElementException("Symbol table underflow");
        long start =  System.nanoTime();
        root = deleteMax(root);
        long stop =  System.nanoTime();
        long elapsedTime = stop - start;
        LOGGER.info("SUCCESSFULLY DELETED MAX: SIZE= "+ noOfNodes +",TIME REQUIRED= "+ elapsedTime);
        assert check();
    }

	/*
	 * 
	 * 
	 * helpers
	 */
	
	private TreeNode<T> add(TreeNode<T> root, T val){
		if (root == null) {
			return new TreeNode<T>(val);
		}
        int cmp = val.compareTo(root.value);
        if (cmp < 0) 
        	root.left  = add(root.left,  val);
        else if (cmp > 0) 
        	root.right = add(root.right, val);
        else              
        	root.value   = val;
        root.size = 1 + size(root.left) + size(root.right);
        return root;
	}

    private TreeNode<T> remove(TreeNode<T> x, T key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.value);
        if      (cmp < 0) x.left = remove(x.left,  key);
        else if (cmp > 0) x.right = remove(x.right, key);
        else { 
            if (x.right == null) return x.left;
            if (x.left  == null) return x.right;
            TreeNode<T> t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = (t.left);
        } 
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    } 
    private TreeNode<T> min(TreeNode<T> x) { 
        if (x.left == null) 
        	return x; 
        else                
        	return min(x.left); 
    }
    private int size(TreeNode<T> x) {
        if (x == null) return 0;
        else return x.size;
    }

    private TreeNode<T> deleteMin(TreeNode<T> x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }
    private T get(TreeNode<T> x, T key) {
        if (key == null) throw new IllegalArgumentException("calls get() with a null key");
        if (x == null) return null;
        int cmp = key.compareTo(x.value);
        if      (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else              return x.value;
    }
    
    private TreeNode<T> deleteMax(TreeNode<T> x) {
        if (x.right == null) return x.left;
        x.right = deleteMax(x.right);
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }
    private TreeNode<T> max(TreeNode<T> x) {
        if (x.right == null) 
        	return x; 
        else                 
        	return max(x.right); 
    }
    
    
    /*
     * 
     * More helpers
     */
    
    private T floor(T key) {
        if (key == null) throw new IllegalArgumentException("argument to floor() is null");
        if (isEmpty()) throw new NoSuchElementException("calls floor() with empty symbol table");
        TreeNode<T> x = floor(root, key);
        if (x == null) return null;
        else return x.value;
    } 

    private TreeNode<T> floor(TreeNode<T> x, T key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.value);
        if (cmp == 0) return x;
        if (cmp <  0) return floor(x.left, key);
        TreeNode<T> t = floor(x.right, key); 
        if (t != null) return t;
        else return x; 
    } 

    private T floor2(T key) {
        return floor2(root, key, null);
    }

    private T floor2(TreeNode<T> x, T key, T best) {
        if (x == null) return best;
        int cmp = key.compareTo(x.value);
        if      (cmp  < 0) return floor2(x.left, key, best);
        else if (cmp  > 0) return floor2(x.right, key, x.value);
        else               return x.value;
    } 

    public T ceiling(T key) {
        if (key == null) throw new IllegalArgumentException("argument to ceiling() is null");
        if (isEmpty()) throw new NoSuchElementException("calls ceiling() with empty symbol table");
        TreeNode<T> x = ceiling(root, key);
        if (x == null) return null;
        else return x.value;
    }

    private TreeNode<T> ceiling(TreeNode<T> x, T key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.value);
        if (cmp == 0) return x;
        if (cmp < 0) { 
            TreeNode<T> t = ceiling(x.left, key); 
            if (t != null) return t;
            else return x; 
        } 
        return ceiling(x.right, key); 
    } 

   public T select(int k) {
        if (k < 0 || k >= size()) {
            throw new IllegalArgumentException("argument to select() is invalid: " + k);
        }
        TreeNode<T> x = select(root, k);
        return x.value;
    }

    // Return key of rank k. 
    private TreeNode<T> select(TreeNode<T> x, int k) {
        if (x == null) return null; 
        int t = size(x.left); 
        if      (t > k) return select(x.left,  k); 
        else if (t < k) return select(x.right, k-t-1); 
        else            return x; 
    } 

    public int rank(T key) {
        if (key == null) throw new IllegalArgumentException("argument to rank() is null");
        return rank(key, root);
    } 

    private int rank(T key, TreeNode<T> x) {
        if (x == null) return 0; 
        int cmp = key.compareTo(x.value); 
        if      (cmp < 0) return rank(key, x.left); 
        else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right); 
        else              return size(x.left); 
    } 

    public Iterable<T> keys() {
        if (isEmpty()) return new LinkedList<T>();
        return keys(min(), max());
    }

    public Iterable<T> keys(T lo, T hi) {
        if (lo == null) throw new IllegalArgumentException("first argument to keys() is null");
        if (hi == null) throw new IllegalArgumentException("second argument to keys() is null");

        Queue<T> queue = new LinkedList<T>();
        keys(root, queue, lo, hi);
        return queue;
    } 

    private void keys(TreeNode<T> x, Queue<T> queue, T lo, T hi) { 
        if (x == null) return; 
        int cmplo = lo.compareTo(x.value); 
        int cmphi = hi.compareTo(x.value); 
        if (cmplo < 0) keys(x.left, queue, lo, hi); 
        if (cmplo <= 0 && cmphi >= 0) queue.add(x.value); 
        if (cmphi > 0) keys(x.right, queue, lo, hi); 
    } 

    public int size(T lo, T hi) {
        if (lo == null) throw new IllegalArgumentException("first argument to size() is null");
        if (hi == null) throw new IllegalArgumentException("second argument to size() is null");

        if (lo.compareTo(hi) > 0) return 0;
        if (contains(hi)) return rank(hi) - rank(lo) + 1;
        else              return rank(hi) - rank(lo);
    }

    public int height() {
        return height(root);
    }
    private int height(TreeNode<T> x) {
        if (x == null) return -1;
        return 1 + Math.max(height(x.left), height(x.right));
    }

    public Iterable<T> levelOrder() {
        Queue<T> keys = new LinkedList<>();
        Queue<TreeNode<T>> queue = new LinkedList<TreeNode<T>>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode<T> x = queue.poll();
            if (x == null) continue;
            keys.add(x.value);
            queue.add(x.left);
            queue.add(x.right);
        }
        return keys;
    }

    
    /*************************************************************************
     *  Check integrity of BST data structure.
     ***************************************************************************/
     public boolean check() {
         if (!isBST())            LOGGER.severe("Not in symmetric order");
         //if (!isSizeConsistent()) LOGGER.severe("Subtree counts not consistent");
         //if (!isRankConsistent()) LOGGER.severe("Ranks not consistent");
         //return isBST() && isSizeConsistent() && isRankConsistent();
         return isBST();
     }

     // does this binary tree satisfy symmetric order?
     // Note: this test also ensures that data structure is a binary tree since order is strict
     private boolean isBST() {
         return isBST(root, null, null);
     }

     // is the tree rooted at x a BST with all keys strictly between min and max
     // (if min or max is null, treat as empty constraint)
     // Credit: Bob Dondero's elegant solution
     private boolean isBST(TreeNode<T> x, T min, T max) {
         if (x == null) return true;
         if (min != null && x.value.compareTo(min) <= 0) return false;
         if (max != null && x.value.compareTo(max) >= 0) return false;
         return isBST(x.left, min, x.value) && isBST(x.right, x.value, max);
     } 

     // are the size fields correct?
     private boolean isSizeConsistent() { 
    	 return isSizeConsistent(root); 
     }
     private boolean isSizeConsistent(TreeNode<T> x) {
         if (x == null) return true;
         if (x.size != size(x.left) + size(x.right) + 1) return false;
         return isSizeConsistent(x.left) && isSizeConsistent(x.right);
     } 

     // check that ranks are consistent
     private boolean isRankConsistent() {
         for (int i = 0; i < size(); i++)
             if (i != rank(select(i))) return false;
         for (T key : keys())
             if (key.compareTo(select(rank(key))) != 0) return false;
         return true;
     }
         

}

