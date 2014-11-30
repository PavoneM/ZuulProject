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
     * create the static map of the game
     */
    public void generateStaticMap(){
    	
    	// Creation des salles
    	Classroom classroom1 = new Classroom(language.get("cl"), "Cl", generateStaticPlanning());
    	Classroom classroom2 = new Classroom(language.get("cl"), "Cl", generateStaticPlanning());
    	Classroom classroom3 = new Classroom(language.get("cl"), "Cl", generateStaticPlanning());
    	Classroom classroom4 = new Classroom(language.get("cl"), "Cl", generateStaticPlanning());
    	Classroom classroom5 = new Classroom(language.get("cl"), "Cl", generateStaticPlanning());
    	ExamRoom examroom1 = new ExamRoom(language.get("ex"), "Ex", generateStaticPlanning());
    	ExamRoom examroom2 = new ExamRoom(language.get("ex"), "Ex", generateStaticPlanning());
    	ExamRoom examroom3 = new ExamRoom(language.get("ex"), "Ex", generateStaticPlanning());
        Library library1 = new Library(language.get("li"), "Li", Config.oopLecture);
        Library library2 = new Library(language.get("li"), "Li", Config.oopLecture);
        Lab lab1 = new Lab(language.get("la"), "La", generateStaticPlanning());
        Lab lab2 = new Lab(language.get("la"), "La", generateStaticPlanning());
        Lab lab3 = new Lab(language.get("la"), "La", generateStaticPlanning());
        Lab lab4 = new Lab(language.get("la"), "La", generateStaticPlanning());
        Lab lab5 = new Lab(language.get("la"), "La", generateStaticPlanning());
        LunchRoom lunchroom = new LunchRoom(language.get("lu"), "Lu",.1f);
        
        time = new Time();
        
        time.addObserver(classroom1);
        time.addObserver(classroom2);
        time.addObserver(classroom3);
        time.addObserver(classroom4);
        time.addObserver(classroom5);
        time.addObserver(examroom1);
        time.addObserver(examroom2);
        time.addObserver(examroom3);
        time.addObserver(lab1);
        time.addObserver(lab2);
        time.addObserver(lab3);
        time.addObserver(lab4);
        time.addObserver(lab5);
        time.addObserver(library1);
        time.addObserver(library2);
        
        new Thread(time).start();
        //couloir ligne 2
        Corridor corridor1 = new Corridor(language.get("co"), "Co", true, false);
        Corridor corridor2 = new Corridor(language.get("co"), "Co", false, false);
        //couloir ligne 3
        Corridor corridor3 = new Corridor(language.get("co"), "Co", false, true);
        //couloir ligne 4
        Corridor corridor4 = new Corridor(language.get("co"), "Co", false, true);
        //couloir ligne 5
        Corridor corridor5 = new Corridor(language.get("co"), "Co", true, false);
        Corridor corridor6 = new Corridor(language.get("co"), "Co", false, false);
        Corridor corridor7 = new Corridor(language.get("co"), "Co", false, true);
        Corridor corridor8 = new Corridor(language.get("co"), "Co", false, false);
        //couloir ligne 6
        Corridor corridor9 = new Corridor(language.get("co"), "Co", false, false);
        //couloir ligne 7
		Corridor corridor10 = new Corridor(language.get("co"), "Co", false, false);
		// couloir ligne 8
        Corridor corridor11 = new Corridor(language.get("co"), "Co", true, false);
        
       	//ligne 1 & 2
         examroom1.setExit(language.get("so"),corridor1);
         examroom2.setExit(language.get("so"),corridor2);
         corridor1.setExit(language.get("no"),examroom1);
         corridor1.setExit(language.get("ea"),corridor2);
         corridor2.setExit(language.get("we"),corridor1);
         corridor2.setExit(language.get("no"),examroom2);
         corridor2.setExit(language.get("ea"),classroom1);
         classroom1.setExit(language.get("we"),corridor2);
         corridor2.setExit(language.get("so"),corridor3);
         ArrayList ligne1 = new ArrayList();
         ligne1.add(examroom1);
         ligne1.add(examroom2);
         ArrayList ligne2 = new ArrayList();
         ligne2.add(corridor1);
         ligne2.add(corridor2);
         ligne2.add(classroom1);


         //ligne3
         corridor3.setExit(language.get("no"),corridor2);
         corridor3.setExit(language.get("ea"),classroom2);
         corridor3.setExit(language.get("we"),lab1);
         corridor3.setExit(language.get("so"),corridor4);
         classroom2.setExit(language.get("we"),corridor3);
         lab1.setExit("ea",corridor3);
         ArrayList ligne3 = new ArrayList();
         ligne3.add(lab1);
         ligne3.add(corridor3);
         ligne3.add(classroom2);


         //ligne 4
         corridor4.setExit(language.get("no"),corridor3);
         corridor4.setExit(language.get("we"),classroom3);
         corridor4.setExit(language.get("ea"),lab2);
         corridor4.setExit(language.get("so"),corridor6);
         classroom3.setExit(language.get("ea"),corridor4);
         lab2.setExit(language.get("we"),corridor4);
         library1.setExit(language.get("so"),corridor8);
         ArrayList ligne4 = new ArrayList();
         ligne4.add(classroom3);
         ligne4.add(corridor4);
         ligne4.add(lab2);
         ligne4.add(library1);


         //ligne5
         lunchroom.setExit(language.get("ea"),corridor5);
         corridor5.setExit(language.get("we"),lunchroom);
         corridor5.setExit(language.get("ea"),corridor6);
         corridor6.setExit(language.get("we"),corridor5);
         corridor6.setExit(language.get("no"),corridor4);
         corridor6.setExit(language.get("so"),corridor9);
         corridor6.setExit(language.get("ea"),corridor7);
         corridor7.setExit(language.get("we"),corridor6);
         corridor7.setExit(language.get("ea"),corridor8);
         corridor8.setExit(language.get("no"),library1);
         corridor8.setExit(language.get("we"),corridor7);
         ArrayList ligne5 = new ArrayList();
         ligne5.add(lunchroom);
         ligne5.add(corridor5);
         ligne5.add(corridor6);
         ligne5.add(corridor7);
         ligne5.add(corridor8);

         //ligne 6 
         corridor9.setExit(language.get("no"),corridor6);
         corridor9.setExit(language.get("we"),classroom4);
         corridor9.setExit(language.get("ea"),lab3);
         corridor9.setExit(language.get("so"),corridor10);
         classroom4.setExit(language.get("ea"),corridor9);
         lab3.setExit(language.get("we"),corridor9);
         ArrayList ligne6 = new ArrayList();
         ligne6.add(classroom4);
         ligne6.add(corridor9);
         ligne6.add(lab3);
         //ligne7
         corridor10.setExit(language.get("no"),corridor9);
         corridor10.setExit(language.get("we"),lab4);
         corridor10.setExit(language.get("ea"),classroom5);
         corridor10.setExit(language.get("so"),corridor11);
         classroom5.setExit(language.get("we"),corridor10);
         lab4.setExit(language.get("ea"),corridor10);
         ArrayList ligne7 = new ArrayList();
         ligne7.add(lab4);
         ligne7.add(corridor10);
         ligne7.add(classroom5);

         //ligne8
         corridor11.setExit(language.get("no"),corridor10);
         corridor11.setExit(language.get("we"),library2);
         corridor11.setExit(language.get("ea"),lab5);
         corridor11.setExit(language.get("so"),examroom3);
         lab5.setExit(language.get("we"),corridor11);
         library2.setExit(language.get("ea"),corridor11);
         ArrayList ligne8 = new ArrayList();
         ligne8.add(library2);
         ligne8.add(corridor11);
         ligne8.add(lab5);

         //ligne 9
         examroom3.setExit(language.get("no"),corridor11);
          ArrayList ligne9 = new ArrayList();
          ligne9.add(examroom3);

        // ajout des lignes à la grille
          map.add(ligne1);
          map.add(ligne2);
          map.add(ligne3);
          map.add(ligne4);
          map.add(ligne5);
          map.add(ligne6);
          map.add(ligne7);
          map.add(ligne8);
          map.add(ligne9);

        student.setCurrentRoom(corridor8);
        
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
