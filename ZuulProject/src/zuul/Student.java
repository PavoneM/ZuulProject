package zuul;

import java.util.ArrayList;

import zuul.item.Item;
import zuul.room.Room;

public class Student {
	
	// Energie du joueur
	private int energy;
	
	// Salle courante du joueur
	private Room currentRoom;
	
	// Sac à dos du joueur
	private ArrayList<Item> backpack;
	
	public Student(){
		energy = 10;
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
		energy = e;
	}
	
	public int getEnergy(){
		return energy;
	}
	
	//TODO test this
	public void increaseEnergy(int e){
		if((energy+2) <= 20 ) energy += e;
		else energy=20;
	}
	
	public void decreaseEnergy(int e){
		energy -= e;
	}
	
	public void addToBackpack(Item i){
		backpack.add(i);
	}
	
	public void removeRandomLecture(){
		if(backpack.size() == 0){
			System.out.println("You are lucky, you don't have more items in your backpack");
			return;
		}
		int random = (int)(Math.random() * (backpack.size()-1));
		System.out.println("You lost the item : " + backpack.get(random));
		backpack.remove(random);
	}
	
	public String displayBackpack(){
		String ret = "";
		if( backpack.size() == 0 ) return "You have 0 items on your back";
		for(int i=0;i<backpack.size();i++)
			ret+="- "+backpack.get(i)+"\n";
		return ret;
	}
	
	public boolean checkBackpack(){
		for(int i = 0; i < Config.oopLecture.size();i++){
			if (!backpack.contains(Config.oopLecture.get(i)))
					return false;
		}
		return true;
	}
}
