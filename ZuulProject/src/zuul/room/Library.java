package zuul.room;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import zuul.Lecture;
import zuul.Time;
import zuul.item.Book;
import zuul.item.Item;

public class Library extends Room implements Observer{
	
	private Book book;
	private boolean open;
	
	public Library(String description, String icon, ArrayList<Item> l) {
		super(description, icon);
		book = new Book("Book", l);
	}
	
	@Override
	public String getLongDescription(){
		return "Welcome to the Library ! Here you can read books, and you can learn new lessons (or known lessons :D )\n"
			 + "You can read only one book per day, so... Good luck !";
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
