package zuul.room;

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
        return "You are " + super.description + ".\n" + "The lights are " + switched;
    }
	
	public boolean getPhotocopier(){
		return photocopier;
	}
	
	public Tablet getTablet(){
		return tablet;
	}
	
}
