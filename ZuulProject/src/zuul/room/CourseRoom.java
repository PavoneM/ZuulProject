/**
 * Cette classe fait partie du jeu "World of Zuul".
 * "World of Zuul" est un jeu très simple qui a été développé dans le cadre
 * du module de POO à Polytech Nice.
 *
 * Cette classe fait partie du package zuul.room
 * 
 * @author  Manuel Pavone et Vincent Forquet
 * @version 30.11.2014
 */

package zuul.room;

import java.util.Observable;
import java.util.Observer;
import zuul.Lecture;
import zuul.Time;
import zuul.Game;

public class CourseRoom extends Room implements Observer{
	
	// Planning de la salle
	private Planning planning;
	
	// Leçon actuelle dans la salle
	private Lecture currentCourse;
	
	/**
	 * Constructeur normal pour créer un objet de type CourseRoom
	 * @param description Description de la salle
	 * @param icon Acronyme de la salle
	 * @param planning Planning
	 */
	public CourseRoom(String description, String icon, Planning p) {
		super(description, icon);
		planning = p;
		currentCourse = new Lecture("Object Oriented Programming", "OOP", null);
	}
	
	/**
	 * Redéfinition de la méthode update à cause de l'implémentation de l'interface Observer
	 */
	@Override
	public void update(Observable o, Object arg) {
		
		// Heure modifiée
		int hour = (Integer) arg;
		
		// Observable en question
		int day = ((Time) o).getDay();
		
		// Changement de le la leçon en cours en fonction du Planning
		currentCourse = planning.getTable()[(hour-8)/2][day];
	}
	
	public String displayPlanning(){
		return planning.toString();
	}
	
	/**
	 *  Redéfinition de la méthode getLongDescription héritée de Room
	 *  Cette redéfinition ajoute la description de la leçon courrante
	 *  Et éventuellement une description du cours en question (Si c'est du OOP)
	 */
	@Override
	public String getLongDescription(){
		
		// Instanciation de la variable status
		String status = "";
		
		// Si c'est un cours de java on complète
		if(currentCourse.isEqual("OOP")) status = Game.language.get("toobadoop");
		
		// retour de la description
		return Game.language.get("youare") + description +"."
				+ Game.language.get("youareinlecture")+ currentCourse.getName()
				+ "\n"+status;
	}
	
	/**
	 * Retourne la leçon actuelle dans la salle
	 * @return leçon courrante
	 */
	public Lecture getCurrentCourse() {
		return currentCourse;
	}
}
