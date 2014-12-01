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
import zuul.item.Cheat;
import zuul.item.Tablet;

public class Corridor extends Room{
	
	// Lumières allumées ou eteintes
	private boolean light;
	
	// Attribut du photocopieur
	private boolean photocopier;
	
	// Item tablet dans le couloir
	private Tablet tablet;
	
	// Item cheat dans le couloir
	private Cheat cheat;

	/**
	 * Constructeur normal d'un objet de type Couloir
	 * @param description Description de la salle
	 * @param icon Acronyme de la salle
	 * @param photocopier Existance d'un photocopieur
	 * @param t Existance d'une tablette
	 */
	public Corridor(String description, String icon, boolean photocopier, boolean t) {
		super(description, icon);
		light = false;
		this.photocopier = photocopier;
		if(t) tablet = new Tablet("Tablet");
		else tablet = null;
		cheat = new Cheat("Answer sheet");
	}
	
	/**
	 * Test si les lumières sont allumées ou pas
	 * @return Vérité allumé / eteintes
	 */
	public boolean isLight() {
		return light;
	}

	/**
	 * Changement allumé / etein (lumières)
	 * @param light
	 */
	public void setLight(boolean light) {
		this.light = light;
	}
	
	/**
	 * Redéfinition de la description d'un couloir
	 */
	@Override
    public String getLongDescription() {
		String switched=(light)?"ON":"OFF";
		switched+= " \n==> "+super.getExitString();
		if( light ){
			if(photocopier) switched+= Game.language.get("photocopier");
			if(tablet != null) switched+= Game.language.get("corritablet");
		}
		return Game.language.get("youare") + super.description + ".\n" + Game.language.get("lightsare") + switched;
    }
	
	/**
	 * Retour si un photocopieur est présent dans la salle
	 * @return Vérité si il y a un photocopieur
	 */
	public boolean getPhotocopier(){
		return photocopier;
	}
	
	/**
	 * Retour si une tablette est présente dans la salle
	 * @return Vérité si il y a une tablette
	 */
	public Tablet getTablet(){
		return tablet;
	}
	
	/**
	 * Retourne le cheat associé à la salle
	 * @return Cheat
	 */
	public Cheat getCheat(){
		return cheat;
	}
	
	/**
	 * Accesseur de modification du cheat de la salle
	 * @param c cheat
	 */
	public void setCheat(Cheat c){
		cheat = c;
	}
	
	/**
	 * Accesseur de modification du tablet dans la salle
	 * @param t tablet
	 */
	public void setTablet(Tablet t){
		tablet = t;
	}
}
