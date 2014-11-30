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
import zuul.Config;

public class Tablet extends Item{
	
	// Leçons disponibles
	private ArrayList<Item> lessons;
	
	/**
	 * Constructeur normal pour un type Tablet
	 * @param name Nom de l'item
	 */
	public Tablet(String name) {
		super(name);
		lessons = Config.oopLecture;
	}
	
	/**
	 * Méthode appelée quand nous voulons lire un livre
	 * @return Retourne une lesson au hasard parmis les choix disponibles
	 */
	public Item readTablet(){

		// Le joueur a 70% de chance que la lesson soit un livre de OOP
		if( Math.random() >= .3f) 
			return lessons.get((int)(Math.random() * (lessons.size()-1)));
		else 
			return null;
	}
}
