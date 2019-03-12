package Core;

import java.util.logging.Logger;

public class SplayBST <T extends Comparable<T>> extends Tree<T>{
	public SplayBST(Logger LOG) {	
		LOGGER = LOG;
	}
	public SplayBST() {	
	}
	public boolean contains(T key) {
        return get(key) != null;
    }

    // return value associated with the given key
    // if no such value, return null
    public T get(T key) {
        root = splay(root, key);
        int cmp = key.compareTo(root.value);
        if (cmp == 0) return root.value;
        else          return null;
    }    

   /***************************************************************************
    *  Splay tree insertion.
    ***************************************************************************/
    public void add(T value) {
        // splay key to root
    	long start = System.nanoTime();
        if (root == null) {
            root = new TreeNode<T>(value);
            long stop = System.nanoTime();
            long elapsedTime = stop - start;
            noOfNodes++;
            LOGGER.info("SUCCESSFULLY INSERTED: SIZE= "+ noOfNodes +",TIME REQUIRED= "+ elapsedTime);
            return;
        }
        
        root = splay(root, value);

        int cmp = value.compareTo(root.value);
        
        // Insert new node at root
        if (cmp < 0) {
            TreeNode<T> n = new TreeNode<T>(value);
            n.left = root.left;
            n.right = root;
            root.left = null;
            root = n;
        }

        // Insert new node at root
        else if (cmp > 0) {
            TreeNode<T> n = new TreeNode<T>(value);
            n.right = root.right;
            n.left = root;
            root.right = null;
            root = n;
        }

        // It was a duplicate key. Simply replace the value
        else {
            root.value = value;
        }
        long stop = System.nanoTime();
        long elapsedTime = stop - start;
        noOfNodes++;
        LOGGER.info("SUCCESSFULLY INSERTED: SIZE= "+ noOfNodes +",TIME REQUIRED= "+ elapsedTime);
        
    }
    
   /***************************************************************************
    *  Splay tree deletion.
    ***************************************************************************/
    /* This splays the key, then does a slightly modified Hibbard deletion on
     * the root (if it is the node to be deleted; if it is not, the key was 
     * not in the tree). The modification is that rather than swapping the
     * root (call it node A) with its successor, it's successor (call it TreeNode<T> B)
     * is moved to the root position by splaying for the deletion key in A's 
     * right subtree. Finally, A's right child is made the new root's right 
     * child.
     */
    public void remove(T  key) {
    	long start = System.nanoTime();
        if (root == null) return; // empty tree
        
        root = splay(root, key);

        int cmp = key.compareTo(root.value);
        
        if (cmp == 0) {
            if (root.left == null) {
                root = root.right;
            } 
            else {
                TreeNode<T> x = root.right;
                root = root.left;
                splay(root, key);
                root.right = x;
            }
        }
        long stop = System.nanoTime();
        long elapsedTime = stop - start;
        noOfNodes--;
        LOGGER.info("SUCCESSFULLY REMOVED: SIZE= "+ noOfNodes +",TIME REQUIRED= "+ elapsedTime);

        // else: it wasn't in the tree to remove
    }
    
    
   /***************************************************************************
    * Splay tree function.
    * **********************************************************************/
    // splay key in the tree rooted at TreeNode<T> h. If a node with that key exists,
    //   it is splayed to the root of the tree. If it does not, the last node
    //   along the search path for the key is splayed to the root.
    private TreeNode<T> splay(TreeNode<T> h, T  key) {
        if (h == null) return null;

        int cmp1 = key.compareTo(h.value);

        if (cmp1 < 0) {
            // key not in tree, so we're done
            if (h.left == null) {
                return h;
            }
            int cmp2 = key.compareTo(h.left.value);
            if (cmp2 < 0) {
                h.left.left = splay(h.left.left, key);
                h = rotateRight(h);
            }
            else if (cmp2 > 0) {
                h.left.right = splay(h.left.right, key);
                if (h.left.right != null)
                    h.left = rotateLeft(h.left);
            }
            
            if (h.left == null) return h;
            else                return rotateRight(h);
        }

        else if (cmp1 > 0) { 
            // key not in tree, so we're done
            if (h.right == null) {
                return h;
            }

            int cmp2 = key.compareTo(h.right.value);
            if (cmp2 < 0) {
                h.right.left  = splay(h.right.left, key);
                if (h.right.left != null)
                    h.right = rotateRight(h.right);
            }
            else if (cmp2 > 0) {
                h.right.right = splay(h.right.right, key);
                h = rotateLeft(h);
            }
            
            if (h.right == null) return h;
            else                 return rotateLeft(h);
        }

        else return h;
    }


   /***************************************************************************
    *  Helper functions.
    ***************************************************************************/

    // height of tree (1-node tree has height 0)
    public int height() { return height(root); }
    private int height(TreeNode<T> x) {
        if (x == null) return -1;
        return 1 + Math.max(height(x.left), height(x.right));
    }

    
    public int size() {
        return size(root);
    }
    
    private int size(TreeNode<T> x) {
        if (x == null) return 0;
        else return 1 + size(x.left) + size(x.right);
    }
    
    // right rotate
    private TreeNode<T> rotateRight(TreeNode<T> h) {
    	long start = System.nanoTime();
        TreeNode<T> x = h.left;
        h.left = x.right;
        x.right = h;
        long stop = System.nanoTime();
        long elapsedTime = stop - start;
        LOGGER.info("SUCCESSFULLY ROTATE RIGHT: SIZE= "+ noOfNodes +",TIME REQUIRED= "+ elapsedTime);
        return x;
    }

    // left rotate
    private TreeNode<T> rotateLeft(TreeNode<T> h) {
    	long start = System.nanoTime();
        TreeNode<T> x = h.right;
        h.right = x.left;
        x.left = h;
        long stop = System.nanoTime();
        long elapsedTime = stop - start;
        LOGGER.info("SUCCESSFULLY ROTATE LEFT: SIZE= "+ noOfNodes +",TIME REQUIRED= "+ elapsedTime);
        return x;
    }

}
