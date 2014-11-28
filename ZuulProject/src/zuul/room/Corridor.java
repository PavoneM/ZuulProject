package zuul.room;

import zuul.item.Cheat;
import zuul.item.Tablet;

public class Corridor extends Room{
	
	private boolean light;
	private boolean photocopier;
	private Tablet tablet;

	public Corridor(String description, String icon, boolean photocopier, boolean t) {
		super(description, icon);
		light = false;
		this.photocopier = photocopier;
		if(t) tablet = new Tablet("Tablet");
		else tablet = null;
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
			if(photocopier) switched+= "\nOh wait ! there is a photocopier in this corridor. Type 'take cheat' if you want cheating :)";
			if(tablet != null) switched+= "\nThere is a tablet in this room !! Type 'take tablet' for pick this item up";
		}
		return "You are " + super.description + ".\n" + "The lights are " + switched;
    }
	
	public boolean getPhotocopier(){
		return photocopier;
	}
	
	public Tablet getTablet(){
		return tablet;
	}
	
	public Cheat getCheat(){
		return new Cheat("Answer sheet");
	}
	
}
