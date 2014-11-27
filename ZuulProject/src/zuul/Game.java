package zuul;

import java.util.ArrayList;
import java.util.Observer;
import java.util.concurrent.ExecutionException;

import zuul.item.LectItem;
import zuul.room.Classroom;
import zuul.room.Corridor;
import zuul.room.CourseRoom;
import zuul.room.ExamRoom;
import zuul.room.Lab;
import zuul.room.Library;
import zuul.room.LunchRoom;
import zuul.room.Planning;
import zuul.room.Room;

public class Game {
	
	// Parser de lecture de commande
    private Parser parser;
    
    // Attribut désignant l'étudiant
    private Student student;
    
    // Carte de polytech
    private ArrayList<ArrayList<? extends Room>> map;
    
    private Time time;
	
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
    	Classroom classroom = new Classroom("in a classroom", "Cl", generateStaticPlanning());
    	ExamRoom examroom = new ExamRoom("in a exam room", "Ex", generateStaticPlanning());
        Library library = new Library("in the library", "Li");
        Lab lab = new Lab("in a computing lab", "La", generateStaticPlanning());
        LunchRoom lunchroom = new LunchRoom("in the lunchroom", "Lu",.1f);
        
        time = new Time();
        
        time.addObserver(classroom);
        time.addObserver(examroom);
        time.addObserver(lab);
        
        new Thread(time).start();
        
        Corridor corridor1 = new Corridor("in a corridor1", "Co");
        Corridor corridor2 = new Corridor("in a corridor2", "Co");
        Corridor corridor3 = new Corridor("in a corridor3", "Co");
        
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
    
