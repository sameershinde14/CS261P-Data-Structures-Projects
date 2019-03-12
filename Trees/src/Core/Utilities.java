package Core;

import java.util.Random;
import java.util.logging.Logger;

public class Utilities {
	public static Tree createTree(String name, Logger log) {
		if(name.equals("BST")) {
			return new BST(log);
		}
		else if(name.equals("AVL")) {
			return new AVL(log);
		}
		else if(name.equals("SPLAY")) {
			return new SplayBST(log);
		}
		else if(name.equals("RBTree")) {
			return new RedBlackTree(log);
		}
		return null;
	}
	public static void populateTree(Tree node, String type, long seed, int count) {
		Random rand = new Random(seed);
		for(int i = 0; i < count; i++) {
			int entry = 0;
			if(type.equals("Gaussion")) {
				entry = (int) rand.nextGaussian();
			}
			else {
				entry = rand.nextInt(32767);
			}
			node.add(entry);
		}	

	}
	public static void deleteFromTree(Tree node, String type, long seed, int toBeDeleted) {
		Random rand = new Random(seed);
		int i = 0;
		while(i < toBeDeleted){
			int entry = 0;
			if(type.equals("Gaussion")) {
				entry = (int) rand.nextGaussian();
			}
			else {
				entry = rand.nextInt(32767);
			}
			if(node.contains(entry))
				node.remove(entry);
			else
				continue;
			i++;
		}	
		
	}
	public static void traverseTree(Tree node) {
		node.display();
	}

}
