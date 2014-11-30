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

public class Item {
	
	// Nom de l'item
	private String name;
	
	/**
	 * Constructeur normal pour la création d'un objet de type Item
	 * @param name Nom de l'item
	 */
	public Item(String name) {
		this.name = name;
	}
	
	/**
	 * Accesseur de consultation de l'attribut name
	 * @return Nom de l'item
	 */
	public String getName() {
		return name;
	}
}
