package zuul.room;

import java.util.Observable;
import java.util.Observer;

import zuul.Lecture;
import zuul.Time;

public class CourseRoom extends Room implements Observer{
	
	private Planning planning;
	private Lecture currentCourse;
	
	public CourseRoom(String description, String icon, Planning p) {
		super(description, icon);
		planning = p;
		currentCourse = new Lecture("Object Oriented Programming", "OOP");
	}

	@Override
	public void update(Observable o, Object arg) {
		int hour = (Integer) arg;
		int day = ((Time) o).getDay();
		currentCourse = planning.getTable()[(hour-8)/2][day];
	}
	
	public String displayPlanning(){
		return planning.toString();
	}

	@Override
	public String getLongDescription(){
		return "You are " + description +"."
				+ "\nYou are in a lecture of "+ currentCourse.getName()
				+ "\n==>" + getExitString();
	}
}
