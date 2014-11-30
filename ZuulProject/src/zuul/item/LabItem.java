package zuul.item;

import java.util.ArrayList;


public class LabItem extends Item {

	public LabItem(String name) {
		super(name);
	}
	
	@Override
	public String toString(){
		return "Lab item : "+super.getName();
	}
	
	public boolean haveLectItem(ArrayList<Item> bp){
		for(int i=0;i<bp.size();i++)
			if(bp.get(i) instanceof LectItem && ((LectItem)bp.get(i)).getName().equals(this.getName()))
				return true;
		return false;
	}
}
