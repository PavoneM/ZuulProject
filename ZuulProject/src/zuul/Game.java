package zuul;

import java.util.ArrayList;
import zuul.room.Room;

public class Game {
	
	// Parser de lecture de commande
    private Parser parser;
    
    // Attribut désignant l'étudiant
    private Student student;
    
    // Carte de polytech
    private ArrayList<ArrayList<? extends Room>> map;
	
    /**
     * Create the game and initialise its internal map.
     */
    public Game() {
        student = new Student();
        map = new ArrayList<ArrayList<? extends Room>>();
        parser = new Parser();
        generateStaticMap();
    }
    
    /**
     * Create a static map for begin a game.
     * The map is composed by:
     * 	- 3 corridors
     *  - One of each room
     */
    public void generateStaticMap(){
    	
    	// Creation des salles
    	Room classroom, lab, examroom, library, lunchroom;
    	Room corridor1,corridor2,corridor3; 

        // Instanciation des salles
    	classroom = new Room("in a classroom");
    	examroom = new Room("in a exam room");
        library = new Room("in the library");
        lab = new Room("in a computing lab");
        lunchroom = new Room("in the lunchroom");
        
        corridor1 = new Room("in a corridor1");
        corridor2 = new Room("in a corridor2");
        corridor3 = new Room("in a corridor3");
        
        ///////
        /////// ICI OPTTIMISATION AVEC LE SET EXIT (Creer automatiquement la deuxième sortie)
        ///////
        
        // Installation des sorties pour chaque salle
        // Colonne 1
        corridor1.setExit("north", classroom);
        corridor1.setExit("south", library);
        corridor1.setExit("east", corridor2);
        classroom.setExit("south", corridor1);
        library.setExit("north", corridor1);

        // Colonne 2
        corridor2.setExit("north", lab);
        corridor2.setExit("south", lunchroom);
        corridor2.setExit("east", corridor3);
        corridor2.setExit("west", corridor1);
        lab.setExit("south", corridor2);
        lunchroom.setExit("north", corridor2);
        
        // Colonne 3
        corridor3.setExit("north", examroom);
        corridor3.setExit("west", corridor2);        
        examroom.setExit("south", corridor3);
        
        // Creation de la map
        // Ligne 1
        ArrayList ligne1 = new ArrayList();
        ligne1.add(classroom);
        ligne1.add(lab);
        ligne1.add(examroom);
        
        // Ligne 2
        ArrayList ligne2 = new ArrayList();
        ligne2.add(corridor1);
        ligne2.add(corridor2);
        ligne2.add(corridor3);
        
        // Ligne 3
        ArrayList ligne3 = new ArrayList();
        ligne3.add(library);
        ligne3.add(lunchroom);
        
        // Ajout des lignes à la grille
        map.add(ligne1);
        map.add(ligne2);
        map.add(ligne3);
        
        // Placement du joueur dans le couloir d'entrée
        student.setCurrentRoom(corridor1);
        
        // Découverte de la zone de départ
        student.getCurrentRoom().discover();
    }
    
