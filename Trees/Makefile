# ======================================================================
# FILE:        Makefile
#
# AUTHOR:      Sameer Shinde
#
# DESCRIPTION: This file contains commands for this assignment.
#              Commands are as follow:
#
#              - make            - compiles the project 
#                                  and executes the executable
#
#              - make clean      - deletes the executable
# ======================================================================


all: 
	@javac -d build src/Core/*.java src/BSTTestCases/*.java src/AVLTestCases/*.java src/SplayTestCases/*.java src/RBTTestCases/*.java
	@java -cp build BSTTestCases.bsttc01 
	@java -cp build BSTTestCases.bsttc02 
	@java -cp build BSTTestCases.bsttc03 
	@java -cp build BSTTestCases.bsttc04 
	@java -cp build BSTTestCases.bsttc05 
	@java -cp build BSTTestCases.bsttc06 
	@java -cp build BSTTestCases.bsttc07 
	@java -cp build BSTTestCases.bsttc08 
	@java -cp build BSTTestCases.bsttc09 

	
	@java -cp build AVLTestCases.avltc01
	@java -cp build AVLTestCases.avltc02
	@java -cp build AVLTestCases.avltc03
	@java -cp build AVLTestCases.avltc04
	@java -cp build AVLTestCases.avltc05
	@java -cp build AVLTestCases.avltc06
	@java -cp build AVLTestCases.avltc07
	@java -cp build AVLTestCases.avltc08
	@java -cp build AVLTestCases.avltc09

	@java -cp build SplayTestCases.splaytc01
	@java -cp build SplayTestCases.splaytc02
	@java -cp build SplayTestCases.splaytc03
	@java -cp build SplayTestCases.splaytc04
	@java -cp build SplayTestCases.splaytc05
	@java -cp build SplayTestCases.splaytc06
	@java -cp build SplayTestCases.splaytc07
	@java -cp build SplayTestCases.splaytc08
	@java -cp build SplayTestCases.splaytc09

	@java -cp build RBTTestCases.rbttc01
	@java -cp build RBTTestCases.rbttc02
	@java -cp build RBTTestCases.rbttc03
	@java -cp build RBTTestCases.rbttc04
	@java -cp build RBTTestCases.rbttc05
	@java -cp build RBTTestCases.rbttc06
	@java -cp build RBTTestCases.rbttc07
	@java -cp build RBTTestCases.rbttc08
	@java -cp build RBTTestCases.rbttc09
	
clean:
	rm -rf build/*