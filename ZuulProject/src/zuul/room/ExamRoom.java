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

public class ExamRoom extends CourseRoom{
	
	/**
	 * Constructeur normal pour créer un objet de type ExamRoom
	 * @param description Description de la salle
	 * @param icon Acronyme de la salle
	 * @param planning Planning (que pour Courseroom)
	 */
	public ExamRoom(String description, String icon, Planning planning) {
		super(description, icon, planning);
	}

	
}
