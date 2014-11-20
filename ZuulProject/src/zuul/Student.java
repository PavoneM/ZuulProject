package zuul;

import zuul.room.Room;

public class Student {
	
	// Energie du joueur
	private int Energy;
	
	// Salle courante du joueur
	private Room currentRoom;
	
	public Student(){
		Energy = 10;
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
	
	
}
