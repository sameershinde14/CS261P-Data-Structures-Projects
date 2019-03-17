package BSTTestCases;

import Core.Tree;
import Core.TreeNode;
import Core.Utilities;
import java.io.IOException;
import java.util.logging.*;

public class bsttc06 {
	private static final Logger LOGGER = Logger.getLogger(bsttc06.class.getName());
	private static FileHandler fh;  
	
	public static void main(String[] args) {
		
		
		try {
			System.out.println("Starting Test case for Removes and Display");
			init("./Logs/BST/bsttc06.log");
			
			Tree<Integer> root = Utilities.createTree("BST", LOGGER);
			
			int count = 10000;
			
			LOGGER.warning("INSERTING "+count+" entries");
			
			Utilities.populateTree(root, "Uniform", (long) 5.0,count);
			
			int toBeDeleted = count/2;
	        
			Utilities.deleteFromTree(root, "Uniform", (long) 5.0,toBeDeleted);
			
			root.display();
			
			if(root.check()) {
				System.out.println("Test successfully Executed");
				LOGGER.fine("Test for remove successfully completed");
			}
			else {
				System.out.println("Test not successful");
				LOGGER.fine("Test for remove failed");
			}
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
