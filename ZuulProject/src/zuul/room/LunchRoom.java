package zuul.room;

public class LunchRoom extends Room{
	
	private float chance;

	public LunchRoom(String description, String icon, float chance) {
		super(description, icon);
		this.chance=chance;
	}

	@Override
	public String getLongDescription(){
		return "You are " + description +"."
				+ "\nHere you can drink coffee for charge your energy bar ! But you can also be attracted by the Babyfoot ! ("+(chance*100)+"% chance)"
				+ "\nIf you want to drink a coffee type 'drink'";
	}
	
	public float getChance() {
		return chance;
	}

	public void setChance(float chance) {
		this.chance = chance;
	}

	public boolean drink(){
		return (Math.random() > chance);
	}

}
