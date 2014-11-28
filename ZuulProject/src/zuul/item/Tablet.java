package zuul.item;

import java.util.ArrayList;
import zuul.Config;

public class Tablet extends Item{
	
	private ArrayList<Item> lessons;
	
	public Tablet(String name) {
		super(name);
		lessons = Config.oopLecture;
	}
	
	public Item readTablet(){
		// TODO Charger la chance de lire la tablette de la classe config
		if( Math.random() >= .5f) 
			return lessons.get((int)(Math.random() * (lessons.size()-1)));
		else 
			return null;
	}
}
