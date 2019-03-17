package Core;

public class  TreeNode<T>{
	public T value;
	public TreeNode<T> left,right;
	public int size;
	public int height;
	public boolean color;
	// constructors
	
	public TreeNode(){
		left = null;
		right = null;
		size = 0;
		height = 0;
		color = false;
	}
	public TreeNode(T value) {
		this.value = value;
	}
	public TreeNode(T value, TreeNode<T> l, TreeNode<T> r) {
		left = l;
		right = r;
	}	
	public TreeNode(T value, int height, int size) {
		this.value = value;
		this.height = height;
		this.size = size;
	}
	public TreeNode(T value, boolean color, int size) {
		this.value = value;
		this.color = color;
		this.size = size;
	}
}
