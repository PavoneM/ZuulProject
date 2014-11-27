package zuul.item;

public class Item {
	
	private String name;
	private String description;
	protected int power;
	
	public Item(String name, String description) {
		super();
		this.name = name;
		this.description = description;
		this.power = 0;
	}
	
	public int getPower() {
		return power;
	}
	
	public String getDescription() {
		return description;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setPower(int power) {
		this.power = power;
	}
	
	
}
