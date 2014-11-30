package zuul.room;

import zuul.Game;
import zuul.item.Cheat;
import zuul.item.Tablet;

public class Corridor extends Room{
	
	private boolean light;
	private boolean photocopier;
	private Tablet tablet;
	private Cheat cheat;

	public Corridor(String description, String icon, boolean photocopier, boolean t) {
		super(description, icon);
		light = false;
		this.photocopier = photocopier;
		if(t) tablet = new Tablet("Tablet");
		else tablet = null;
		cheat = new Cheat("Answer sheet");
	}

	public boolean isLight() {
		return light;
	}

	public void setLight(boolean light) {
		this.light = light;
	}
	
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
	
	public boolean getPhotocopier(){
		return photocopier;
	}
	
	public Tablet getTablet(){
		return tablet;
	}
	
	public Cheat getCheat(){
		return cheat;
	}
	
	public void setCheat(Cheat c){
		cheat = c;
	}
	
	public void setTablet(Tablet t){
		tablet = t;
	}
}
