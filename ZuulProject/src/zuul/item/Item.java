package zuul.item;


public class Item {
	
	private String name;
	private Quizz quizz;
	
	public Item(String name, Quizz quizz) {
		super();
		this.name = name;
		this.quizz = quizz;
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
	
}
