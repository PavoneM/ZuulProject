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

public class LabItem extends Item {
	
	/**
	 * Constructeur normal d'un objet de type LabItem
	 * @param name Nom de l'item
	 */
	public LabItem(String name) {
		super(name);
	}
	
	@Override
	public String toString(){
		return "Lab item : "+super.getName();
	}
	
	/**
	 * Cette méthode a été crée pour vérifier si l'étudiant avait déjà assisté 
	 * à la leçon avant de faire le TD
	 * 
	 * @param bp Backpack de l'étudiant
	 * @return La vérité si l'étudiant a déja fait la Lecture
	 */
	public boolean haveLectItem(ArrayList<Item> bp){
		
		// On itère sur tout le sac à dos
		for(int i=0;i<bp.size();i++)
			// Si on trouve un objet de type LectItem on vérifie que c'est bien le même que ce TP
			if(bp.get(i) instanceof LectItem && ((LectItem)bp.get(i)).getName().equals(this.getName()))
				return true;
		
		// Sinon on retourne faux
		return false;
	}
}
