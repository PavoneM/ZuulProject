package zuul;

import java.util.ArrayList;

import zuul.item.Item;
import zuul.room.Room;

public class Student {
	
	// Energie du joueur
	private int Energy;
	
	// Salle courante du joueur
	private Room currentRoom;
	
	// Sac à dos du joueur
	private ArrayList<Item> backpack;
	
	public Student(){
		Energy = 10;
		backpack = new ArrayList<Item>();
	}
	
	public ArrayList<Item> getBackpack() {
		return backpack;
	}

	public void addBackpack(Item i) {
		this.backpack.add(i);
	}
	
	public void setCurrentRoom(Room r) {
		currentRoom = r;
	}
	
	public Room getCurrentRoom(){
		return currentRoom;
	}
	
	public void setEnergy(int e){
		Energy = e;
	}
	
	public int getEnergy(){
		return Energy;
	}
	
	//TODO test this
	public void increaseEnergy(int e){
		if((Energy+2) <= 20 ) Energy += e;
		else Energy=20;
	}
	
	public void decreaseEnergy(int e){
		Energy -= e;
	}
	
	public String displayBackpack(){
		String ret = "";
		for(int i=0;i<backpack.size();i++)
			ret+="- "+backpack.get(i)+"\n";
		return ret;
	}
}
