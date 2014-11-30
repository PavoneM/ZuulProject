/**
 * Cette classe fait partie du jeu "World of Zuul".
 * "World of Zuul" est un jeu très simple qui a été développé dans le cadre
 * du module de POO à Polytech Nice.
 *
 * Cette classe fait partie du package zuul.item
 * 
 * @author  Manuel Pavone et Vincent Forquet
 * @version 30.11.2014
 */

package zuul.item;

import java.util.ArrayList;

public class Book extends Item{
	
	// Liste des leçons disponibles
	private ArrayList<Item> lessons;
	
	/**
	 * Crée un objet de type Book. 
	 * @param name Nom du livre
	 * @param l Liste des lessons disponibles
	 */
	public Book(String name, ArrayList<Item> l) {
		super(name);
		lessons = l;
	}
	
	/**
	 * Méthode appelée quand nous voulons lire un livre
	 * @return Retourne une lesson au hasard parmis les choix disponibles
	 */
	public Item readBook(){
		//TODO Classe config chance
		// Il y a 0.4 chance que le livre ne soit pas OOP
		if(Math.random() <= .4f) return null;
		
		// Sinon renvois une lesson au hasard
		int random = (int)(Math.random() * (lessons.size()-1));
		return lessons.get(random);
	}

}
