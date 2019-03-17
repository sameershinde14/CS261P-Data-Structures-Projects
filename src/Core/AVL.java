package Core;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.logging.Logger;

public class AVL <T extends Comparable<T>> extends Tree<T> {

    /**
     * Initializes an empty symbol table.
     */
    public AVL() {
    }
    public AVL(Logger LOG) {	
		LOGGER = LOG;
	}

    /**
     * Checks if the symbol table is empty.
     * 
     * @return {@code true} if the symbol table is empty.
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Returns the number key-value pairs in the symbol table.
     * 
     * @return the number key-value pairs in the symbol table
     */
    public int size() {
        return size(root);
    }

    /**
     * Returns the number of nodes in the subtree.
     * 
     * @param x the subtree
     * 
     * @return the number of nodes in the subtree
     */
    private int size(TreeNode<T> x) {
        if (x == null) return 0;
        return x.size;
    }

    /**
     * Returns the height of the internal AVL tree. It is assumed that the
     * height of an empty tree is -1 and the height of a tree with just one node
     * is 0.
     * 
     * @return the height of the internal AVL tree
     */
    public int height() {
        return height(root);
    }

    /**
     * Returns the height of the subtree.
     * 
     * @param x the subtree
     * 
     * @return the height of the subtree.
     */
    private int height(TreeNode<T>x) {
        if (x == null) return -1;
        return x.height;
    }

    /**
     * Returns the value associated with the given key.
     * 
     * @param key the key
     * @return the value associated with the given key if the key is in the
     *         symbol table and {@code null} if the key is not in the
     *         symbol table
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public T get(T key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        TreeNode<T>x = get(root, key);
        if (x == null) return null;
        return x.value;
    }

    /**
     * Returns value associated with the given key in the subtree or
     * {@code null} if no such key.
     * 
     * @param x the subtree
     * @param key the key
     * @return value associated with the given key in the subtree or
     *         {@code null} if no such key
     */
    private TreeNode<T>get(TreeNode<T> x, T key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.value);
        if (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else return x;
    }

    /**
     * Checks if the symbol table contains the given key.
     * 
     * @param key the key
     * @return {@code true} if the symbol table contains {@code key}
     *         and {@code false} otherwise
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public boolean contains(T key) {
        return get(key) != null;
    }

    /**
     * Inserts the specified key-value pair into the symbol table, overwriting
     * the old value with the new value if the symbol table already contains the
     * specified key. Deletes the specified key (and its associated value) from
     * this symbol table if the specified value is {@code null}.
     * 
     * @param key the key
     * @param val the value
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void add(T val) {
        if (val == null) throw new IllegalArgumentException("first argument to put() is null");
        long start =  System.nanoTime();
        root = put(root, val);
        long stop =  System.nanoTime();
        long elapsedTime = stop - start;
        noOfNodes++;
		LOGGER.info("SUCCESSFULLY INSERTED: SIZE= "+ noOfNodes +",TIME REQUIRED= "+ elapsedTime);
        //assert check();
    }

    /**
     * Inserts the key-value pair in the subtree. It overrides the old value
     * with the new value if the symbol table already contains the specified key
     * and deletes the specified key (and its associated value) from this symbol
     * table if the specified value is {@code null}.
     * 
     * @param x the subtree
     * @param key the key
     * @param val the value
     * @return the subtree
     */
    private TreeNode<T>put(TreeNode<T>x, T val) {
        if (x == null) return new TreeNode<T>(val, 0, 1);
        int cmp = val.compareTo(x.value);
        if (cmp < 0) {
            x.left = put(x.left, val);
        }
        else if (cmp > 0) {
            x.right = put(x.right, val);
        }
        else {
            x.value = val;
            return x;
        }
        x.size = 1 + size(x.left) + size(x.right);
        x.height = 1 + Math.max(height(x.left), height(x.right));
        return balance(x);
    }

    /**
     * Restores the AVL tree property of the subtree.
     * 
     * @param x the subtree
     * @return the subtree with restored AVL property
     */
    private TreeNode<T>balance(TreeNode<T>x) {
    	long start = System.nanoTime();
        if (balanceFactor(x) < -1) {
            if (balanceFactor(x.right) > 0) {
                x.right = rotateRight(x.right);
            }
            x = rotateLeft(x);
        }
        else if (balanceFactor(x) > 1) {
            if (balanceFactor(x.left) < 0) {
                x.left = rotateLeft(x.left);
            }
            x = rotateRight(x);
        }
        long stop = System.nanoTime();
        long elapsedTime = stop - start;
        LOGGER.info("SUCCESSFULLY BALANCED: SIZE= "+ noOfNodes +",TIME REQUIRED= "+ elapsedTime);
        return x;
    }

