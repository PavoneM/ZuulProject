package zuul;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observer;
import java.util.concurrent.ExecutionException;
import java.util.prefs.BackingStoreException;

import zuul.item.Cheat;
import zuul.item.Item;
import zuul.item.LabItem;
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
    
	public static HashMap<String,String> language;
    /**
     * Create the game and initialise its internal map.
     */
    public Game(HashMap<String,String> lang) { 
    	language = lang;    	
    	Config.initialize();
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
    	Classroom classroom = new Classroom(language.get("cl"), "Cl", generateStaticPlanning());
    	ExamRoom examroom = new ExamRoom(language.get("ex"), "Ex", generateStaticPlanning());
        Library library = new Library(language.get("li"), "Li", Config.oopLecture);
        Lab lab = new Lab(language.get("la"), "La", generateStaticPlanning());
        LunchRoom lunchroom = new LunchRoom(language.get("lu"), "Lu",.1f);
        
        time = new Time();
        
        time.addObserver(classroom);
        time.addObserver(examroom);
        time.addObserver(lab);
        time.addObserver(library);
        
        new Thread(time).start();
        
        Corridor corridor1 = new Corridor(language.get("co"), "Co", false, false);
        Corridor corridor2 = new Corridor(language.get("co"), "Co", true, false);
        Corridor corridor3 = new Corridor(language.get("co"), "Co", false, true);
        
        ///////
        /////// ICI OPTTIMISATION AVEC LE SET EXIT (Creer automatiquement la deuxième sortie)
        ///////
        
        // Installation des sorties pour chaque salle
        // Colonne 1
        corridor1.setExit(language.get("no"), classroom);
        corridor1.setExit(language.get("so"), library);
        corridor1.setExit(language.get("ea"), corridor2);
        classroom.setExit(language.get("we"), corridor1);
        library.setExit(language.get("no"), corridor1);

        // Colonne 2
        corridor2.setExit(language.get("no"), lab);
        corridor2.setExit(language.get("so"), lunchroom);
        corridor2.setExit(language.get("ea"), corridor3);
        corridor2.setExit(language.get("we"), corridor1);
        lab.setExit(language.get("so"), corridor2);
        lunchroom.setExit(language.get("no"), corridor2);
        
        // Colonne 3
        corridor3.setExit(language.get("no"), examroom);
        corridor3.setExit(language.get("we"), corridor2);        
        examroom.setExit(language.get("so"), corridor3);
        
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
    	
    	for(int i=0;i<5;i++){
    		table[0][i] = new Lecture(language.get("eng"), "ENG",null);
    		table[1][i] = new Lecture(language.get("oop"), "OOP", Config.oopLecture);
    		table[2][i] = new Lecture(language.get("mat"), "MAT", null);
    		table[3][i] = new Lecture(language.get("alg"), "ALG", null);
    		table[4][i] = new Lecture(language.get("ass"), "ASS", null);
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
    			else if(student.getCurrentRoom().existexit(language.get("no")) && i == (currentI-1) && j == currentJ && !map.get(i).get(j).isDiscovered())
    				System.out.print("[??]");
    			
    			// Si on peut aller au sud, on affiche [?]
    			else if(student.getCurrentRoom().existexit(language.get("so")) && i == (currentI+1) && j == currentJ && !map.get(i).get(j).isDiscovered())
    				System.out.print("[??]");
    			
    			// Si on peut aller au ouest, on affiche [?]
    			else if(student.getCurrentRoom().existexit(language.get("we")) && i == currentI && j == (currentJ-1) && !map.get(i).get(j).isDiscovered())
    				System.out.print("[??]");
    			
    			// Si on peut aller au est, on affiche [?]
    			else if(student.getCurrentRoom().existexit(language.get("ea")) && i == currentI && j == (currentJ+1) && !map.get(i).get(j).isDiscovered())
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
    	System.out.print("\n"+language.get("nrj"));
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
        System.out.println(language.get("wel"));
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
            System.out.println(language.get("dunno"));
            return false;
        }
        
        // Lire la prochaine commande
        String commandWord = command.getCommandWord();
        
        // Afficher l'aide
        if (commandWord.equals(language.get("help"))) {
            printHelp();
        }
        
        // Aller dans une direction
        else if (commandWord.equals(language.get("go"))) {
            wantToQuit = goRoom(command);
        }
        
        // Afficher la carte
        else if (commandWord.equals(language.get("map"))) {
            displayMap();
        } 
        
        // Afficher le backpack
        else if (commandWord.equals(language.get("backpack"))) {
            displayBackpack();
        } 
        
        // Afficher le temps
        else if (commandWord.equals(language.get("time"))) {
            System.out.println(time.getTime());
        }
        
        // Attendre
        else if (commandWord.equals(language.get("wait"))) {
            waiting(command);
        } 
        
        // Montrer l'énergie
        else if (commandWord.equals(language.get("energy"))) {
            energyBar();
        } 
        
        // Allumer les lumières
        else if (commandWord.equals(language.get("take"))) {
            if( student.getCurrentRoom() instanceof Corridor )
            	if( ((Corridor) student.getCurrentRoom()).isLight() ) take(command);
            	else System.out.println(language.get("cantlights"));
            else System.out.println(language.get("notcorrit"));
        }
        
        // Allumer les lumières
        else if (commandWord.equals(language.get("switch"))) {
            if( student.getCurrentRoom() instanceof Corridor ) switchLight(command);
            else System.out.println(language.get("notcorri"));
        }
        
        else if (commandWord.equals(language.get("check"))) {
        	checkPlanning(command);
        }
        
        else if (commandWord.equals(language.get("drink")) && student.getCurrentRoom() instanceof LunchRoom) {
        	if(student.getEnergy() < 20 ) drink();
        	else System.out.println(language.get("maxnrj"));
        }
        
        // Quitter le jeu
        else if (commandWord.equals(language.get("quit"))) {
            wantToQuit = quit(command);
        }
        else {
        	System.out.println(language.get("cantdothis"));
        }
        
        return wantToQuit;
    }
    
    private void take(Command command) {
    	
    	// Test si la commande contient un deuxième mot
        if (!command.hasSecondWord()) {
            // Si il n'y a pas de deuxième mot
            System.out.println(language.get("takewhat?"));
            return;
        }
		
        Corridor cor = (Corridor) student.getCurrentRoom();
        
        String secWord = command.getSecondWord();
        
        if ( !secWord.equals(language.get("cheat")) && !secWord.equals(language.get("tablet"))){
        	System.out.println(language.get("sheetortab"));
        	return;
        }
        
        if (secWord.equals(language.get("tablet")) && cor.getTablet()!= null){
        	System.out.print(language.get("tablettook"));
        	Item i = cor.getTablet().readTablet();
        	if(i == null){
        		System.out.println(language.get("tabletgame"));
        		student.removeRandomLecture();
        	}
        	else{
        		System.out.println(language.get("tabletread")+i.getName());
        		student.addBackpack(i);
        		System.out.println(language.get("nrjdecrease"));
        		student.decreaseEnergy(2);
        	}
        	cor.setTablet(null);
        }
        else if(secWord.equals(language.get("cheat")) && cor.getPhotocopier()){
        	Cheat c = cor.getCheat();
        	if(c==null){
        		System.out.println(language.get("nomorecheat"));
        		return; 
        	}
        	cor.setCheat(null);
        	System.out.println(language.get("youfind")+c+language.get("sheetbackpack"));
        	student.addBackpack(c);
        }
        else System.out.println(language.get("thereisno")+secWord +language.get("incorri"));
        return;
	}

	private void displayBackpack() {
		System.out.println(student.displayBackpack());
	}

	private void waiting(Command command) {
    	
    	// Test si la commande contient un deuxième mot
        if (!command.hasSecondWord()) {
            // Si il n'y a pas de deuxième mot
            System.out.println(language.get("waithowmuch"));
            return;
        }
        
        int theTime = 0;
        
        try {
        	theTime = Integer.parseInt(command.getSecondWord());
        } catch (Exception e) {
			System.out.println(language.get("waitpls"));
			return;
		}
		
        if( theTime <8 || theTime > 17){
        	System.out.println(language.get("timebetween"));
        	return;
        }
        	
        waitTo(theTime);
	}

	private void drink() {
		if(((LunchRoom) student.getCurrentRoom()).drink()){
			System.out.println(language.get("ggnrj"));
			student.increaseEnergy(2);
			energyBar();
		}
		else{
			System.out.println(language.get("babyfoot"));
			student.removeRandomLecture();
		}
		System.out.println(language.get("anotherdrink"));
			
	}

	private void switchLight(Command cmd) {
    	
    	// Test si la commande contient un deuxième mot
        if (!cmd.hasSecondWord()) {
            // Si il n'y a pas de deuxième mot, afficher "go where"
            System.out.println(language.get("switchwhat"));
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
        System.out.println(language.get("intro"));
        parser.showCommands();
    }

    /**
     * Try to in to one direction. If there is an exit, enter the new room,
     * otherwise print an error message.
     * @return 
     */
    private boolean goRoom(Command command) {
    	
        // Clear de la console
    	for (int i = 0; i < 50; ++i) System.out.println();
    	
    	// Test si la commande contient un deuxième mot
        if (!command.hasSecondWord()) {
            // Si il n'y a pas de deuxième mot, afficher "go where"
            System.out.println(language.get("where"));
            return false;
        }
        
        // Deuxième mot : choix de la direction
        String direction = command.getSecondWord();
        
        // Choix de la sortie de la salle
        Room nextRoom = student.getCurrentRoom().getExit(direction);
        
        // Si il y a pas de sortie, afficher "There is no door"
        if (nextRoom == null) {
        	displayMap();
            System.out.println(language.get("nodoor"));
        } 
        
        // Si la sortie existe
        else {
			if (nextRoom instanceof CourseRoom) {
				Lecture nextRoomC = ((CourseRoom) nextRoom).getCurrentCourse();
				boolean checked = verifyCheckPlanning();
				if (checked && nextRoomC.getAcronym().equals("OOP")){
					if(nextRoom instanceof ExamRoom){
						
						if(!student.checkBackpack()){
							System.out.println(language.get("examchecklesson"));
							return false;
						}
						if(student.getEnergy() < 18){
							System.out.println(language.get("examchecknrj"));
							return false;
						}
						System.out.println(language.get("exammessage"));
						Quizz q = new Quizz(Config.QA, parser, student.getBackpack());
						if(q.start()){
							System.out.println(language.get("bravo"));
							return true;
						}
						else{
							System.out.println(language.get("badgame"));
							student.setEnergy(2);
						}
					}
					else{
					
						// Afficher la carte
						displayMap();
	
						// Découvrir la nouvelle salle
						nextRoom.discover();
						
						Item currentLecture = nextRoomC.getCurrentLesson(student.getBackpack(), nextRoom); 
						
						if(currentLecture == null){
							System.out.println(language.get("allitems"));
							return false;
						}
						
						if(currentLecture instanceof LabItem && !((LabItem)currentLecture).haveLectItem(student.getBackpack())){
							System.out.println(language.get("haventlecture"));
							return false;
						}
						
						// Afficher la description de la salle
						System.out.println(nextRoom.getLongDescription());
						System.out.println(language.get("subjectof")+ currentLecture);
	            		waitFor(10);
	            		student.addBackpack(currentLecture);
	            		System.out.println(language.get("coursefinished"));
	            		student.decreaseEnergy(2);
					}
				}
				else if(checked && !nextRoomC.equals("OOP")){
					// Afficher la carte
					displayMap();
					
					System.out.println(language.get("notoop"));
					
					System.out.println(student.getCurrentRoom().getLongDescription());
				}
					 
			}
			else if (nextRoom instanceof Library){
				
				if( !((Library) nextRoom).isOpen() ){
					
					// Afficher la carte
					displayMap();
					
					// Afficher la description de la salle
					System.out.println(student.getCurrentRoom().getLongDescription());
					
					System.out.println(language.get("libraryclosed"));

					return false;
				}
				
				// Afficher la carte
				displayMap();

				// Découvrir la nouvelle salle
				nextRoom.discover();

				// Afficher la description de la salle
				System.out.println(nextRoom.getLongDescription());
				
				//TODO lire un livre
				Item i = ((Library) nextRoom).getABook();
				
				if( i == null ) {
					System.out.println(language.get("boringbook"));
				}
				else{
					System.out.println(language.get("goodbook")+i.toString());
					student.addBackpack(i);
				}
				
				waitFor(10);
				
				time.setHour(16);
				
				System.out.println(language.get("nrjdecrease"));
				student.decreaseEnergy(2);
			}
			else{
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
        return false;
    }

    private void checkPlanning(Command command) {

        // Clear de la console
    	for (int i = 0; i < 50; ++i) System.out.println();
    	
    	// Test si la commande contient un deuxième mot
        if (!command.hasSecondWord()) {
            // Si il n'y a pas de deuxième mot, afficher "go where"
            System.out.println(language.get("errorcheck"));
            return;
        }
        
        // Deuxième mot : choix de la direction
        String direction = command.getSecondWord();
        
        // Choix de la sortie de la salle
        Room nextRoom = student.getCurrentRoom().getExit(direction);
        
        // Si il y a pas de sortie, afficher "There is no door"
        if (nextRoom == null) {
        	displayMap();
            System.out.println(language.get("nodoor"));
            return;
        }
        
        // Test si c'est une CourseRoom
        if (!(nextRoom.getClass().getSuperclass().getName().equals("zuul.room.CourseRoom"))) {
        	displayMap();
            System.out.println(language.get("noplanning"));
            return;
        }
        
        else {
			System.out.println(((CourseRoom)nextRoom).displayPlanning());
			System.out.println("\n\n"+time.getTime());
        }
	}

	private boolean verifyCheckPlanning() {
		
		String c = null;
		
		System.out.println(language.get("check?"));
		
		while((c=parser.getCommand().getCommandWord()) == null){
			System.out.println(language.get("invalplanning"));
		}
    	
		if(c.equals(language.get("yes"))) return true;
		else if( c.equals(language.get("non"))){
	        // Clear de la console
	    	for (int i = 0; i < 50; ++i) System.out.println();
			// Afficher la carte
			displayMap();

			// Afficher la description de la salle
			System.out.println(student.getCurrentRoom().getLongDescription() + "\n");
			System.out.println(language.get("nocheck"));
		}
		else System.out.println(language.get("invalplanning"));
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
            System.out.println(language.get("quitwhat"));
            return false;
        } 
        
        // Sinon, quitter l'application
        else {
            return true;
        }
    }
    
    public void waitTo(int hour){
    	int currentTime = this.time.getHour();
    	if(hour-currentTime >= 0)
    		this.progressBar(hour-currentTime);
    	else{
    		this.progressBar(11+hour-currentTime);
    		time.increaseDay();
    	}
    	time.setHour(hour);
    }
    
    public void waitFor(int seconds){
    	this.progressBar(seconds);
    }
    
    // On suppose qu'on ne donne pas plus que 100 secondes à attendre
    private void progressBar(int hour){
    	
    	if(hour==0){
    		System.out.println(language.get("timerror"));
    		return;
    	}
    	
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
		Game game = new Game(Config.langFr);
		
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
        System.out.println(language.get("byebye"));

	}
}
