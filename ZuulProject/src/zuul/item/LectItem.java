package zuul.item;


public class LectItem extends Item {
	
	public LectItem(String name, Quizz quizz) {
		super(name, quizz);
	}
	
	@Override
	public String toString(){
		return "Lecture item : "+super.getName();
	}
}
