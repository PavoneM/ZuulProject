package zuul.item;


public class LabItem extends Item {

	public LabItem(String name) {
		super(name);
	}
	
	@Override
	public String toString(){
		return "Lab item : "+super.getName();
	}
}
