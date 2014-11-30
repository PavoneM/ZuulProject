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

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import zuul.Game;
import zuul.item.Book;
import zuul.item.Item;

public class Library extends Room implements Observer{
	
	// Le book présent actuellement dans la libraire
	private Book book;
	
	// Status de la libraire (ouverte / fermée)
	private boolean open;
	
	/**
	 * Constructeur normal pour créer un objet de type Library
	 * @param description Description de la salle
	 * @param icon Acronyme de la salle
	 */
	public Library(String description, String icon, ArrayList<Item> l) {
		super(description, icon);
		book = new Book(Game.language.get("book"), l);
	}
	
	/**
	 * Redéfinition de la méthode getLongDescription
	 */
	@Override
	public String getLongDescription(){
		return Game.language.get("welcomelibrary");
	}
	 
	/**
	 * Méthode permettant à l'étudiant arrivant dans la librairie de prendre un livre
	 * @return Le livre de la librairie
	 */
	public Item getABook(){
		return book.readBook();
	}
	
	/**
	 * Méthode permettant de savoir si la librairie est ouverte
	 * @return Vérité si la librairie est ouverte
	 */
	public boolean isOpen(){
		return open;
	}
	
	/**
	 * Redéfinition de la méthode update à cause de l'implémentation de l'interface Observer
	 * L'implémentation de cette interface permet notamment d'ouvrir et de fermer la bibliothèque en fonction de l'heure
	 */
	@Override
	public void update(Observable arg0, Object arg) {
		
		// Heure modifiée
		int hour = (Integer) arg;
		
		// Si on est entre 12h et 15h la librairie est ouverte
		if(hour>=12 && hour <=15) open = true;
		
		// Sinon elle est fermée
		else open = false;
	}
}
