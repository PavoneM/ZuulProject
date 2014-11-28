package zuul.item;


public class LectItem extends Item {
	
	public LectItem(String name) {
		super(name);
	}
	
	@Override
	public String toString(){
		return "Lecture item : "+super.getName();
	}
}
