package RBTTestCases;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import Core.Tree;
import Core.Utilities;

public class rbttc08 {
	private static final Logger LOGGER = Logger.getLogger(rbttc08.class.getName());
	private static FileHandler fh;  
	
	public static void main(String[] args) {	
		try {
			System.out.println("Starting Test case for Skewed Inserts");
			init("./Logs/RBTree/rbttc08.log");
			
			Tree<Integer> root = Utilities.createTree("RBTree", LOGGER);
			
			int count = 10000;
			
			LOGGER.warning("INSERTING "+count+" entries");
			
			Utilities.populateSkewedTree(root, "Uniform", (long) 5.0,count);
	        
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
