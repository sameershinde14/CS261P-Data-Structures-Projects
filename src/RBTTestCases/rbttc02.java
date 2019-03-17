package RBTTestCases;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import Core.Tree;
import Core.TreeNode;
import Core.Utilities;

public class rbttc02 {
	private static final Logger LOGGER = Logger.getLogger(rbttc02.class.getName());
	private static FileHandler fh;  
	
	public static void main(String[] args) {
		
		
		try {
			System.out.println("Starting Test case for find");
			init("./Logs/RBTree/rbttc02.log");
			
			Tree<Integer> root = Utilities.createTree("RBTree", LOGGER);
			
			int count = 10;
			
			LOGGER.warning("INSERTING "+count+" entries");
			
			Utilities.populateTree(root, "Uniform", (long) 5.0,10);
	        root.display();
			// Test for successful find
	        TreeNode<Integer> element = root.find(9146);
			
			if(element !=null)
				LOGGER.fine("Element Found: "+ element.value);
			else
				LOGGER.severe("Element not found");
			
			// Test for unsuccessful find
			TreeNode<Integer> element2 = root.find(5);
			
			if(element2 !=null)
				LOGGER.fine("Element Found: "+ element2.value);
			else
				LOGGER.severe("Element not found");
			
			System.out.println("Test successfully Executed");
			LOGGER.fine("Test for find successfully completed");
			
		}
		catch (SecurityException e) {  
	        e.printStackTrace();  
	    }  
	}

	private static void init(String path) {
		try {
			fh = new FileHandler(path);
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		}  
		LOGGER.setLevel(Level.ALL);
        LOGGER.addHandler(fh);
        SimpleFormatter formatter = new SimpleFormatter();  
        fh.setFormatter(formatter);  
		LOGGER.info("Logger Name: "+LOGGER.getName());		
	}
}