    /**
     * Returns the balance factor of the subtree. The balance factor is defined
     * as the difference in height of the left subtree and right subtree, in
     * this order. Therefore, a subtree with a balance factor of -1, 0 or 1 has
     * the AVL property since the heights of the two child subtrees differ by at
     * most one.
     * 
     * @param x the subtree
     * @return the balance factor of the subtree
     */
    private int balanceFactor(TreeNode<T>x) {
        return height(x.left) - height(x.right);
    }

    /**
     * Rotates the given subtree to the right.
     * 
     * @param x the subtree
     * @return the right rotated subtree
     */
    private TreeNode<T>rotateRight(TreeNode<T>x) {
    	long start = System.nanoTime();
        TreeNode<T>y = x.left;
        x.left = y.right;
        y.right = x;
        y.size = x.size;
        x.size = 1 + size(x.left) + size(x.right);
        x.height = 1 + Math.max(height(x.left), height(x.right));
        y.height = 1 + Math.max(height(y.left), height(y.right));
        long stop = System.nanoTime();
        long elapsedTime = stop - start;
        LOGGER.info("SUCCESSFULLY ROTATE RIGHT: SIZE= "+ noOfNodes +",TIME REQUIRED= "+ elapsedTime);
        return y;
    }

    /**
     * Rotates the given subtree to the left.
     * 
     * @param x the subtree
     * @return the left rotated subtree
     */
    private TreeNode<T>rotateLeft(TreeNode<T>x) {
    	long start = System.nanoTime();
        TreeNode<T>y = x.right;
        x.right = y.left;
        y.left = x;
        y.size = x.size;
        x.size = 1 + size(x.left) + size(x.right);
        x.height = 1 + Math.max(height(x.left), height(x.right));
        y.height = 1 + Math.max(height(y.left), height(y.right));
        long stop = System.nanoTime();
        long elapsedTime = stop - start;
        LOGGER.info("SUCCESSFULLY ROTATE LEFT: SIZE= "+ noOfNodes +",TIME REQUIRED= "+ elapsedTime);
        return y;
    }

    /**
     * Removes the specified key and its associated value from the symbol table
     * (if the key is in the symbol table).
     * 
     * @param key the key
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void remove(T key) {
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
        if (!contains(key)) return;
        long start =  System.nanoTime();
        root = delete(root, key);
        long stop =  System.nanoTime();
        long elapsedTime = stop - start;
        noOfNodes--;
        LOGGER.info("SUCCESSFULLY REMOVED: SIZE= "+ noOfNodes +",TIME REQUIRED= "+ elapsedTime);
        
       // assert check();
    }

    /**
     * Removes the specified key and its associated value from the given
     * subtree.
     * 
     * @param x the subtree
     * @param key the key
     * @return the updated subtree
     */
    private TreeNode<T>delete(TreeNode<T>x, T key) {
        int cmp = key.compareTo(x.value);
        if (cmp < 0) {
            x.left = delete(x.left, key);
        }
        else if (cmp > 0) {
            x.right = delete(x.right, key);
        }
        else {
            if (x.left == null) {
                return x.right;
            }
            else if (x.right == null) {
                return x.left;
            }
            else {
                TreeNode<T>y = x;
                x = min(y.right);
                x.right = deleteMin(y.right);
                x.left = y.left;
            }
        }
        x.size = 1 + size(x.left) + size(x.right);
        x.height = 1 + Math.max(height(x.left), height(x.right));
        return balance(x);
    }

    /**
     * Removes the smallest key and associated value from the symbol table.
     * 
     * @throws NoSuchElementException if the symbol table is empty
     */
    public void deleteMin() {
        if (isEmpty()) throw new NoSuchElementException("called deleteMin() with empty symbol table");
        root = deleteMin(root);
        assert check();
    }

    /**
     * Removes the smallest key and associated value from the given subtree.
     * 
     * @param x the subtree
     * @return the updated subtree
     */
    private TreeNode<T>deleteMin(TreeNode<T>x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.size = 1 + size(x.left) + size(x.right);
        x.height = 1 + Math.max(height(x.left), height(x.right));
        return balance(x);
    }

