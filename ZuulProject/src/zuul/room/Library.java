package zuul.room;

import java.util.ArrayList;
import zuul.Lecture;
import zuul.item.Book;
import zuul.item.Item;

public class Library extends Room{
	
	private Book book;
	
	public Library(String description, String icon, ArrayList<Item> l) {
		super(description, icon);
		book = new Book("Book", null, l);
	}
	
	 @Override
	 public String getLongDescription(){
		 return "Welcome to the Library ! Here you can read books, and you can learn new lessons (or known lessons :D )\n"
				 + "You can read only one book per day, so... Good luck !";
	 }
	 
	 public Item getABook(){
		 return book.readBook();
	 }
}
