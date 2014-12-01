/**
 * Cette classe fait partie du jeu "World of Zuul".
 * "World of Zuul" est un jeu très simple qui a été développé dans le cadre
 * du module de POO à Polytech Nice.
 *
 * Cette classe fait partie du package zuul
 * 
 * @author  Manuel Pavone et Vincent Forquet
 * @version 30.11.2014
 */

package zuul;

import java.util.ArrayList;
import zuul.item.Item;
import zuul.room.Room;

public class Student {
	
	// Energie du joueur
	private int energy;
	
	// Salle courante du joueur
	private Room currentRoom;
	
	// Sac à dos du joueur
	private ArrayList<Item> backpack;
	
	/**
	 * Constructeur par défaut pour l'instanciation d'un objet de type Student
	 */
	public Student(){
		energy = 10;
		backpack = new ArrayList<Item>();
	}
	
	/**
	 * Retourne le sac à dos du client
	 * @return Sac à dos
	 */
	public ArrayList<Item> getBackpack() {
		return backpack;
	}
	
	/**
	 * Ajout d'un item au sac à dos
	 * @param i Objet à ajouter au sac à dos
	 */
	public void addBackpack(Item i) {
		this.backpack.add(i);
	}
	
	/**
	 * Changer la salle courrante
	 * @param r Salle
	 */
	public void setCurrentRoom(Room r) {
		currentRoom = r;
	}
	
	
	/**
	 * Retourne la salle où se trouve le joueur
	 * @return Salle où se trouve le joueur
	 */
	public Room getCurrentRoom(){
		return currentRoom;
	}
	
	/**
	 * Mettre l'énergie du joueur à un certain niveau
	 * @param e Niveau d'énergie
	 */
	public void setEnergy(int e){
		energy = e;
	}
	
	/**
	 * Retourn l'énergie actuelle du joueur
	 * @return Energie du joueur
	 */
	public int getEnergy(){
		return energy;
	}
	
	/**
	 * Augmenter l'énergie d'un certain montant
	 * @param e Nombre de cubes d'énergie à augmenter
	 */
	public void increaseEnergy(int e){
		if((energy+2) <= 20 ) energy += e;
		else energy=20;
	}
	
	/**
	 * Dimiuer l'énergie d'un certain montant
	 * @param e Nombre de cubes d'énergie à diminuer
	 */
	public void decreaseEnergy(int e){
		if((energy-2) >= 0 ) energy -= e;
		else energy=0;
	}

	/**
	 * Ajouter un objet au sac à dos de l'étudiant
	 * @param i Objet à ajouter à l'inventaire
	 */
	public void addToBackpack(Item i){
		backpack.add(i);
	}
	
	/**
	 * Enlever une leçon du sac à dos de l'étudiant
	 */
	public void removeRandomLecture(){
		
		// Test si le sac à dos n'a pas d'item
		if(backpack.size() == 0){
			// On ne fait rien
			return;
		}
		
		// Sinon, on prend une leçon au hasard
		int random = (int)(Math.random() * (backpack.size()-1));
		
		// On affiche la leçon qui va être enlevée
		System.out.println(Game.language.get("lostitem") + backpack.get(random));
		
		// On enlève l'item
		backpack.remove(random);
	}
	
	/**
	 * Retourne la chaine de caractère contenant la description du sac à dos
	 * @return String du sac à dos
	 */
	public String displayBackpack(){
		
		// On crée une variable ret
		String ret = "";
		
		// Si le sac à dos est vide on retourne une chaine de caractère
		if( backpack.size() == 0 ) return Game.language.get("0item");
		
		// Sinon pour chaque item on appelle le toString
		for(int i=0;i<backpack.size();i++)
			ret+="- "+backpack.get(i)+"\n";
		
		// On retourne le résultat
		return ret;
	}
	
	/**
	 * Méthode qui sert de vérification si le joueur a assisté à toutes les leçons de java
	 * @return Vérité si il a assisté à tous les cours
	 */
	public boolean checkBackpack(){
		for(int i = 0; i < Config.oopLecture.size();i++){
			if (!backpack.contains(Config.oopLecture.get(i)))
					return false;
		}
		return true;
	}
}
