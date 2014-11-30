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

public class LectItem extends Item {
	
	/**
	 * Constructeur normal d'un objet de type LectItem
	 * @param name Nom de l'item
	 */
	public LectItem(String name) {
		super(name);
	}
	
	@Override
	public String toString(){
		return "Lecture item : "+super.getName();
	}
}
