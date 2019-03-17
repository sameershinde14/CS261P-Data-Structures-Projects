package SplayTestCases;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import Core.Tree;
import Core.Utilities;

public class splaytc01 {
	private static final Logger LOGGER = Logger.getLogger(splaytc01.class.getName());
	private static FileHandler fh;  
	
	public static void main(String[] args) {
		
		
		try {
			System.out.println("Starting Test case for Inserts and Display");
			init("./Logs/SplayTree/splaytc01.log");
			
			Tree<Integer> root = Utilities.createTree("SPLAY", LOGGER);
			
			int count = 10;
			
			LOGGER.warning("INSERTING "+count+" entries");
			
			Utilities.populateTree(root, "Uniform", (long) 5.0,10);
	        
			root.display();
			
			
			System.out.println("Test successfully Executed");
			LOGGER.fine("Test for insert successfully completed");

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
