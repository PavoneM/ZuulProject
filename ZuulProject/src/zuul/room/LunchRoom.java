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
import zuul.Game;

public class LunchRoom extends Room{
	
	// Chance de jouer au babyfoot
	private float chance;
	
	/**
	 * Constructeur normal pour créer un objet de type CourseRoom
	 * @param description Description de la salle
	 * @param icon Acronyme de la salle
	 * @param chance chance de jouer au babyfoot
	 */
	public LunchRoom(String description, String icon, float chance) {
		super(description, icon);
		this.chance=chance;
	}
	
	/**
	 * Redéfinition de la méthode getLongDescription
	 */
	@Override
	public String getLongDescription(){
		return Game.language.get("youare") + description +"."
				+ Game.language.get("welcomelunchroom")+(chance*100)+"% chance)";
	}
	
	/**
	 * Méthode drink utilisée dans la cafetéria pour boire du café
	 * @return Vérité si il a réussi à boire du café
	 */
	public boolean drink(){
		return (Math.random() > chance);
	}

}
