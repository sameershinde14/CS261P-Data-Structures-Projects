package Core;

public interface ITree<T> {
	public void add(T key);
	public void remove(T key);
	public TreeNode<T> find(T value); 
	public int getHeight(TreeNode<T> root);
	public int getDepth(TreeNode<T> root);
}
