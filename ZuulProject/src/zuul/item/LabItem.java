package zuul.item;


public class LabItem extends Item {

	public LabItem(String name, Quizz quizz) {
		super(name, quizz);
	}
	
	@Override
	public String toString(){
		return "Lab item : "+super.getName();
	}
}
