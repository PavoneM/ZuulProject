package zuul.room;

public class Corridor extends Room{
	
	private boolean light;

	public Corridor(String description, String icon) {
		super(description, icon);
		light = false;
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
		if(light) switched+= " \n==> "+super.getExitString();
        return "You are " + super.description + ".\n" + "The lights are " + switched;
    }

}
