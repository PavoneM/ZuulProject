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

public class Classroom extends CourseRoom{
	
	/**
	 * Constructeur normal pour créer un objet de type Classroom
	 * @param description Description de la salle
	 * @param icon Acronyme de la salle
	 * @param planning Planning (que pour Courseroom)
	 */
	public Classroom(String description, String icon, Planning planning) {
		super(description, icon, planning);
	}
	
}
