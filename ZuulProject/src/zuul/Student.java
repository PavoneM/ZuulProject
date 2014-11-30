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
		backpack = (ArrayList<Item>) Config.oopLecture.clone();
		backpack.remove(3);
		backpack.remove(6);
		energy=20;
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
		if((energy-2) >= 0 ) energy -= e;
		else energy=0;
	}
	
	public void addToBackpack(Item i){
		backpack.add(i);
	}
	
	public void removeRandomLecture(){
		if(backpack.size() == 0){
			return;
		}
		int random = (int)(Math.random() * (backpack.size()-1));
		System.out.println(Game.language.get("lostitem") + backpack.get(random));
		backpack.remove(random);
	}
	
	public String displayBackpack(){
		String ret = "";
		if( backpack.size() == 0 ) return Game.language.get("0item");
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
