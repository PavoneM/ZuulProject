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

import zuul.Game;
import zuul.Config;

public class Cheat extends Item{
	
	// Leçon sur laquelle porte le cheat
	private Item lesson;
	
	// Numéro de la lesson
	private int num;
	
	/**
	 * Crée un objet de type Cheat
	 * @param name nom de l'objet
	 */
	public Cheat(String name) {
		super(name);
		this.generateCheat();
	}
	
	/**
	 * La méthode generateCheat permet l'instanciation de l'attribut lesson et de l'attribut num.
	 * La création de cette méthode existe dans le seul but de ne pas poluer le constructeur.
	 */
	private void generateCheat(){
		
		// Génère un nombre alléatoire parmis les lessons disponibles
		int random = (int) (Math.random()*(Config.oopLecture.size()/2));
		
		// Instanciation de num
		this.num = random;
		
		// Instanciation de lesson
		lesson = Config.oopLecture.get(random);
	}
	
	@Override
	public String toString(){
		return super.getName() + Game.language.get("about") + lesson.getName();
	}
	
	/**
	 * Retourne la lesson correspondant à la feuille de cheat
	 * @return Lesson (LectItem / LabItem)
	 */
	public Item getLesson() {
		return lesson;
	}
	
	/**
	 * Retourne le numéro de la lesson correspondant au cours
	 * @return Numero de la lesson
	 */
	public int getNum() {
		return num;
	}

}
