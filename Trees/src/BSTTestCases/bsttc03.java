package BSTTestCases;

import Core.Tree;
import Core.TreeNode;
import Core.Utilities;
import java.io.IOException;
import java.util.logging.*;

public class bsttc03 {
	private static final Logger LOGGER = Logger.getLogger(bsttc01.class.getName());
	private static FileHandler fh;  
	
	public static void main(String[] args) {
		
		
		try {
			System.out.println("Starting Test case for remove");
			init("./Logs/BST/bsttc03.log");
			
			Tree<Integer> root = Utilities.createTree("BST", LOGGER);
			
			int count = 10;
			
			LOGGER.warning("INSERTING "+count+" entries");
			
			Utilities.populateTree(root, "Uniform", (long) 5.0,10);
	        
			root.display();
	        
			root.remove(9146);
			
			root.display();
	        
			System.out.println("Test successfully Executed");
			LOGGER.fine("Test for remove successfully completed");
			
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