    public Planning generateStaticPlanning(){
    	
    	Lecture[][] table = new Lecture[5][5];
    	
    	// TODO effacer les lectures quand le fichier de config sera bon
    	ArrayList<LectItem> oopLecture = new ArrayList<LectItem>();
    	oopLecture.add(new LectItem("Introducing Objects", null));
    	oopLecture.add(new LectItem("Object Oriented Programming", null));
    	oopLecture.add(new LectItem("Improving structures with inherithence", null));
    	oopLecture.add(new LectItem("Abstraction techniques", null));
    	
    	ArrayList<LectItem> engLecture = new ArrayList<LectItem>();
    	engLecture.add(new LectItem("English lesson 1", null));
    	engLecture.add(new LectItem("English lesson 2", null));
    	engLecture.add(new LectItem("English lesson 3", null));
    	engLecture.add(new LectItem("English lesson 4", null));
    	
    	ArrayList<LectItem> mathLecture = new ArrayList<LectItem>();
    	mathLecture.add(new LectItem("Maths lecture 1", null));
    	mathLecture.add(new LectItem("Maths lecture 2", null));
    	mathLecture.add(new LectItem("Maths lecture 3", null));
    	mathLecture.add(new LectItem("Maths lecture 4", null));
    	
    	ArrayList<LectItem> algLecture = new ArrayList<LectItem>();
    	algLecture.add(new LectItem("AlgNum lecture 1", null));
    	algLecture.add(new LectItem("AlgNum lecture 2", null));
    	algLecture.add(new LectItem("AlgNum lecture 3", null));
    	algLecture.add(new LectItem("AlgNum lecture 4", null));
    	
    	ArrayList<LectItem> assLecture = new ArrayList<LectItem>();
    	assLecture.add(new LectItem("Assembly lecture 1", null));
    	assLecture.add(new LectItem("Assembly lecture 2", null));
    	assLecture.add(new LectItem("Assembly lecture 3", null));
    	assLecture.add(new LectItem("Assembly lecture 4", null));
    	
    	
    	for(int i=0;i<5;i++){
    		table[0][i] = new Lecture("English", "ENG", engLecture);
    		table[1][i] = new Lecture("Object Oriented Programming", "OOP", oopLecture);
    		table[2][i] = new Lecture("Maths", "MAT", mathLecture);
    		table[3][i] = new Lecture("Alg Num", "ALG", algLecture);
    		table[4][i] = new Lecture("Assembly", "ASS", assLecture);
    	}
    	
    	Planning p = new Planning(table);
    	
    	return p;
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
    			
    			// Recherche du caractère à afficher
    			String icon = map.get(i).get(j).getIcon();
    			
    			// Si on trouve la salle courante on affique des parenthèses
    			if(i == currentI && j == currentJ)
    				System.out.print("("+icon+")");
    			
    			// Si la salle est connue on l'affiche avec des crochets
    			else if(map.get(i).get(j).isDiscovered())
    				System.out.print("["+icon+"]");
    			
    			// Si on peut aller au nord, on affiche [?]
    			else if(student.getCurrentRoom().existexit("north") && i == (currentI-1) && j == currentJ && !map.get(i).get(j).isDiscovered())
    				System.out.print("[??]");
    			
    			// Si on peut aller au sud, on affiche [?]
    			else if(student.getCurrentRoom().existexit("south") && i == (currentI+1) && j == currentJ && !map.get(i).get(j).isDiscovered())
    				System.out.print("[??]");
    			
    			// Si on peut aller au ouest, on affiche [?]
    			else if(student.getCurrentRoom().existexit("west") && i == currentI && j == (currentJ-1) && !map.get(i).get(j).isDiscovered())
    				System.out.print("[??]");
    			
    			// Si on peut aller au est, on affiche [?]
    			else if(student.getCurrentRoom().existexit("east") && i == currentI && j == (currentJ+1) && !map.get(i).get(j).isDiscovered())
    				System.out.print("[??]");
    			
    			// Si on ne connait pas la salle, ou il n'existe pas de salle on affiche des espaces
    			else System.out.print("    ");
    
    		}
    		System.out.print("\n");
    	}
    	
    	System.out.println("\n/*********************************/\n");
    	
    }
    /** 
     * Display the energy of the player
     */
    private void energyBar(){
    	System.out.print("\nYour energy : ");
    	System.out.print("|| ");
    	for (int i = 1; i<= 20; i++){
    		if(i<=student.getEnergy()) System.out.print((char)248+" ");
    		else System.out.print("  ");
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
            if(student.getCurrentRoom() instanceof CourseRoom)
            	if(((CourseRoom) student.getCurrentRoom()).getCurrentCourse().isEqual("OOP")){
            		waitFor(10);
            		System.out.println("Your energy has been decreased by 2\nYou can now exit if you want");
            	}
        }
        
        // Allumer les lumières
        else if (commandWord.equals("switch")) {
            if( student.getCurrentRoom() instanceof Corridor ) switchLight(command);
            else System.out.println("You are not in a Corridor, there is no light to switch on/off...");
        }
        
        else if (commandWord.equals("check")) {
        	checkPlanning(command);
        }
        
        else if (commandWord.equals("drink") && student.getCurrentRoom() instanceof LunchRoom) {
        	if(student.getEnergy() < 20 ) drink();
        	else System.out.println("Sorry but you can't drink more because your energy is full");
        }
        
        // Quitter le jeu
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        
        else {
        	System.out.println("You can't do this action here ...");
        }
        
        return wantToQuit;
    }
    
    private void drink() {
		if(((LunchRoom) student.getCurrentRoom()).drink()){
			System.out.println("Good job ! You increased your energy !");
			student.increaseEnergy(2);
			energyBar();
		}
		else{
			System.out.println("Sorry but you started to play babyfoot and you have lost a random item");
			// TODO enlever une Lecture
		}
		System.out.println("If you want to play again type another time 'drink'");
			
	}

	private void switchLight(Command cmd) {
    	
    	// Test si la commande contient un deuxième mot
        if (!cmd.hasSecondWord()) {
            // Si il n'y a pas de deuxième mot, afficher "go where"
            System.out.println("Switch what? (on/off)");
            return;
        }
        
        // Deuxième mot : on ou off
        String secondWord = cmd.getSecondWord();
        
        if( secondWord.toLowerCase().equals("on") )
        	((Corridor)student.getCurrentRoom()).setLight(true);
        else if( secondWord.toLowerCase().equals("off") )
        	((Corridor)student.getCurrentRoom()).setLight(false);
        
        // Afficher la description de la salle
        System.out.println(student.getCurrentRoom().getLongDescription());
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
    	
        // Clear de la console
    	for (int i = 0; i < 50; ++i) System.out.println();
    	
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

			if (((nextRoom instanceof CourseRoom) && verifyCheckPlanning()) || !(nextRoom instanceof CourseRoom)) {
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
    }

    private void checkPlanning(Command command) {

        // Clear de la console
    	for (int i = 0; i < 50; ++i) System.out.println();
    	
    	// Test si la commande contient un deuxième mot
        if (!command.hasSecondWord()) {
            // Si il n'y a pas de deuxième mot, afficher "go where"
            System.out.println("Error : Check + direction");
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
        
        // Test si c'est une CourseRoom
        if (!(nextRoom.getClass().getSuperclass().getName().equals("zuul.room.CourseRoom"))) {
        	displayMap();
            System.out.println("Your room haven't a planning !");
            return;
        }
        
        else {
			System.out.println(((CourseRoom)nextRoom).displayPlanning());
			System.out.println("\n\n"+time.getTime());
        }
	}

	private boolean verifyCheckPlanning() {
		System.out.println("Have you checked the planning in front of the door? (yes/no)");
    	Command c = parser.getCommand();
		if(c.getCommandWord().equals("yes")) return true;
		else if( c.getCommandWord().equals("no")){
	        // Clear de la console
	    	for (int i = 0; i < 50; ++i) System.out.println();
			// Afficher la carte
			displayMap();

			// Afficher la description de la salle
			System.out.println(student.getCurrentRoom().getLongDescription() + "\n");
			System.out.println("Ok, so check it with 'check + direction'");
		}
		else System.out.println("Your command is invalid. Check the planning before entering");
		return false;
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
    
    public void waitTo(int hour){
    	int currentTime = this.time.getHour();
    	this.progressBar(hour);
    	this.time.setHour(currentTime+hour);
    }
    
    public void waitFor(int seconds){
    	this.progressBar(seconds);
    }
    
    // On suppose qu'on ne donne pas plus que 100 secondes à attendre
    private void progressBar(int hour){
    	int nb = 100/hour;
    	int nbParSec=1000/nb;
    	
    	System.out.print("||");
    	for(int i=0; i<100 ; i++){
    		try{
    			Thread.sleep(nbParSec);
    		}catch(Exception e){}
    		System.out.print("=");
    	}
    	System.out.print("||");
    }
    
	public static void main(String[] args) throws Exception {
		
		// Creation du jeu
		Game game = new Game();
		
		// Afficher la page de bienvenu
        game.printWelcome();
        
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
