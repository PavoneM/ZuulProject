package zuul.item;

import java.util.ArrayList;

import zuul.Lecture;


public class Book extends Item{
	
	private ArrayList<Item> lessons;
	
	public Book(String name, ArrayList<Item> l) {
		super(name);
		lessons = l;
	}
	
	public Item readBook(){
		int random = (int)(Math.random() * (lessons.size()-1));
		return lessons.get(random);
	}

}
