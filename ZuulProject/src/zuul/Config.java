package zuul;

import java.util.ArrayList;
import java.util.HashMap;

import zuul.item.Item;
import zuul.item.LabItem;
import zuul.item.LectItem;

public class Config {
	
	//hashmap contenant le texte du jeu en fonction de la langue selectionnée
	
	public static HashMap<String,String> langEn = new HashMap<String,String>();
	
	public static HashMap<String,String> langFr = new HashMap<String,String>();
	
	public static ArrayList<Item> oopLecture;
	
	public static HashMap<String, ArrayList<String>> QA;
	
	public static void initialize(){
		langEn.put("wel", "Welcome on the Polytech'Groland Building! \n Type help if you need help");
		langEn.put("cl", "in a classroom");
		langEn.put("ex", "in an exam room");
		langEn.put("li", "in the library");
		langEn.put("la", "in a computing lab");
		langEn.put("lu", "in the lunchroom");
		langEn.put("co", "in a corridor");
		langEn.put("no", "north");
		langEn.put("so", "south");
		langEn.put("ea", "east");
		langEn.put("we", "west");
		langEn.put("eng", "English");
		langEn.put("oop", "Object Oriented Programming");
		langEn.put("mat", "Maths");
		langEn.put("alg", "Algo Num");
		langEn.put("ass", "Asssembly");
		langEn.put("nrj", "Your energy : ");
		langEn.put("dunno", "I don't know what you mean");
		langEn.put("notcorri", "You are not in a corridor. There is no light.");
		langEn.put("switchwhat", "Switch wThat ? (on/off)");
		langEn.put("intro", "You are lost. You are alone. \n You wander around the university. \n Your command words are :");
		langEn.put("where", "Go where ?");
		langEn.put("nodoor", "There is no door !");
		langEn.put("errorcheck", "Error : Check + direction.");
		langEn.put("noplanning", "Error : your room doesn't have a planning");
		langEn.put("check?", "Did you check the planning on the door ? (yes/no)");
		langEn.put("nocheck", "Ok, so check it with 'check + direction'");
		langEn.put("invalplanning", "Your command is invalid. Check the planning before entering the room");
		langEn.put("quitwhat", "Quit what ?");
		langEn.put("byebye", "Thanks for playing. Good bye !");
		langEn.put("today", "Today, we are ");
		langEn.put("monday", "Monday");
		langEn.put("tuesday", "Tuesday");
		langEn.put("wednesday", "Wednesday");
		langEn.put("thursday", "Thursday");
		langEn.put("friday", "Friday");
		langEn.put("hour", "hour");
		langEn.put("second", "second");
		langEn.put("exits", "Exits");
		langEn.put("lecture", "You are in a lecture of :");
		langEn.put("cantlights","You can't take anything until the lights are on !");
		langEn.put("notcorrit","You are not in a corridor. There is nothing to take");
		langEn.put("maxnrj","You energy is already at its maximum !");
		langEn.put("cantdothis","You can't do this now.");
		langEn.put("takewhat", "Take what ?");
		langEn.put("sheetortab","You can only take a sheet or a tablet, sorry");
		langEn.put("tablettook", "You take the tablet and...");
		langEn.put("tabletgame","you start to play a game ! You forgot a random lesson");
		langEn.put("tabletread", "you read something about ");
		langEn.put("nrjdecrease", " your energy decrease by 2 points");
		langEn.put("nomorecheat", "There is no more cheat here");
		langEn.put("youfind", "You find a ");
		langEn.put("sheetbackpack", "\nThe answer sheet is now in your backpack ! ");
		langEn.put("thereisno", "There is no ");
		langEn.put("incorri", " in this corridor");
		langEn.put("waithowmuch", "Wait how much time ?");
		langEn.put("waitpls", "Wait + integer please !");
		langEn.put("timebetween", "The time must be between 8 and 17 !");
		langEn.put("ggnrj", "Good job, you increase your energy by 2 !");
		langEn.put("babyfoot", "You start to play babyfoot. You forget one random lesson.");
		langEn.put("anotherdrink", "If you want to play again type 'drink'");
		langEn.put("examchecklesson", "You must have listened all lectures and do all lab session to take the exam");
		langEn.put("examchecknrj", "You must have more than 18 energy to pass the exam");
		langEn.put("exammessage", "Welcome to the exam room.\nAfter suffering from listening to all OOP courses, here is the final boss of this game.\nPrepare yourself for battle !");
		langEn.put("bravo", "Congratulations ! You passed the exam and you can now begin developping as a professionnal. We hope you will earn millions of dollars ! Bye bye. ");
		langEn.put("badgame", "You completely failed your exam. You suck. Maybe another time ! Now return tu work.");
		langEn.put("allitems", "You already have all the items of this course");
		langEn.put("subjectof", "The subject of the lesson is");
		langEn.put("coursefinished", "\nYour energy has been decreased by 2\nYou can now go out thanks !");
		langEn.put("notoop", "Sorry it's not an OOP course you are now in your previous room");
		langEn.put("libraryclosed", "Library is currently closed. Try to come between 12h and 16h");
		langEn.put("boringbook", "\n ==> You find a boring book but you continue to reading for fun");
		langEn.put("goodbook", "\n ==> You find a boring book but you continue to reading for fun");
		langEn.put("timeerror", "Invalid time: Don't type the current hour !");
		langEn.put("go", "go");
		langEn.put("quit", "quit");
		langEn.put("help", "help");
		langEn.put("switch", "switch");
		langEn.put("yes", "yes");
		langEn.put("non", "no");
		langEn.put("time", "time");
		langEn.put("check", "check");
		langEn.put("drink", "drink");
		langEn.put("map", "map");
		langEn.put("energy", "energy");
		langEn.put("wait","wait");
		langEn.put("backpack", "backpack");
		langEn.put("take", "take");
		langEn.put("true", "true");
		langEn.put("false", "false");
		langEn.put("answermustbe", "Your answer must be true or false !");
		langEn.put("yourmark", "Your mark is ");
		langEn.put("lostitem", "You lost the item : ");
		langEn.put("0item", "You have 0 item in your backpack");
		langEn.put("about", " about ");
		langEn.put("tablet", "Tablet");
		langEn.put("answersheet", "Answer sheet ");
		langEn.put("photocopier", "\nOh wait ! there is a photocopier in this corridor. Type 'take cheat' to take the answer sheet");
		langEn.put("corritablet", "\nThere is a tablet in this room !! Type 'take tablet' to take it");
		langEn.put("youare", "You are ");
		langEn.put("lightsare", " The lights are ");
		langEn.put("youareinlecture", "You are in a lecture of ");
		langEn.put("toobadoop", "Too bad ! you are in OOP course so you have to wait until it ends ! Sorry :(");
		langEn.put("welcomelibrary", "Welcome to the Library ! Here you can read books, and you can learn new lessons!\n");
		langEn.put("welcomelunchroom","Welcome to the lunchroom. You can drink coffee or play the babyfoot. \nIf you play the babyfoot, you'll forget a random lesson. \nType 'drink' to play ");
		langEn.put("book", "Book");
		langEn.put("cheat", "cheat");
		langFr.put("retcheat", "Your answer sheet say that the anwser is");
		langEn.put("haventlecture", "Before coming to lab session you have to go on the lecture");
		langEn.put("", "");
		langEn.put("", "");
		langEn.put("", "");
		langEn.put("", "");
		langEn.put("", "");
		langEn.put("", "");
		langEn.put("", "");
		langEn.put("", "");
		langEn.put("", "");
		
		
		langFr.put("wel", "Bienvenue à Polytech'Groland ! \n Tapez 'aide' si vous avez besoin d'aide");
		langFr.put("cl", "dans une salle de cours");
		langFr.put("ex", "dans une salle d'examens");
		langFr.put("li", "dans la bibliothèque");
		langFr.put("la", "dans une salle d'informatique");
		langFr.put("lu", "dans la salle de repos");
		langFr.put("co", "dans un couloir");
		langFr.put("no", "nord");
		langFr.put("so", "sud");
		langFr.put("ea", "est");
		langFr.put("we", "ouest");
		langFr.put("eng", "Anglais");
		langFr.put("oop", "Programmation orientée objet");
		langFr.put("mat", "Maths");
		langFr.put("alg", "Algo Num");
		langFr.put("ass", "Asssembleur");
		langFr.put("nrj", "Votre energie");
		langFr.put("dunno", "Commande non reconnue");
		langFr.put("notcorri", "Vous n'êtes pas dans un couloir. Il n'y a pas de lumières");
		langFr.put("switchwhat", "Appuyer sur quoi ? (on/off)");
		langFr.put("intro", "Vous êtes perdu. Vous êtes seul. \n Vous vagabondez dans l'université. \n Vos mots de commande sont :");
		langFr.put("where", "Aller où ?");
		langFr.put("nodoor", "Il n'y a pas de porte !");
		langFr.put("errorcheck", "Erreur : Check + direction.");
		langFr.put("noplanning", "Erreur : cette salle n'a pas de planning");
		langFr.put("check?", "Avez vous regardé le planning sur la porte ? (oui/non)");
		langFr.put("nocheck", "Ok, vérifiez le avec 'check + direction'");
		langFr.put("invalplanning", "Votre commande n'est pas valide, regardez le planning avant d'entrer dans la salle");
		langFr.put("quitwhat", "Que voulez vous quitter ?");
		langFr.put("byebye", "Merci d'avoir joué. Au revoir !");
		langFr.put("today", "Aujourd'hui, nous sommes ");
		langFr.put("monday", "Lundi");
		langFr.put("tuesday", "Mardi");
		langFr.put("wednesday", "Mercredi");
		langFr.put("thursday", "Jeudi");
		langFr.put("friday", "Vendredi");
		langFr.put("hour", "heures");
		langFr.put("second", "secondes");
		langFr.put("exits", "Sorties ");
		langFr.put("lecture", "Vous êtes dans un cours de : ");
		langFr.put("cantlights","Allumez les lumières avant d'essayer de prendre quelque chose !");
		langFr.put("notcorrit","Vous n'êtes pas dans un couloir, il n'y a rien à prendre.");
		langFr.put("maxnrj","Votre energie est déja au maximum !");
		langFr.put("cantdothis","Vous ne pouvez pas faire ça maintenant.");
		langFr.put("takewhat", "Prendre quoi ?");
		langFr.put("sheetortab","Vous ne pouvez vous saisir que d'une feuille ou d'une tablette.");
		langFr.put("tablettook", "Vous prenez la tablette et...");
		langFr.put("tabletgame","vous commencez à jouer à un jeu ! Vous oubliez une leçon aléatoire !");
		langFr.put("tabletread", "vous lisez quelque chose sûr ");
		langFr.put("energy decrease", " votre energie baisse de 2 points");
		langFr.put("nomorecheat", "Il n'y a plus de pompes ici ! ");
		langFr.put("youfind", "Vous trouvez une ");
		langFr.put("sheetbackpack", "\nLa feuille de pompes est maintenant dans votre sac à dos ! ");
		langFr.put("thereisno", "Il n'y a pas ");
		langFr.put("incorri", " dans ce couloir");
		langFr.put("waithowmuch", "Attendre combien de temps ?");
		langFr.put("waitpls", "Attendre + nombre entier svp !");
		langFr.put("timebetween", "L'heure doit être comprise entre 8h et 17h !");
		langFr.put("ggnrj", "Bien joué, votre énergie augmente de 2 ! ");
		langFr.put("babyfoot", "Vous vommencez à jouer au babyfoot. Vous oubliez une leçon aléatoire !");
		langFr.put("anotherdrink", "Si vous voulez essayer encore, tapez 'boire' ");
		langFr.put("examchecklesson", "Vous devez avoir assisté à tous les cours et td pour rentrer en exam ! ");
		langFr.put("examchecknrj", "Vous devez avoir au moins 18 énergie pour passer l'exam.");
		langFr.put("exammessage", "Bienvenue dans la salle d'exam.\nAprès avoir tant souffert d'écouter les cours de POO, voici le dernier boss de ce jeu.\nPréparez vous pour le combat final !");
		langFr.put("bravo", "Bravo ! Vous avez réussi l'examen et vous vous pouvez maintenant commencer à développer comme un pro ! Nous espérons que votre portefeuille se remplisse de millions d'euros ! Au revoir ! ");
		langFr.put("badgame", "Vous avez échoué l'examen. Vous êtes mauvais. Retournez travailler.");
		langFr.put("allitems", "Vous avez déja tous les items de ce cours.");
		langFr.put("subjectof", "Le sujet du cours est :");
		langFr.put("coursefinished", "\nVotre énergie a baissé de 2.\nVous  pouvez sortir !");
		langFr.put("notoop", "Désolé, ce n'est pas un cours de POO, vous êtes maintenant dans la salle précédente.");
		langFr.put("libraryclosed", "La bibliothèque est fermée. Essayer de venir entre 12h et 16h");
		langFr.put("boringbook", "\n ==> Vous trouvez un livre sans rapport avec la POO.");
		langFr.put("goodbook", "\n ==> You find a boring book but you continue to reading for fun");
		langFr.put("timeerror", "Erreur ! Ne tapez pas l'heure actuelle ! ");
		langFr.put("go", "go");
		langFr.put("quit", "quitter");
		langFr.put("help", "aide");
		langFr.put("switch", "appuyer");
		langFr.put("yes", "oui");
		langFr.put("non", "non");
		langFr.put("time", "horloge");
		langFr.put("check", "check");
		langFr.put("drink", "boire");
		langFr.put("map", "carte");
		langFr.put("energy", "energie");
		langFr.put("wait","attendre");
		langFr.put("backpack", "sac");
		langFr.put("take", "prendre");
		langFr.put("true", "vrai");
		langFr.put("false", "faux");
		langFr.put("answermustbe", "Votre réponse doit être vrai ou faux !");
		langFr.put("yourmark", "Vous obtenez une note de : ");
		langFr.put("lostitem", "YVous avez perdu l'item : ");
		langFr.put("0item", "Vous n'avez aucun item dans le sac à dos");
		langFr.put("about", " sur ");
		langFr.put("tablet", "Tablette");
		langFr.put("answersheet", "Feuille de réponse ");
		langFr.put("photocopier", "\nAttendez ! il y a un photocopieur dans ce couloir ! Ecrivez 'prendre feuille' si vous voulez prendre la feuille de réponses. ");
		langFr.put("corritablet", "\nIl y a une tablette ici ! Ecrivez 'prendre tablette' pour la prendre.");
		langFr.put("youare", "Vous êtes  ");
		langFr.put("lightsare", " Les lumières sont ");
		langFr.put("youareinlecture", "Vous êtes dans un cours de ");
		langFr.put("toobadoop", "Vous êtes dans un cours d'OOP et devez attendre qu'il se termine. Dommage.");
		langFr.put("welcomelibrary", "Bienvenue dans la bibliothèque ! Ici, vous pouvez lire et découvrir de nouvelles leçons !\n");
		langFr.put("welcomelunchroom","Bienvenue dans le foyer. Vous pouvez boire du café ou jouer au babyfoot. \nSi vous jouez au babyfoot, vous oublierez une leçon aléatoire. \nEcrivez 'boire' pour jouer ");
		langFr.put("book", "Livre");
		langFr.put("cheat", "feuille");
		langFr.put("retcheat", "Votre antisèche vous dire que la reponse est");
		langEn.put("haventlecture", "Avant de venir en TD il faut faire il cours !");
		
		
		
    	oopLecture = new ArrayList<Item>();
    	oopLecture.add(new LectItem("Introducing Objects"));
    	oopLecture.add(new LectItem("Object Oriented Programming"));
    	oopLecture.add(new LectItem("Improving structures with inherithence"));
    	oopLecture.add(new LectItem("Abstraction techniques"));
    	oopLecture.add(new LabItem("Introducing Objects"));
    	oopLecture.add(new LabItem("Object Oriented Programming"));
    	oopLecture.add(new LabItem("Improving structures with inherithence"));
    	oopLecture.add(new LabItem("Abstraction techniques"));
    	
    	
    	ArrayList<String> questionsIntroducingObject = new ArrayList<String>();
    	ArrayList<String> answersIntroducingObject = new ArrayList<String>();
    	ArrayList<String> questionsInheritance= new ArrayList<String>();
    	ArrayList<String> answersInheritance= new ArrayList<String>();
    	ArrayList<String> questionsAbstraction= new ArrayList<String>();
    	ArrayList<String> answersAbstraction= new ArrayList<String>();
    	ArrayList<String> questionsFurther= new ArrayList<String>();
    	ArrayList<String> answersFurther= new ArrayList<String>();
    	
    	QA = new HashMap<String, ArrayList<String>>();
    	
    	questionsIntroducingObject.add("The method toLowerCase( ) changes all lowercase letters to uppercase and all uppercase letters to lowercase.");
    	questionsIntroducingObject.add("The following statement is valid  double price = 7450.98;");
    	questionsIntroducingObject.add("Mathematicians and computers doesn't interpret the equal sign (=) in the same way.");
    	questionsIntroducingObject.add("All variables must be declared before they can be used.");
    	answersIntroducingObject.add("false");
    	answersIntroducingObject.add("true");
      	answersIntroducingObject.add("true");
    	answersIntroducingObject.add("false");
    	
    	QA.put("question1", questionsIntroducingObject);
    	QA.put("answer1", answersIntroducingObject);
    	
    	questionsInheritance.add("To determine whether a reference variable that points to an object is of a particular class type, Java provides the operator instanceof.");
    	questionsInheritance.add("The private members of a superclass can be accessed by a subclass.");
    	questionsInheritance.add("In single inheritance, the subclass is derived from a single superclass.");
    	questionsInheritance.add("Inheritance implies an “is-a” relationship.");
    	answersInheritance.add("true");
    	answersInheritance.add("false");
    	answersInheritance.add("true");
    	answersInheritance.add("true");
    	
    	QA.put("question2", questionsInheritance);
    	QA.put("answer2", answersInheritance);
    	
    	questionsAbstraction.add("An abstract class can be instancied");
    	questionsAbstraction.add("An interface is a class that contains only abstract methods and/or named constants");
    	questionsAbstraction.add("You can instantiate an object of a subclass of an abstract class, but only if the subclass gives the definitions of all the abstract methods of the superclass.");
    	questionsAbstraction.add("A subclass cannot directly access public members of a superclass.");
    	answersAbstraction.add("false");
    	answersAbstraction.add("true");
    	answersAbstraction.add("true");
    	answersAbstraction.add("false");
    	
    	QA.put("question3", questionsAbstraction);
    	QA.put("answer3", answersAbstraction);
    	
    	questionsFurther.add("Rhydon was the first pokémon ever created");
    	questionsFurther.add("In Final Fantasy IV, Golbeza is Cecil's father");
    	questionsFurther.add("Super Mario first appeared in Donkey Kong");
    	questionsFurther.add("Samus Aran, from the Metroid Serie, is a boy");
    	answersFurther.add("true");
    	answersFurther.add("false");
    	answersFurther.add("true");
    	answersFurther.add("false");
    	
    	QA.put("question4", questionsFurther);
    	QA.put("answer4", answersFurther);
	}	
}
