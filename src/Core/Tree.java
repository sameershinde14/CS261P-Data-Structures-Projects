package Core;

import java.util.logging.Logger;

public abstract class Tree<T extends Comparable<T>> implements ITree<T> {
	private int depth;
	private int height;
	protected int noOfNodes;
	protected TreeNode<T> root;
	protected Logger LOGGER;
	
	@Override
	public abstract void add(T key);
	@Override
	public abstract void remove(T key);
	@Override
	public TreeNode<T> find(T value) {
		long start =  System.nanoTime();
		TreeNode<T> res = search(root, value);
		long stop =  System.nanoTime();
		long elapsedTime = stop - start;
		if(res !=null)
			LOGGER.info("SUCCESSFULL FIND: SIZE= "+ noOfNodes +",TIME REQUIRED: "+ elapsedTime);
		else
			LOGGER.info("UNSUCCESSFULL FIND: SIZE= "+ noOfNodes +",TIME REQUIRED: "+ elapsedTime);
		return res;
	}
	@Override
	public int getHeight(TreeNode<T> root) {
		return height;
	}
	@Override
	public int getDepth(TreeNode<T> root) {
		return depth;
	}
	public TreeNode<T> root(){
		return root;
	}
	public void display() {
		inorder(root);
	}
	// helpers
	private TreeNode<T> search(TreeNode<T> root, T value) {
		 if (root==null || root.value.compareTo(value)==0) 
			 return root; 
		 
		 if (root.value.compareTo(value) > 0) // check this condition
		    return search(root.left, value); 
		  
		 return search(root.right, value); 
	}
	
	private void inorder(TreeNode<T> root) { 
        if (root != null) { 
            inorder(root.left); 
            System.out.println(root.value); 
            inorder(root.right); 
        } 
    } 
	public boolean check() {
		return false;
	}
	public boolean contains(T Key) {
		return false;
	}
	
}
