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

import java.util.Observable;

public class Time extends Observable implements Runnable{
	
	// Jour courrant
	private int day;
	
	// Heure courrante
	private int hour;
	
	// Minute courrante
	private int minutes;
	
	// Jours de la semaine (affichage)
	private String[] days = {Game.language.get("monday"), Game.language.get("tuesday"),
			Game.language.get("wednesday"), Game.language.get("thursday"), Game.language.get("friday")};
	
	/**
	 * Redéfinition de la méthode run pour exécuter le nouveau thread
	 */
	public void run() {
		
		// On initialise les différentes variables
		day=0;
		hour=8;
		minutes=0;
		
		// On notifie que le temps a commencé
		setChanged();
		notifyObservers(hour);
		
		// On boucle indéfiniment
		while(true){
			
			// Sleep du thread courrant pour que le temps ne s'écoule pas trop vite
			try { Thread.sleep(100); }
			catch (Exception e) {e.printStackTrace();}
			
			// Incrémentation des minutes
			minutes++;
			
			// Si les minutes sont supérieures à 60
			if(minutes>=60){
				
				// Incrémentation des heures et reset des minutes
				hour++;
				minutes=0;
				
				// Incrémentation du jour et reset de l'heure
				if(hour>=18){
					day++;
					hour=8;
				}
				
				// Notification aux observer
				setChanged();
				notifyObservers(hour);
			}
			
			// Si on arrive à vendredi, on met le jour à 0
			if((day+1)>=5){
				day=0;
			}
		}
	}
	
	/**
	 * Retour de l'heure
	 * @return Heure
	 */
	public int getHour() {
		return hour;
	}
	
	/**
	 * Retour du jour
	 * @return Jour
	 */
	public int getDay() {
		return day;
	}
	
	/**
	 * Accesseur de modification pour l'heure
	 * @param hour Heure à laquelle on veut modifier le temps
	 */
	public void setHour(int hour) {
		
		// On met les minutes à 0
		this.minutes=0;
		
		// Si l'heure est supérieure à 18, on chanque l'heure
		if(hour>=18)
			this.hour = 8 + (this.hour-18);
		else 
			this.hour = hour;
		
		// Notification aux Observers
		setChanged();
		notifyObservers(hour);
	}
	
	/**
	 * Retour tu temps actuel sous la forme d'une chaine de caractère
	 * @return String temps actuel
	 */
	public String getTime(){
		String theDay = days[day];
		return Game.language.get("today")+ theDay +"   "+String.format("%02d", hour)+":"+String.format("%02d", minutes);
	}
	
	/**
	 * Incrémenter les jours de 1
	 */
	public void increaseDay() {
		if(day>=5) day=0;
		else day++;
	}
	
}