    /**
     * Removes the largest key and associated value from the symbol table.
     * 
     * @throws NoSuchElementException if the symbol table is empty
     */
    public void deleteMax() {
        if (isEmpty()) throw new NoSuchElementException("called deleteMax() with empty symbol table");
        root = deleteMax(root);
        assert check();
    }

    /**
     * Removes the largest key and associated value from the given subtree.
     * 
     * @param x the subtree
     * @return the updated subtree
     */
    private TreeNode<T>deleteMax(TreeNode<T>x) {
        if (x.right == null) return x.left;
        x.right = deleteMax(x.right);
        x.size = 1 + size(x.left) + size(x.right);
        x.height = 1 + Math.max(height(x.left), height(x.right));
        return balance(x);
    }

    /**
     * Returns the smallest key in the symbol table.
     * 
     * @return the smallest key in the symbol table
     * @throws NoSuchElementException if the symbol table is empty
     */
    public T min() {
        if (isEmpty()) throw new NoSuchElementException("called min() with empty symbol table");
        long start = System.nanoTime();
        T res =  min(root).value;
        long stop = System.nanoTime();
        long elapsedTime = stop - start;
        LOGGER.info("SUCCESSFULLY FOUND MIN: SIZE= "+ noOfNodes +",TIME REQUIRED= "+ elapsedTime);
        return res;
    }

    /**
     * Returns the TreeNode<T>with the smallest key in the subtree.
     * 
     * @param x the subtree
     * @return the TreeNode<T>with the smallest key in the subtree
     */
    private TreeNode<T>min(TreeNode<T>x) {
        if (x.left == null) return x;
        
        return min(x.left);
    }

    /**
     * Returns the largest key in the symbol table.
     * 
     * @return the largest key in the symbol table
     * @throws NoSuchElementException if the symbol table is empty
     */
    public T max() {
        if (isEmpty()) throw new NoSuchElementException("called max() with empty symbol table");
        long start = System.nanoTime();
        T res =  max(root).value;
        long stop = System.nanoTime();
        long elapsedTime = stop - start;
        LOGGER.info("SUCCESSFULLY FOUND MIN: SIZE= "+ noOfNodes +",TIME REQUIRED= "+ elapsedTime);
        return res;
    }

    /**
     * Returns the TreeNode<T>with the largest key in the subtree.
     * 
     * @param x the subtree
     * @return the TreeNode<T>with the largest key in the subtree
     */
    private TreeNode<T>max(TreeNode<T>x) {
        if (x.right == null) return x;
        return max(x.right);
    }

