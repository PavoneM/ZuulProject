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
import java.util.HashMap;

import zuul.item.Cheat;
import zuul.item.Item;

public class Quizz {
	
	// Liste des question réponses
	private HashMap<String, ArrayList<String>> qa;
	
	// Sac à dos de l'étudiant
	private ArrayList<Item> bp;
	
	// Parser pour capturer les commande
	private Parser p;
	
	// Note de l'examen final
	private int grade;
	
	/**
	 * Constructeur normal pour un objet de la classe Quizz
	 * @param QA Liste des questions réponses pour le quizz
	 * @param p Parser
	 * @param b Sac à dos de l'étudiant
	 */
	public Quizz(HashMap<String, ArrayList<String>> QA, Parser p, ArrayList<Item> b) {
		this.qa = QA;
		this.p = p;
		this.grade = 0;
		this.bp = b;
	}
	
	/**
	 * Méthode pour démarrer le quizz
	 * @return Retourne si l'utilisateur a passé l'examen ou pas
	 */
	public boolean start(){
		
		// Itération sur le nombre de questions à partir de la classe Config
		for(int i=1; i<=Config.oopLecture.size()/2;i++){
			
			// Choix d'une question random parmis les sujets
			int random = (int) (Math.random()*(qa.get("question"+i).size()-1));
			
			// Affichage de la question
			System.out.println("Question "+i+ " : " + qa.get("question"+i).get(random));
			
			// Affectation de la réponse
			String answer = qa.get("answer"+i).get(random);
			
			// Si l'utilisateur a une antisèche, afficher la réponse
			if(verifyCheat(i)) System.out.println(Game.language.get("retcheat")+" "+ qa.get("answer"+i).get(random));
			
			// Instanciation de la string giventAnswer
			String givenAnswer = null;
			
			// Tant que vrai
			while (true){
				
				// Si l'utilisateur n'a pas donné une vrai commande
				if((givenAnswer = p.getCommand().getCommandWord()) == null){
					
					// Afficher message d'erreur
					System.out.println(Game.language.get("answermustbe"));
					continue;
				}
				// Sinon, si c'est une commande mais pas un true / false
				else if(!givenAnswer.equals(Game.language.get("true")) && !givenAnswer.equals(Game.language.get("false"))){ 
					
					// Afficher message d'erreur
					System.out.println(Game.language.get("answermustbe"));
					continue;
				}
				
				// Sinon arreter la boucle
				else break;
			}
			
			// Si la réponse est correcte on augmente la note
			if( givenAnswer.equals(Game.language.get(answer)))
				grade++;
			// Sinon on enlève des points
			else grade--;
		}
		
		// Afficher la note à l'écran
		System.out.println(Game.language.get("yourmark")+grade + "/" + Config.oopLecture.size()/2);
		
		// Retour si l'étudiant a passé l'exam ou pas
		if(grade >= Config.oopLecture.size()/4) return true;
		else return false;
	}
	
	/**
	 * Méthode pour vérifier s'il possède une antisèche
	 * @param sub Numéro de la question
	 * @return Si il possède une feuille de cheat
	 */
	private boolean verifyCheat(int sub){
		
		// Itération sur le sac à dos
		for(int i=0;i<bp.size();i++)
			// Si il possède une antisèche sur la question proposée retourner true
			if(bp.get(i) instanceof Cheat && ((Cheat) bp.get(i)).getNum() == sub)
				return true;
		
		// retourner false sinon
		return false;
	}
}
