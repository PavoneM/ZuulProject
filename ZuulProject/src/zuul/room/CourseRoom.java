package zuul.room;

public class CourseRoom extends Room{
	
	private Planning planning;
	
	public CourseRoom(String description, String icon, Planning p) {
		super(description, icon);
		planning = p;
	}
	

}