    /**
     * Returns the largest key in the symbol table less than or equal to
     * {@code key}.
     * 
     * @param key the key
     * @return the largest key in the symbol table less than or equal to
     *         {@code key}
     * @throws NoSuchElementException if the symbol table is empty
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public T floor(T key) {
        if (key == null) throw new IllegalArgumentException("argument to floor() is null");
        if (isEmpty()) throw new NoSuchElementException("called floor() with empty symbol table");
        TreeNode<T>x = floor(root, key);
        if (x == null) return null;
        else return x.value;
    }

    /**
     * Returns the TreeNode<T>in the subtree with the largest key less than or equal
     * to the given key.
     * 
     * @param x the subtree
     * @param key the key
     * @return the TreeNode<T>in the subtree with the largest key less than or equal
     *         to the given key
     */
    private TreeNode<T>floor(TreeNode<T>x, T key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.value);
        if (cmp == 0) return x;
        if (cmp < 0) return floor(x.left, key);
        TreeNode<T>y = floor(x.right, key);
        if (y != null) return y;
        else return x;
    }

    /**
     * Returns the smallest key in the symbol table greater than or equal to
     * {@code key}.
     * 
     * @param key the key
     * @return the smallest key in the symbol table greater than or equal to
     *         {@code key}
     * @throws NoSuchElementException if the symbol table is empty
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public T ceiling(T key) {
        if (key == null) throw new IllegalArgumentException("argument to ceiling() is null");
        if (isEmpty()) throw new NoSuchElementException("called ceiling() with empty symbol table");
        TreeNode<T>x = ceiling(root, key);
        if (x == null) return null;
        else return x.value;
    }

    /**
     * Returns the TreeNode<T>in the subtree with the smallest key greater than or
     * equal to the given key.
     * 
     * @param x the subtree
     * @param key the key
     * @return the TreeNode<T>in the subtree with the smallest key greater than or
     *         equal to the given key
     */
    private TreeNode<T> ceiling(TreeNode<T>x, T key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.value);
        if (cmp == 0) return x;
        if (cmp > 0) return ceiling(x.right, key);
        TreeNode<T>y = ceiling(x.left, key);
        if (y != null) return y;
        else return x;
    }

    /**
     * Returns the kth smallest key in the symbol table.
     * 
     * @param k the order statistic
     * @return the kth smallest key in the symbol table
     * @throws IllegalArgumentException unless {@code k} is between 0 and
     *             {@code size() -1 }
     */
    public T select(int k) {
        if (k < 0 || k >= size()) throw new IllegalArgumentException("k is not in range 0-" + (size() - 1));
        TreeNode<T>x = select(root, k);
        return x.value;
    }

    /**
     * Returns the TreeNode<T>with key the kth smallest key in the subtree.
     * 
     * @param x the subtree
     * @param k the kth smallest key in the subtree
     * @return the TreeNode<T>with key the kth smallest key in the subtree
     */
    private TreeNode<T>select(TreeNode<T>x, int k) {
        if (x == null) return null;
        int t = size(x.left);
        if (t > k) return select(x.left, k);
        else if (t < k) return select(x.right, k - t - 1);
        else return x;
    }

    /**
     * Returns the number of keys in the symbol table strictly less than
     * {@code key}.
     * 
     * @param key the key
     * @return the number of keys in the symbol table strictly less than
     *         {@code key}
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public int rank(T key) {
        if (key == null) throw new IllegalArgumentException("argument to rank() is null");
        return rank(key, root);
    }

    /**
     * Returns the number of keys in the subtree less than key.
     * 
     * @param key the key
     * @param x the subtree
     * @return the number of keys in the subtree less than key
     */
    private int rank(T key, TreeNode<T>x) {
        if (x == null) return 0;
        int cmp = key.compareTo(x.value);
        if (cmp < 0) return rank(key, x.left);
        else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right);
        else return size(x.left);
    }

    /**
     * Returns all keys in the symbol table.
     * 
     * @return all keys in the symbol table
     */
    public Iterable<T> keys() {
        return keysInOrder();
    }

    /**
     * Returns all keys in the symbol table following an in-order traversal.
     * 
     * @return all keys in the symbol table following an in-order traversal
     */
    public Iterable<T> keysInOrder() {
        Queue<T> queue = new LinkedList<T>();
        keysInOrder(root, queue);
        return queue;
    }

    /**
     * Adds the keys in the subtree to queue following an in-order traversal.
     * 
     * @param x the subtree
     * @param queue the queue
     */
    private void keysInOrder(TreeNode<T>x, Queue<T> queue) {
        if (x == null) return;
        keysInOrder(x.left, queue);
        queue.add(x.value);
        keysInOrder(x.right, queue);
    }

    /**
     * Returns all keys in the symbol table following a level-order traversal.
     * 
     * @return all keys in the symbol table following a level-order traversal.
     */
    public Iterable<T> keysLevelOrder() {
        Queue<T> queue = new LinkedList<T>();
        if (!isEmpty()) {
            Queue<TreeNode<T>> queue2 = new LinkedList<TreeNode<T>>();
            queue2.add(root);
            while (!queue2.isEmpty()) {
                TreeNode<T>x = queue2.poll();
                queue.add(x.value);
                if (x.left != null) {
                    queue2.add(x.left);
                }
                if (x.right != null) {
                    queue2.add(x.right);
                }
            }
        }
        return queue;
    }

    /**
     * Returns all keys in the symbol table in the given range.
     * 
     * @param lo the lowest key
     * @param hi the highest key
     * @return all keys in the symbol table between {@code lo} (inclusive)
     *         and {@code hi} (exclusive)
     * @throws IllegalArgumentException if either {@code lo} or {@code hi}
     *             is {@code null}
     */
    public Iterable<T> keys(T lo, T hi) {
        if (lo == null) throw new IllegalArgumentException("first argument to keys() is null");
        if (hi == null) throw new IllegalArgumentException("second argument to keys() is null");
        Queue<T> queue = new LinkedList<T>();
        keys(root, queue, lo, hi);
        return queue;
    }

    /**
     * Adds the keys between {@code lo} and {@code hi} in the subtree
     * to the {@code queue}.
     * 
     * @param x the subtree
     * @param queue the queue
     * @param lo the lowest key
     * @param hi the highest key
     */
    private void keys(TreeNode<T>x, Queue<T> queue, T lo, T hi) {
        if (x == null) return;
        int cmplo = lo.compareTo(x.value);
        int cmphi = hi.compareTo(x.value);
        if (cmplo < 0) keys(x.left, queue, lo, hi);
        if (cmplo <= 0 && cmphi >= 0) queue.add(x.value);
        if (cmphi > 0) keys(x.right, queue, lo, hi);
    }

    /**
     * Returns the number of keys in the symbol table in the given range.
     * 
     * @param lo minimum endpoint
     * @param hi maximum endpoint
     * @return the number of keys in the symbol table between {@code lo}
     *         (inclusive) and {@code hi} (exclusive)
     * @throws IllegalArgumentException if either {@code lo} or {@code hi}
     *             is {@code null}
     */
    public int size(T lo, T hi) {
        if (lo == null) throw new IllegalArgumentException("first argument to size() is null");
        if (hi == null) throw new IllegalArgumentException("second argument to size() is null");
        if (lo.compareTo(hi) > 0) return 0;
        if (contains(hi)) return rank(hi) - rank(lo) + 1;
        else return rank(hi) - rank(lo);
    }

    /**
     * Checks if the AVL tree invariants are fine.
     * 
     * @return {@code true} if the AVL tree invariants are fine
     */
    public boolean check() {
        if (!isBST()) LOGGER.severe("Symmetric order not consistent");
        if (!isAVL()) LOGGER.severe("AVL property not consistent");
        if (!isSizeConsistent()) LOGGER.severe("Subtree counts not consistent");
        if (!isRankConsistent()) LOGGER.severe("Ranks not consistent");
        return isBST() && isAVL() && isSizeConsistent() && isRankConsistent();
    }

    /**
     * Checks if AVL property is consistent.
     * 
     * @return {@code true} if AVL property is consistent.
     */
    private boolean isAVL() {
        return isAVL(root);
    }

    /**
     * Checks if AVL property is consistent in the subtree.
     * 
     * @param x the subtree
     * @return {@code true} if AVL property is consistent in the subtree
     */
    private boolean isAVL(TreeNode<T>x) {
        if (x == null) return true;
        int bf = balanceFactor(x);
        if (bf > 1 || bf < -1) return false;
        return isAVL(x.left) && isAVL(x.right);
    }

    /**
     * Checks if the symmetric order is consistent.
     * 
     * @return {@code true} if the symmetric order is consistent
     */
    private boolean isBST() {
        return isBST(root, null, null);
    }

    /**
     * Checks if the tree rooted at x is a BST with all keys strictly between
     * min and max (if min or max is null, treat as empty constraint) Credit:
     * Bob Dondero's elegant solution
     * 
     * @param x the subtree
     * @param min the minimum key in subtree
     * @param max the maximum key in subtree
     * @return {@code true} if if the symmetric order is consistent
     */
    private boolean isBST(TreeNode<T>x, T min, T max) {
        if (x == null) return true;
        if (min != null && x.value.compareTo(min) <= 0) return false;
        if (max != null && x.value.compareTo(max) >= 0) return false;
        return isBST(x.left, min, x.value) && isBST(x.right, x.value, max);
    }

    /**
     * Checks if size is consistent.
     * 
     * @return {@code true} if size is consistent
     */
    private boolean isSizeConsistent() {
        return isSizeConsistent(root);
    }

    /**
     * Checks if the size of the subtree is consistent.
     * 
     * @return {@code true} if the size of the subtree is consistent
     */
    private boolean isSizeConsistent(TreeNode<T> x) {
        if (x == null) return true;
        if (x.size != size(x.left) + size(x.right) + 1) return false;
        return isSizeConsistent(x.left) && isSizeConsistent(x.right);
    }

    /**
     * Checks if rank is consistent.
     * 
     * @return {@code true} if rank is consistent
     */
    private boolean isRankConsistent() {
        for (int i = 0; i < size(); i++)
            if (i != rank(select(i))) return false;
        for (T key : keys())
            if (key.compareTo(select(rank(key))) != 0) return false;
        return true;
    }

}
