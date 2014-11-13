package zuul;

import java.util.ArrayList;
import zuul.room.Room;

public class Game {
	
    private Parser parser;
    private Student student;
    private ArrayList<ArrayList<? extends Room>> map;
	
    /**
     * Create the game and initialise its internal map.
     */
    public Game() {
        student = new Student();
        map = new ArrayList<ArrayList<? extends Room>>();
        generateStaticMap();
        parser = new Parser();
    }
    
    public void generateStaticMap(){
    	Room classroom, lab, examroom, library, lunchroom;
    	Room corridor1,corridor2,corridor3; 

        // create the rooms
    	classroom = new Room("in a classroom");
    	examroom = new Room("in a exam room");
        library = new Room("in the library");
        lab = new Room("in a computing lab");
        lunchroom = new Room("in the lunchroom");
        
        corridor1 = new Room("in a corridor1");
        corridor2 = new Room("in a corridor2");
        corridor3 = new Room("in a corridor3");

        // initialise room exits
        corridor1.setExit("north", classroom);
        corridor1.setExit("south", library);
        corridor1.setExit("east", corridor2);

        corridor2.setExit("north", lab);
        corridor2.setExit("south", lunchroom);
        corridor2.setExit("east", corridor3);
        corridor2.setExit("west", corridor1);
        
        corridor3.setExit("north", examroom);
        corridor3.setExit("west", corridor2);
        
        
        ///////
        /////// ICI OPTTIMISATION AVEC LE SET EXIT (Creer automatiquement la deuxième sortie)
        ///////
        
        classroom.setExit("south", corridor1);
        lab.setExit("south", corridor2);
        library.setExit("north", corridor1);
        examroom.setExit("south", corridor3);
        lunchroom.setExit("north", corridor2);
        
        ArrayList ligne1 = new ArrayList();
        ligne1.add(classroom);
        ligne1.add(lab);
        ligne1.add(examroom);
        
        ArrayList ligne2 = new ArrayList();
        ligne2.add(corridor1);
        ligne2.add(corridor2);
        ligne2.add(corridor3);
        
        ArrayList ligne3 = new ArrayList();
        ligne3.add(library);
        ligne3.add(lunchroom);
        
        map.add(ligne1);
        map.add(ligne2);
        map.add(ligne3);
        
        student.setCurrentRoom(corridor1); // start game outside
        student.getCurrentRoom().discover();
    }
    
    public void displayMap(){
    	
    	System.out.println("\n/*********************************/\n");
    	
    	boolean aDecouvert = false;
    	int currentI=0, currentJ=0;
    	while((currentJ = map.get(currentI).indexOf(student.getCurrentRoom()) ) == -1 )
    		currentI++;
    	
    	System.out.println("I "+currentI + " J "+ currentJ);
    	
    	for(int i=0; i<map.size(); i++){
        	System.out.print("\t");
    		for(int j=0; j<map.get(i).size(); j++){
    			if(i == currentI && j == currentJ)
    				System.out.print("(C)");
    			else if(map.get(i).get(j).isDiscovered())
    				System.out.print("[C]");
    			else if(student.getCurrentRoom().existexit("north") && i == (currentI-1) && j == currentJ && !map.get(i).get(j).isDiscovered())
    				System.out.print("[?]");
    			else if(student.getCurrentRoom().existexit("south") && i == (currentI+1) && j == currentJ && !map.get(i).get(j).isDiscovered())
    				System.out.print("[?]");
    			else if(student.getCurrentRoom().existexit("west") && i == currentI && j == (currentJ-1) && !map.get(i).get(j).isDiscovered())
    				System.out.print("[?]");
    			else if(student.getCurrentRoom().existexit("east") && i == currentI && j == (currentJ+1) && !map.get(i).get(j).isDiscovered())
    				System.out.print("[?]");
    			else System.out.print("   ");
    
    		}
    		System.out.print("\n");
    		aDecouvert=false;
    	}
    	
    	System.out.println("\n/*********************************/\n");
    	
    }
    
    /**
     * Print out the opening message for the player.
     */
    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(student.getCurrentRoom().getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * 
     * @param command
     *            The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) {
        boolean wantToQuit = false;
    	for (int i = 0; i < 50; ++i) System.out.println();
        
        if (command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        } else if (commandWord.equals("go")) {
            goRoom(command);
        } else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        // else command not recognised.
        
        
        return wantToQuit;
    }
    
    /**
     * Print out some help information. Here we print some stupid, cryptic
     * message and a list of the command words.
     */
    private void printHelp() {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /**
     * Try to in to one direction. If there is an exit, enter the new room,
     * otherwise print an error message.
     */
    private void goRoom(Command command) {
    	
        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();
        
        // Try to leave current room.
        Room nextRoom = student.getCurrentRoom().getExit(direction);
        
        if (nextRoom == null) {
        	displayMap();
            System.out.println("There is no door!");
        } else {
            student.setCurrentRoom(nextRoom);
            displayMap();
            nextRoom.discover();
            System.out.println(student.getCurrentRoom().getLongDescription());
        }
    }

    /**
     * "Quit" was entered. Check the rest of the command to see whether we
     * really quit the game.
     * 
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        } else {
            return true; // signal that we want to quit
        }
    }
    
	public static void main(String[] args) {
		Game game = new Game();
		
        game.printWelcome();

        // Enter the main command loop. Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (!finished) {
            Command command = game.parser.getCommand();
            finished = game.processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");

	}
	
	

}
