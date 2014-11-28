package zuul.item;

import java.util.ArrayList;

import zuul.Lecture;


public class Book extends Item{
	
	private ArrayList<Item> lessons;
	
	public Book(String name, Quizz quizz, ArrayList<Item> l) {
		super(name, quizz);
		lessons = l;
	}
	
	public Item readBook(){
		int random = (int)(Math.random() * (lessons.size()-1));
		return lessons.get(random);
	}

}
