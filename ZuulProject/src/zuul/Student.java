package zuul;

import zuul.room.Room;

public class Student {
	
	// Energie du joueur
	private int energy;
	
	// Salle courante du joueur
	private Room currentRoom;
	
	public Student(){
		energy = 10;
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
	
	public boolean hasEnergy(){
		if (energy == 0){
			return false;
		}
		return true;
	}	
}
