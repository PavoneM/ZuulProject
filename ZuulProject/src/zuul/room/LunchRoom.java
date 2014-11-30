package zuul.room;
import zuul.Game;
public class LunchRoom extends Room{
	
	private float chance;

	public LunchRoom(String description, String icon, float chance) {
		super(description, icon);
		this.chance=chance;
	}

	@Override
	public String getLongDescription(){
		return Game.language.get("youare") + description +"."
				+ Game.language.get("welcomelunchroom")+(chance*100)+"% chance)";
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
