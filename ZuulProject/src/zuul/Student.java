package zuul;

import zuul.room.Room;

public class Student {
	
	// Energie du joueur
	private int Energy;
	
	// Salle courante du joueur
	private Room currentRoom;

	public void setCurrentRoom(Room r) {
		currentRoom = r;
	}
	
	public Room getCurrentRoom(){
		return currentRoom;
	}
	
	
	
}
