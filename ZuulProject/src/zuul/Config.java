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
		langEn.put("nrj", "Your energy");
		langEn.put("dunno", "I don't know what you mean");
		langEn.put("notcorri", "You are not in a corridor. There is no light.");
		langEn.put("switchwhat", "Switch what ? (on/off)");
		langEn.put("intro", "You are lost. You are alone. \n You wander around the university. \n Your command words are :");
		langEn.put("where", "Go where ?");
		langEn.put("nodoor", "There is no door !");
		langEn.put("errorcheck", "Error : Check + direction.");
		langEn.put("noplanning", "Error : your room doesn't have a planning");
		langEn.put("check?", "Did you check the planning on the door ? (yes/no");
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
		langFr.put("check?", "Avez vous regardé le planning sur la porte ? (oui/non");
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
    	answersAbstraction.add("true");
    	answersAbstraction.add("false");
    	answersAbstraction.add("true");
    	answersAbstraction.add("false");
    	
    	QA.put("question4", questionsFurther);
    	QA.put("answer4", answersFurther);
	}	
}
