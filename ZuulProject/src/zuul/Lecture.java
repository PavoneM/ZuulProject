/**
 * Cette classe fait partie du jeu "World of Zuul".
 * "World of Zuul" est un jeu très simple qui a été développé dans le cadre
 * du module de POO à Polytech Nice.
 *
 * Cette classe fait partie du package zuul
 * 
 * @author  Manuel Pavone et Vincent Forquet
 * @version 30.11.2014
 */

package zuul;

import java.util.ArrayList;
import zuul.item.Item;
import zuul.room.Room;

public class Lecture {
	
	// Nom de la matière
	private String name;
	
	// Acronyme de la matière
	private String acronym;
	
	// Liste contenant tous les cours de la matière
	private ArrayList<Item> lessons;
	
	/**
	 * Construction normal pour l'instanciation d'un objet de type Lecture
	 * @param name Nom de la matière
	 * @param acronym Acronyme
	 * @param less Liste des leçons
	 */
	public Lecture(String name, String acronym, ArrayList<Item> less) {
		this.name = name;
		this.acronym = acronym;
		this.lessons = less;
		
	}
	
	/**
	 * Retourne le nom de la matière
	 * @return Nom de la matière
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Retourne l'acronyme de la matière
	 * @return Acronyme
	 */
	public String getAcronym() {
		return acronym;
	}
	
	/**
	 * Retourne l'égalité entre deux acronymes
	 * @param acro acronyme d'une autre matière
	 * @return Vérité de l'égalité entre deux acronymes
	 */
	public boolean isEqual(String acro){
		if ( acronym.equals(acro) ) return true;
		return false;
	}
	
	/**
	 * Retournes les leçons de la matières
	 * @return leçons
	 */
	public ArrayList<Item> getLessons(){
		return lessons;
	}
	
	/**
	 * Retourne pour une matière donnée la leçon que doit apprendre l'étudiant
	 * @param bp Sac à dos de l'étudiant
	 * @param item Room dans laquelle il rentre
	 * @return Retourne le cours correspondant
	 */
	public Item getCurrentLesson(ArrayList<Item> bp, Room item){
		
		// Initialisation de la variable nomClasse
		String nomClasse="";
		
		// Si la salle est une Classroom
		if(item.getClass().getName().equals("zuul.room.Classroom"))
			nomClasse="zuul.item.LectItem";
		
		// Sinon la salle est un Lab
		else if(item.getClass().getName().equals("zuul.room.Lab"))
			nomClasse="zuul.item.LabItem";
		
		// On itère sur toutes les leçons
		for(int j=0;j<lessons.size();j++)
			// Si il y en a une qui n'est pas connue par l'étudiant on retourne cette leçon
			if(!bp.contains(lessons.get(j)) && lessons.get(j).getClass().getName().equals(nomClasse))
				return lessons.get(j);
		
		// null sinon
		return null;
	}
}
