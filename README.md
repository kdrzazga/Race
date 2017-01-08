/* Race
  
  Main class to start the project - package presentation.test/GameTest.java
  
  System tests in folder logic/test, secondary system tests in lib/test

  Project INCLUDES binaries for LightWeightJavaopenGL. Bundling LWJGL doens't violate its license.
  
  To setup project under NetBeans:
  1. Add LWJGL jars to libraries - details below:
	  1.1. Open project in NetBeans.
	  1.2. In Projects tab (by default on the left), right-click project and select Properties option. Project Properites window appears.
	  1.3. On the left pane of Project Properites window click node Libraries. In the center of the window tabs appear.
	  1.4. In tab Compile click button Add JAR/Folder. File Dialog window opens.
	  1.5. Select folder LWJGL/jars and confirm with button Open
  2. Add library path = "path\to\LWJGL\native\libraries\for\given\OS" to VM options - details below:
	  2.1. Open the same Porject Properites window as in point 1.2.
	  2.2. On the left pane of Project Properites window click node Run. In the center of the window text boxes appear.
	  2.3. In the largest text box type -Djava.library.path="path\to\LWJGL\native\libraries\for\given\OS" 
	  (e.g. -Djava.library.path="E:\programming\java\Race\trunk\LWJGL\native\windows\") and confirm with button OK.
	  
  Now the project is compileable.
  
*/
