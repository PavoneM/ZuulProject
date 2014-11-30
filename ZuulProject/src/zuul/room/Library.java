package zuul.room;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import zuul.Game;
import zuul.Lecture;
import zuul.Time;
import zuul.item.Book;
import zuul.item.Item;

public class Library extends Room implements Observer{
	
	private Book book;
	private boolean open;
	
	public Library(String description, String icon, ArrayList<Item> l) {
		super(description, icon);
		book = new Book(Game.language.get("book"), l);
	}
	
	@Override
	public String getLongDescription(){
		return Game.language.get("welcomelibrary");
	}
	 
	public Item getABook(){
		return book.readBook();
	}
	
	public boolean isOpen(){
		return open;
	}
	
	@Override
	public void update(Observable arg0, Object arg) {
		int hour = (Integer) arg;
		if(hour>=12 && hour <=15) open = true;
		else open = false;
	}
}
