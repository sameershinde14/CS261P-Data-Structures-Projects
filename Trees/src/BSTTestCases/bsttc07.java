package BSTTestCases;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import Core.Tree;
import Core.Utilities;

public class bsttc07 {
	private static final Logger LOGGER = Logger.getLogger(bsttc07.class.getName());
	private static FileHandler fh;  
	
	public static void main(String[] args) {
		
		
		try {
			System.out.println("Starting Test case for Traversal");
			init("./Logs/BST/bsttc07.log");
			
			Tree<Integer> root = Utilities.createTree("BST", LOGGER);
			
			int count = 10000;
			
			LOGGER.warning("INSERTING "+count+" entries");
			
			Utilities.populateTree(root, "Uniform", (long) 5.0,count);
	        Utilities.traverseTree(root);
			if(root.check()) {
				System.out.println("Test successfully Executed");
				LOGGER.fine("Test for traversal successfully completed");
			}
			else {
				System.out.println("Test not successful");
				LOGGER.fine("Test for traversal failed");
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