    public void displayMap(){
    	
    	System.out.println("\n/*********************************/\n");
    	
    	// Coordonées de la salle courante 
    	int currentI=0, currentJ=0;
    	
    	// Tant qu'on ne trouve pas la salle dans la grille on incrémente les coordonées
    	while((currentJ = map.get(currentI).indexOf(student.getCurrentRoom()) ) == -1 )
    		currentI++;
    	
    	// Itération sur toute la grille
    	for(int i=0; i<map.size(); i++){
        	System.out.print("\t");
    		for(int j=0; j<map.get(i).size(); j++){
    			
    			// Si on trouve la salle courante on affique des parenthèses
    			if(i == currentI && j == currentJ)
    				System.out.print("(C)");
    			
    			// Si la salle est connue on l'affiche avec des crochets
    			else if(map.get(i).get(j).isDiscovered())
    				System.out.print("[C]");
    			
    			// Si on peut aller au nord, on affiche [?]
    			else if(student.getCurrentRoom().existexit("north") && i == (currentI-1) && j == currentJ && !map.get(i).get(j).isDiscovered())
    				System.out.print("[?]");
    			
    			// Si on peut aller au sud, on affiche [?]
    			else if(student.getCurrentRoom().existexit("south") && i == (currentI+1) && j == currentJ && !map.get(i).get(j).isDiscovered())
    				System.out.print("[?]");
    			
    			// Si on peut aller au ouest, on affiche [?]
    			else if(student.getCurrentRoom().existexit("west") && i == currentI && j == (currentJ-1) && !map.get(i).get(j).isDiscovered())
    				System.out.print("[?]");
    			
    			// Si on peut aller au est, on affiche [?]
    			else if(student.getCurrentRoom().existexit("east") && i == currentI && j == (currentJ+1) && !map.get(i).get(j).isDiscovered())
    				System.out.print("[?]");
    			
    			// Si on ne connait pas la salle, ou il n'existe pas de salle on affiche des espaces
    			else System.out.print("   ");
    
    		}
    		System.out.print("\n");
    	}
    	
    	System.out.println("\n/*********************************/\n");
    	
    }
    /** 
     * Display the energy of the player
     */
    private void energyBar(){
    	System.out.print("Your energy : ");
    	System.out.print("|| ");
    	for (int i = 0; i< student.getEnergy(); i++){
    		System.out.print((char)248+" ");
    	}
    	System.out.println("||");
    }
    /**
     * Print out the opening message for the player.
     */
    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome on the Polytech'Groland building!");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        displayMap();
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
    	
    	// Variable pour quitter
        boolean wantToQuit = false;
        
        // Clear de la console
    	for (int i = 0; i < 50; ++i) System.out.println();
        
    	// Test si la commande existe
        if (command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }
        
        // Lire la prochaine commande
        String commandWord = command.getCommandWord();
        
        // Afficher l'aide
        if (commandWord.equals("help")) {
            printHelp();
        } 
        
        // Aller dans une direction
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        
        // Quitter le jeu
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }        
        
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
    	
    	// Test si la commande contient un deuxième mot
        if (!command.hasSecondWord()) {
            // Si il n'y a pas de deuxième mot, afficher "go where"
            System.out.println("Go where?");
            return;
        }
        
        // Deuxième mot : choix de la direction
        String direction = command.getSecondWord();
        
        // Choix de la sortie de la salle
        Room nextRoom = student.getCurrentRoom().getExit(direction);
        
        // Si il y a pas de sortie, afficher "There is no door"
        if (nextRoom == null) {
        	displayMap();
            System.out.println("There is no door!");
        } 
        
        // Si la sortie existe
        else {
        	// Définir la salle courante
            student.setCurrentRoom(nextRoom);
            
            // Afficher la carte
            displayMap();
            
            // Découvrir la nouvelle salle
            nextRoom.discover();
            
            // Afficher la description de la salle
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
    	
    	// Si il y a un deuxième mot, ne pas quitter
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        } 
        
        // Sinon, quitter l'application
        else {
            return true;
        }
    }
    
    public static void progressBar(int seconds){
    	System.out.println("||====================>");
    	/*for(int i=0; i<seconds ; i++){
    		System.out.print("||");
    		try{
    			Thread.sleep(seconds*1000);
    		}catch(Exception e){}
    		
    	}
    	*/
    }
    
	public static void main(String[] args) {
		
		// Creation du jeu
		Game game = new Game();
		
		// Afficher la page de bienvenu
        game.printWelcome();
        game.energyBar();
        // Variable pour savoir si le joueur veut arrêter de jouer
        boolean finished = false;
        
        while (!finished) {
        	
        	// Lire la commande
            Command command = game.parser.getCommand();
            
            // Gérer la commande
            finished = game.processCommand(command);
        }
        
        // Afficher une phrase de conclusion du jeu
        System.out.println("Thank you for playing.  Good bye.");

	}
}
