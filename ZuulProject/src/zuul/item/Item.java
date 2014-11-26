package zuul.item;

public class Item {
	
	private String name;
	private Quizz quizz;
	protected int power;
	
	public Item(String name, Quizz quizz) {
		super();
		this.name = name;
		this.quizz = quizz;
		this.power = 0;
	}
	
	public int getPower() {
		return power;
	}
	
	public Quizz getQuizz() {
		return quizz;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(Quizz quizz) {
		this.quizz = quizz;
	}
	
	public void setPower(int power) {
		this.power = power;
	}
	
	
}
