package zuul;

import java.util.ArrayList;

import zuul.item.Item;
import zuul.item.LectItem;
import zuul.room.Classroom;
import zuul.room.CourseRoom;
import zuul.room.Room;

public class Lecture {
	
	private String name;
	private String acronym;
	private ArrayList<Item> lessons;
	
	public Lecture(String name, String acronym, ArrayList<Item> less) {
		this.name = name;
		this.acronym = acronym;
		// TODO charger du fichier de config les différentes lessons
		this.lessons = less;
		
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAcronym() {
		return acronym;
	}
	
	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}
	
	public boolean isEqual(String acro){
		if ( acronym.equals(acro) ) return true;
		return false;
	}
	
	public ArrayList<Item> getLessons(){
		return lessons;
	}
	
	public Item getCurrentLesson(ArrayList<Item> bp, Room item){
		String nomClasse="";
		if(item.getClass().getName().equals("zuul.room.Classroom"))
			nomClasse="zuul.item.LectItem";
		else if(item.getClass().getName().equals("zuul.room.Lab"))
			nomClasse="zuul.item.LabItem";
		
		for(int j=0;j<lessons.size();j++)
			if(!bp.contains(lessons.get(j)) && lessons.get(j).getClass().getName().equals(nomClasse))
				return lessons.get(j);
		return null;
	}
}
