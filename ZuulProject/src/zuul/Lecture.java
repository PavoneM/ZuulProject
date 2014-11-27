package zuul;

import java.util.ArrayList;
import zuul.item.LectItem;

public class Lecture {
	
	private String name;
	private String acronym;
	private ArrayList<LectItem> lessons;
	
	public Lecture(String name, String acronym, ArrayList<LectItem> less) {
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
}
