
OVERVIEW:

•	The Text_based game project simulates a player solving a maze
•	Every cell in the dungeon is connected to every other cell with at least one path.
•	The number of path between two cell is defined by the a property called interconnectivity.
•	The Dungeons are on two types Wrapping and Non-Wrapping.
•	The columns and rows in a wrapping dungeon can wrap on to themselves, i.e. row1 wraps to row1 and col1 wraps on to col1.
•	The Dungeon has two only types of locations tunnels with 2 exits and caves with 1,3 or 4 exits.
•	Only the Caves can have treasures in them. One can have multiple treasures. Tunnels cannot have treasure.
•	There are 3 types of treasures namely Diamonds, Saphire and Ruby which are randomly allocated to the caves.
•	At least 20 percent of the caves have 1 or more treasure and arrow
•	Two caves are selected at random to be start and end caves such that the minimum distance between them is always 5 or more.
•	The dungeon is created using Random Krushkal's Algorithm.
•	There are Otyugh a type of monsters in some of the caves but never in tunnel.
•	There are arrows in caves and the player can pick the arrows and use them to kill the monster why shooting at them.



LIST OF FEATURES. 
•	Represent wrapping and non-wrapping dungeon.
•	Move the player in a valid direction
•	Accumulate the treasures as the player moves through the maze
•	Visit all cells in the maze
•	Visit Goal node starting from Start node.
•	Shoot Arrow is a given direction for a given distance.
•	Picking up Arrows.


HOW TO RUN. 
•	Extract the zip file
•	Open cmd/Terminal and go into the res/folder 
•	The jar file is present here.
•	In the terminal type: java –jar DungeonView.jar <row> <col> <type> <interconnectivity> <cave with treasures> <Monsters> e.g. java -jar DungeonView.jar 5 5 1 3 0.6 4     .Enter 1 for Wrapping
	and 2 for non-wrapping dungeon.
•	Following this the user has to follow the instructions given by the driver to play the game.



HOW TO USE THE PROGRAM
This is an interactive application where the user has to give inputs to move the player in a particular direction. Run the application as per HOW TO RUN section.
After the program starts running the user will be asked to enter an option to play the game which are as follows:
			
			* The user needs to select one of the actions MOVE,PICK or SHOOT
			* The user has to give a direction N,S,E,W if the user selects to move.
			* The user has to give a distance between 1-5 and a direction if he/she chooses to SHOOT.
			* The user has to pick between ARROW and TREASURE if he/she decides to PICK.
			* The user has to give ARROW or TREASURE to pick.			
Depending on the choice of the user the description and movement of the player in the maze is displayed on the console.If the user does not select of the given option
Then an error message is shown and then the user is asked to enter the choice again.

DESCRIPTION OF EXAMPLES. 
•	Display wrapping Dungeon and non-wrapping dungeon
•	Display Players moving through each matrix of the node.
•	Display the location description
•	Display the player description
•	Display the location description after the player picks the treasure
•	Display the updated maze after every move.


DESIGN/MODEL CHANGES
•	The Monster interface has been replaced by Monster interface implemented by Otyugh class. 
•	Builder pattern is now used to create the dungeon.
•	Command pattern is now being used to execute the user commands.
•	String dump of the dungeon on the image has been removed.
•	An enum has been added for valid user options
•	An enum has been added for allowed direction
•	Arrowtracer class has been added to trace the arrows path.
•	IPlayer and ILocation have been made package private and are Player and Location classes.
•	Smell enum introduced to represent the intensity of smell.




ASSUMPTIONS:
•	The Player does not have a choice to select treasure. The player will pick all treasure at a location through which the player passes.
•	The Player does not have a choice to pick just 1 arrow. The player will pick all arrows at a location through which the player passes.
•	The minimum size of the grid has to be 5 * 5 or more.
•	There is only one Arena. They're cannot be multiple instances of Arena.
•	Nothing will happen in case an invalid option is given.The description of current location and last state of player will be printed.
•	The arrow can be shot for a distance [1 5] both inclusive.
•	There cannot be more than 1 monster in a location.
•	The game terminates when the user is killed or has reached the end location and escaped the monster.






LIMITATION
•	The program does not have a GUI. It command Line based application which limits representation of the game.
•	The program does not allow the player to choose treasures at a location.
•	The program does not allow the player to pick just one arrow at a location.The player has to pick all arrows.
•	The program expects inputs from the user and that too specific inputs. This makes it prone to errors.
•	The program does not support the data to be persistent in the memory.



CITATIONS
•	https://pencilprogrammer.com/java-programs/substring-frequency/
•	https://www.geeksforgeeks.org/merge-two-sets-in-java/
•	https://www.baeldung.com/java-shuffle-collection





