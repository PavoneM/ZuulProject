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
    
    // Attribut contentant le temps courrant
    private Time time;
    
    // Langage charché à partir de la configuration
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
    	
    	// Creation des salles de cours
    	Classroom classroom1 = new Classroom(language.get("cl"), "Cl", generateStaticPlanning());
    	Classroom classroom2 = new Classroom(language.get("cl"), "Cl", generateStaticPlanning());
    	Classroom classroom3 = new Classroom(language.get("cl"), "Cl", generateStaticPlanning());
    	Classroom classroom4 = new Classroom(language.get("cl"), "Cl", generateStaticPlanning());
    	Classroom classroom5 = new Classroom(language.get("cl"), "Cl", generateStaticPlanning());
    	
    	// Creation des salles d'exam
    	ExamRoom examroom1 = new ExamRoom(language.get("ex"), "Ex", generateStaticPlanning());
    	ExamRoom examroom2 = new ExamRoom(language.get("ex"), "Ex", generateStaticPlanning());
    	ExamRoom examroom3 = new ExamRoom(language.get("ex"), "Ex", generateStaticPlanning());
    	
    	// Création des deux librairies
        Library library1 = new Library(language.get("li"), "Li", Config.oopLecture);
        Library library2 = new Library(language.get("li"), "Li", Config.oopLecture);
        
        // Creation des Lab
        Lab lab1 = new Lab(language.get("la"), "La", generateStaticPlanning());
        Lab lab2 = new Lab(language.get("la"), "La", generateStaticPlanning());
        Lab lab3 = new Lab(language.get("la"), "La", generateStaticPlanning());
        Lab lab4 = new Lab(language.get("la"), "La", generateStaticPlanning());
        Lab lab5 = new Lab(language.get("la"), "La", generateStaticPlanning());
        
        // Creation de la cafetéria
        LunchRoom lunchroom = new LunchRoom(language.get("lu"), "Lu",.1f);
        
        time = new Time();
        
        // Ajout des observer à time
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
        
        // Lancement du thread time
        new Thread(time).start();
        
        // Couloir ligne 2
        Corridor corridor1 = new Corridor(language.get("co"), "Co", true, false);
        Corridor corridor2 = new Corridor(language.get("co"), "Co", false, false);
        
        // Couloir ligne 3
        Corridor corridor3 = new Corridor(language.get("co"), "Co", false, true);
        
        // Couloir ligne 4
        Corridor corridor4 = new Corridor(language.get("co"), "Co", false, true);
        
        // Couloir ligne 5
        Corridor corridor5 = new Corridor(language.get("co"), "Co", true, false);
        Corridor corridor6 = new Corridor(language.get("co"), "Co", false, false);
        Corridor corridor7 = new Corridor(language.get("co"), "Co", false, true);
        Corridor corridor8 = new Corridor(language.get("co"), "Co", false, false);
        
        // Couloir ligne 6
        Corridor corridor9 = new Corridor(language.get("co"), "Co", false, false);
        
        // Couloir ligne 7
		Corridor corridor10 = new Corridor(language.get("co"), "Co", false, false);
		
		// Couloir ligne 8
        Corridor corridor11 = new Corridor(language.get("co"), "Co", true, false);
        
       	// Ligne 1 & 2
         // Ajout des sorties
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
         ligne1.add(null);
         ligne1.add(examroom1);
         ligne1.add(examroom2);
         ligne1.add(null);
         ligne1.add(null);
         
         ArrayList ligne2 = new ArrayList();
         ligne2.add(null);
         ligne2.add(corridor1);
         ligne2.add(corridor2);
         ligne2.add(classroom1);
         ligne2.add(null);


         //ligne3
         corridor3.setExit(language.get("no"),corridor2);
         corridor3.setExit(language.get("ea"),classroom2);
         corridor3.setExit(language.get("we"),lab1);
         corridor3.setExit(language.get("so"),corridor4);
         classroom2.setExit(language.get("we"),corridor3);
         lab1.setExit("ea",corridor3);
         ArrayList ligne3 = new ArrayList();
         ligne3.add(null);
         ligne3.add(lab1);
         ligne3.add(corridor3);
         ligne3.add(classroom2);
         ligne3.add(null);


         //ligne 4
         corridor4.setExit(language.get("no"),corridor3);
         corridor4.setExit(language.get("we"),classroom3);
         corridor4.setExit(language.get("ea"),lab2);
         corridor4.setExit(language.get("so"),corridor6);
         classroom3.setExit(language.get("ea"),corridor4);
         lab2.setExit(language.get("we"),corridor4);
         library1.setExit(language.get("so"),corridor8);
         ArrayList ligne4 = new ArrayList();
         ligne4.add(null);
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
         ligne6.add(null);
         ligne6.add(classroom4);
         ligne6.add(corridor9);
         ligne6.add(lab3);
         ligne6.add(null);
         
         //ligne7
         corridor10.setExit(language.get("no"),corridor9);
         corridor10.setExit(language.get("we"),lab4);
         corridor10.setExit(language.get("ea"),classroom5);
         corridor10.setExit(language.get("so"),corridor11);
         classroom5.setExit(language.get("we"),corridor10);
         lab4.setExit(language.get("ea"),corridor10);
         ArrayList ligne7 = new ArrayList();
         ligne7.add(null);
         ligne7.add(lab4);
         ligne7.add(corridor10);
         ligne7.add(classroom5);
         ligne7.add(null);

         //ligne8
         corridor11.setExit(language.get("no"),corridor10);
         corridor11.setExit(language.get("we"),library2);
         corridor11.setExit(language.get("ea"),lab5);
         corridor11.setExit(language.get("so"),examroom3);
         lab5.setExit(language.get("we"),corridor11);
         library2.setExit(language.get("ea"),corridor11);
         ArrayList ligne8 = new ArrayList();
         ligne8.add(null);
         ligne8.add(library2);
         ligne8.add(corridor11);
         ligne8.add(lab5);
         ligne8.add(null);

         //ligne 9
         examroom3.setExit(language.get("no"),corridor11);
          ArrayList ligne9 = new ArrayList();
          ligne9.add(null);
          ligne9.add(null);
          ligne9.add(examroom3);
          ligne9.add(null);
          ligne9.add(null);

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
    
    /**
     * Génère un planning static pour chaque salle
     * @return Planning correspondant pour chaque salle
     */
    public Planning generateStaticPlanning(){
    	
    	// Crée une matrice de lectures
    	Lecture[][] table = new Lecture[5][5];
    	
    	// Chaine statique des lessons
    	String[] lectures = {"eng", "oop", "mat", "alg", "ass"};
    	
    	// Itération sur tous les cours
    	for(int i=0;i<5;i++)
    		// Sur toutes les leçons
    		for(int j=0 ; j<5 ; j++){
    			int random = (int) (Math.random()*4);
    			System.out.println(" Lecture " + language.get(lectures[random]) + " " + lectures[random]);
	    		if(random != 1) table[j][i] = new Lecture(language.get(lectures[random]), lectures[random].toUpperCase(),null);
	    		else table[j][i] = new Lecture(language.get(lectures[random]), lectures[random].toUpperCase(), Config.oopLecture);
    		}
    	
    	// Creation du planning à partir de la table
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
    			
    			// Verification que ce n'est pas null
    			if(map.get(i).get(j) == null ){
    				System.out.print("    ");
    				continue;
    			}
    			
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
    	
    	// Generation de l'énergie
    	System.out.print("\n"+language.get("nrj"));
    	System.out.print("|| ");
    	
    	// Creation de l'energy bar
    	for (int i = 1; i<= 20; i++){
    		if(i<=student.getEnergy()) System.out.print((char)248+" ");
    		else System.out.print("  ");
    	}
    	
    	// Fin de la barre d'énergie
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
        
        // Prendre un objet
        else if (commandWord.equals(language.get("take"))) {
        	
        	// On peut prendre que si il est dans un couloir
            if( student.getCurrentRoom() instanceof Corridor )
            	
            	// Si les lumières sont allumées
            	if( ((Corridor) student.getCurrentRoom()).isLight() ) take(command);
            
            	// Sinon on peut rien prendre
            	else System.out.println(language.get("cantlights"));
            
            // Sinon on est pas dans un couloir
            else System.out.println(language.get("notcorrit"));
        }
        
        // Allumer les lumières
        else if (commandWord.equals(language.get("switch"))) {
        	
        	// Si on est pas dans un couloir
            if( student.getCurrentRoom() instanceof Corridor ) switchLight(command);
            else System.out.println(language.get("notcorri"));
        }
        
        // Check les planning
        else if (commandWord.equals(language.get("check"))) {
        	checkPlanning(command);
        }
        
        // Boire du café
        else if (commandWord.equals(language.get("drink")) && student.getCurrentRoom() instanceof LunchRoom) {
        	if(student.getEnergy() < 20 ) drink();
        	else System.out.println(language.get("maxnrj"));
        }
        
        // Quitter le jeu
        else if (commandWord.equals(language.get("quit"))) {
            wantToQuit = quit(command);
        }
        
        // On peut pas faire l'action
        else {
        	System.out.println(language.get("cantdothis"));
        }
        
        return wantToQuit;
    }
    
    /**
     * Méthode pour prendre un objet en fonction d'une commande donnée
     * @param command Commande rentrée par l'utilisateur
     */
    private void take(Command command) {
    	
    	// Test si la commande contient un deuxième mot
        if (!command.hasSecondWord()) {
            // Si il n'y a pas de deuxième mot
            System.out.println(language.get("takewhat?"));
            return;
        }
		
        // Creation d'une copie du couloir
        Corridor cor = (Corridor) student.getCurrentRoom();
        
        // Récupération du deuxième mot
        String secWord = command.getSecondWord();
        
        // Si le deuxième mot n'est pas cheat ou tablet
        if ( !secWord.equals(language.get("cheat")) && !secWord.equals(language.get("tablet"))){
        	System.out.println(language.get("sheetortab"));
        	return;
        }
        
        // Si le deuxième mot est tablet et qu'il y a une tablette
        if (secWord.equals(language.get("tablet")) && cor.getTablet()!= null){
        	
        	// Prendre la tablette
        	System.out.print(language.get("tablettook"));
        	
        	// Item que nous renvois la tablette
        	Item i = cor.getTablet().readTablet();
        	
        	// Si il joue
        	if(i == null){
        		System.out.println(language.get("tabletgame"));
        		student.removeRandomLecture();
        	}
        	
        	// Si il lit un vrai cours
        	else{
        		// Lire la tablet
        		System.out.println(language.get("tabletread")+i.getName());
        		
        		// Ajouter l'item au backpack
        		student.addBackpack(i);
        		
        		// On augmente l'énergie
        		System.out.println(language.get("nrjdecrease"));
        		student.decreaseEnergy(2);
        	}
        	cor.setTablet(null);
        }
        
        // Si le deuxième mot est cheat et qu'il y a une antisèche
        else if(secWord.equals(language.get("cheat")) && cor.getPhotocopier()){
        	
        	// Récupération du cheat
        	Cheat c = cor.getCheat();
        	
        	// Si il y a plus de cheat dans la salle
        	if(c==null){
        		System.out.println(language.get("nomorecheat"));
        		return; 
        	}
        	
        	// On ajoute le cheat au couloir
        	cor.setCheat(null);
        	System.out.println(language.get("youfind")+c+language.get("sheetbackpack"));
        	student.addBackpack(c);
        }
        else System.out.println(language.get("thereisno")+secWord +language.get("incorri"));
        return;
	}
    
    /**
     * Methode pour afficher le sac à dos
     */
	private void displayBackpack() {
		System.out.println(student.displayBackpack());
	}
	
	/**
	 * Méthode pour attendre jusqu'à une certaine heure
	 * @param command Commande lue
	 */
	private void waiting(Command command) {
    	
    	// Test si la commande contient un deuxième mot
        if (!command.hasSecondWord()) {
            // Si il n'y a pas de deuxième mot
            System.out.println(language.get("waithowmuch"));
            return;
        }
        
        // Creation du temps
        int theTime = 0;
        
        // Parsing de l'heure
        try {
        	theTime = Integer.parseInt(command.getSecondWord());
        } catch (Exception e) {
			System.out.println(language.get("waitpls"));
			return;
		}
		
        // L'heure doit être comprise entre 8h et 17h
        if( theTime <8 || theTime > 17){
        	System.out.println(language.get("timebetween"));
        	return;
        }
        	
        waitTo(theTime);
	}
	
	/**
	 * Boire une tasse de café à la cafetéria
	 */
	private void drink() {
		
		// Si drink nous renvois true on bois
		if(((LunchRoom) student.getCurrentRoom()).drink()){
			System.out.println(language.get("ggnrj"));
			student.increaseEnergy(2);
			energyBar();
		}
		// Sinon on joue au babyfoot
		else{
			System.out.println(language.get("babyfoot"));
			student.removeRandomLecture();
		}
		System.out.println(language.get("anotherdrink"));
			
	}
	
	/**
	 * Methode pour allumer les lumières dans un couloir
	 * @param cmd
	 */
	private void switchLight(Command cmd) {
    	
    	// Test si la commande contient un deuxième mot
        if (!cmd.hasSecondWord()) {
            // Si il n'y a pas de deuxième mot, afficher "go where"
            System.out.println(language.get("switchwhat"));
            return;
        }
        
        // Deuxième mot : on ou off
        String secondWord = cmd.getSecondWord();
        
        // Si c'est on
        if( secondWord.toLowerCase().equals("on") )
        	((Corridor)student.getCurrentRoom()).setLight(true);
        
        // Si c'est off
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
        	
        	// Si la prochaine salle est une salle de cours
			if (nextRoom instanceof CourseRoom) {
				
				// On caste la prochaine salle
				Lecture nextRoomC = ((CourseRoom) nextRoom).getCurrentCourse();
				
				// Vérification du planning
				boolean checked = verifyCheckPlanning();
				
				// Si il a check le planning et c'est un cours de OOP
				if (checked && nextRoomC.getAcronym().equals("OOP")){
					
					// Si la prochaine salale est une salle d'examen
					if(nextRoom instanceof ExamRoom){
						
						// On vérifie qu'il a fait tous les cours / TD
						if(!student.checkBackpack()){
							System.out.println(language.get("examchecklesson"));
							return false;
						}
						
						// On vérifie qu'il a au moins 18 d'énergie
						if(student.getEnergy() < 18){
							System.out.println(language.get("examchecknrj"));
							return false;
						}
						
						// Salle d'exam
						System.out.println(language.get("exammessage"));
						
						// On crée le quizz
						Quizz q = new Quizz(Config.QA, parser, student.getBackpack());
						
						// On lance le quizz et on vérifie qu'il a reussit
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
						
						// On charge le cours dans la prochaine salle
						Item currentLecture = nextRoomC.getCurrentLesson(student.getBackpack(), nextRoom); 
						
						// Si il a déjà fait tous les cours
						if(currentLecture == null){
							System.out.println(language.get("allitems"));
							return false;
						}
						
						// Si c'est un lab et il n'a pas fait le cours
						if(currentLecture instanceof LabItem && !((LabItem)currentLecture).haveLectItem(student.getBackpack())){
							System.out.println(language.get("haventlecture"));
							return false;
						}
						
						// Afficher la description de la salle
						System.out.println(nextRoom.getLongDescription());
						System.out.println(language.get("subjectof")+ currentLecture);
						
						// Attendre 10 secondes
	            		waitFor(10);
	            		
	            		// Ajouter l'item au backpack
	            		student.addBackpack(currentLecture);
	            		System.out.println(language.get("coursefinished"));
	            		student.decreaseEnergy(2);
					}
				}
				// Si il a vérifié l'emplois du temps mais c'est pas un OOP
				else if(checked && !nextRoomC.equals("OOP")){
					// Afficher la carte
					displayMap();
					
					System.out.println(language.get("notoop"));
					
					System.out.println(student.getCurrentRoom().getLongDescription());
				}
					 
			}
			
			// Si c'est une librairie
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
				
				Item i = ((Library) nextRoom).getABook();
				
				// Si on lit pas un livre de OOP
				if( i == null ) {
					System.out.println(language.get("boringbook"));
				}
				else{
					System.out.println(language.get("goodbook")+i.toString());
					student.addBackpack(i);
				}
				
				// Attendre 10 secondes
				waitFor(10);
				
				// Changer l'heure
				time.setHour(16);
				
				// Diminuer l'énergie
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
    
    /**
     * Vérifier le planning de la salle et l'afficher
     * @param command Commande rentrée par l'uttilisateur
     */
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
    
    /**
     * Vérification du planning
     * @return Vérité si il a check le planning
     */
	private boolean verifyCheckPlanning() {
		
		// Création d'une chaine de caractère
		String c = null;
		
		// Affichage question check
		System.out.println(language.get("check?"));
		
		// Si c'est une vrai commande
		while((c=parser.getCommand().getCommandWord()) == null){
			System.out.println(language.get("invalplanning"));
		}
    	
		// Si c'est un yes
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
    
    /**
     * Attendre jusqu'à une certaine heure
     * @param hour Heure
     */
    public void waitTo(int hour){
    	
    	// Prendre le temps courrant
    	int currentTime = this.time.getHour();
    	
    	// Si la différence est supérieure à 0
    	if(hour-currentTime >= 0)
    		this.progressBar(hour-currentTime);
    	else{
    		this.progressBar(11+hour-currentTime);
    		time.increaseDay();
    	}
    	time.setHour(hour);
    }
    
    /**
     * Attendre n heures de temps
     * @param seconds secondes
     */
    public void waitFor(int seconds){
    	this.progressBar(seconds);
    }
    
    // On suppose qu'on ne donne pas plus que 100 secondes à attendre
    private void progressBar(int hour){
    	
    	// Si il faut attendre 0 heures erreur
    	if(hour==0){
    		System.out.println(language.get("timerror"));
    		return;
    	}
    	
    	// Sinon on calcule le nombre de "=" à afficher
    	int nb = 100/hour;
    	int nbParSec=1000/nb;
    	
    	// Creation de la progress bar
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
		Game game = new Game(Config.langEn);
		
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
